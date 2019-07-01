/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import static main.ToolBar.pathDField;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Vasyl
 */
public class ParseTypeTree {
    public static int quant = 0;
    public static String[][] typeArray = new String[50000][3];
    public String fileTypeTree;
    public ArrayList<ArrayList<String>> type = new ArrayList<ArrayList<String>>();
    public ArrayList<ArrayList<String>> property = new ArrayList<ArrayList<String>>();
    public ArrayList<String> data = new ArrayList<String>();
//    public ArrayList<String> type = new ArrayList<String>();
//    public ArrayList<Str ing> typeAll = new ArrayList<String>();
//    public ArrayList<ArrayList<String>> typeAll = new ArrayList<ArrayList<String>>();
    public int typeCounter = 0;
    public int propertyCounter = 0;
    public int dataCounter = 0;
    public int counter = 0;
    
    public ParseTypeTree(){
        doParseTypeTree();
    }

    public void doParseTypeTree() {
        for(int i=0; i<typeArray.length; i++)
            for(int j=0; j<typeArray[0].length; j++)
                    typeArray[i][j] = null;
    
        
        fileTypeTree = pathDField+"*.xml";

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open the TypeTree file"); 
//        FileFilter filter = new FileNameExtensionFilter("Dictionary files", ".xml");
//        fileChooser.setAcceptAllFileFilterUsed(true);
//        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setSelectedFile(new File(fileTypeTree));
        fileChooser.setVisible(true);
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileTypeTree = fileChooser.getSelectedFile().getAbsolutePath();
        } 
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

            boolean btypes = false;
            boolean bistype = false;
            boolean btype = false;
            boolean bisproperty = false;
            boolean bpname = false;
            boolean bdtype = false;

            @Override
            public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("types")) { btypes = true; }

		if (qName.equalsIgnoreCase("is-type")) { bistype = true; }

		if (qName.equalsIgnoreCase("type")) { btype = true; }

		if (qName.equalsIgnoreCase("is-property")) { bisproperty = true; }

		if (qName.equalsIgnoreCase("pname")) { bpname = true; }

		if (qName.startsWith("dtype")) { bdtype = true; }
            }

            @Override
            public void endElement(String uri, String localName,String qName) throws SAXException {
		//System.out.println("End Element :" + qName);
            }

            @Override
            public void characters(char ch[], int start, int length) throws SAXException {

		if (btypes) { btypes = false;	}

		if (bistype) {
//			System.out.println("id : " + new String(ch, start, length));
			bistype = false;
		}

		if (btype) {
                    typeArray[counter][0] = new String(ch, start, length);
//                    propertyCounter = 0;    
//                    dataCounter = 0;
                    btype = false;
		}

		if (bisproperty) { bisproperty = false; }

		if (bpname) {
//                    if(counter>=14000) {return;}
                    typeArray[counter][1] = new String(ch, start, length);
                    bpname = false;
		}

		if (bdtype) {
                    String stt = new String(ch, start, length);
                    typeArray[counter++][2] = (String) stt.subSequence(1, stt.length()-1);
                    bdtype = false;
		}
            }
        };
        JOptionPane.showMessageDialog(null, "The hierarchy is loaded !");

        saxParser.parse(fileTypeTree, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {}

//                System.out.println(data);
//                System.out.println(property);
//                System.out.println(type);
                
        for(int i=0; i<typeArray.length; i++) {
            for(int j=0; j<typeArray[0].length; j++) {
                    if (typeArray[i][2]==null) {quant = i; return;}
                    System.out.print("\t"+typeArray[i][j]);
            }
            System.out.println();
        }

//        return typeArray;
    }
     
    
    
    
}
