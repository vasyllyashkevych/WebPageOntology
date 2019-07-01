package json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import static main.DictionaryThing.jTableThingPlenty;
import static main.ToolBar.hSchema;
import static main.ToolBar.ttbbll;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import util.FileManager;



public class FromDatabase {
    
    public static ArrayList<String> loadedList = new ArrayList<String>();
    public static ArrayList<String> typesList = new ArrayList<String>();
    public static String constStr01 = "<script type=\"application/ld+json\">\n";
    public static String constStr02 = "\"@context\": \"http://schema.org\",\n";
    public static String constStr99 = "\n</script>";
    
    ObjectMapper mapper = new ObjectMapper();
    JsonNode root;
    public static String resultUpdate = null;        

	public static int q_neurons = 0;
	public static int q_groups =0;
	public static int q_trainperson =0;
	public static int qDtrainperson =0;
	static String fn = "";
	public static ArrayList<Integer> countInGroup = new ArrayList<Integer>();
	public static ArrayList<ArrayList<Integer>> trainProfile = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>> trainDProfile = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<ArrayList<Integer>> testProfile = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<Integer> curProfile = new ArrayList<Integer>();
	public static ArrayList<Integer> personalChoices = new ArrayList<Integer>();

	
	public FromDatabase(){
//		parseFile();
//                getListProperty("Action");
//		ToJsonFile.main(null);
//                parseFile("TieAction");
                getScripts();
	}
	
	// To find person into list
	//===========================================
 	public int findPerson(String fStr){
//		for (int i=0; i<persons.size(); i++)
//			if (persons.get(i).uid.equals(fStr)) {
//				return i;
//			}	
		return -1;
	} 
	
