/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

import main.StringConstClass;

public class InputForm extends JFrame {

	private static final long serialVersionUID = 1L;
	public static File fileS = null;

	public static String s;
	public static String[] arrayRule = new String[1000];
	public static String[][] arrayGifts = new String[70][30];
	public static int quantRule = 0;
	public static String[] agiftStr = {"Leather touch screen gloves", "Sport event ticket", "E-book", "Electronic reading device", "Membership to a club", "Ticket to a game",
			"A book that signed by his favorite author",  "Coffee mug with a favorite quote printed on it", "Wine making kit", "Electric Shavers", "BestBuy gift card", "Bookstore gift card", 
			"Ticket to a game or concert", "Restaurant gift card", "Remote organizer", "VIP movie ticket", "Cuisinart CPM-100 popcorn maker", "Xinda 100 projector", "Roku 4", "Infinity floorstanding speaker",
			"Movie collection software", "Sennheiser RS120 On-Ear Wireless RF Headphones", "Netflix gift card", "iTunes gift card", "Movie ticket", "Fashion magazine","Handwritten bookmark", "Stylish bookends", 
			"Watch movement cufflinks", "Natural wooden watch", "Hex tie",  "Backpack with a favorite quote printed on it", "Cufflinks", "Hammock","Card holder", 
			"Books that signed by his favorite authors", "Gift card of his favorite clothing store", "Sunglasses",  "Shirt from his favorite brand", "Team clothing", "Belt", "Classic suit", "Tie", "Shoes",
			"Gift card of vintage shop", "Ticket to a concert", "Jewelry portfolio", "Phone case with a favorite quote printed on it", "Jewelry Cleaner", "Instyler hot iron", 
			"Book themed temporary tattoos", "Fashion show ticket",  "Necklace with a favorite quote graved on it", "Scarf", "Makeup bag with a favorite quote printed on it",
			"Wallet", "Earrings", "Bracelet", "Dress from her favorite brand", "Bag",  "Book themed wall art", "Cosmetics", "Skin care", "High heels"};


	public static String[] expFile = { ".clp", ".txt" };

	private JButton btnFind = new JButton(StringConstClass.btnFrameSrt1);
	private JButton btnMake = new JButton("Make a rule");
	private JButton btnSaveToFile = new JButton("Save to file");
	private JButton sFile = new JButton("Close Button");

	// private JButton addFile = new JButton("Add Button");

	private JLabel labelgender = new JLabel(StringConstClass.qusStr1, JButton.RIGHT);
	private JLabel labeloccasion = new JLabel(StringConstClass.qusStr2, JButton.RIGHT);
	private JLabel labelage = new JLabel(StringConstClass.qusStr3, JButton.RIGHT);
	private JLabel labelinterest = new JLabel(StringConstClass.qusStr4, JButton.RIGHT);
	private JLabel labelpreferring = new JLabel(StringConstClass.qusStr5, JButton.RIGHT);
	private JLabel labelfreetime = new JLabel(StringConstClass.qusStr6, JButton.RIGHT);
	private JLabel labelscore = new JLabel(StringConstClass.qusStr7, JButton.RIGHT);
	private JLabel labelrange = new JLabel(StringConstClass.qusStr8, JButton.RIGHT);
//	private JLabel labelfind = new JLabel("Search the rule - >", JButton.RIGHT);
	private JLabel filerulename = new JLabel("Choose a name of file - >", JButton.RIGHT);

	private String[] strings = {};
	private JList<Object> list = new JList<Object>(strings);
	public static JLabel im = new JLabel();
	public static JLabel im1 = new JLabel();
	public static JLabel im2 = new JLabel();
	public static JLabel im3 = new JLabel();
	private static final Map<String, String> string_by_search = new HashMap<String, String>();
	private JScrollPane ScrollPane = new JScrollPane(null);

