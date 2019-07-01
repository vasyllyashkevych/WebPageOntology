/**
 * @author Vasyl Lyashkevych
 */
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import static main.ToolBar.dataRelations;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import static main.ToolBar.jToolTabPanel;
import static main.ToolBar.tabls;
import static main.ToolBar.fileChoosercBox;
import static main.ToolBar.pathDField;
import static main.ToolBar.rowTemplate;
import static main.ToolBar.showMessBox;

// The main class of dictionary panel
//==============================================================================
public class DictionaryPane extends JPanel {

    public static int maxColRow = 101;

    public static int newKeyw = 0;
    private boolean dictionaryState;
    private boolean singlemode = false;
    private int maxTabl = 200;

    private static String fileDictName;
    public static String keywSValue;
    public static String keywOValue;
    public static String nameValue;

    public static int intkeywords = 0;
    int colDictWidth = 300;
    int TIW = 30;
    int colTablWidth = 260;
    //  private String[] nrow = null;
    private String[] nKrow = new String[maxTabl];
    private int intTemp = 0;

    private final JLabel label1 = new JLabel("<HTML><b>Concept</b><HTML>", JButton.LEFT);
    private final JLabel label2 = new JLabel("Dictionary", JButton.CENTER);
    private final JLabel filterLabel = new JLabel("Filter to", JButton.LEFT);
//    private final JLabel label4 = new JLabel("Selected keywords from inforamtion resource", JButton.CENTER);

    private javax.swing.JTextField keywField;

    private final JPanel dictPanel = new JPanel();
    private final JPanel tablPanel = new JPanel();
    private final JPanel entPanel = new JPanel();
    private final JPanel panePanel = new JPanel();

    private JScrollPane jScrollDictPane = null;
    private JScrollPane jScrollTablPane = null;
    private JScrollPane jScrollKeywPane = null;
    private JScrollPane jMainScrollPane = null;

    private final javax.swing.JCheckBox formatCheckBox;

    private static final String[] conceptType = new String[]{"-", "isEntity", "isProperty"};
    public static JComboBox<String> filterComboBox;

    public static JTable jTableDictionary = null;
    public static JTable jTablePlenty = null;
    public static JTable jTableKeywords = null;
    private DefaultTableModel mdataTableKeywords = null;
    private DefaultTableModel mdataDictionary = null;
    private DefaultTableModel mdataTablePlenty = null;

    private final JButton updateButton = new JButton("Update tables");
    private final JButton addKeywButton = new JButton("add to Dictionary");
    private final JButton saveDictButton = new JButton("save Dictionary");
    private final JButton filterButton = new JButton("To filter");

    private final JButton jButton1 = new JButton("load Dictionary");
    private final JButton jButton2 = new JButton("Add selected keywords from inforamtion resource to Dictionary");
    private final JButton jButton3 = new JButton("Concepts without relations");
    private final JButton jButton4 = new JButton("Correct concept in ontology");
    private final JButton jButton5 = new JButton("Delete selected concept");
    private final JButton jButton6 = new JButton("Reserved button");
    private final JButton jButton7 = new JButton("Save changes to the dictionary");
    private final JButton jButton8 = new JButton("Clear all marks");

    // Constructor implementation
    //==========================================================================  
    public DictionaryPane() {
        super();
        for (int i = 0; i < 17000; i++) {
            for (int j = 0; j < 17000; j++) {
                dataRelations[i][j] = null;
            }
        }

        this.formatCheckBox = new JCheckBox("XML/RDF");
        this.formatCheckBox.setSelected(true);

        this.dictionaryState = true;
        jButton1.setBackground(Color.GRAY);
        jButton4.setBackground(Color.PINK);
        jButton5.setBackground(Color.PINK);
        saveDictButton.setBackground(Color.GRAY);
        jButton2.setBackground(Color.ORANGE);
        jButton7.setBackground(Color.ORANGE);

        keywField = new javax.swing.JTextField();

        this.filterComboBox = new JComboBox<>(conceptType);

        initActionDP();
    }

