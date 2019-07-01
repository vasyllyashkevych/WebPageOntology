/**
 * @author Vasyl Lyashkevych
 */
package util;

import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JViewport;
import javax.swing.LayoutStyle;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import static main.DictionaryPane.jTableDictionary;
import static main.DictionaryThing.jTableThingDictionary;
import static main.ToolBar.dataRelations;
import static main.ToolBar.pathTField;
import static main.ToolBar.tabls;
import static main.ToolBar.things;

public class Tabl1 extends JTable {
    
    public static int widthColumns = 25;
    public static int rowColumnHeigth = 300;

    
    public static String[][] dataMas = new String[1024][1024];
    
    public static javax.swing.JScrollPane tablePane = null;
    public static javax.swing.JTable table = null;
    public static DefaultTableModel data;
    
    public static JPanel mainPanel = new JPanel();
    public static javax.swing.JScrollPane mainPane = new JScrollPane();

    public static JButton jButton = new JButton("Update");
    public static JButton jButton0 = new JButton("Load");
    public static JButton jButton1 = new JButton("Save");
    public static JButton jButton2 = new JButton("find Subjects");
    public static JButton jButton3 = new JButton("find Objects");
    public static JButton jButton4 = new JButton("sort Subjects");
    public static JButton jButton7 = new JButton("sort Objects");
    public static JButton jButton5 = new JButton("delete Objects");
    public static JButton jButton6 = new JButton("delete Subjects");
    public static ButtonGroup group = new ButtonGroup();
    public static JRadioButton rowButton = new JRadioButton("Row", true);
    public static JRadioButton colButton = new JRadioButton("Column", false);
    
    public static javax.swing.JCheckBox showMessages;
    public static javax.swing.JCheckBox formatCheckBox;


    public static int colCountState = 1;
    public static int rowCountState = 0;
    public static int colCount = 1024;
    public static int rowCount = 1024;
    public static int rowHeaderWidth = 0;
    public static int colHeaderHeight = 0;

    public static String[] rowVector;
    public static int withCol = widthColumns;
    public static String fileTName;
    
        
    public static void datafill(javax.swing.JTabbedPane tbp){
        rowCountState = qSubject();
        colCountState = qObject();
        
        int t_index = tbp.getSelectedIndex();
        int starti = 1000*t_index;
        int startj = 1000*t_index;
        int point = 1000*t_index;
//        int finish = 1000*t_index+t_index;
        for (int j=0; j<=colCountState; j++) {
            if (j==1000) {break;}
//            System.out.println(table.getColumnModel().getColumn(j).getHeaderValue().toString());
//            if (table.getColumnModel().getColumn(j).getHeaderValue().toString().equals("")) {break;}
            dataRelations[starti][startj]= table.getColumnModel().getColumn(j).getHeaderValue().toString();
            startj++;
        }                
            startj = starti;
            starti++;

        for(int i=0; i<=rowCountState;i++){
            startj=point;
            for (int j=0; j<=colCountState; j++) {
                if (i==1000 | j==1000) {break;}
//                System.out.println(table.getModel().getValueAt(i, j).toString());
//                if (table.getModel().getValueAt(i, j).toString().equals("")) {break;}
                dataRelations[starti][startj]= table.getModel().getValueAt(i, j).toString();
                startj++;
            }                
            starti++;
        }    

    }
    
    public Tabl1(javax.swing.JTabbedPane jtbp){
        for(int i=0; i<1024; i++)
            for(int j=0; j<1024; j++)
                dataMas[i][j]="";
        
        this.showMessages = new JCheckBox("Messaging");
        this.showMessages.setSelected(true);
        this.formatCheckBox = new JCheckBox("XML/RDF");
        this.formatCheckBox.setSelected(true);
        this.jButton2.setBackground(Color.CYAN);
        this.jButton3.setBackground(Color.CYAN);
        this.jButton4.setBackground(Color.YELLOW);
        this.jButton7.setBackground(Color.YELLOW);
        this.jButton5.setBackground(Color.PINK);
        this.jButton6.setBackground(Color.PINK);
        initActionTabl(jtbp);
    }
    
