/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.json;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.io.IOUtils;
import util.FileManager;

public class XMLtoJsonConverter {
    private URL url = null;
    private InputStream inputStream = null;
    private String flName = "";
    public void getXMLfromJson() {
        flName = FileManager.main("To choose XML file");
        try{
            url = XMLtoJsonConverter.class.getClassLoader().getResource(flName);
            inputStream = url.openStream();
            String xml = IOUtils.toString(inputStream);
            JSON objJson = new XMLSerializer().read(xml);
            System.out.println("JSON data : " + objJson);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
     try {
                if (inputStream != null) {
                    inputStream.close();
                }
                url = null;
            } catch (IOException ex) {}
        }
    }
    public static void main(String[] args) {
        new XMLtoJsonConverter().getXMLfromJson();
    }
}