/**
 * @author Vasyl Lyashkevych
 */
package main;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.LayoutStyle;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static json.BuildJsonProperty.getJsonSchemaGenerator;
import json.FromDatabase;
import static main.ToolBar.dataRelations;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import static main.ToolBar.jToolTabPanel;
import static main.ToolBar.things;
import static main.ToolBar.pathDField;
import static main.ToolBar.rowTemplate;
import static main.ToolBar.showMessBox;

// The main class of dictionary panel
//==============================================================================
public class DictionaryThing extends JPanel {
    
 
    public static int maxColRow = 101;

    public static int newKeyw = 0;
    private boolean dictionaryState;
    private boolean singlemode = false;
    private final int maxTabl = 200;
    
    private static String fileDictName;
    public static String keywSValue;
    public static String keywOValue;
    public static String nameValue;

    public static int intkeywords = 0;
    
    private final int colDictWidth = 300;
    private final int TIW = 30;
    private final int colTablWidth = 260;
    private String[] nKrow = new String[maxTabl];
    private int intTemp = 0;

    private final JLabel label1 = new JLabel("Entity/Property", JButton.LEFT);
    private final JLabel label2 = new JLabel("Entities", JButton.CENTER);
    private final JLabel filterLabel = new JLabel("Filter to",JButton.LEFT);
//    private final JLabel label4 = new JLabel("Selected keywords from inforamtion resource", JButton.CENTER);
    
    private javax.swing.JTextField keywField;
    
    private final JPanel dictThingPanel = new JPanel();
    private final JPanel tablThingPanel = new JPanel();
    private final JPanel entThingPanel = new JPanel();
    private final JPanel paneThingPanel = new JPanel();
    
    private JScrollPane jScrollThingDictPane = null;
    private JScrollPane jScrollThingTablPane = null;
    private JScrollPane jScrollThingKeywPane = null;
    private JScrollPane jMainThingScrollPane = null;
   
    private javax.swing.JCheckBox formatCheckBox;
    
    private static final String[] conceptType = new String[] {"-","isType", "isProperty"};
    public static JComboBox<String> filterComboBox;


    
    public static JTable jTableThingDictionary = null;
    public static JTable jTableThingPlenty = null;
    public static JTable jTableThingKeywords = null;
    private DefaultTableModel mdataTableThingKeywords = null;
    private DefaultTableModel mdataThingDictionary = null;
    private DefaultTableModel mdataThingTablePlenty = null;
    
    private final JButton updateButton = new JButton("Update tables");
    private final JButton addKeywButton =  new JButton("add to Dictionary");
    private final JButton saveDictButton =  new JButton("save Dictionary");
    private final JButton filterButton =  new JButton("To filter");
    
    private final JButton jButton1 = new JButton("load Dictionary");
    private final JButton jButton2 = new JButton("Add selected keywords from inforamtion resource to Dictionary");
    private final JButton jButton3 = new JButton("Concepts without relations");
    private final JButton jButton4 = new JButton("Correct concept in ontology");
    private final JButton jButton5 = new JButton("Delete selected concept");
    private final JButton jButton6 = new JButton("new Entity");
    private final JButton jButton10 = new JButton("JsonGen");
    private final JButton jButton7 = new JButton("Save changes to the dictionary");
    private final JButton jButton8 = new JButton("Clear all marks");
    private final JButton jButton9 = new JButton("To add new type of relations");
    
    // Constructor implementation
    //==========================================================================  
    public DictionaryThing(){
        super();
        for(int i=0; i<21000; i++)
            for(int j=0; j<21000; j++)
                dataRelations[i][j]=null;
        
        this.formatCheckBox = new JCheckBox("XML/RDF");
        this.formatCheckBox.setSelected(false);

        this.dictionaryState = true;
        jButton1.setBackground(Color.GRAY);
        jButton4.setBackground(Color.PINK);
        jButton5.setBackground(Color.PINK);
        saveDictButton.setBackground(Color.GRAY);
        jButton2.setBackground(Color.ORANGE);
        jButton7.setBackground(Color.CYAN);
        jButton9.setBackground(Color.CYAN);

        keywField= new javax.swing.JTextField();
        
        this.filterComboBox = new JComboBox<>(conceptType);


        initActionDP();
    }
    