    // Interface implementation
    //==========================================================================  
    public JPanel getDictionaryPane(JPanel mainPanel) {
//        qpt = Integer.parseInt(ToolBar.qtableField.getText()) ;
//        q = qpt*2+1;
        jMainScrollPane = new JScrollPane();
        jMainScrollPane.setBackground(Color.LIGHT_GRAY);

        GroupLayout layout1 = new GroupLayout(dictPanel);
        dictPanel.setLayout(layout1);
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
                                .addComponent(getDictScrollPane(), GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(getDictScrollPane(), GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(20, 20, 20)
                .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(updateButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
        );

        GroupLayout layout2 = new GroupLayout(tablPanel);
        tablPanel.setLayout(layout2);
        layout2.setHorizontalGroup(layout2.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jButton7, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                        .addComponent(getTablScrollPane(), GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE))
        );
        layout2.setVerticalGroup(layout2.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(20, 20, 20)
                .addGroup(layout2.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(getTablScrollPane(), GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                )
        );

        GroupLayout layout3 = new GroupLayout(entPanel);
        entPanel.setLayout(layout3);
        layout3.setHorizontalGroup(layout3.createSequentialGroup()
                .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 1380, GroupLayout.PREFERRED_SIZE)
                        .addComponent(getKeywScrollPane(), GroupLayout.PREFERRED_SIZE, 1380, GroupLayout.PREFERRED_SIZE))
        );
        layout3.setVerticalGroup(layout3.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(20, 20, 20)
                .addGroup(layout3.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(getKeywScrollPane(), GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                )
        );

        GroupLayout layout0 = new GroupLayout(panePanel);
        panePanel.setLayout(layout0);
        layout0.setHorizontalGroup(layout0.createSequentialGroup()
                .addGroup(layout0.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout0.createSequentialGroup()
                                .addComponent(dictPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tablPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout0.createSequentialGroup()
                                .addComponent(entPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                )
        );
        layout0.setVerticalGroup(layout0.createSequentialGroup()
                .addGroup(layout0.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(dictPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(tablPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(20, 20, 20)
                .addGroup(layout0.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(entPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton8, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panePanel, GroupLayout.PREFERRED_SIZE, 1450, GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jButton8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panePanel, GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE)
                )
        );

        jMainScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Interconnectedness between dictionary and concept's presentations"));
        jMainScrollPane.setViewportView(mainPanel);
        jMainScrollPane.setVisible(true);

        return mainPanel;
    }

    //==========================================================================
    //  Table's functions for procession
    //==========================================================================
    private JScrollPane getDictScrollPane() {
        if (jScrollDictPane == null) {
            jScrollDictPane = new JScrollPane();
            jScrollDictPane.setRowHeaderView(getTableDictionary());
            jScrollDictPane.setViewportView(getTableDictionary());
        }
        return jScrollDictPane;
    }


    private JTable getTableDictionary() {
        if (jTableDictionary == null) {
            String[] headDict = new String[maxTabl + 1];
            headDict[0] = "A list of concepts";
            int j = 1;
            int k = 0;
            do {
                headDict[j] = String.valueOf("S" + k);
                headDict[j + 1] = String.valueOf("O" + k);
                k++;
                j += 2;
            } while (k <= (Integer) maxTabl / 2 - 1);

            mdataDictionary = new DefaultTableModel(headDict, 0);

            for (int i = 0; i < intkeywords; i++) {
                mdataDictionary.addRow(rowTemplate);
            }

            jTableDictionary = new JTable(mdataDictionary);
            jTableDictionary.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            jTableDictionary.setSelectionForeground(Color.BLUE);
            jTableDictionary.setSelectionBackground(Color.yellow);

            jTableDictionary.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getButton() == MouseEvent.BUTTON1) {
                        int row = jTableDictionary.rowAtPoint(evt.getPoint());
                        //    int col = jTableDictionary.columnAtPoint(evt.getPoint());

                        for (int j = 0; j < jTablePlenty.getRowCount(); j++) {
                            jTablePlenty.isCellEditable(j, 2);
                            jTablePlenty.getModel().setValueAt("", j, 2);
                            jTablePlenty.isCellEditable(j, 3);
                            jTablePlenty.getModel().setValueAt("", j, 3);
                        }
                        jTableDictionary.getSelectedRow();
                        int j = 1;
                        do {
                            int ir = (Integer) j / 2;
                            if (jTableDictionary.getValueAt(row, j).equals("X")) {
                                jTablePlenty.isCellEditable(ir, 2);
                                jTablePlenty.setValueAt("X", ir, 2);
                            }
                            if (jTableDictionary.getValueAt(row, j + 1).equals("X")) {
                                jTablePlenty.isCellEditable(ir, 3);
                                jTablePlenty.setValueAt("X", ir, 3);
                            }
                            j += 2;
                            //    JOptionPane.showMessageDialog(null, ""+j);
                        } while (j < (jTablePlenty.getRowCount() * 2 + 1));
                        //                      }
                        jTableDictionary.repaint();
                        jTableDictionary.updateUI();
                        jTablePlenty.repaint();
                        jTablePlenty.updateUI();
                    }
                }
            });

            jTableDictionary.setColumnSelectionAllowed(false);
            jTableDictionary.setRowSelectionAllowed(true);
            jTableDictionary.setAutoscrolls(true);
            jTableDictionary.getColumnModel().getColumn(0).setPreferredWidth(colDictWidth);
            for (int i = 1; i <= maxTabl; i++) {
                jTableDictionary.getColumnModel().getColumn(i).setPreferredWidth(TIW);
            }
        }
        return jTableDictionary;
    }

    private JScrollPane getTablScrollPane() {
        if (jScrollTablPane == null) {
            jScrollTablPane = new JScrollPane();
            jScrollTablPane.setRowHeaderView(getTables());
            jScrollTablPane.setViewportView(getTables());
        }
        return jScrollTablPane;
    }

    private JTable getTables() {
        if (jTablePlenty == null) {
            mdataTablePlenty = new DefaultTableModel(new String[]{"Triples", "Type of relations", "S", "O"}, 0);
            for (int i = 0; i < 5; i++) {
                mdataTablePlenty.addRow(new String[]{"", "", "", ""});
            }

            jTablePlenty = new JTable(mdataTablePlenty);
            jTablePlenty.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTablePlenty.setColumnSelectionAllowed(false);
            jTablePlenty.setRowSelectionAllowed(true);

            jTablePlenty.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getButton() == MouseEvent.BUTTON1) {
                        int row = jTablePlenty.rowAtPoint(evt.getPoint());
                        int col = jTablePlenty.columnAtPoint(evt.getPoint());
                        if (col > 1) {
                            jTablePlenty.isCellEditable(row, col);
                            if (jTablePlenty.getModel().getValueAt(row, col).toString().equals("X")) {
                                jTablePlenty.isCellEditable(row, col);
                                jTablePlenty.getModel().setValueAt("", row, col);
                            } else {
                                jTablePlenty.isCellEditable(row, col);
                                jTablePlenty.getModel().setValueAt("X", row, col);
                            }

                        }
                        jTablePlenty.repaint();
                        jTablePlenty.updateUI();
                    }
                }
            });