    // ActionListeners implementation
    //==========================================================================
    public static void initActionTabl(javax.swing.JTabbedPane jtbp){
        rowButton.addActionListener((ActionEvent event) -> {
            table.setColumnSelectionAllowed(false);
            table.setRowSelectionAllowed(true);
        });
        
        colButton.addActionListener((ActionEvent event) -> {
            table.setColumnSelectionAllowed(true);
            table.setRowSelectionAllowed(false);

        });
        
        group.add(rowButton);
        group.add(colButton);
    
        jButton.addActionListener((ActionEvent event) -> {
            dataUpdate(jtbp);
        });
    
        jButton0.addActionListener((ActionEvent event) -> {
            if(!formatCheckBox.isSelected()) {doLoadXML(jtbp);}
            else {doLoadRDF(jtbp);}
        });
    
        jButton1.addActionListener((ActionEvent event) -> {
            if(!formatCheckBox.isSelected()) {doSaveXML(jtbp);}
            else {doSaveRDF(jtbp);}
        });
    
        jButton2.addActionListener((ActionEvent event) -> {
            findEmptyRow(0);
        });
    
        jButton3.addActionListener((ActionEvent event) -> {
            findEmptyCol(1);
        });
    
        jButton4.addActionListener((ActionEvent event) -> {
            sortSubject();
        });
    
        jButton7.addActionListener((ActionEvent event) -> {
            sortObject();
        });
    
        jButton5.addActionListener((ActionEvent event) -> {
            deleteEmptyCol();
        });
    
        jButton6.addActionListener((ActionEvent event) -> {
            deleteEmptyRow();
        });
    }
            
    // The interface part of main panel with table and components
    //==========================================================================
    public static JScrollPane getTableTemplate(String names){
        GroupLayout layout1 = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout1);
        layout1.setHorizontalGroup(layout1.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout1.createSequentialGroup()
                    .addComponent(formatCheckBox, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton0, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton7, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton6, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(showMessages, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(rowButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(colButton, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout1.createSequentialGroup()
                        .addComponent(getTablePane(), GroupLayout.PREFERRED_SIZE, 1450, GroupLayout.PREFERRED_SIZE))
            )
        );
                            
        layout1.setVerticalGroup(layout1.createSequentialGroup()
            .addGap(20, 20, 20)
            .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(formatCheckBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showMessages, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(rowButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(colButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(20, 20, 20)
            .addGroup(layout1.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(getTablePane(), GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE)
            )
        );

        table.getColumnModel().getColumn(0).setHeaderValue(names);
         
        mainPane.setViewportView(mainPanel);
        mainPane.setVisible(true);
        return mainPane;
    }

    //==========================================================================
    //  The functions for working with type of relations
    //==========================================================================
    public static JScrollPane getTablePane() {
        if (tablePane == null) {
            tablePane = new JScrollPane();
            tablePane.setRowHeaderView(getTableRelation());
            tablePane.setViewportView(getTableRelation());
            tablePane.setColumnHeader(new JViewport() {
            @Override public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.height = rowColumnHeigth;  // Col header Height
                return d;
                }
            });
        }
        return tablePane;
    }
    
    //  Table presentation
    //==========================================================================
    public static JTable getTableRelation(){
        if (table == null) {
            rowVector = new String[colCount];
                for(int i=0; i<colCount;i++) {rowVector[i]="";}
            data = new DefaultTableModel(rowVector, 0);
                for (int i = 0; i < rowCount; i++) { data.addRow(rowVector); }
		
            table = new JTable(data);
            table.getTableHeader().setDefaultRenderer(new VerticalTableHeaderCellRenderer());
                for(int i=1; i<colCount; i++)  { table.getColumnModel().getColumn(i).setPreferredWidth(withCol);}

                table.setSelectionForeground(Color.BLACK);
                table.setSelectionBackground(Color.YELLOW);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.setAutoscrolls(true);
            table.setColumnSelectionAllowed(false);
            table.setRowSelectionAllowed(true);
    
            table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {} 
                if(evt.getButton()==MouseEvent.BUTTON1) {
                    int row = table.rowAtPoint(evt.getPoint());
                    int col = table.columnAtPoint(evt.getPoint());
                    if ((0<col & col<colCountState)&(0<=row & row<rowCountState)){
                        table.isCellEditable(row, col);
                        String symbol = table.getModel().getValueAt(row, col).toString();
                        switch(symbol)  {
                            case "X": {
                                        table.getModel().setValueAt("", row, col);
                                        break;
                            }
                            default : {
                                        table.getModel().setValueAt("X", row, col);
                                        break;
                            }
                        }
                    }
                }
                table.repaint();
                table.updateUI(); }
            });
        }
        return table;
    }