    // To pars JSON files with data
    //===========================================
    public ArrayList parseFile(String tp) {
        ArrayList<String> constTypes = new ArrayList<String>();
//        for (int i=0; i<hSchema.length; i++)    constTypes.add(hSchema[i]);
        String inPath = "/";    
        
        boolean readFile = false;
        JSONParser parser = new JSONParser();
        try {
            fn = FileManager.main("To choose the JSON file with data!");
            if(fn.indexOf(inPath)==-1){inPath = "\\";}
            Object obj = parser.parse(new FileReader(fn));
            fn = fn.substring(fn.lastIndexOf(inPath)+1, fn.length());
           
            switch (fn) {
                // To read about entities and its properties
                case "DataFormat.json" : {
                    System.out.println("DataFormat.json ");
                    readFile = true;
//           			cp = new ChosenPerson();
                    JSONObject jsonObject = (JSONObject) obj;
//           			cp.pid = Integer.parseInt(jsonObject.get("id").toString());
                
                    JSONArray entList = (JSONArray) jsonObject.get("@Type");
                    Iterator entIterator = entList.iterator();
                    while (entIterator.hasNext() & readFile) {
                        JSONObject fObject = (JSONObject) entIterator.next();
                        JSONArray prpList = (JSONArray) fObject.get(tp);
                        if (prpList==null) {continue;}
                        readFile = false;
                        for(int i=0; i<prpList.size(); i++)
                            constTypes.add(prpList.get(i).toString());
                        System.out.println("Parsed 't"+tp +" is\t"+constTypes);
                        
                        
                        
//                        String entName = (String) entIterator.next().toString();
//                        if(!entName.contains(tp)) {continue;}
//                        System.out.println("Parsed 't"+tp +" is\t"+entName);
                    }
           			
                    
           			
//           			for (int i=0; i<persons.size(); i++) {
//           				System.out.println("Persons "+persons.get(i).uid.toString());
//           				System.out.println("Persons back_likes: "+persons.get(i).back_likes.toString());
//           				System.out.println("Persons back_dislikes: "+persons.get(i).back_dislikes.toString());
//           			}
        			 System.out.println("End training.json ");

           		}
           		case "DataFormat02.json" : {
         			 System.out.println("Start choices.json ");
            			readFile = true;
           			
           			JSONArray tstArray = new JSONArray();
           			Iterator<JSONObject> tp_iterator = tstArray.iterator();
           			while (tp_iterator.hasNext()) {
               			JSONObject jpObject = (JSONObject) tp_iterator.next();

               			String from_id = jpObject.get("id").toString();
               			 System.out.println("from_id "+from_id);
           				String to_id = jpObject.get("to_id").toString();
              			 System.out.println("to_id "+to_id);
           				
               			JSONArray profArray = new JSONArray();
               			Iterator<JSONObject> profIterator = profArray.iterator();
               			while (profIterator.hasNext()) {
                   			JSONObject profObject = (JSONObject) profIterator.next();
                   			String pf = jpObject.get("profile").toString();
                   			System.out.println("pf "+pf);
               			}
               			
           				String preference = jpObject.get("preference").toString();
           				String result = jpObject.get("result").toString();

           			}         
        			 System.out.println("End choices.json ");
           			
//           			for (int i=0; i<persons.size(); i++) {
//           				System.out.println("Persons "+persons.get(i).uid.toString());
//           				System.out.println("Persons back_likes: "+persons.get(i).back_likes.toString());
//           				System.out.println("Persons back_dislikes: "+persons.get(i).back_dislikes.toString());
//           			}
        	   
           		}
           		case "DataFormat03.json" :{
//           			System.out.println("Start test.json ");
//           			JSONObject jsonObject = (JSONObject) obj;
//           			JSONArray testList = (JSONArray) jsonObject.get("test");
//           			Iterator<JSONObject> iteratorTest = testList.iterator();
//           			while (iteratorTest.hasNext()) {
//           				JSONObject testID = (JSONObject) iteratorTest.next();
////           				testID.get("to_id");
////           				System.out.println(testID);
//                   			JSONArray testProfList = (JSONArray) testID.get("profile");
////           				System.out.println(testProfList);
//                   			
//                   			Iterator<Long> iteratorTestProf = testProfList.iterator();
//                   			ArrayList<Integer> p = new ArrayList<Integer>();
//                   			while (iteratorTestProf.hasNext()) {
//                   				p.add((int)(long) iteratorTestProf.next());
//                   			}
//               				System.out.println(p.toString());
//                   			testProfile.add(p);
//           			}
//           			
//           			System.out.println("testProfile2:\t"+testProfile.get(2).toString());
//           			System.out.println("testProfile3:\t"+testProfile.get(3).toString());
//           			System.out.println("testProfile4:\t"+testProfile.get(4).toString());
//           			System.out.println("end test.json ");
//           			return constTypes;
           		}
           		
           		default  : {
//                                    System.out.println("Unknown File.json ! File is not parsed !");
//                                JsonNode root = mapper.readTree(new File(FileManager.main("Open JSON-file to wrapper")));
//                                String resultOriginal = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
//                                System.out.println("The content of unknown scheme file\n\r" + resultOriginal);
                                }	
           }
           
// 			for (int i=0; i<trainProfile.size(); i++) {
// 	  				System.out.print("\n"+trainProfile.get(i).toString());
//	  				System.out.println();
//  			}	
 			
 			for (int i=0; i<countInGroup.size(); i++) {
 				q_neurons += countInGroup.get(i);
			}	
//			System.out.println("\n"+"Persons choices: "+personalChoices.toString());
//			System.out.println("\n"+"countInGroup: "+countInGroup.toString());
			
 			q_groups = countInGroup.size();
	  		q_trainperson = trainProfile.size();
	  		qDtrainperson = trainDProfile.size();

//	  		System.out.println("q_groups: "+q_groups);
//			System.out.println("q_trainperson: "+q_trainperson);
//			System.out.println("qDtrainperson: "+qDtrainperson);
//			System.out.println("q_neurons: "+q_neurons);

       } catch (Exception e) {
           e.printStackTrace();
       }
        return constTypes;
   }
   
    // To search the order of type of relations for Thing
    //==========================================================================
    private static int getTypePosition(String strPos){
        int pos = -1;
        for(int i=0; i<jTableThingPlenty.getRowCount(); i++)
            if(jTableThingPlenty.getModel().getValueAt(i, 1).toString().equals(strPos)) {pos = i;}
        if (pos==-1) {JOptionPane.showMessageDialog(null, "Incorrect name of relation !?");}
        return pos;
    }
 	
    // To get a list of properties for defined types
    //===========================================
    public static ArrayList getProperty(String tStr){
        ArrayList<String> prpStr = new ArrayList<String>(); 
        int tr = getTypePosition("isTypeProperty");
        for(int i=0; i<ttbbll.get(tr).table.getRowCount(); i++){
            if(ttbbll.get(tr).table.getValueAt(i, 0).equals(tStr)) {
                for(int j=0; j<ttbbll.get(tr).table.getColumnCount(); j++){
                    if (ttbbll.get(tr).table.getModel().getValueAt(i, j).equals("X")){
                        prpStr.add(ttbbll.get(tr).table.getColumnModel().getColumn(j).getHeaderValue().toString());
                    }
                }
            }    
        }
        return prpStr;
    }
 	
