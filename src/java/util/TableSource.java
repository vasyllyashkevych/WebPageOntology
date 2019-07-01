/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import javax.swing.AbstractListModel;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import util.VerticalTableHeaderCellRenderer;



//  The class of template for table's presentation of knowledge
//==============================================================================    
public class TableSource extends JScrollPane  {

    int rowCount = 1024;
    int colCount = 1024;
    int RWLength = 50;
    int CHLength = 70;
    int DCW = 20;
    
    
    public static void main(String args[]) {
        TableSource ts = new TableSource();
        //tb.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ts.setVisible(true);
    }    
    
//  The constructor of this class
//==============================================================================    
    public JScrollPane getTableSource(){
         
        //The abstract model of table's presentation
        ListModel lm;
        lm = new AbstractListModel() {
            String[] headers = new String[rowCount];
            

        //  Get max value of string's width
        private int getStringRowWidth(String[] str){
            RWLength = headers[0].length();
            for (int i=0; i<rowCount; i++) {
                if (RWLength<headers[i].length())
                    RWLength = headers[i].length();
                }
            return RWLength;
        }

        @Override
            public int getSize() {
                return headers.length;
            }
            

        @Override
            public Object getElementAt(int index) {
                return headers[index];
            }
        }; 
        
        DefaultTableModel dtm = new DefaultTableModel(lm.getSize(),colCount);
        JTable table = new JTable(dtm);
        table.getTableHeader().setDefaultRenderer(new VerticalTableHeaderCellRenderer());

        for (int i=0; i<colCount; i++)
        table.getColumnModel().getColumn(i).setPreferredWidth(DCW);// .setTotalColumnWidth();
    

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //Row selected functions
     //   table.setColumnSelectionAllowed(false);
     //   table.setRowSelectionAllowed(true);
        //Cells selections
        table.setColumnSelectionAllowed(true);
        table.setRowSelectionAllowed(true);

        
        // Create header rotation
        JList rowHeader = new JList(lm);
       // if (fieldlength)
       
       
       
        rowHeader.setFixedCellWidth(RWLength); 
        rowHeader.setFixedCellHeight(table.getRowHeight());
     
      
        //Set render
        rowHeader.setCellRenderer(new RowRenderer(table));
        
     
        //JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setColumnHeader(new JViewport() {
            @Override public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.height = CHLength;
                return d;
            }
        });
        
        pane.setRowHeaderView(rowHeader);
        return pane;
    }
 
}

// Modified RowRenderer
//==============================================================================
class RowRenderer extends JLabel implements ListCellRenderer {
        
    public RowRenderer(JTable table){
        JTableHeader header = table.getTableHeader();
        setOpaque(true);
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        setHorizontalAlignment(CENTER);
        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setFont(header.getFont());
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText((value==null)?"":value.toString());
        return this;
    }
  
}