    // Sort the objects
    //==========================================================================
    public static void sortObject(){
        rowCountState = qSubject();
        colCountState = qObject();
            System.out.println("rowCountState" + rowCountState);
            System.out.println("colCountState" + colCountState);
        
        String[][] data = new String[rowCountState+1][colCountState];
        for(int i=1; i<colCountState; i++){
            data[0][i]=table.getColumnModel().getColumn(i).getHeaderValue().toString();
            if (!data[0][i].equals("")) {System.out.println("a"+data[0][i]);}
        }        
        
        for(int i=1; i<rowCountState+1; i++){
            for(int j=0; j<colCountState; j++){
                System.out.println("cr: i * j : "+i+"  "+j);
                data[i][j]=table.getModel().getValueAt(i-1, j).toString();
                if (!data[i][j].equals("")) {System.out.print("   "+data[i][j]);}
            }
            System.out.println("");
        }
        
        String tempStr = "";
        int a, b = 0;
        String str_a, str_b = "";
        for(int i=0; i<colCountState; i++){
            for(int j=2; j<colCountState; j++){
                str_a = data[i][j-1].substring(0, 0);
                str_b = data[i][j].substring(0, 0);
                a = (int)str_a.charAt(0);
                b = (int)str_b.charAt(0);
                if (a>b){ 
                    table.getColumnModel().getColumn(j).setHeaderValue(str_b);
                    table.getColumnModel().getColumn(j-1).setHeaderValue(str_a);
                }
            }
        }
    
    }
    
    // Sort the subjects
    //==========================================================================
    public static void sortSubject(){
        rowCountState = qSubject();
        colCountState = qObject();
        
        String[][] data = new String[rowCountState+1][colCountState];
        for(int i=1; i<colCountState; i++){
            data[0][i]=table.getColumnModel().getColumn(i).getHeaderValue().toString();
            if (!data[0][i].equals("")) {System.out.println("a"+data[0][i]);}
        }        
        
        for(int i=1; i<rowCountState+1; i++){
            for(int j=0; j<colCountState; j++){
                System.out.println("cr: i * j : "+i+"  "+j);
                data[i][j]=table.getModel().getValueAt(i-1, j).toString();
                if (!data[i][j].equals("")) {System.out.print("   "+data[i][j]);}
            }
            System.out.println("");
        }

        
        String tempStr = "";
        int a, b = 0;
        String str_a, str_b = "";
        for(int i=1; i<colCountState; i++){
            for(int j=1; j<colCountState; j++){
                if(i==j) {continue;}
/*                str_a = table.getColumnModel().getColumn(i).getHeaderValue().toString().substring(0, 0);
                a = str_a.getKeyChar();
                str_b = table.getColumnModel().getColumn(j).getHeaderValue().toString().substring(0, 0);
                b = str_b.getKeyChar();
                if (a>b){ 
                    table.getColumnModel().getColumn(i).getHeaderValue() = str_b;
                    table.getColumnModel().getColumn(j).getHeaderValue() = str_a;
                    
  */                  
                    
                
        
            }
        }
    }
    
    
    // Search object by name
    //==========================================================================
    public static int fObject(String po){
        for(int i=1; i<=colCountState; i++){
            if (table.getColumnModel().getColumn(i).getHeaderValue().toString().equals(po)){
                return i;
            }
        }
        return -1;
    }
    
    // Search subject by name
    //==========================================================================
    public static int fSubject(String ps){
        for(int i=0; i<=rowCountState; i++){
            if (table.getModel().getValueAt(i, 0).toString().equals(ps)){
                return i;
            }
        }
        return -1;
    }
    
    // Search quantity of objects
    //==========================================================================
    public static int qObject(){
        for(int i=1; i<1024; i++){
            if (table.getColumnModel().getColumn(i).getHeaderValue().toString().isEmpty() | table.getColumnModel().getColumn(i).getHeaderValue().toString().equals("")){
                return i;
            }
        }
        return 0;
    }
    
    // Search quantiry of subjects
    //==========================================================================
    public static int qSubject(){
        for(int i=0; i<1024; i++){
            if (table.getModel().getValueAt(i, 0).toString().isEmpty() | table.getModel().getValueAt(i, 0).toString().equals("")){
                return i;
            }
        }
        return 0;
    }
    