    // To get a list of inherited properties for defined types
    //===========================================
    public static ArrayList getListInheritance(String iStr){
        ArrayList<String> entStr = new ArrayList<String>(); 
        int tr = getTypePosition("isInheritanceOf");
        for(int i=0; i<ttbbll.get(tr).table.getRowCount(); i++){
            if(ttbbll.get(tr).table.getValueAt(i, 0).equals(iStr)) {
                for(int j=0; j<ttbbll.get(tr).table.getColumnCount(); j++){
                    if (ttbbll.get(tr).table.getModel().getValueAt(i, j).equals("X")){
                        entStr.add(ttbbll.get(tr).table.getColumnModel().getColumn(j).getHeaderValue().toString());
                    }
                }
            }    
        }
        return entStr;
    }
 	
    // To get full list of properties
    //===========================================
    public static ArrayList getListProperty(String ename){
        ArrayList<String> prpList = new ArrayList<String>();
        ArrayList<String> tList = new ArrayList<String>();
        prpList = getProperty(ename);
        
//        boolean eXit = true;
//        while (eXit) {
            tList = getListInheritance(ename);
//            if(tList.size()==0) {
//                eXit = false; 
//                continue;
//            }
            for(int j=0; j<tList.size(); j++) {
                String tStr = tList.get(j).toString();
                ArrayList<String> temp = new ArrayList<String>();
                temp = getProperty(tStr);
                for (int k=0; k<temp.size(); k++)    
                    prpList.add(temp.get(k).toString());
            }    
            
            for(int j=0; j<prpList.size(); j++) {
                System.out.println(prpList.get(j).toString());
            }
//        }
        
        
        return prpList;
    }
    
    public void getScripts(){
        try  
        {  


            File file = new File (FileManager.main("Get data file"));  
            InputStream inputStream = new FileInputStream(file);  
            StringBuilder builder =  new StringBuilder();  
            int ptr = 0;  
            while ((ptr = inputStream.read()) != -1 )  
            {  
                builder.append((char) ptr); 
                System.out.println(ptr);
            }
            
        }   catch(IOException ex)   {}
            catch(Exception e)  {}
        
        loadedList = parseFile("TieAction");
        typesList = parseFile("TieAction");

        try {
            ObjectNode root = mapper.createObjectNode();
        
            for(int i=0; i<loadedList.size(); i++)
                if (typesList.contains(loadedList.get(i).toString()) ){
                    
                    ((ObjectNode) root).put(loadedList.get(i), loadedList.get(i));
                
                }

//            // 2. If middle name is empty , update to M
//            JsonNode nameNode = root.path("agent");
//            ((ObjectNode) nameNode).put("@type", "Organization");
//            if ("".equals(nameNode.path("name").asText())) {
//                ((ObjectNode) nameNode).put("name", "M");
//            }
//
//            // 3. Create a new field in nameNode
//            ((ObjectNode) nameNode).put("object", "mkyong");
//            ((ObjectNode) nameNode).put("nickname", "mkyong");
//
//            // 4. Remove last field in nameNode
//            ((ObjectNode) nameNode).remove("last");
//
//            // 5. Create a new ObjectNode and add to root
//            ObjectNode positionNode = mapper.createObjectNode();
//            positionNode.put("name", "Developer");
//            positionNode.put("years", 10);
//            ((ObjectNode) root).set("position", positionNode);
//
//            // 6. Create a new ArrayNode and add to root
//            ArrayNode gamesNode = mapper.createArrayNode();
//
//            ObjectNode game1 = mapper.createObjectNode();
//            game1.put("name", "Fall Out 4");
//            game1.put("price", 49.9);
//
//            ObjectNode game2 = mapper.createObjectNode();
//            game2.put("name", "Dark Soul 3");
//            game2.put("price", 59.9);
//
//            gamesNode.add(game1);
//            gamesNode.add(game2);
//            ((ObjectNode) root).set("games", gamesNode);
//
//            // 7. Append a new Node to ArrayNode
//            ObjectNode email = mapper.createObjectNode();
//            email.put("type", "email");
//            email.put("ref", "abc@mkyong.com");
//
//            JsonNode contactNode = root.path("contact");
//            ((ArrayNode) contactNode).add(email);

            String resultUpdate = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
            System.out.println("Structure\n " + resultUpdate);
        }
        catch (JsonGenerationException e) { e.printStackTrace(); } 
        catch (JsonMappingException e) { e.printStackTrace();	}
        catch (IOException e) { e.printStackTrace();	}

        try {
		
            JSONObject obj = new JSONObject();
            for(int i=0; i<loadedList.size(); i++) {
                String value = loadedList.get(i).toString();
                if(typesList.contains(value)) {
                    String param1 = value.substring(0, value.indexOf(","));
                    String param2 = value.substring(value.indexOf(",")+1, value.lastIndexOf(","));
                    String param3 = value.substring(value.lastIndexOf(",")+1, value.length()-1);
                    JSONArray lists = new JSONArray();
                        if (param1 != null) lists.add(param1);
                        if (param2 != null) lists.add(param2);
                        if (param3 != null) lists.add(param3);
                    obj.put(value, lists);
                }
            }    
		FileWriter file = new FileWriter(FileManager.main("Save script!"));
		file.write(constStr01); //
		file.write(constStr02); //
		file.write(obj.toJSONString()); //
		file.write(constStr99); //
		file.flush();
		file.close();

	} catch (IOException e) {
		e.printStackTrace();
	}
//        writeFile();
    }
    
