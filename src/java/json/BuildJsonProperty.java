/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;
 
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import static json.ParseTypeTree.quant;
import static json.ParseTypeTree.typeArray;
import static main.ToolBar.hSchema;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import util.FileManager;
 
public class BuildJsonProperty {
    String[] tempProperty = new String[]{"areaServed", "ratingCount"};
 
    public BuildJsonProperty() throws IOException {
//            getData();
            getGeneratorData(tempProperty);
    }

    // To create JSONArray from data
    //===========================================
    public void getData() throws IOException {
        new ParseTypeTree();
        
	JSONObject obj = new JSONObject();
	JSONObject objE = new JSONObject();
	JSONObject objP = new JSONObject();
            JSONArray eList = new JSONArray(); 
            JSONArray pList = new JSONArray(); 
            JSONArray dList = new JSONArray(); 
        String ent_name = "";    
        String prp_name = "";

        int i = 0;
        boolean stPrp = false;
        boolean stEnt = false;
        while(typeArray[i][2]!=null){
            if ((typeArray[i][0]!=null) & (typeArray[i][1]!=null) & (typeArray[i][2]!=null)) {
                if(stEnt){
                    objE = new JSONObject();
                    objE.put(ent_name, pList);
                    eList.add(objE);
                    obj.put("Entity", eList);
                    stEnt = false;
                }
                stEnt = true;
                ent_name=typeArray[i][0];
                prp_name=typeArray[i][1];
                pList = new JSONArray();
                dList = new JSONArray();
                dList.add(typeArray[i++][2]);
                objP = new JSONObject();
                stPrp = true;
            }    
            while ((typeArray[i][0]==null) & (typeArray[i][1]==null) & (typeArray[i][2]!=null)) {
                while ((typeArray[i][1]==null) & (typeArray[i][2]!=null)) {
                    dList.add(typeArray[i++][2]);
                }
                objP.put(prp_name, dList);
                pList.add(objP);
            }    

            while ((typeArray[i][0]==null) & (typeArray[i][1]!=null) & (typeArray[i][2]!=null)) {
                if (stPrp) {
                    objP.put(prp_name, dList);
                    pList.add(objP);
                    stPrp = false;
                }
                prp_name = typeArray[i][1];
                dList = new JSONArray();
                dList.add(typeArray[i++][2]);
                while ((typeArray[i][1]==null) & (typeArray[i][2]!=null)) {
                    dList.add(typeArray[i++][2]);
                }
                objP = new JSONObject();
                objP.put(prp_name, dList);
                pList.add(objP);
                dList = new JSONArray();
            }    
        }     
            
	try {
		FileWriter file = new FileWriter(FileManager.main("To choose JSON file for writing!"));
		file.write(obj.toJSONString());
		file.flush();
		file.close();
	} catch (IOException e) {
		e.printStackTrace();
	}

//	System.out.print(obj);
    }

    // To create JSONArray from data
    //===========================================
    public void getPtype() throws IOException {
        new ParseTypeTree();
        
	JSONObject obj = new JSONObject();
	JSONObject objE = new JSONObject();
	JSONObject objP = new JSONObject();
            JSONArray eList = new JSONArray(); 
            JSONArray pList = new JSONArray(); 
            JSONArray dList = new JSONArray(); 
        String ent_name = "";    
        String prp_name = "";

        int i = 0;
        boolean stPrp = false;
        boolean stEnt = false;
        while(typeArray[i][2]!=null){
            if ((typeArray[i][0]!=null) & (typeArray[i][1]!=null) & (typeArray[i][2]!=null)) {
                if(stEnt){
                    objE = new JSONObject();
                    objE.put(ent_name, pList);
                    eList.add(objE);
                    obj.put("Entity", eList);
                    stEnt = false;
                }
                stEnt = true;
                ent_name=typeArray[i][0];
                prp_name=typeArray[i][1];
                pList = new JSONArray();
                dList = new JSONArray();
                dList.add(typeArray[i++][2]);
                objP = new JSONObject();
                stPrp = true;
            }    
            while ((typeArray[i][0]==null) & (typeArray[i][1]==null) & (typeArray[i][2]!=null)) {
                while ((typeArray[i][1]==null) & (typeArray[i][2]!=null)) {
                    dList.add(typeArray[i++][2]);
                }
                objP.put(prp_name, dList);
                pList.add(objP);
            }    

            while ((typeArray[i][0]==null) & (typeArray[i][1]!=null) & (typeArray[i][2]!=null)) {
                if (stPrp) {
                    objP.put(prp_name, dList);
                    pList.add(objP);
                    stPrp = false;
                }
                prp_name = typeArray[i][1];
                dList = new JSONArray();
                dList.add(typeArray[i++][2]);
                while ((typeArray[i][1]==null) & (typeArray[i][2]!=null)) {
                    dList.add(typeArray[i++][2]);
                }
                objP = new JSONObject();
                objP.put(prp_name, dList);
                pList.add(objP);
                dList = new JSONArray();
            }    
        }     
            
	try {
		FileWriter file = new FileWriter(FileManager.main("To choose JSON file for writing!"));
		file.write(obj.toJSONString());
		file.flush();
		file.close();
	} catch (IOException e) {
		e.printStackTrace();
	}

//	System.out.print(obj);
    }