    // Interface implementation
    //==========================================================================  
    public JPanel getThingDictionaryPane(JPanel mainPanel){
//        qpt = Integer.parseInt(ToolBar.qtableField.getText()) ;
//        q = qpt*2+1;
        jMainThingScrollPane = new JScrollPane();
        jMainThingScrollPane.setBackground(Color.LIGHT_GRAY);
    
        GroupLayout layout1 = new GroupLayout(dictThingPanel);
        dictThingPanel.setLayout(layout1);
        layout1.setHorizontalGroup(layout1.createSequentialGroup()
            .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout1.createSequentialGroup()
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(keywField, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addKeywButton, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout1.createSequentialGroup()
                        .addComponent(filterLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addComponent(filterComboBox, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(filterButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGroup(layout1.createSequentialGroup()
                        .addComponent(getDictThingScrollPane(), GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout1.createSequentialGroup()
                        .addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE))
            )
        );
        layout1.setVerticalGroup(layout1.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(keywField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(addKeywButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20, 20, 20)
            .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(filterLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(filterComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(filterButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20, 20, 20)
            .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(getDictThingScrollPane(), GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20, 20, 20)
            .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(updateButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            )
        );
    
        GroupLayout layout2 = new GroupLayout(tablThingPanel);
        tablThingPanel.setLayout(layout2);
        layout2.setHorizontalGroup(layout2.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jButton7, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                        .addComponent(getTablScrollPane(), GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE))
        );
        layout2.setVerticalGroup(layout2.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(10, 10, 10)
            .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(getTablScrollPane(), GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(10, 10, 10)
            .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jButton9, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
            )
        );
    
        GroupLayout layout3 = new GroupLayout(entThingPanel);
        entThingPanel.setLayout(layout3);
        layout3.setHorizontalGroup(layout3.createSequentialGroup()
            .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 1380, GroupLayout.PREFERRED_SIZE)
                        .addComponent(getThingKeywScrollPane(), GroupLayout.PREFERRED_SIZE, 1380, GroupLayout.PREFERRED_SIZE))
        );
        layout3.setVerticalGroup(layout3.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20, 20, 20)
            .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(getThingKeywScrollPane(), GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
            )
        );
    
        GroupLayout layout0 = new GroupLayout(paneThingPanel);
        paneThingPanel.setLayout(layout0);
        layout0.setHorizontalGroup(layout0.createSequentialGroup()
            .addGroup(layout0.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout0.createSequentialGroup()
                        .addComponent(dictThingPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tablThingPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout0.createSequentialGroup()
                        .addComponent(entThingPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            )
        );
        layout0.setVerticalGroup(layout0.createSequentialGroup()
            .addGroup(layout0.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(dictThingPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(tablThingPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20, 20, 20)
            .addGroup(layout0.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(entThingPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            )
        );
    
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addComponent(formatCheckBox, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(saveDictButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton10, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton8, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paneThingPanel, GroupLayout.PREFERRED_SIZE, 1450, GroupLayout.PREFERRED_SIZE))
            )
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(formatCheckBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(saveDictButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(paneThingPanel, GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
            )
        );
        
        jMainThingScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Interconnectedness between dictionary and concept's presentations"));
        jMainThingScrollPane.setViewportView(mainPanel);
        jMainThingScrollPane.setVisible(true);
        
        return mainPanel;
    }

    //==========================================================================
    //  Table's functions for procession
    //==========================================================================
    private JScrollPane getDictThingScrollPane() {
        if (jScrollThingDictPane == null) {
            jScrollThingDictPane = new JScrollPane();
            jScrollThingDictPane.setRowHeaderView(getTableThingDictionary());
            jScrollThingDictPane.setViewportView(getTableThingDictionary());
        }
        return jScrollThingDictPane;
    }
    
    private JTable getTableThingDictionary(){
        if (jTableThingDictionary == null) {
            String[] headDict = new String[maxTabl+1];
            headDict[0] = "A list of concepts";
            int j=1;
            int k=0;
            do {
                headDict[j] = String.valueOf("S"+k);
                headDict[j+1] = String.valueOf("O"+k);
                k++;
                j+=2;
            } while (k<=(Integer)maxTabl/2-1);
            
            mdataThingDictionary = new DefaultTableModel(headDict, 0);
      
            for (int i = 0; i < intkeywords; i++) {
		mdataThingDictionary.addRow(rowTemplate);
            }
		
            jTableThingDictionary = new JTable(mdataThingDictionary);
            jTableThingDictionary.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            jTableThingDictionary.setSelectionForeground(Color.BLUE);
            jTableThingDictionary.setSelectionBackground(Color.yellow );

            jTableThingDictionary.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if(evt.getButton()==MouseEvent.BUTTON1) {
                        int row = jTableThingDictionary.rowAtPoint(evt.getPoint());
                    //    int col = jTableDictionary.columnAtPoint(evt.getPoint());
 
                            for(int j=0; j<jTableThingPlenty.getRowCount();j++){
                                jTableThingPlenty.isCellEditable(j, 2);
                                jTableThingPlenty.getModel().setValueAt("", j, 2);
                                    jTableThingPlenty.isCellEditable(j, 3);
                                    jTableThingPlenty.getModel().setValueAt("", j, 3);
                            }
                            jTableThingDictionary.getSelectedRow();
                            int j=1;
                            do{
                                int ir = (Integer)j/2;
                                if (jTableThingDictionary.getValueAt(row, j).equals("X")){
                                    jTableThingPlenty.isCellEditable(ir, 2);
                                    jTableThingPlenty.setValueAt("X", ir, 2);
                                }
                                if (jTableThingDictionary.getValueAt(row, j+1).equals("X")){
                                    jTableThingPlenty.isCellEditable(ir, 3);
                                    jTableThingPlenty.setValueAt("X", ir, 3);
                                }
                                j+=2;
                            //    JOptionPane.showMessageDialog(null, ""+j);
                            } while (j<(jTableThingPlenty.getRowCount()*2+1));
  //                      }
                        jTableThingDictionary.repaint();
                        jTableThingDictionary.updateUI();
                            jTableThingPlenty.repaint();
                            jTableThingPlenty.updateUI();
                    }
                }
            });

            jTableThingDictionary.setColumnSelectionAllowed(false);
            jTableThingDictionary.setRowSelectionAllowed(true);
            jTableThingDictionary.setAutoscrolls(true);
            jTableThingDictionary.getColumnModel().getColumn(0).setPreferredWidth(colDictWidth);
            for(int i=1; i<=maxTabl; i++)
                jTableThingDictionary.getColumnModel().getColumn(i).setPreferredWidth(TIW);
        }
        return jTableThingDictionary;
    }

    private JScrollPane getTablScrollPane() {
        if (jScrollThingTablPane == null) {
            jScrollThingTablPane = new JScrollPane();
            jScrollThingTablPane.setRowHeaderView(getTables());
            jScrollThingTablPane.setViewportView(getTables());
        }
        return jScrollThingTablPane;
    }
    
    private JTable getTables(){
        if (jTableThingPlenty == null) {
            mdataThingTablePlenty = new DefaultTableModel(new String[]{"Triple's name", "Type of relations", "S", "O"}, 0);
            for (int i = 0; i <1; i++) {
		mdataThingTablePlenty.addRow(new String[]{"", "", "", ""});
            }
		
            jTableThingPlenty = new JTable(mdataThingTablePlenty);
            jTableThingPlenty.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTableThingPlenty.setColumnSelectionAllowed(false);
            jTableThingPlenty.setRowSelectionAllowed(true);
            
            
            jTableThingPlenty.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if(evt.getButton()==MouseEvent.BUTTON1) {
                        int row = jTableThingPlenty.rowAtPoint(evt.getPoint());
                        int col = jTableThingPlenty.columnAtPoint(evt.getPoint());
                        if (col>1){
                            jTableThingPlenty.isCellEditable(row, col);
                            if (jTableThingPlenty.getModel().getValueAt(row, col).toString().equals("X")) {
                                jTableThingPlenty.isCellEditable(row, col);
                                jTableThingPlenty.getModel().setValueAt("", row, col);
                            }   else    {
                                jTableThingPlenty.isCellEditable(row, col);
                                jTableThingPlenty.getModel().setValueAt("X", row, col);
                            }
                                
                            
                        }
                            jTableThingPlenty.repaint();
                            jTableThingPlenty.updateUI();
                    }
                }
            });

            jTableThingPlenty.setAutoscrolls(true);
            jTableThingPlenty.getColumnModel().getColumn(0).setPreferredWidth(colTablWidth);
            jTableThingPlenty.getColumnModel().getColumn(1).setPreferredWidth(colTablWidth);
            jTableThingPlenty.getColumnModel().getColumn(2).setPreferredWidth(TIW);
            jTableThingPlenty.getColumnModel().getColumn(3).setPreferredWidth(TIW);
        }
        return jTableThingPlenty;
    }    


    private JScrollPane getThingKeywScrollPane() {
        if (jScrollThingKeywPane == null) {
            jScrollThingKeywPane = new JScrollPane();
            jScrollThingKeywPane.setRowHeaderView(getThingKeywords());
            jScrollThingKeywPane.setViewportView(getThingKeywords());
        }
        return jScrollThingKeywPane;
    }
    
    private JTable getThingKeywords(){
        if (jTableThingKeywords == null) {
            String[] headKeyw = new String[maxTabl+1];
            headKeyw[0] = "Found keywords";
            int j=1;
            int k=1;
            do {
                headKeyw[j] = String.valueOf("S"+k);
                headKeyw[j+1] = String.valueOf("O"+k);
                k++;
                j+=2;
            } while (k<=(Integer)maxTabl/2);

            mdataTableThingKeywords = new DefaultTableModel(headKeyw, 0);
            for (int i = 0; i < maxTabl; i++) {
		mdataTableThingKeywords.addRow(rowTemplate);
            }
		
            jTableThingKeywords = new JTable(mdataTableThingKeywords);
            jTableThingKeywords.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTableThingKeywords.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if(evt.getButton()==MouseEvent.BUTTON1) {
                        int row = jTableThingKeywords.rowAtPoint(evt.getPoint());
                        int col = jTableThingKeywords.columnAtPoint(evt.getPoint());
                        if (col>0 && col<=jTableThingPlenty.getRowCount() && row<newKeyw){
                            jTableThingKeywords.isCellEditable(row, col);
                            if (jTableThingKeywords.getModel().getValueAt(row, col).toString().equals("X")) {
                                jTableThingKeywords.isCellEditable(row, col);
                                jTableThingKeywords.getModel().setValueAt("", row, col);
                            } else
                            if (jTableThingKeywords.getModel().getValueAt(row, col).toString().equals("")) {
                                if(col % 2 == 0) {
                                    jTableThingKeywords.isCellEditable(row, col-1);
                                    jTableThingKeywords.getModel().setValueAt("", row, col-1);
                                    jTableThingKeywords.isCellEditable(row, col);
                                    jTableThingKeywords.getModel().setValueAt("X", row, col);
                                } else {
                                    jTableThingKeywords.isCellEditable(row, col+1);
                                    jTableThingKeywords.getModel().setValueAt("", row, col+1);
                                    jTableThingKeywords.isCellEditable(row, col);
                                    jTableThingKeywords.getModel().setValueAt("X", row, col);
                                }
                            }
                        }
                        jTableThingKeywords.repaint();
                        jTableThingKeywords.updateUI();
                    }
                }
            });