	JComboBox<String> genderBox = new JComboBox<String>(StringConstClass.genderString);
	JComboBox<String> occasionBox = new JComboBox<String>(StringConstClass.occasionString);
	JComboBox<String> ageBox = new JComboBox<String>(StringConstClass.ageString);
	JComboBox<String> interestBox = new JComboBox<String>(StringConstClass.interestString);
	JComboBox<String> preferringBox = new JComboBox<String>(StringConstClass.preferringString);
	JComboBox<String> freetimeBox = new JComboBox<String>(StringConstClass.freetimeString);
	JComboBox<String> scoreBox = new JComboBox<String>(StringConstClass.scoreString);
	JComboBox<String> rangeBox = new JComboBox<String>(StringConstClass.rangeString);
	JComboBox<String> giftBox = new JComboBox<String>(StringConstClass.giftString);
	JComboBox<String> giftBox1 = new JComboBox<String>(StringConstClass.giftString);
	JComboBox<String> giftBox2 = new JComboBox<String>(StringConstClass.giftString);
	JComboBox<String> expFileBox = new JComboBox<String>(expFile);

	private JTextField findRule = new JTextField();
	private JTextField textFile = new JTextField();
	
	private int k = 0; 

	public InputForm() {
		super();
		initComponents();
		setVisible(true);
	}

	public void closeFrame(ActionEvent e) {
		processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	public void closingFrame(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			this.dispose();
		}
	}
	
// Pick up the image for gifts
//===============================================
	public void chooseGift(String s, int gc){
		BufferedImage ch_img = null;
		String gIc=null;
		int coefw=80;
		int coefh=80;
		for (k=0; k<=agiftStr.length-1; k++){
			if (s.equals(agiftStr[k].toString())){
				gIc="img/img_gift/g"+(k+1)+".png"; }
		}

		try {
				ch_img = ImageIO.read(new File(gIc));
				} catch (IOException e) {
					e.printStackTrace();
				}
		if (ch_img.getHeight()>ch_img.getWidth()) {coefw=(int)80/(ch_img.getHeight()/ch_img.getWidth());}
		else {coefh=(int)80/(ch_img.getWidth()/ch_img.getHeight());}
		
		switch (gc) {
		case 1:{
			im1.setBounds(0, 0, 80, 80);
			im1.setIcon(new ImageIcon(ch_img.getScaledInstance(coefw, coefh, 0) ));
				im1.repaint();
		}  break;
		case 2: {
				im2.setBounds(0, 90, 80, 80);
				im2.setIcon(new ImageIcon(ch_img.getScaledInstance(coefw, coefh, 0) ));
					im2.repaint();
		}  break;
		case 3: {
			im3.setBounds(0, 180, 80, 80);
			im3.setIcon(new ImageIcon(ch_img.getScaledInstance(coefw, coefh, 0) ));
			im3.repaint();
		} break;
		}
		
		
		im1.setVisible(true);
		im2.setVisible(true);
		im3.setVisible(true);
		im.add(im1);
		im.add(im2);
		im.add(im3);
			
				
		im.repaint();
}
	
	
	public void initVar(){
		for (k=0; k<=StringConstClass.giftString.length-1; k++){
			arrayGifts[k][0] =StringConstClass.giftString[k];
				arrayGifts[k][1] = "gender ";
				arrayGifts[k][2] = "occasion ";
				arrayGifts[k][3] = "age ";
				arrayGifts[k][4] = "interest ";
				arrayGifts[k][5] = "preferring ";
				arrayGifts[k][6] = "freetime ";
				arrayGifts[k][7] = "score ";
				arrayGifts[k][8] = "range ";
				arrayGifts[k][9] = "certainty ";
		}  
}
        
        public static void centerFrame(JFrame frame) {
	    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
	    if (x < 0) {
	        x = 0;
	    }
	    int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
	    if (y < 0) {
	        y = 0;
	    }
	    frame.setBounds(x, y, frame.getWidth(), frame.getHeight());
	}