    // To find the defined properties 
    //===========================================
    private boolean isPresent(String[] sArr, String sName){
        boolean isP = false;
        for (int i=0; i<sArr.length; i++){
            if (sArr[i].equals(sName)) {return true;}
        }
        return isP;
    }
    // To generate JSONArray from data
    //===========================================
    public void getGeneratorData(String[] flds) throws IOException {
        new ParseTypeTree();
        
	JSONObject obj = new JSONObject();
	JSONObject objE = new JSONObject();
	JSONObject objP = new JSONObject();
            JSONArray eList = new JSONArray(); 
            JSONArray pList = new JSONArray(); 
            JSONArray dList = new JSONArray(); 
        String ent_name = "";    
        String prp_name = "";

        int i = 0;
        boolean stPrp = false;
        boolean stEnt = false;
        while(typeArray[i][2]!=null){
            if ((typeArray[i][0]!=null) & (typeArray[i][1]!=null) & (typeArray[i][2]!=null)) {
                if(stEnt){
                    objE = new JSONObject();
                    objE.put(ent_name, pList);
                    eList.add(objE);
                    obj.put("Entity", eList);
                    stEnt = false;
                }
                stEnt = true;
                ent_name=typeArray[i][0];
                prp_name=typeArray[i][1];
                pList = new JSONArray();
                dList = new JSONArray();
                dList.add(typeArray[i++][2]);
                objP = new JSONObject();
                stPrp = true;
            }    
                    boolean b1 = isPresent(flds, prp_name);  if(!b1) {i++; continue;}
            while ((typeArray[i][0]==null) & (typeArray[i][1]==null) & (typeArray[i][2]!=null)) {
                while ((typeArray[i][1]==null) & (typeArray[i][2]!=null)) {
                    dList.add(typeArray[i++][2]);
                }
                objP.put(prp_name, dList);
                pList.add(objP);
            }    

            while ((typeArray[i][0]==null) & (typeArray[i][1]!=null) & (typeArray[i][2]!=null)) {
                if (stPrp) {
                    objP.put(prp_name, dList);
                    pList.add(objP);
                    stPrp = false;
                }
                prp_name = typeArray[i][1];
                    boolean b = isPresent(flds, prp_name);  if(!b) {i++; continue;}
                dList = new JSONArray();
                dList.add(typeArray[i++][2]);
                while ((typeArray[i][1]==null) & (typeArray[i][2]!=null)) {
                    dList.add(typeArray[i++][2]);
                }
                objP = new JSONObject();
                objP.put(prp_name, dList);
                pList.add(objP);
                dList = new JSONArray();
            }    
        }     
            
	try {
		FileWriter file = new FileWriter(FileManager.main("To choose JSON file for writing!"));
		file.write(obj.toJSONString());
		file.flush();
		file.close();
	} catch (IOException e) {
		e.printStackTrace();
	}

//	System.out.print(obj);
    }

    public static void getJsonSchemaGenerator(){
        JSONObject obj = new JSONObject();
        JSONArray eList = new JSONArray(); 
        ArrayList<String> tar = new ArrayList<String>();
        
        for(int i=0; i<hSchema.length; i++){
            tar = FromDatabase.getListProperty(hSchema[i]);
            JSONArray pList = new JSONArray(); 
            for(int j=0; j<tar.size(); j++) {
                pList.add(tar.get(j));
            }
            JSONObject objE = new JSONObject();
            objE.put(hSchema[i], pList);
            eList.add(objE);
        }    
            obj.put("@Type", eList);

	try {
		FileWriter file = new FileWriter(FileManager.main("To save JSON scheme for generator !"));
		file.write(obj.toJSONString());
		file.flush();
		file.close();
	} catch (IOException e) {
		e.printStackTrace();
	}        
    }
    
    // Main method
    //===========================================
    public static void main(String[] args) throws IOException{
        new BuildJsonProperty();
    }    
}