            int coefh = 17;
            if (coefh > intkeywords) {
                coefh = intkeywords;
            }
            jTablePlenty.setPreferredScrollableViewportSize(
                    new Dimension(jTablePlenty.getPreferredScrollableViewportSize().width + 210, coefh * jTablePlenty.getRowHeight()));
            jTablePlenty.setAutoscrolls(true);
            jTablePlenty.getColumnModel().getColumn(0).setPreferredWidth(colTablWidth);
            jTablePlenty.getColumnModel().getColumn(1).setPreferredWidth(colTablWidth);
            jTablePlenty.getColumnModel().getColumn(2).setPreferredWidth(TIW);
            jTablePlenty.getColumnModel().getColumn(3).setPreferredWidth(TIW);
        }
        return jTablePlenty;
    }

    private JScrollPane getKeywScrollPane() {
        if (jScrollKeywPane == null) {
            jScrollKeywPane = new JScrollPane();
            jScrollKeywPane.setRowHeaderView(getKeywords());
            jScrollKeywPane.setViewportView(getKeywords());
        }
        return jScrollKeywPane;
    }

    private JTable getKeywords() {
        if (jTableKeywords == null) {
            String[] headKeyw = new String[maxTabl + 1];
            headKeyw[0] = "Found keywords";
            int j = 1;
            int k = 1;
            do {
                headKeyw[j] = String.valueOf("S" + k);
                headKeyw[j + 1] = String.valueOf("O" + k);
                k++;
                j += 2;
            } while (k <= (Integer) maxTabl / 2);

            mdataTableKeywords = new DefaultTableModel(headKeyw, 0);
            for (int i = 0; i < maxTabl; i++) {
                mdataTableKeywords.addRow(rowTemplate);
            }

            jTableKeywords = new JTable(mdataTableKeywords);
            jTableKeywords.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTableKeywords.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getButton() == MouseEvent.BUTTON1) {
                        int row = jTableKeywords.rowAtPoint(evt.getPoint());
                        int col = jTableKeywords.columnAtPoint(evt.getPoint());
                        if (col > 0 && col <= jTablePlenty.getRowCount() && row < newKeyw) {
                            jTableKeywords.isCellEditable(row, col);
                            if (jTableKeywords.getModel().getValueAt(row, col).toString().equals("X")) {
                                jTableKeywords.isCellEditable(row, col);
                                jTableKeywords.getModel().setValueAt("", row, col);
                            } else if (jTableKeywords.getModel().getValueAt(row, col).toString().equals("")) {
                                if (col % 2 == 0) {
                                    jTableKeywords.isCellEditable(row, col - 1);
                                    jTableKeywords.getModel().setValueAt("", row, col - 1);
                                    jTableKeywords.isCellEditable(row, col);
                                    jTableKeywords.getModel().setValueAt("X", row, col);
                                } else {
                                    jTableKeywords.isCellEditable(row, col + 1);
                                    jTableKeywords.getModel().setValueAt("", row, col + 1);
                                    jTableKeywords.isCellEditable(row, col);
                                    jTableKeywords.getModel().setValueAt("X", row, col);
                                }
                            }
                        }
                        jTableKeywords.repaint();
                        jTableKeywords.updateUI();
                    }
                }
            });

            jTableKeywords.setColumnSelectionAllowed(false);
            jTableKeywords.setRowSelectionAllowed(true);

            int coefh = 17;
            if (coefh > intkeywords) {
                coefh = intkeywords;
            }
            jTableKeywords.setPreferredScrollableViewportSize(
                    new Dimension(jTableKeywords.getPreferredScrollableViewportSize().width + 210, coefh * jTableKeywords.getRowHeight()));
            jTableKeywords.setAutoscrolls(true);
            jTableKeywords.getColumnModel().getColumn(0).setPreferredWidth(colDictWidth);
            for (int i = 1; i <= maxTabl; i++) {
                jTableKeywords.getColumnModel().getColumn(i).setPreferredWidth(TIW);
            }
        }
        return jTableKeywords;
    }

    public void doSearchEmptyKeyw() {
        int check;
        jTableDictionary.isEditing();
        for (int i = 0; i < jTableDictionary.getRowCount(); i++) {
            check = 0;
            jTableDictionary.setRowSelectionInterval(i, i);
            for (int j = 1; j < maxColRow; j++) {
                if (!jTableDictionary.getValueAt(i, j).equals("")) {
                    check++;
                }
            }
            if (check == 0) {
                if (showMessBox.isSelected()) {
                    JOptionPane.showMessageDialog(null, "The undetermined keyword is SELECTED !");
                }
                return;
            }
        }
        if (showMessBox.isSelected()) {
            JOptionPane.showMessageDialog(null, "The undetermined keyword don't find!");
        }
    }

    public void doAnalysis() {
        String[] nOfTables = new String[jTablePlenty.getRowCount() * 2];

        int k = 0;
        for (int i = 0; i < jTablePlenty.getRowCount(); i++) {
            jTablePlenty.setRowSelectionInterval(i, i);
            nOfTables[k++] = "O" + (i + 1) + " - >" + jTablePlenty.getModel().getValueAt(i, 0).toString() + " - " + jTablePlenty.getModel().getValueAt(i, 1).toString();
            nOfTables[k++] = "   p" + (i + 1) + " - >" + jTablePlenty.getModel().getValueAt(i, 0).toString() + " - " + jTablePlenty.getModel().getValueAt(i, 1).toString();
        }

        jToolTabPanel.setSelectedIndex(3);
    }

    public void addKeywAction() {
        String keyw = keywField.getText();
        boolean keywexists = false;
        if (!keyw.equals("")) {
            for (int i = 0; i < jTableDictionary.getRowCount(); i++) {
                if (jTableDictionary.getModel().getValueAt(i, 0).toString().equals(keyw)) {
                    keywexists = true;
                    if (showMessBox.isSelected()) {
                        jTableDictionary.setRowSelectionInterval(i, i);
                        JOptionPane.showMessageDialog(null, "The keywords \"" + keyw + "\" already exists into the dictionary");
                    }
                }
            }

            if (!keywexists) {
                mdataDictionary.addRow(rowTemplate);
                intkeywords = mdataDictionary.getRowCount();
                jTableDictionary.isCellEditable(intkeywords - 1, 0);
                mdataDictionary.setValueAt(keyw, intkeywords - 1, 0);
                jTableDictionary.setRowSelectionInterval(intkeywords - 1, intkeywords - 1);
                if (showMessBox.isSelected()) {
                    JOptionPane.showMessageDialog(null, "The keywords \"" + keyw + "\" just added to the dictionary");
                }
            }
            jTableDictionary.repaint();
            jTableDictionary.updateUI();
        }
    }

    public void initActionDP() {

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
            tabls.setSelectedIndex(1);
        });

        jButton7.addActionListener((ActionEvent e) -> {
            int gsr = jTableDictionary.getSelectedRow();
            jTableDictionary.isEditing();
            for (int j = 1; j < jTablePlenty.getRowCount() * 2 + 1; j++) {
                jTableDictionary.setValueAt("", gsr, j);
            }
            for (int i = 0; i < jTablePlenty.getRowCount(); i++) {
                int ir = (Integer) i * 2 + 1;
                jTablePlenty.isCellEditable(i, 2);
                if (jTablePlenty.getModel().getValueAt(i, 2).toString().equals("X")) {
                    jTableDictionary.isCellEditable(gsr, ir);
                    jTableDictionary.setValueAt("X", gsr, ir);
                }
                jTablePlenty.isCellEditable(i, 3);
                if (jTablePlenty.getModel().getValueAt(i, 3).toString().equals("X")) {
                    jTableDictionary.isCellEditable(gsr, ir + 1);
                    jTableDictionary.setValueAt("X", gsr, ir + 1);
                }
            }
            jTableDictionary.repaint();
            jTableDictionary.updateUI();
        });

        jButton8.addActionListener((ActionEvent e) -> {
            for (int i = 0; i < jTablePlenty.getRowCount(); i++) {
                jTablePlenty.getModel().setValueAt("", i, 2);
                jTablePlenty.getModel().setValueAt("", i, 3);
            }
            jTableDictionary.repaint();
            jTableDictionary.updateUI();
        });

        jButton4.addActionListener((ActionEvent e) -> {
            String name = JOptionPane.showInputDialog(this, "Enter a new correct name:");
            int srow = jTableDictionary.getSelectedRow();
            jTableDictionary.getModel().setValueAt(name, srow, 0);
        });

        addKeywButton.addActionListener((ActionEvent e) -> {
            //  if (searchKeyw.isSelected()) {addKeywButtonActionPerformed(e);}
            //else{ 
            addKeywAction();
            //}
        });

        saveDictButton.addActionListener((ActionEvent e) -> {
            if (formatCheckBox.isSelected()) {
                doSaveRDF();
            } else {
                doSaveXML();
            }
        });

        jButton3.addActionListener((ActionEvent e) -> {
            doSearchEmptyKeyw();
        });

        jButton5.addActionListener((ActionEvent e) -> {
            mdataDictionary.removeRow(jTableDictionary.getSelectedRow());
            intkeywords--;
        });

        jButton6.addActionListener((ActionEvent e) -> {
            new InputForm();
        });

        jButton1.addActionListener((ActionEvent e) -> {
            if (formatCheckBox.isSelected()) {
                doLoadRDF();
            } else {
                doLoadXML();
            }
        });

        jButton2.addActionListener((ActionEvent e) -> {
            //
        });

    }

    private int ffindkeyw(String args) {
        int f = 0;
        do {
            jTableDictionary.setRowSelectionInterval(f, f);
            if (jTableDictionary.getModel().getValueAt(f, 0).toString().equals(args)) {
                return f;
            }
            f++;
        } while (f < intkeywords);
        f = -1;
        return f;
    }

    private void doSaveXML() {

        fileDictName = pathDField + "Dictionary.xml";
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
            for (int i = 0; i < jTablePlenty.getRowCount(); i++) {
                jTablePlenty.setRowSelectionInterval(i, i);
                if (!jTablePlenty.getValueAt(i, 0).toString().equals("")) {
                    Element tabl = doc.createElement("typeOfRelation");
                    rootElement.appendChild(tabl);
                    tabl.setAttribute("id", String.valueOf(i));

                    nameValue = jTablePlenty.getValueAt(i, 0).toString();
//                    Element obj = doc.createElement("object"); obj.appendChild(doc.createTextNode(nameValue)); tabl.appendChild(obj);
                    nameValue = nameValue + "|" + jTablePlenty.getValueAt(i, 1).toString();
                    Element tname = doc.createElement("relation");
                    tname.appendChild(doc.createTextNode(nameValue));
                    tabl.appendChild(tname);
                }
            }

            for (int i = 0; i < intkeywords; i++) {
                Element keyw = doc.createElement("is-concept");
                rootElement.appendChild(keyw);
                keyw.setAttribute("id", String.valueOf(i));

                jTableDictionary.setRowSelectionInterval(i, i);
                nameValue = jTableDictionary.getValueAt(i, 0).toString();
                Element namew = doc.createElement("concept");
                namew.appendChild(doc.createTextNode(nameValue));
                keyw.appendChild(namew);

                int j = 1;
                do {
                    keywSValue = jTableDictionary.getValueAt(i, j).toString();
                    keywOValue = jTableDictionary.getValueAt(i, j + 1).toString();
                    if (!keywSValue.equals("")) {
                        Element o = doc.createElement("s" + String.valueOf(j));
                        o.appendChild(doc.createTextNode("X"));
                        keyw.appendChild(o);
                    }
                    if (!keywOValue.equals("")) {
                        Element p = doc.createElement("o" + String.valueOf(j));
                        p.appendChild(doc.createTextNode("X"));
                        keyw.appendChild(p);
                    }
                    j += 2;
                } while (j < (Integer) maxTabl / 2);
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(fileDictName));
            transformer.transform(source, result);
            if (showMessBox.isSelected()) {
                dictionaryState = true;
                JOptionPane.showMessageDialog(null, "File saved!");
            }
        } catch (ParserConfigurationException | TransformerException pce) {
        }
    }

    private void doSaveRDF() {

        fileDictName = pathDField + "Dictionary.xml";
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

            for (int i = 0; i < jTablePlenty.getRowCount(); i++) {
                jTablePlenty.setRowSelectionInterval(i, i);
                if (!jTablePlenty.getValueAt(i, 0).toString().equals("")) {

                    nameValue = jTablePlenty.getValueAt(i, 0).toString();
//                    Element obj = doc.createElement("object"); obj.appendChild(doc.createTextNode(nameValue)); tabl.appendChild(obj);
                    nameValue = nameValue + "|" + jTablePlenty.getValueAt(i, 1).toString();
                    Element tname = doc.createElement("tname");
                    tname.appendChild(doc.createTextNode(nameValue));
                    tof.appendChild(tname);
                    tname.setAttribute("id", String.valueOf(i));
                }
            }

            for (int i = 0; i < intkeywords; i++) {
                Element keyw = doc.createElement("concept");
                rootElement.appendChild(keyw);
                keyw.setAttribute("id", String.valueOf(i));

                jTableDictionary.setRowSelectionInterval(i, i);
                nameValue = jTableDictionary.getValueAt(i, 0).toString();
                Element namew = doc.createElement("con_name");
                namew.appendChild(doc.createTextNode(nameValue));
                keyw.appendChild(namew);

                int j = 0;
                do {
                    keywSValue = jTableDictionary.getValueAt(i, 2 * j + 1).toString();
                    keywOValue = jTableDictionary.getValueAt(i, 2 * j + 2).toString();
                    if (!keywSValue.equals("")) {
                        Element o = doc.createElement("subjectOf");
                        o.appendChild(doc.createTextNode(jTablePlenty.getValueAt(j, 1).toString()));
                        keyw.appendChild(o);
                    }
                    if (!keywOValue.equals("")) {
                        Element p = doc.createElement("objectOf");
                        p.appendChild(doc.createTextNode(jTablePlenty.getValueAt(j, 1).toString()));
                        keyw.appendChild(p);
                    }
                    //j+=2;    
                    j++;
                    if (j > jTablePlenty.getRowCount()) {
                        break;
                    }
                } while (j < (Integer) maxTabl / 2);
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(fileDictName));
            transformer.transform(source, result);
            if (showMessBox.isSelected()) {
                dictionaryState = true;
                JOptionPane.showMessageDialog(null, "File saved!");
            }
        } catch (ParserConfigurationException | TransformerException pce) {
        }
    }

    private void doLoadXML() {

        if (dictionaryState) {
            dictionaryState = false;
            mdataDictionary.addRow(rowTemplate);

            if (jTableDictionary.getRowCount() > 0) {
                for (int i = 0; i <= jTableDictionary.getRowCount(); i++) {
                    mdataDictionary.removeRow(0);
                }
            }
            // mdataDictionary.removeRow(0);

            intkeywords = 0;
            fileDictName = pathDField + "Dictionary.xml";

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
            } else {
                return;
            }

            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();

                DefaultHandler handler = new DefaultHandler() {

                    boolean bdictionary = false;
                    boolean btabl = false;
                    boolean btname = false;
                    boolean bkeyw = false;
                    boolean bnamew = false;
                    boolean bo = false;
                    boolean bp = false;
                    int qtabl = 0;
                    String sV = "";
                    StringBuilder sVs;

                    @Override
                    public void startElement(String uri, String localName, String qName,
                            Attributes attributes) throws SAXException {

                        //	System.out.println("Start Element :" + qName);
                        sV = qName;

                        if (qName.equalsIgnoreCase("dictionary")) {
                            bdictionary = true;
                        }

                        if (qName.equalsIgnoreCase("typeOfRelation")) {
                            btabl = true;
                        }

                        if (qName.equalsIgnoreCase("relation")) {
                            btname = true;
                        }

                        if (qName.equalsIgnoreCase("is-concept")) {
                            bkeyw = true;
                        }

                        if (qName.equalsIgnoreCase("concept")) {
                            bnamew = true;
                        }

                        if (qName.startsWith("s")) {
                            bo = true;
                        }
                        if (qName.startsWith("o")) {
                            bp = true;
                        }

                    }

                    @Override
                    public void endElement(String uri, String localName,
                            String qName) throws SAXException {
                        //System.out.println("End Element :" + qName);
                    }

                    public void saveTuple(String strT) {
                        if (qtabl >= mdataTablePlenty.getRowCount()) {
                            mdataTablePlenty.addRow(new String[]{"", "", "", ""});
                        }
                        //  mdataTablePlenty.addRow(new String[]{"", "", "", ""});            
                        jTablePlenty.setRowSelectionInterval(qtabl, qtabl);
                        jTablePlenty.getModel().isCellEditable(qtabl, 0);
                        jTablePlenty.setValueAt(strT.substring(0, strT.indexOf("|")), qtabl, 0);
                        jTablePlenty.getModel().isCellEditable(qtabl, 1);
                        jTablePlenty.setValueAt(strT.substring(strT.indexOf("|") + 1, strT.length()), qtabl, 1);
                        qtabl++;
                    }

                    public void saveKeyw(String strT) {
//            if(intkeywords>mdataDictionary.getRowCount()) {mdataDictionary.addRow(rowTemplate);}
                        mdataDictionary.addRow(rowTemplate);
                        jTableDictionary.setRowSelectionInterval(intkeywords, intkeywords);
                        jTableDictionary.getModel().isCellEditable(intkeywords, 1);

                        jTableDictionary.setValueAt(strT, intkeywords, 0);
                        intkeywords++;
                    }

                    public void saveORelation(int rO) {
                        jTableDictionary.getModel().isCellEditable(intkeywords - 1, rO);
                        jTableDictionary.setValueAt("X", intkeywords - 1, rO);
                    }

                    public void savePRelation(int rP) {
                        jTableDictionary.getModel().isCellEditable(intkeywords - 1, rP + 1);
                        jTableDictionary.setValueAt("X", intkeywords - 1, rP + 1);
                    }

                    @Override
                    public void characters(char ch[], int start, int length) throws SAXException {

                        if (bdictionary) {
                            //	System.out.println("dictionary : " + new String(ch, start, length));
                            bdictionary = false;
                        }

                        if (btabl) {
                            //	System.out.println("id : " + new String(ch, start, length));
                            btabl = false;
                        }

                        if (btname) {
                            //	System.out.println("tabl name  : " + new String(ch, start, length));
                            saveTuple(new String(ch, start, length));
                            btname = false;
                        }

                        if (bkeyw) {
                            //	System.out.println("id : " + new String(ch, start, length));
                            bkeyw = false;
                        }

                        if (bnamew) {
                            //	System.out.println("keyw : " + new String(ch, start, length));
                            saveKeyw(new String(ch, start, length));
                            bnamew = false;
                        }

                        if (bo) {
                            sVs = new StringBuilder(sV);
                            sV = sVs.deleteCharAt(0).toString();
                            saveORelation(Integer.parseInt(sV));
                            bo = false;
                        }
                        if (bp) {
                            sVs = new StringBuilder(sV);
                            sV = sVs.deleteCharAt(0).toString();
                            savePRelation(Integer.parseInt(sV));
                            bp = false;
                        }

                    }

                };
                JOptionPane.showMessageDialog(null, "You must check the table hierarchy !");
                jToolTabPanel.setSelectedIndex(1);
                saxParser.parse(fileDictName, handler);
            } catch (ParserConfigurationException | SAXException | IOException e) {
            }

        } else {
            JOptionPane.showMessageDialog(null, "The changers into dictionary doesn't save !");
        }
    }

    private void doLoadRDF() {

        if (dictionaryState) {
            dictionaryState = false;
            mdataDictionary.addRow(rowTemplate);

            if (jTableDictionary.getRowCount() > 0) {
                for (int i = 0; i <= jTableDictionary.getRowCount(); i++) {
                    mdataDictionary.removeRow(0);
                }
            }
            // mdataDictionary.removeRow(0);

            intkeywords = 0;
            fileDictName = pathDField + "Dictionary.xml";

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
            } else {
                return;
            }
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
                    StringBuilder sVs;
                    int position = 0;

                    @Override
                    public void startElement(String uri, String localName, String qName,
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

                    public void saveTuple(String strT) {
                        if (qtabl >= mdataTablePlenty.getRowCount()) {
                            mdataTablePlenty.addRow(new String[]{"", "", "", ""});
                        }
                        //  mdataTablePlenty.addRow(new String[]{"", "", "", ""});            
                        jTablePlenty.setRowSelectionInterval(qtabl, qtabl);
                        jTablePlenty.getModel().isCellEditable(qtabl, 0);
                        jTablePlenty.setValueAt(strT.substring(0, strT.indexOf("|")), qtabl, 0);
                        jTablePlenty.getModel().isCellEditable(qtabl, 1);
                        jTablePlenty.setValueAt(strT.substring(strT.indexOf("|") + 1, strT.length()), qtabl, 1);
                        qtabl++;
                    }

                    /*        index1 = rstr.indexOf(" ", index1+1);
        temp = rstr.substring(index2, index1);
                     */
                    public void saveKeyw(String strT) {
//            if(intkeywords>mdataDictionary.getRowCount()) {mdataDictionary.addRow(rowTemplate);}
                        mdataDictionary.addRow(rowTemplate);
                        jTableDictionary.setRowSelectionInterval(intkeywords, intkeywords);
                        jTableDictionary.getModel().isCellEditable(intkeywords, 1);

                        jTableDictionary.setValueAt(strT, intkeywords, 0);
                        intkeywords++;
                    }

                    public void saveSRelation(int rO) {
                        jTableDictionary.getModel().isCellEditable(intkeywords - 1, rO);
                        jTableDictionary.setValueAt("X", intkeywords - 1, rO);
                    }

                    public void saveORelation(int rP) {
                        jTableDictionary.getModel().isCellEditable(intkeywords - 1, rP);
                        jTableDictionary.setValueAt("X", intkeywords - 1, rP);
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
                            for (int i = 0; i < jTablePlenty.getRowCount(); i++) {
                                if (jTablePlenty.getValueAt(i, 1).toString().equals(subjStr)) {
//                                System.out.println(subjStr+"  - "+i);
                                    position = i;
                                }
                            }
                            position = position * 2 + 1;
                            saveSRelation(position);
                            bsubjectof = false;
                        }
                        if (bobjectof) {
                            objStr = new String(ch, start, length);
                            for (int i = 0; i < jTablePlenty.getRowCount(); i++) {
                                if (jTablePlenty.getValueAt(i, 1).toString().equals(objStr)) {
//                                System.out.println(objStr+"  - "+i);
                                    position = i;
                                }
                            }
                            position = position * 2 + 2;
                            saveORelation(position);
                            bobjectof = false;
                        }

                    }

                };
                JOptionPane.showMessageDialog(null, "You must chech the table hierarchy !");
                jToolTabPanel.setSelectedIndex(1);
                saxParser.parse(fileDictName, handler);
            } catch (ParserConfigurationException | SAXException | IOException e) {
            }

        } else {
            JOptionPane.showMessageDialog(null, "The changers into dictionary doesn't save !");
        }
    }

}