    // Update data from Dictionary
    //==========================================================================
    public static void dataUpdate(javax.swing.JTabbedPane jtbp){
            rowCountState=qSubject();
//            JOptionPane.showMessageDialog(null, "rows "+rowCountState);
            colCountState=qObject();
//            JOptionPane.showMessageDialog(null, "cols "+colCountState);

        String[] buttons = { "Create?", "Load and update?", "Cancel" };
        int rc = JOptionPane.showOptionDialog(null, "The typeR :  "+jtbp.getTitleAt(jtbp.getSelectedIndex())+".xml is not loaded. Do you want to load or continue?", 
                        "Confirmation", JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[2]);
        if(rc==0){
            for(int i=1; i<=colCountState; i++) {
                table.getColumnModel().getColumn(i).setHeaderValue("");
            }    
                    
            for(int i=0; i<=rowCountState; i++)
                for(int j=0; j<=colCountState; j++) 
                    table.getModel().setValueAt("", i, j);
                            
            rowCountState=0;
            colCountState=1;
        }

        if(rc==1){ 
            for(int i=1; i<=colCountState; i++) {
                table.getColumnModel().getColumn(i).setHeaderValue("");
            }    
                    
            for(int i=0; i<=rowCountState; i++)
                for(int j=0; j<=colCountState; j++)
                    table.getModel().setValueAt("", i, j);

            if(!formatCheckBox.isSelected()) {doLoadXML(jtbp);}
            else {doLoadRDF(jtbp);}
        }
                
        if(rc==2){
            return;
        }
        
        int index = jtbp.getSelectedIndex();
        if (jtbp==tabls) {
            for (int i=0; i<jTableDictionary.getRowCount(); i++){
                jTableDictionary.setRowSelectionInterval(i, i);
                if (jTableDictionary.getModel().getValueAt(i, index*2+2).toString().equals("X")) { 
                    String ts = jTableDictionary.getModel().getValueAt(i, 0).toString();
                    int result = fObject(ts);
                    if (result ==-1) {
                        table.getColumnModel().getColumn(colCountState).setHeaderValue(ts);
                        colCountState++;
                    }
                };
                if (jTableDictionary.getModel().getValueAt(i, index*2+1).toString().equals("X")) { 
                    String ts = jTableDictionary.getModel().getValueAt(i, 0).toString();
                    int result = fSubject(ts);
                    if (result==-1) {
                        table.getModel().setValueAt(ts, rowCountState, 0);
                        rowCountState++;
                    }    
                };  
            }
        
        }
        
        if (jtbp==things) {
            for (int i=0; i<jTableThingDictionary.getRowCount(); i++){
                jTableThingDictionary.setRowSelectionInterval(i, i);
                if (jTableThingDictionary.getModel().getValueAt(i, index*2+2).toString().equals("X")) { 
                    String ts = jTableThingDictionary.getModel().getValueAt(i, 0).toString();
                    int result = fObject(ts);
                    if (result == -1) {
                        table.getColumnModel().getColumn(colCountState).setHeaderValue(ts);
                        colCountState++;
                    }
                };
                if (jTableThingDictionary.getModel().getValueAt(i, index*2+1).toString().equals("X")) { 
                    String ts = jTableThingDictionary.getModel().getValueAt(i, 0).toString();
                    int result = fSubject(ts);
                    if (result == -1) {
                        table.getModel().setValueAt(ts, rowCountState, 0);
                        rowCountState++;
                    }    
                };  
            }
        
        }
        
        table.repaint();
        table.updateUI();

        if (showMessages.isSelected()) JOptionPane.showMessageDialog(null, "The presentation is updated !");
        datafill(jtbp);
    }   
    