	// Save to file
	// ==============================================
	public static void writeFile() {
		int i = 0;
		try {
			File file = new File(FileManager.main("To check file to save generator results!"));
			if (file.exists()) {file.delete();}
			if (file.createNewFile()) {
				BufferedWriter out = new BufferedWriter(new FileWriter(FileManager.main("To choose file to save generator results!")));
				out.write(constStr01);
				out.write(constStr02);
				out.write(resultUpdate.toString());
				out.write(constStr99);
				out.close();
				JOptionPane.showMessageDialog(null, "File is saved successfully!");
			} else
				JOptionPane.showMessageDialog(null, "File cannot be saved !");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
    
    
    
    //===========================================
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        new FromDatabase();
    }
}

/*

           			JSONObject jsonObject = (JSONObject) obj;
           			cp.pid = Integer.parseInt(jsonObject.get("id").toString());
                
           			JSONArray countList = (JSONArray) jsonObject.get("count");
           			Iterator iterator = countList.iterator();
           			while (iterator.hasNext()) {
           				countInGroup.add(Integer.parseInt(iterator.next().toString()));
//           				System.out.println(str);
           			}
           			
           			JSONArray profPList = (JSONArray) jsonObject.get("choices");
           			Iterator iteratorP = profPList.iterator();
           			while (iteratorP.hasNext()) {
           				personalChoices.add(Integer.parseInt(iteratorP.next().toString()));
           			}
           			
           			JSONArray likesList = (JSONArray) jsonObject.get("likes");
           			Iterator iteratorLikes = likesList.iterator();
           			while (iteratorLikes.hasNext()) {
           				JSONObject trainID = (JSONObject) iteratorLikes.next();
           				trainID.get("to_id");
                   			JSONArray likesProfList = (JSONArray) trainID.get("profile");
                   			Iterator iteratorProfLikes = likesProfList.iterator();
                   			curProfile.clear();
                   			while (iteratorProfLikes.hasNext()) {
                   				curProfile.add(Integer.parseInt(iteratorProfLikes.next().toString()));
                   			}
           			trainProfile.add(curProfile);
           			}
           			
           			curProfile.clear();
           			JSONArray dlikesList = (JSONArray) jsonObject.get("dislikes");
           			Iterator iteratordLikes = dlikesList.iterator();
           			while (iteratordLikes.hasNext()) {
           				JSONObject trainID = (JSONObject) iteratordLikes.next();
           				trainID.get("to_id");
                   			JSONArray dlikesProfList = (JSONArray) trainID.get("profile");
                   			Iterator iteratordProfLikes = dlikesProfList.iterator();
                   			while (iteratordProfLikes.hasNext()) {
                   				curProfile.add(Integer.parseInt(iteratordProfLikes.next().toString()));
                   			}
           			trainDProfile.add(curProfile);
           			}
           			
           			

*/