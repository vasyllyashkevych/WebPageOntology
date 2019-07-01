/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.json;

//import java.io.File;
//import java.io.IOException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import util.FileManager;


/**
 * Need to make appropriate changes into JSONObject.java
 *
 * @author Vasyl
 */
public class JSONObject {

    private LinkedHashMap<Object, Object> map;

    public JSONObject() throws JSONException, IOException {
        this.map = new LinkedHashMap<>();
        load();
    }
 
 
    public JSONObject(Map<String, Object> map) {
        this.map = new LinkedHashMap<>();
        if (map != null) {
            Iterator<Entry<String, Object>> i = map.entrySet().iterator();
            while (i.hasNext()) {
                Entry<String, Object> entry = i.next();
                Object value = entry.getValue();
                if (value != null) {
//                this.map.put(entry.getKey(), wrap(value));
                }
            }
        }
    }
    
    public void load() throws IOException, JSONException{
        String xml = FileUtils.readFileToString(new File(FileManager.main("Open xml file!")));
        System.out.println(org.json.XML.toJSONObject(xml).toString());
    }

    public void main(String[] args){
        try {
            new JSONObject();
        } catch (JSONException ex) {
            Logger.getLogger(JSONObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JSONObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}