    // Find empty columns
    //==========================================================================
    public static int findEmptyCol(int start){
        String[] buttons = {"Next columns","Cancel" };
        String name = JOptionPane.showInputDialog( "Name of object / 0? - empty object ");

        if(!name.equals("0?")) {
            for (int i=1; i<=colCountState; i++){
                if (table.getColumnModel().getColumn(i).getHeaderValue().toString().contains(name) | 
                    table.getColumnModel().getColumn(i).getHeaderValue().toString().equals(name)) {
                        table.setColumnSelectionAllowed(true);
                        table.setRowSelectionAllowed(false);
                        table.setColumnSelectionInterval(i, i);
                        table.scrollRectToVisible(table.getCellRect(0,i, true)); 

                        int rc = JOptionPane.showOptionDialog(null, "Did you find the : "+table.getColumnModel().getColumn(i).getHeaderValue().toString()+" ?", 
                            "Confirmation", JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
                        if(rc==0){ continue; }
                        if(rc==1){ return 0; }    
                }
            }
        }   else {
            int kt;
            table.setColumnSelectionAllowed(true);
            table.setRowSelectionAllowed(false);
            for (int i=start; i<=colCountState; i++){
                kt=0;
                for (int j=0; j<=rowCountState; j++){
                    table.getModel().isCellEditable(j, i);
                    if (!table.getModel().getValueAt(j, i).toString().equals("")){kt++;}
                }
                if (kt==0){
                    table.setColumnSelectionInterval(i, i);
                    String infObject = table.getColumnModel().getColumn(i).getHeaderValue().toString();
                    if (showMessages.isSelected()) JOptionPane.showMessageDialog(null, "The object :  "+infObject+" has not any relationships with subjects !");
                    return i;
                }
            }
        }
        
        if (showMessages.isSelected()) JOptionPane.showMessageDialog(null, "The empty column does not exist! Search is completed !");
        return -1;
    }   
    
    // Find empty rows
    //==========================================================================
    public static int findEmptyRow(int start){
        String[] buttons = {"Next columns","Cancel" };
        String name = JOptionPane.showInputDialog( "Name of object / 0? - empty subject ");

        if(!name.equals("0?")) {
            for (int i=1; i<=rowCountState; i++){
                if (table.getModel().getValueAt(i, 0).toString().contains(name) | 
                    table.getModel().getValueAt(i, 0).toString().equals(name)) {
                        table.setColumnSelectionAllowed(false);
                        table.setRowSelectionAllowed(true);
                        table.setRowSelectionInterval(i, i);
                        table.scrollRectToVisible(table.getCellRect(i,0, true)); 
                
                        int rc = JOptionPane.showOptionDialog(null, "Did you find the : "+table.getModel().getValueAt(i, 0).toString()+" ?", 
                            "Confirmation", JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
                        if(rc==0){ continue; }
                        if(rc==1){ return 0; }    
                }
            }
        }   else {
            int kt;
            table.setColumnSelectionAllowed(false);
            table.setRowSelectionAllowed(true);
            for (int i=start; i<=rowCountState; i++){
                kt=0;
                for (int j=1; j<=colCountState; j++){
                    table.getModel().isCellEditable(i, j);
                    if (!table.getModel().getValueAt(i, j).toString().equals("")){kt++;}
                }
                if (kt==0){
                    table.setRowSelectionInterval(i, i);
                    String infObject = table.getModel().getValueAt(i, 0).toString();
                    if (showMessages.isSelected()) JOptionPane.showMessageDialog(null, "The subject :  "+infObject+" has not any relationships with objects !");
                    return i;
                }
            }
        }
        if (showMessages.isSelected()) JOptionPane.showMessageDialog(null, "The empty row does not exist! Search is completed !");
        return -1;
    }   
    
    // Deleting empty columns
    //==========================================================================
    public static void deleteEmptyCol(){
        boolean stateMess;
        int tempColCount = colCountState;
        if (showMessages.isSelected()){ stateMess = true;} 
        else { stateMess = false;}
            
        showMessages.setSelected(false);
        for (int col = 1; col<=tempColCount; col++){
            int result = findEmptyCol(col);
            if (result > 0) {
                String infProperty = table.getColumnModel().getColumn(col).getHeaderValue().toString();
                String[] buttons = { "Yes", "Next columns","Cancel" };
                int rc = JOptionPane.showOptionDialog(null, "The object :  "+infProperty+" is empty. To delete \""+infProperty+"\" from presentation? Search other object ?", 
                        "Confirmation", JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[2]);
                if(rc==0){
                    table.getColumnModel().removeColumn(table.getColumnModel().getColumn(col));
                    table.getColumnModel().addColumn(table.getColumnModel().getColumn(colCount));
                    colCountState--;
                }

                if(rc==1){ continue; }
                
                if(rc==2){
                    showMessages.setSelected(stateMess);   
                    return;
                }
            } else {
                if (showMessages.isSelected()) JOptionPane.showMessageDialog(null, "There are not empty objects!");
            }    
        }        
        showMessages.setSelected(stateMess);   
        table.repaint();
        table.updateUI();
    }
       
    // Deleting empty rows (subjects)
    //==========================================================================
    public static void deleteEmptyRow(){
        boolean stateMess;
        int tempRowCount = rowCountState;
        if (showMessages.isSelected()){ stateMess = true;} 
        else { stateMess = false;}
            
        showMessages.setSelected(false);
        for (int row = 1; row<=tempRowCount; row++){
            int result = findEmptyRow(row);
            if (result > 0) {
                String infObject = table.getModel().getValueAt(row, 0).toString();
                String[] buttons = { "Yes", "Next columns","Cancel" };
                int rc = JOptionPane.showOptionDialog(null, "The subject :  "+infObject+" is empty. To delete \""+infObject+"\" from presentation? Search other subject ?", 
                        "Confirmation", JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[2]);
                if(rc==0){
                    data.removeRow(row);
                    data.addRow(rowVector);
                    rowCountState--;
                }

                if(rc==1){ continue; }
                
                if(rc==2){
                    showMessages.setSelected(stateMess);   
                    return;
                }
            } else {
                if (showMessages.isSelected()) JOptionPane.showMessageDialog(null, "There are not empty subjects");
            }    
        }        
        showMessages.setSelected(stateMess);   
        table.repaint();
        table.updateUI();
    }   

    // The methods for working with XML
    // =========================================================================
    public static void doSaveXML(javax.swing.JTabbedPane jtbp) {
        datafill(jtbp);
        
        String tempString;
        
        fileTName = pathTField+jtbp.getTitleAt(jtbp.getSelectedIndex())+".xml";
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("dictionary files", "xml");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setDialogTitle("Choose a file for relations saving"); 
        fileChooser.setSelectedFile(new File(fileTName));
        int userSelection = fileChooser.showSaveDialog(fileChooser);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileTName = fileChooser.getSelectedFile().getAbsolutePath();
        }
            
	try {
            DocumentBuilderFactory docTFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docTBuilder = docTFactory.newDocumentBuilder();
		Document doc = docTBuilder.newDocument();
		Element rootElement = doc.createElement("triples");
		doc.appendChild(rootElement);
                
        for (int j=1; j<colCountState; j++){
            table.setColumnSelectionInterval(j, j);
            tempString = table.getColumnModel().getColumn(j).getHeaderValue().toString();
            if (!tempString.equals("")) {
                Element prt = doc.createElement("object");
                rootElement.appendChild(prt);
                prt.setAttribute("id", String.valueOf(j));
                    Element pname = doc.createElement("obj-name"); 
                    pname.appendChild(doc.createTextNode(tempString)); 
                    prt.appendChild(pname);
            }
        }        

        for(int i=0; i<rowCountState; i++) {    
            Element obj = doc.createElement("subject");
            rootElement.appendChild(obj);
            obj.setAttribute("id", String.valueOf(i));
                
            table.setRowSelectionInterval(i, i);
            tempString = table.getValueAt(i, 0).toString();
                Element oname = doc.createElement("subj-name"); 
                oname.appendChild(doc.createTextNode(tempString)); 
                obj.appendChild(oname);
                
            int j=1; 
            do {
                tempString = table.getValueAt(i, j).toString();
                if (!tempString.equals("")) {
                    Element r = doc.createElement("r"+String.valueOf(j)); 
                    r.appendChild(doc.createTextNode(tempString)); 
                    obj.appendChild(r);
                }
                j++;    
            } while (j<colCountState);  
        }    
	
        // write the content into xml file
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
                
	StreamResult result = new StreamResult(new File(fileTName));
	transformer.transform(source, result);
        if (showMessages.isSelected()) JOptionPane.showMessageDialog(null, "File saved!");

	} catch (ParserConfigurationException | TransformerException pce) { }
//        datafill();
    }

    // The methods for working with XML
    // =========================================================================
    public static void doSaveRDF(javax.swing.JTabbedPane jtbp) {
        datafill(jtbp);
        String tempString;
        
        fileTName = pathTField+jtbp.getTitleAt(jtbp.getSelectedIndex())+".xml";
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("dictionary files", "xml");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setDialogTitle("Choose a file for relations saving"); 
        fileChooser.setSelectedFile(new File(fileTName));
        int userSelection = fileChooser.showSaveDialog(fileChooser);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileTName = fileChooser.getSelectedFile().getAbsolutePath();
        }
            
	try {
            DocumentBuilderFactory docTFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docTBuilder = docTFactory.newDocumentBuilder();
		Document doc = docTBuilder.newDocument();
		Element rootElement = doc.createElement("triples");
		doc.appendChild(rootElement);
                
                    Element tof = doc.createElement("typeof"); 
                    tof.appendChild(doc.createTextNode(table.getColumnModel().getColumn(0).getHeaderValue().toString())); 
                    rootElement.appendChild(tof);

                    Element expl = doc.createElement("discription"); 
                    expl.appendChild(doc.createTextNode(" subject_name : typeof : object_name")); 
                    rootElement.appendChild(expl);

                
            for(int i=0; i<rowCountState; i++) {    
                Element subj = doc.createElement("subject");
                rootElement.appendChild(subj);
                subj.setAttribute("id", String.valueOf(i));
                
                table.setRowSelectionInterval(i, i);
                tempString = table.getValueAt(i, 0).toString();
                    Element sname = doc.createElement("subject_name"); 
                    sname.appendChild(doc.createTextNode(tempString)); 
                    subj.appendChild(sname);
                
                int j=1; 
                do {
                    tempString = table.getValueAt(i, j).toString();
                    if (!tempString.equals("")) {
                        Element oname = doc.createElement("object_name"); 
                        oname.appendChild(doc.createTextNode(table.getColumnModel().getColumn(j).getHeaderValue().toString())); 
                        subj.appendChild(oname);
                    }
                    j++;    
                } while (j<colCountState);  
            }    
	
        // write the content into xml file
	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	Transformer transformer = transformerFactory.newTransformer();
	DOMSource source = new DOMSource(doc);
                
	StreamResult result = new StreamResult(new File(fileTName));
	transformer.transform(source, result);
        if (showMessages.isSelected()) JOptionPane.showMessageDialog(null, "File saved!");

	} catch (ParserConfigurationException | TransformerException pce) { }
    }