	private void initComponents() {

		ActionListener actionListenerg1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> giftBox = (JComboBox)e.getSource();
                        String item = (String)giftBox.getSelectedItem();
                        chooseGift(item, 1);
            }
        };
        giftBox.addActionListener(actionListenerg1);

        
		ActionListener actionListenerg2 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> giftBox1 = (JComboBox)e.getSource();
                        String item1 = (String)giftBox1.getSelectedItem();
                        chooseGift(item1, 2);
            }
        };
        giftBox1.addActionListener(actionListenerg2);
		
        ActionListener actionListenerg3= new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> giftBox2 = (JComboBox)e.getSource();
                        String item2 = (String)giftBox2.getSelectedItem();
                        chooseGift(item2, 3);
            }
        };
        giftBox2.addActionListener(actionListenerg3);

        im.setBounds(0, 0, 90, 260);
		im.setVisible(true);
		add(im);	
		setTitle("Creation a rule for Gift Advisor");
		setSize(new Dimension(700, 700));
		try {
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("img/img_btn/bgr.png")))));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		centerFrame(this);
		setPreferredSize(null);
		setResizable(false);

		ScrollPane.setViewportView(list);
		updateList();

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(50, 50, 50)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addGroup(layout.createSequentialGroup()
										.addComponent(labelgender, GroupLayout.PREFERRED_SIZE, 200,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(genderBox, GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(labelpreferring, GroupLayout.PREFERRED_SIZE, 230,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(preferringBox,
										GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))

								.addGroup(layout.createSequentialGroup()
										.addComponent(labeloccasion, GroupLayout.PREFERRED_SIZE, 200,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(occasionBox, GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(labelfreetime, GroupLayout.PREFERRED_SIZE, 230,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(freetimeBox, GroupLayout.PREFERRED_SIZE, 160,
												GroupLayout.PREFERRED_SIZE))

								.addGroup(layout.createSequentialGroup()
										.addComponent(labelage, GroupLayout.PREFERRED_SIZE, 200,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(ageBox, GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(labelscore, GroupLayout.PREFERRED_SIZE, 230,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(scoreBox,
												GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))

								.addGroup(layout.createSequentialGroup()
										.addComponent(labelinterest, GroupLayout.PREFERRED_SIZE, 200,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(interestBox, GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(labelrange, GroupLayout.PREFERRED_SIZE, 230,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(rangeBox,
												GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))

								.addGroup(layout.createSequentialGroup()
										.addComponent(giftBox, GroupLayout.PREFERRED_SIZE, 200,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(giftBox1, GroupLayout.PREFERRED_SIZE, 200,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(giftBox2, GroupLayout.PREFERRED_SIZE, 200,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnMake,
												GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
/*
								.addGroup(layout.createSequentialGroup()
										.addComponent(labelfind, GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(findRule, GroupLayout.PREFERRED_SIZE, 160,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnFind, GroupLayout.PREFERRED_SIZE, 350,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
*/
								.addGroup(layout.createSequentialGroup()
										.addComponent(filerulename, GroupLayout.PREFERRED_SIZE, 160,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(textFile, GroupLayout.PREFERRED_SIZE, 160,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(expFileBox, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnSaveToFile, GroupLayout.PREFERRED_SIZE, 350,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
								.addGroup(layout.createSequentialGroup()
										.addComponent(im, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup().addComponent(ScrollPane,
										GroupLayout.PREFERRED_SIZE, 700, GroupLayout.PREFERRED_SIZE) )
								))
						.addContainerGap()));
		
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(50, 50, 50)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(genderBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(labelgender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(labelpreferring).addComponent(preferringBox,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labeloccasion)
								.addComponent(occasionBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(labelfreetime).addComponent(freetimeBox, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))

								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(labelage)
										.addComponent(ageBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(labelscore).addComponent(scoreBox, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(labelinterest)
										.addComponent(interestBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(labelrange).addComponent(rangeBox, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))

				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup().addGap(200, 200, 200)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(giftBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(giftBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(giftBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnMake, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	/*					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(labelfind, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnFind, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(findRule, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	*/					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(filerulename, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(expFileBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE).addComponent(btnSaveToFile,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))))

				.addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(300, 300, 300)
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(im, GroupLayout.PREFERRED_SIZE, 260,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(ScrollPane, GroupLayout.PREFERRED_SIZE, 260,
														GroupLayout.PREFERRED_SIZE)
												))));

		pack();
		setLocationRelativeTo(null);

		sFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFrame(e);
			}
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closingFrame(e);
			}
		});

		btnSaveToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ff =  textFile.getText() ;
				String ef = expFileBox.getSelectedItem().toString();
				// while (ff.indexOf(".")!=0) {ff.replace(".", "_");}
				// if (expFileBox.getSelectedItem()="") ff=ff+".clp";
				writeFile(ff, ef);
			}
		});
		
		
		btnMake.addActionListener(new ActionListener() {
		//	@*Override
			public void actionPerformed(ActionEvent e) {
				initVar();
				String ss=null;
				String[] _array = strings;
				String[] new_array = (String[]) Array.newInstance(_array.getClass().getComponentType(),
						_array.length + 1);
				System.arraycopy(_array, 0, new_array, 0, _array.length);
				ss=	  genderBox.getSelectedItem() + " - "+  occasionBox.getSelectedItem()+" - "
						+ ageBox.getSelectedItem() + " - " + interestBox.getSelectedItem() + " - " 
						+ preferringBox.getSelectedItem() + " - " + freetimeBox.getSelectedItem() + " - " 
						+ scoreBox.getSelectedItem() + "  - " + rangeBox.getSelectedItem() + " - " 
						+ giftBox.getSelectedItem() +" |" + giftBox1.getSelectedItem()+" |" + giftBox2.getSelectedItem()+";" ;

				arrayRule[quantRule] = quantRule+"\r\n"+ genderBox.getSelectedItem() + occasionBox.getSelectedItem()
						+ ageBox.getSelectedItem() + interestBox.getSelectedItem() 
						+ preferringBox.getSelectedItem() + freetimeBox.getSelectedItem() 
						+ scoreBox.getSelectedItem() + rangeBox.getSelectedItem() + "=>\r\n"
						+giftBox.getSelectedItem() + giftBox1.getSelectedItem() +giftBox2.getSelectedItem() + "\r\n\n";
				new_array[new_array.length - 1] = ss;
//				new_array[new_array.length - 1] = arrayRule[quantRule];
				quantRule += 1;
				strings = new_array;
				string_by_search.put(findRule.getText() + "", new_array[new_array.length - 1]);
				updateList();
		//		MakeGiftList();

			}
		});

		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = string_by_search.get(findRule.getText());
				// boolean b = string_by_search.values(1). indexOf
				if (s != null) {
					JOptionPane.showMessageDialog(null, s);
				}
			}
		});
	}

	@SuppressWarnings({ "serial" })
	private void updateList() {
		list.setModel(new AbstractListModel<Object>() {
			@Override
			public int getSize() {
				return strings.length;
			}

			@Override
			public Object getElementAt(int i) {
				return strings[i];
			}
		});
	}



	// Save to file
	// ==============================================
	public static void writeFile(String fname, String fexp) {
		int i = 0;

		JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setSelectedFile(new File(fname+fexp));
		fileChooser.setVisible(true);
		int result = fileChooser.showSaveDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
			fileS = fileChooser.getSelectedFile();
		}		
		
		try {
			File file = new File(fileS.getAbsolutePath());
			if (file.exists()) {file.delete();}
			if (file.createNewFile()) {
				BufferedWriter out = new BufferedWriter(new FileWriter(fileS.getAbsolutePath()));
				out.write(StringConstClass.initstateStr0);
				out.write(StringConstClass.initstateStr1);

				for (i = 0; i <= quantRule - 1; i++) {
					out.write(";;  "+i+"\r\n");
					out.write(arrayRule[i]);
				}

				out.write(StringConstClass.initstateStr2);

				out.close();
				JOptionPane.showMessageDialog(null, "File   "+fileS.getAbsolutePath()+ "    is saved successfully!");
			} else
				JOptionPane.showMessageDialog(null, "File cannot be saved !");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}