/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import util.FileManager;
import json.Entity;
import json.Property;
import json.OntStruct;


public class FromJsonFiles {
    static String fn = "";
	
    public FromJsonFiles(){
//        parseFile();
        getJsonType("PublicationEvent");
    }
	
    // To find property into list
    //===========================================
    public Property findProperty(String pStr){
        OntStruct os = new OntStruct();
        for(int i=0; i<os.oProperty.size(); i++){
            if(os.oProperty.get(i).getPName().equals(pStr)) {return os.oProperty.get(i);}
        }
        return os.oProperty.get(0);
    } 
	
    // To find entity into list
    //===========================================
    public Entity findEntity(String eStr){
        OntStruct os = new OntStruct();
        for(int i=0; i<os.oEntity.size(); i++){
            if(os.oEntity.get(i).getEntityName().equals(eStr)) {return os.oEntity.get(i);}
        }
        return os.oEntity.get(0);
    } 
	
    // To parse JSON files with data
    //===========================================
    public void parseFile() {
        JSONParser parser = new JSONParser();

        try {
            fn = FileManager.main("To choose the JSON file with data!");
            Object obj = parser.parse(new FileReader(fn));
//            fn = fn.substring(fn.lastIndexOf("/")+1, fn.length());
           
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject jsonObject = (JSONObject) obj;

            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject jObject = (JSONObject) iterator.next();

               	String from_id = jObject.get("from_id").toString();
           	String to_id = jObject.get("to_id").toString();
           	String type = jObject.get("type").toString();

//           				int posFrom = findPerson(from_id); 
//           				int posTo = findPerson(to_id); 

//           			String name = (String) jsonObject.get("Name");
//           			String author = (String) jsonObject.get("Author");
//           			JSONArray companyList = (JSONArray) jsonObject.get("Company List");
//
//           			System.out.println("Name: " + name);
//           			System.out.println("Author: " + author);
//           			System.out.println("\nCompany List:");
//           			Iterator<String> iterator = companyList.iterator();
//           			while (iterator.hasNext()) {
//           				System.out.println(iterator.next());
//           			}
           		
           }

       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   
    // To parse JSON files with data
    //===========================================
    public void getJsonType(String jType) {
        String[] typeList = new String[]{"Of_Action", "Of_CreativeWork", "Of_Event", "Of_Intangible", "Of_MedEntity", "Of_OrgPerson", "Of_PlaceProduct"};
        String type ="";
        JSONParser parser = new JSONParser();
        try {
            fn = FileManager.main("To choose the JSON file with data!");
            Object obj = parser.parse(new FileReader(fn));
                   
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jArray = (JSONArray) jsonObject.get("Entity");

            JSONArray temp = new JSONArray();
            Iterator<JSONObject> iterator = jArray.iterator();
            while (iterator.hasNext()) {
                JSONObject objType = (JSONObject) iterator.next();
                JSONArray jTArray = (JSONArray) objType.get(jType);
                if(jTArray!=null) {temp = jTArray;}
            }    
                System.out.println(temp);
            
           
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
    
    
   @SuppressWarnings("unchecked")
   public static void main(String[] args) {
	   new FromJsonFiles();
   }
}