    // The methods for working with XML
    // =========================================================================
    public static void doLoadXML(javax.swing.JTabbedPane jtbp) {

        for(int i=1; i<=colCountState; i++) {
            table.getColumnModel().getColumn(i).setHeaderValue("");
        }    
                    
        for(int i=0; i<=rowCountState; i++)
            for(int j=0; j<=colCountState; j++)
                table.getModel().setValueAt("", i, j);
        
        colCountState = 1;
        rowCountState = 0;
        rowHeaderWidth = 0;
        colHeaderHeight = 0;
        fileTName = pathTField+jtbp.getTitleAt(jtbp.getSelectedIndex())+".xml";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open the presentation's file"); 
        FileFilter filter = new FileNameExtensionFilter("Presentation's files", ".xml");
        //fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setSelectedFile(new File(fileTName));
        fileChooser.setVisible(true);
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileTName = fileChooser.getSelectedFile().getAbsolutePath();
        } else {return;}
        
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
            boolean bproperties = false;
            boolean bproperty = false;
            boolean bpname = false;
            boolean bobject = false;
            boolean boname = false;
            boolean bp = false;
            String sV = "";
            StringBuilder sVs ;
            String[] rHlist = new String[10];

            public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
		//System.out.println("Start Element :" + qName);
                sV = qName;

