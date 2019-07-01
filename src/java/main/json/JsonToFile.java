/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.json;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.FileManager;

public class JsonToFile {
    
    String fn = "D:\\Freelancer\\MPage\\ODictionary\\JSON\\test1.json";

    
    public JsonToFile(){
//        String fn = FileManager.main("Choose file to save");
        toFillData();
    }
    
    // To make JSON's data structure
    //==========================================================================
    public void structData() throws IOException{
        JSONArray list = new JSONArray();
        list.add("foo");
        list.add(new Integer(100));
        list.add(new Double(1000.21));
        list.add(new Boolean(true));
        list.add(null);
        StringWriter out = new StringWriter();
        list.writeJSONString(out);
    }

    // To write JSON's data structure to file
    //==========================================================================
    public void toFillData() {
	JSONObject obj = new JSONObject();

	JSONArray list = new JSONArray();
	list.add("@type");
	list.add("msg 2");
	list.add("msg 3");

	obj.put("agent", list);
        
	obj.put("object", new Integer(100));
	obj.put("@type", "ListenAction");
	obj.put("@context", "http://schema.org");

	try {
		

		FileWriter file = new FileWriter(fn);
		file.write(obj.toJSONString());
		file.flush();
		file.close();

	} catch (IOException e) {
		e.printStackTrace();
	}

	System.out.print(obj);
    }

    //==========================================================================
    public static void main(String[] args) {
        new JsonToFile();
    }

}