            jTableThingKeywords.setColumnSelectionAllowed(false);
            jTableThingKeywords.setRowSelectionAllowed(true);

/*            int coefh = 17;
            if (coefh>intkeywords) {coefh=intkeywords;}
            jTableThingKeywords.setPreferredScrollableViewportSize(
                    new Dimension(jTableThingKeywords.getPreferredScrollableViewportSize().width+210, coefh*jTableThingKeywords.getRowHeight()));
*/            jTableThingKeywords.setAutoscrolls(true);
            jTableThingKeywords.getColumnModel().getColumn(0).setPreferredWidth(colDictWidth);
            for(int i=1; i<=maxTabl; i++)
                jTableThingKeywords.getColumnModel().getColumn(i).setPreferredWidth(TIW);
        }
        return jTableThingKeywords;
    }
    
   
        
    public void doSearchEmptyKeyw(){
        int check;
        jTableThingDictionary.isEditing();
        for (int i=0; i<jTableThingDictionary.getRowCount(); i++){
            check = 0;
            jTableThingDictionary.setRowSelectionInterval(i, i);
            for (int j=1; j<maxColRow; j++){
                if (!jTableThingDictionary.getValueAt(i, j).equals("")){ check++; }
            }        
            if (check==0){ 
                if (showMessBox.isSelected()){
                    jTableThingDictionary.scrollRectToVisible(jTableThingDictionary.getCellRect(i,0, true)); 
                    JOptionPane.showMessageDialog(null, "The undetermined keyword is SELECTED !"); 
                }
                return;
            }
        }
        if (showMessBox.isSelected()){
            JOptionPane.showMessageDialog(null, "The undetermined keyword don't find!"); 
        }
    }

    // To make visible the input types or properties
    //==========================================================================
    public static void scrollToVisible(JTable table, int rowIndex, int vColIndex) {
        if (!(table.getParent() instanceof JViewport)) {
            return;
        }
        JViewport viewport = (JViewport)table.getParent();

        // This rectangle is relative to the table where the
        // northwest corner of cell (0,0) is always (0,0).
        Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);

        // The location of the viewport relative to the table
        Point pt = viewport.getViewPosition();

        // Translate the cell location so that it is relative
        // to the view, assuming the northwest corner of the
        // view is (0,0)
        rect.setLocation(rect.x-pt.x, rect.y-pt.y);

        table.scrollRectToVisible(rect);

        // Scroll the area into view
        //viewport.scrollRectToVisible(rect);
    }    
    
    // To check input data
    //==========================================================================
    public void addKeywAction(){
        String keyw = keywField.getText();
        boolean keywexists = false;
        if (!keyw.equals("")){
            for (int i=0; i<jTableThingDictionary.getRowCount(); i++){
                if (jTableThingDictionary.getModel().getValueAt(i, 0).toString().equals(keyw)){
                    keywexists = true;
                        jTableThingDictionary.setRowSelectionInterval(i, i);
//                        scrollToVisible(jTableThingDictionary, i, 0);
                        jTableThingDictionary.scrollRectToVisible(jTableThingDictionary.getCellRect(i,0, true)); 
                    if (showMessBox.isSelected()){
                        JOptionPane.showMessageDialog(null, "The keywords \""+keyw+"\" already exists into the dictionary");
                    }    
                } 
            }
            if (!keywexists) {
                mdataThingDictionary.addRow(rowTemplate);
                intkeywords=mdataThingDictionary.getRowCount();
                jTableThingDictionary.isCellEditable(intkeywords-1, 0);
                mdataThingDictionary.setValueAt(keyw, intkeywords-1, 0);
                jTableThingDictionary.setRowSelectionInterval(intkeywords-1, intkeywords-1);
                jTableThingDictionary.scrollRectToVisible(jTableThingDictionary.getCellRect(intkeywords-1,0, true)); 
                if (showMessBox.isSelected()){
                    JOptionPane.showMessageDialog(null, "The keywords \""+keyw+"\" just added to the dictionary");
                }    
            }
        }
    }
    


    public void initActionDP(){

        filterButton.addActionListener((ActionEvent e) -> {
/*          @Override
            public boolean include(Entry entry) {
                 // All rows are included if no filter is set
                 if (filterText.isEmpty())
                 return true;

                // If any of the column values contains the filter text,
                // the row can be shown
                for (int i = 0; i < entry.getValueCount(); i++) {
                    String value = entry.getStringValue(i);
                if (value.toLowerCase().indexOf(filterText) != -1)
                    return true;
                }

                return false;
            }
    */



        });
    
        updateButton.addActionListener((ActionEvent e) -> {
            jToolTabPanel.setSelectedIndex(2);
            things.setSelectedIndex(1);
        });
    
        jButton7.addActionListener((ActionEvent e) -> {
            int gsr = jTableThingDictionary.getSelectedRow();
            jTableThingDictionary.isEditing();
            for (int j=1; j<jTableThingPlenty.getRowCount()*2+1;j++) { jTableThingDictionary.setValueAt("", gsr, j);}
            for(int i=0; i<jTableThingPlenty.getRowCount(); i++){
                int ir = (Integer)i*2+1;
                    jTableThingPlenty.isCellEditable(i, 2);
                if (jTableThingPlenty.getModel().getValueAt(i, 2).toString().equals("X")){
                    jTableThingDictionary.isCellEditable(gsr, ir);
                    jTableThingDictionary.setValueAt("X", gsr, ir);
                }
                    jTableThingPlenty.isCellEditable(i, 3);
                if (jTableThingPlenty.getModel().getValueAt(i, 3).toString().equals("X")){
                    jTableThingDictionary.isCellEditable(gsr, ir+1);
                    jTableThingDictionary.setValueAt("X", gsr, ir+1);
                }
            }
            jTableThingDictionary.repaint();
            jTableThingDictionary.updateUI();
        });

        jButton8.addActionListener((ActionEvent e) -> {
            for(int i=0; i<jTableThingPlenty.getRowCount(); i++){
                jTableThingPlenty.getModel().setValueAt("",i, 2);
                jTableThingPlenty.getModel().setValueAt("",i, 3);
                }        
            jTableThingDictionary.repaint();
            jTableThingDictionary.updateUI();
        });
        
        jButton4.addActionListener((ActionEvent e) -> {
            String name = JOptionPane.showInputDialog(this, "Enter a new correct name:");
            int srow = jTableThingDictionary.getSelectedRow();
            jTableThingDictionary.getModel().setValueAt(name, srow, 0);
        });
        
        addKeywButton.addActionListener((ActionEvent e) -> {
          //  if (searchKeyw.isSelected()) {addKeywButtonActionPerformed(e);}
            //else{ 
                    addKeywAction();
                    //}
        });
    
        saveDictButton.addActionListener((ActionEvent e) -> {
            if (formatCheckBox.isSelected()) { doSaveRDF(); } 
            else { doSaveXML();}
        });
    
        jButton3.addActionListener((ActionEvent e) -> {
            doSearchEmptyKeyw();
        });
    
        jButton5.addActionListener((ActionEvent e) -> {
            mdataThingDictionary.removeRow(jTableThingDictionary.getSelectedRow());
            intkeywords--;
        });
    
        jButton6.addActionListener((ActionEvent e) -> {
//            new InputForm();
            new FromDatabase();

        });
    
        jButton9.addActionListener((ActionEvent e) -> {
            mdataThingTablePlenty.addRow(new String[]{"", "", "", ""});
        });
    
        jButton1.addActionListener((ActionEvent e) -> {
            if (formatCheckBox.isSelected()) { doLoadRDF(); } 
            else { doLoadXML();}
        });

        jButton2.addActionListener((ActionEvent e) -> {
            // getJsonSchemaGenerator
        });
        
        jButton10.addActionListener((ActionEvent e) -> {
            getJsonSchemaGenerator();
        });
        
        
    }
    
    private int ffindkeyw(String args){
        int f = 0; 
        do{
            jTableThingDictionary.setRowSelectionInterval(f, f);
            if (jTableThingDictionary.getModel().getValueAt(f, 0).toString().equals(args)){
                return f;
            }
            f++;
        } while (f<jTableThingDictionary.getRowCount());  
        f = -1;
        return f;
    }
    
    // To save dictionary whereby XML format
    //==========================================================================
    private void doSaveXML() {

        fileDictName = pathDField+"DictionaryThing.xml";
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Dictionary files", ".xml");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Save the dictionary file"); 
        fileChooser.setSelectedFile(new File(fileDictName));
        int userSelection = fileChooser.showSaveDialog(fileChooser);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileDictName = fileChooser.getSelectedFile().getAbsolutePath();
        }
          
	try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("hierarchy-types");
            doc.appendChild(rootElement);

                Element tofr = doc.createElement("is-type-relation");
                for(int i=0; i<jTableThingPlenty.getRowCount(); i++) {    
                    jTableThingPlenty.setRowSelectionInterval(i, i);
                    if (!jTableThingPlenty.getValueAt(i, 0).toString().equals("")) {
                        nameValue = jTableThingPlenty.getValueAt(i, 0).toString();
                        nameValue = nameValue +"|"+ jTableThingPlenty.getValueAt(i, 1).toString();
                        Element trp_name = doc.createElement("triple"); 
                        trp_name.appendChild(doc.createTextNode(nameValue)); 
                        trp_name.setAttribute("id", String.valueOf(i));
                        tofr.appendChild(trp_name);
                    }
                }    
            rootElement.appendChild(tofr);
            Element tnames = doc.createElement("types");

            for(int i=0; i<jTableThingDictionary.getRowCount(); i++) {    
                Element tname = doc.createElement("type");
                tnames.appendChild(tname);
                tname.setAttribute("id", String.valueOf(i));
                
                jTableThingDictionary.setRowSelectionInterval(i, i);
                nameValue = jTableThingDictionary.getValueAt(i, 0).toString();
                Element namet = doc.createElement("name"); 
                namet.appendChild(doc.createTextNode(nameValue)); 
                tname.appendChild(namet);
                
                int j=1; 
                do {
                    keywSValue = jTableThingDictionary.getValueAt(i, j).toString();
                    keywOValue = jTableThingDictionary.getValueAt(i, j+1).toString();
                    if (!keywSValue.equals("")) {
                        Element s = doc.createElement("s"+String.valueOf(j)); 
                        s.appendChild(doc.createTextNode("X")); tname.appendChild(s);
                    }
                    if (!keywOValue.equals("")) {
                        Element o = doc.createElement("o"+String.valueOf(j)); 
                        o.appendChild(doc.createTextNode("X")); tname.appendChild(o);
                    }
                    j+=2;    
                } while (j<(Integer)maxTabl/2);  
            }    
        
            rootElement.appendChild(tnames);
	
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
                
            StreamResult result = new StreamResult(new File(fileDictName));
            transformer.transform(source, result);
            
            dictionaryState = true;
            if (showMessBox.isSelected()){
                JOptionPane.showMessageDialog(null, "File saved!");
            }
        } catch (ParserConfigurationException | TransformerException pce) {}
    }

    private void doSaveRDF() {
        fileDictName = pathDField+"Dictionary.xml";
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Dictionary files", ".xml");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Save the dictionary file"); 
        fileChooser.setSelectedFile(new File(fileDictName));
        int userSelection = fileChooser.showSaveDialog(fileChooser);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileDictName = fileChooser.getSelectedFile().getAbsolutePath();
        }
          
	try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("dictionary");
		doc.appendChild(rootElement);

                Element tof = doc.createElement("typeof");
                rootElement.appendChild(tof);

        for(int i=0; i<jTableThingPlenty.getRowCount(); i++) {    
            jTableThingPlenty.setRowSelectionInterval(i, i);
            if (!jTableThingPlenty.getValueAt(i, 0).toString().equals("")) {
                
                nameValue = jTableThingPlenty.getValueAt(i, 0).toString();
//                    Element obj = doc.createElement("object"); obj.appendChild(doc.createTextNode(nameValue)); tabl.appendChild(obj);
                nameValue = nameValue +"|"+ jTableThingPlenty.getValueAt(i, 1).toString();
                    Element tname = doc.createElement("tname"); tname.appendChild(doc.createTextNode(nameValue)); tof.appendChild(tname);
                    tname.setAttribute("id", String.valueOf(i));
            }
        }    

        for(int i=0; i<intkeywords; i++) {    
            Element keyw = doc.createElement("concept");
            rootElement.appendChild(keyw);
            keyw.setAttribute("id", String.valueOf(i));
                
            jTableThingDictionary.setRowSelectionInterval(i, i);
            nameValue = jTableThingDictionary.getValueAt(i, 0).toString();
            Element namew = doc.createElement("con_name"); namew.appendChild(doc.createTextNode(nameValue)); keyw.appendChild(namew);
                
            int j=0; 
            do {
                keywSValue = jTableThingDictionary.getValueAt(i, 2*j+1).toString();
                keywOValue = jTableThingDictionary.getValueAt(i, 2*j+2).toString();
                if (!keywSValue.equals("")) {Element o = doc.createElement("subjectOf"); o.appendChild(doc.createTextNode(jTableThingPlenty.getValueAt(j, 1).toString())); keyw.appendChild(o);}
                    if (!keywOValue.equals("")) {Element p = doc.createElement("objectOf"); p.appendChild(doc.createTextNode(jTableThingPlenty.getValueAt(j, 1).toString())); keyw.appendChild(p);}
                //j+=2;    
                j++;
                if (j>jTableThingPlenty.getRowCount()) {break;}
            } while (j<(Integer)maxTabl/2);  
        }    
	// write the content into xml file
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
                
	StreamResult result = new StreamResult(new File(fileDictName));
	transformer.transform(source, result);
        if (showMessBox.isSelected()){
            dictionaryState = true;
            JOptionPane.showMessageDialog(null, "File saved!");
        }
	} catch (ParserConfigurationException | TransformerException pce) {
	}
    }


    private void doLoadXML() {
        
        if (dictionaryState) {
            dictionaryState = false;
            
            int i_end = mdataThingDictionary.getRowCount();
            for(int i=0; i<i_end;i++) {
                mdataThingDictionary.removeRow(0);
            }

            i_end = mdataThingTablePlenty.getRowCount();
            for(int i=0; i<i_end;i++) {
                mdataThingTablePlenty.removeRow(0);
            }

        fileDictName = pathDField+"DictionaryThing.xml";

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open the dictionary file"); 
        FileFilter filter = new FileNameExtensionFilter("Dictionary files", ".xml");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setSelectedFile(new File(fileDictName));
        fileChooser.setVisible(true);
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileDictName = fileChooser.getSelectedFile().getAbsolutePath();
        } else {return;}
        
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {

            boolean bscheme = false;
            boolean btofr = false;
            boolean btriple = false;
            boolean btypes = false;
            boolean btype = false;
            boolean btname = false;
            boolean bs = false;
            boolean bo = false;
            int qtabl = 0;
            String sV = "";
            StringBuilder sVs ;

            @Override
            public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                sV = qName;
		if (qName.equalsIgnoreCase("hierarchy-types")) {
			bscheme = true;
		}
		if (qName.equalsIgnoreCase("is-type-relation")) {
			btofr = true;
		}
		if (qName.equalsIgnoreCase("triple")) {
			btriple = true;
		}
		if (qName.equalsIgnoreCase("types")) {
			btypes = true;
		}
		if (qName.equalsIgnoreCase("type")) {
			btype = true;
		}
		if (qName.equalsIgnoreCase("name")) {
			btname = true;
		}
		if (qName.startsWith("s")) {
			bs = true;
		}
		if (qName.startsWith("o")) {
			bo = true;
		}
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
		//System.out.println("End Element :" + qName);
            }
        
            public void saveTriple(String strT){
                mdataThingTablePlenty.addRow(new String[]{"", "", "", ""});            
                jTableThingPlenty.setRowSelectionInterval(jTableThingPlenty.getRowCount()-1, jTableThingPlenty.getRowCount()-1);
                jTableThingPlenty.getModel().isCellEditable(jTableThingPlenty.getRowCount()-1, 0);
                jTableThingPlenty.setValueAt(strT.substring(0, strT.indexOf("|")), jTableThingPlenty.getRowCount()-1, 0);
                jTableThingPlenty.getModel().isCellEditable(jTableThingPlenty.getRowCount()-1, 1);
                jTableThingPlenty.setValueAt(strT.substring(strT.indexOf("|")+1, strT.length() ), jTableThingPlenty.getRowCount()-1, 1);
            }

            public void saveType(String strT){
                mdataThingDictionary.addRow(rowTemplate);
                jTableThingDictionary.setRowSelectionInterval(jTableThingDictionary.getRowCount()-1, jTableThingDictionary.getRowCount()-1);
                jTableThingDictionary.setValueAt(strT, jTableThingDictionary.getRowCount()-1, 0);
            }

        public void saveORelation(int rO){
            jTableThingDictionary.getModel().isCellEditable(jTableThingDictionary.getRowCount()-1, rO);
            jTableThingDictionary.setValueAt("X", jTableThingDictionary.getRowCount()-1, rO);
        }

        public void savePRelation(int rP){
            jTableThingDictionary.getModel().isCellEditable(jTableThingDictionary.getRowCount()-1, rP+1);
            jTableThingDictionary.setValueAt("X", jTableThingDictionary.getRowCount()-1, rP+1);
        }

        @Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (bscheme) {
			bscheme = false;
		}

		if (btofr) {
			btofr = false;
		}

		if (btriple) {
                        saveTriple(new String(ch, start, length));
			btriple = false;
		}

		if (btypes) {
			btypes = false;
		}

		if (btype) {
			btype = false;
		}

		if (btname) {
                        saveType(new String(ch, start, length));
			btname = false;
		}

		if (bs) {
                        sVs = new StringBuilder(sV);
                        sV = sVs.deleteCharAt(0).toString();
                        saveORelation(Integer.parseInt(sV));
                        bs = false;
		}
		if (bo) {
                        sVs = new StringBuilder(sV);
                        sV = sVs.deleteCharAt(0).toString();
                        savePRelation(Integer.parseInt(sV));
			bo = false;
		}

	}

     };

    saxParser.parse(fileDictName, handler);
    } catch (ParserConfigurationException | SAXException | IOException e) {}
        
      } else {JOptionPane.showMessageDialog(null, "The changers into dictionary doesn't save !");}
    }
 


    private void doLoadRDF() {
        
      if (dictionaryState) {
            dictionaryState = false;
        mdataThingDictionary.addRow(rowTemplate);
            
      if (jTableThingDictionary.getRowCount()>0) {      
        for(int i=0; i<=jTableThingDictionary.getRowCount();i++) {
            mdataThingDictionary.removeRow(0);
        }
      }  
       // mdataDictionary.removeRow(0);

        intkeywords = 0;
        fileDictName = pathDField+"Dictionary.xml";

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open the dictionary file"); 
        FileFilter filter = new FileNameExtensionFilter("Dictionary files", ".xml");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setSelectedFile(new File(fileDictName));
        fileChooser.setVisible(true);
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileDictName = fileChooser.getSelectedFile().getAbsolutePath();
        } else {return;}

        try {
	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();

	DefaultHandler handler = new DefaultHandler() {

	boolean bdictionary = false;
	boolean btypeof = false;
	boolean btname = false;
	boolean bconcept = false;
	boolean bcon_name = false;
	boolean bsubjectof = false;
	boolean bobjectof = false;
        int qtabl = 0;
        String subjStr, objStr, sV = "";
        StringBuilder sVs ;
        int position = 0;


        @Override
	public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {

	//	System.out.println("Start Element :" + qName);
                sV = qName;

		if (qName.equalsIgnoreCase("dictionary")) {
			bdictionary = true;
		}

		if (qName.equalsIgnoreCase("typeof")) {
			btypeof = true;
		}

		if (qName.equalsIgnoreCase("tname")) {
			btname = true;
		}

		if (qName.equalsIgnoreCase("concept")) {
			bconcept = true;
		}

		if (qName.equalsIgnoreCase("con_name")) {
			bcon_name = true;
		}

		if (qName.startsWith("subjectOf")) {
			bsubjectof = true;
		}
		if (qName.startsWith("objectOf")) {
			bobjectof = true;
		}

	}

        @Override
	public void endElement(String uri, String localName,
		String qName) throws SAXException {
		//System.out.println("End Element :" + qName);
	}
        
        public void saveTuple(String strT){
            if(qtabl>=mdataThingTablePlenty.getRowCount()) {mdataThingTablePlenty.addRow(new String[]{"", "", "", ""});}
          //  mdataTablePlenty.addRow(new String[]{"", "", "", ""});            
            jTableThingPlenty.setRowSelectionInterval(qtabl, qtabl);
            jTableThingPlenty.getModel().isCellEditable(qtabl, 0);
            jTableThingPlenty.setValueAt(strT.substring(0, strT.indexOf("|")), qtabl, 0);
            jTableThingPlenty.getModel().isCellEditable(qtabl, 1);
            jTableThingPlenty.setValueAt(strT.substring(strT.indexOf("|")+1, strT.length() ), qtabl, 1);
            qtabl++;
        }
/*        index1 = rstr.indexOf(" ", index1+1);
        temp = rstr.substring(index2, index1);
*/
        public void saveKeyw(String strT){
//            if(intkeywords>mdataDictionary.getRowCount()) {mdataDictionary.addRow(rowTemplate);}
            mdataThingDictionary.addRow(rowTemplate);
            jTableThingDictionary.setRowSelectionInterval(intkeywords, intkeywords);
            jTableThingDictionary.getModel().isCellEditable(intkeywords, 1);

            jTableThingDictionary.setValueAt(strT, intkeywords, 0);
            intkeywords++;
        }

        public void saveSRelation(int rO){
            jTableThingDictionary.getModel().isCellEditable(intkeywords-1, rO);
            jTableThingDictionary.setValueAt("X", intkeywords-1, rO);
        }

        public void saveORelation(int rP){
            jTableThingDictionary.getModel().isCellEditable(intkeywords-1, rP);
            jTableThingDictionary.setValueAt("X", intkeywords-1, rP);
        }

        @Override
	public void characters(char ch[], int start, int length) throws SAXException {

		if (bdictionary) {
		//	System.out.println("dictionary : " + new String(ch, start, length));
			bdictionary = false;
		}

		if (btypeof) {
		//	System.out.println("id : " + new String(ch, start, length));
			btypeof = false;
		}

		if (btname) {
		//	System.out.println("tabl name  : " + new String(ch, start, length));
                        saveTuple(new String(ch, start, length));
			btname = false;
		}

		if (bconcept) {
		//	System.out.println("id : " + new String(ch, start, length));
			bconcept = false;
		}

		if (bcon_name) {
		//	System.out.println("keyw : " + new String(ch, start, length));
                        saveKeyw(new String(ch, start, length));
			bcon_name = false;
		}

		if (bsubjectof) {
                        subjStr = new String(ch, start, length);
                        for(int i=0; i<jTableThingPlenty.getRowCount();i++)
                            if (jTableThingPlenty.getValueAt(i, 1).toString().equals(subjStr)) {
//                                System.out.println(subjStr+"  - "+i);
                                position = i; 
                            }
                        position = position * 2 + 1;
                        saveSRelation(position);
                        bsubjectof = false;
		}
		if (bobjectof) {
                        objStr = new String(ch, start, length);
                        for(int i=0; i<jTableThingPlenty.getRowCount();i++)
                            if (jTableThingPlenty.getValueAt(i, 1).toString().equals(objStr)) {
//                                System.out.println(objStr+"  - "+i);
                                position = i; 
                            }
                        position = position * 2 + 2;
                        saveORelation(position);
			bobjectof = false;
		}

	}

     };
    saxParser.parse(fileDictName, handler);
    } catch (ParserConfigurationException | SAXException | IOException e) {}
        
      } else {JOptionPane.showMessageDialog(null, "The changers into dictionary doesn't save !");}
    }
 
}