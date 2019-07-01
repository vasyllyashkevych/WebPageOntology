/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import json.ParseTypeTree;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import static json.ParseTypeTree.typeArray;
import util.FileManager;

public class ToJsonGraph {
    
//    public static String constFirst = "&lt;script type=\"application/ld+json\"&gt;";
//    public static String constEnd = "&lt;"+"/script&gt;";
    
    public static String constFirst = "<script type=\"application/ld+json\">";
    public static String constEnd = "</script>";
    public static String[][] typeArrayMass = typeArray;
    public String fName = "";
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root;
    
    public ToJsonGraph(){
//        new ParseTypeTree();
        wrapperJson();
//        writerJson();
        
    }
            
    // To parse JSON-structure from file
    //===========================================
    public void wrapperJson(){
        //fName = "D:\\Freelancer\\MPage\\ODictionary\\JSON\\User.json"
	try {
            JsonNode root = mapper.readTree(new File(FileManager.main("Open JSON-file to wrapper")));
            String resultOriginal = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
//            System.out.println("Before Update " + resultOriginal);
            String resultUpdate = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
//            System.out.println("After Update " + resultUpdate);
            System.out.println(resultUpdate);
        }
        catch (JsonGenerationException e) { e.printStackTrace(); } 
        catch (JsonMappingException e) { e.printStackTrace();	}
        catch (IOException e) { e.printStackTrace();	}
    }
    
    public void convertXMLtoJson(){
    
    
    }
    
    // To generate JSON-structure from XML shcema
    //===========================================
    public void writerJson(){
	
        try {
            // 1. Update id to 1000
            ((ObjectNode) root).put("agent", "newAgent");

            // 2. If middle name is empty , update to M
            JsonNode nameNode = root.path("agent");
            ((ObjectNode) nameNode).put("@type", "Organization");
            if ("".equals(nameNode.path("name").asText())) {
                ((ObjectNode) nameNode).put("name", "M");
            }

            // 3. Create a new field in nameNode
            ((ObjectNode) nameNode).put("object", "mkyong");
            ((ObjectNode) nameNode).put("nickname", "mkyong");

            // 4. Remove last field in nameNode
            ((ObjectNode) nameNode).remove("last");

            // 5. Create a new ObjectNode and add to root
            ObjectNode positionNode = mapper.createObjectNode();
            positionNode.put("name", "Developer");
            positionNode.put("years", 10);
            ((ObjectNode) root).set("position", positionNode);

            // 6. Create a new ArrayNode and add to root
            ArrayNode gamesNode = mapper.createArrayNode();

            ObjectNode game1 = mapper.createObjectNode();
            game1.put("name", "Fall Out 4");
            game1.put("price", 49.9);

            ObjectNode game2 = mapper.createObjectNode();
            game2.put("name", "Dark Soul 3");
            game2.put("price", 59.9);

            gamesNode.add(game1);
            gamesNode.add(game2);
            ((ObjectNode) root).set("games", gamesNode);

            // 7. Append a new Node to ArrayNode
            ObjectNode email = mapper.createObjectNode();
            email.put("type", "email");
            email.put("ref", "abc@mkyong.com");

            JsonNode contactNode = root.path("contact");
            ((ArrayNode) contactNode).add(email);

            String resultUpdate = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
            System.out.println("After Update " + resultUpdate);
        }
        catch (JsonGenerationException e) { e.printStackTrace(); } 
        catch (JsonMappingException e) { e.printStackTrace();	}
        catch (IOException e) { e.printStackTrace();	}
    
    }

	public static void main(String[] args) {
            
//            System.out.println(constFirst);
//            System.out.println(constEnd);
            
            new ToJsonGraph();
            

	}

}


/*

			// 1. Update id to 1000
			((ObjectNode) root).put("id", 1000L);

			// 2. If middle name is empty , update to M
			JsonNode nameNode = root.path("name");
			if ("".equals(nameNode.path("middle").asText())) {
				((ObjectNode) nameNode).put("middle", "M");
			}

			// 3. Create a new field in nameNode
			((ObjectNode) nameNode).put("nickname", "mkyong");

			// 4. Remove last field in nameNode
			((ObjectNode) nameNode).remove("last");

			// 5. Create a new ObjectNode and add to root
			ObjectNode positionNode = mapper.createObjectNode();
			positionNode.put("name", "Developer");
			positionNode.put("years", 10);
			((ObjectNode) root).set("position", positionNode);

			// 6. Create a new ArrayNode and add to root
			ArrayNode gamesNode = mapper.createArrayNode();

			ObjectNode game1 = mapper.createObjectNode();
			game1.put("name", "Fall Out 4");
			game1.put("price", 49.9);

			ObjectNode game2 = mapper.createObjectNode();
			game2.put("name", "Dark Soul 3");
			game2.put("price", 59.9);

			gamesNode.add(game1);
			gamesNode.add(game2);
			((ObjectNode) root).set("games", gamesNode);

			// 7. Append a new Node to ArrayNode
			ObjectNode email = mapper.createObjectNode();
			email.put("type", "email");
			email.put("ref", "abc@mkyong.com");

			JsonNode contactNode = root.path("contact");
			((ArrayNode) contactNode).add(email);

*/