		if (qName.equalsIgnoreCase("triples")) { 
                    bproperties = true; 
                }
		if (qName.equalsIgnoreCase("object")) {
			bproperty = true;
		}
		if (qName.equalsIgnoreCase("obj-name")) {
			bpname = true;
		}
		if (qName.equalsIgnoreCase("subject")) {
			bobject = true;
		}
		if (qName.equalsIgnoreCase("subj-name")) {
			boname = true;
		}
		if (qName.startsWith("r")) {
			bp = true;
		}
            }

            @Override
            public void endElement(String uri, String localName,String qName) throws SAXException { }
        
            public void saveObject(String strT){
                if (colHeaderHeight < strT.length()){ colHeaderHeight = strT.length();}
                table.setColumnSelectionInterval(colCountState, colCountState);            
                table.getColumnModel().getColumn(colCountState).setHeaderValue(strT);
                colCountState++;
            }

            public void saveSubject(String strT){
                if (rowHeaderWidth < strT.length()){ rowHeaderWidth = strT.length();}
                table.setRowSelectionInterval(rowCountState, rowCountState);
                table.getModel().setValueAt(strT, rowCountState, 0);
                rowCountState++;
            }

            public void savePRelation(int rP){
                table.getModel().isCellEditable(rowCountState-1, rP);
                table.setValueAt("X", rowCountState-1, rP);
            }

            @Override
            public void characters(char ch[], int start, int length) throws SAXException {
		if (bproperties) {
                    bproperties = false;
		}
		if (bproperty) {
                    bproperty = false;
		}
		if (bpname) {
                    bpname = false;
                    saveObject(new String(ch, start, length));
		}
		if (bobject) {
                    bobject = false;
		}
		if (boname) {
                    boname = false;
                    saveSubject(new String(ch, start, length));
		}
		if (bp) {
                    sVs = new StringBuilder(sV);
                    sV = sVs.deleteCharAt(0).toString();
                    savePRelation(Integer.parseInt(sV));
                    bp = false;
		}
            }
        };
        saxParser.parse(fileTName, handler);
      } catch (ParserConfigurationException | SAXException | IOException e) {  }
      
        
        table.getTableHeader().setResizingAllowed(true);

            table.repaint();
            table.updateUI();
            
        datafill(jtbp);
    }

        // The methods for working with RDF
    // =========================================================================
    public static void doLoadRDF(javax.swing.JTabbedPane jtbp) {
        for(int i=1; i<=colCountState; i++) {
            table.getColumnModel().getColumn(i).setHeaderValue("");
        }    
                    
        for(int i=0; i<=rowCountState; i++)
            for(int j=0; j<=colCountState; j++)
                table.getModel().setValueAt("", i, j);
        
        colCountState = 0;
        rowCountState = -1;
        rowHeaderWidth = 0;
        colHeaderHeight = 0;
        fileTName = pathTField+jtbp.getTitleAt(jtbp.getSelectedIndex())+".xml";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open the table's file"); 
        FileFilter filter = new FileNameExtensionFilter("Table's files", ".xml");
        //fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setSelectedFile(new File(fileTName));
        fileChooser.setVisible(true);
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileTName = fileChooser.getSelectedFile().getAbsolutePath();
        } else {return;}
        
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
            boolean btriples = false;
            boolean btypeof = false;
            boolean bdiscription = false;
            boolean bsubject = false;
            boolean bsubject_name = false;
            boolean bobject_name = false;
            String sV = "";
            StringBuilder sVs ;
            String[] rHlist = new String[10];

            public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
		//System.out.println("Start Element :" + qName);
                sV = qName;

		if (qName.equalsIgnoreCase("triples")) { 
                        btriples = true; 
                }
		if (qName.equalsIgnoreCase("typeof")) {
			btypeof = true;
		}
		if (qName.equalsIgnoreCase("discription")) {
			bdiscription = true;
		}
		if (qName.equalsIgnoreCase("subject")) {
			bsubject = true;
		}
		if (qName.equalsIgnoreCase("subject_name")) {
			bsubject_name = true;
		}
		if (qName.equalsIgnoreCase("object_name")) {
			bobject_name = true;
		}
            }

            @Override
            public void endElement(String uri, String localName,String qName) throws SAXException { }
        
            public void saveTypeOf(String strT){
                table.getColumnModel().getColumn(0).setHeaderValue(strT);
            }

            public void saveSubject(String strT){
                rowCountState++;
                if (rowHeaderWidth < strT.length()){ rowHeaderWidth = strT.length();}
                table.setRowSelectionInterval(rowCountState, rowCountState);
                table.getModel().setValueAt(strT, rowCountState, 0);
            }

            public void saveObject(String strT){
                if (colHeaderHeight < strT.length()){ colHeaderHeight = strT.length()+25;}
                
                int result = fObject(strT);
                if (result >0) {
                    table.setColumnSelectionInterval(result, result);            
                    //table.getColumnModel().getColumn(result).setHeaderValue(strT);
                        table.setValueAt("X", rowCountState, result);
                }   
                if (result <0) {
                    colCountState++;
                    table.setColumnSelectionInterval(colCountState, colCountState);            
                    table.getColumnModel().getColumn(colCountState).setHeaderValue(strT);
                        table.setValueAt("X", rowCountState, colCountState);
                }                
            }
            
            @Override
            public void characters(char ch[], int start, int length) throws SAXException {
		if (btriples) {
                    btriples = false;
		}
		if (btypeof) {
                    btypeof = false;
                    saveTypeOf(new String(ch, start, length));
		}
		if (bdiscription) {
                    bdiscription = false;
		}
		if (bsubject) {
                    bsubject = false;
		}
		if (bsubject_name) {
                    bsubject_name = false;
                    saveSubject(new String(ch, start, length));
		}
		if (bobject_name) {
                    bobject_name = false;
                    saveObject(new String(ch, start, length));
		}
            }
        };
        saxParser.parse(fileTName, handler);
      } catch (ParserConfigurationException | SAXException | IOException e) {  }
      
        
        table.getTableHeader().setResizingAllowed(true);
            table.repaint();
            table.updateUI();
            
        datafill(jtbp);
    }
    
}
