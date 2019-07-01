/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFromFile {
    
    public JsonFromFile() throws JsonProcessingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(new File("D:\\Freelancer\\MPage\\ODictionary\\JSON\\test.json"));
                String resultOriginal = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
		System.out.println("Before Update " + resultOriginal);
        getJsonData();
    }
    
    public void getJsonData(){
	JSONParser parser = new JSONParser();
        try {
            
            
            Object obj = parser.parse(new FileReader("D:\\Freelancer\\MPage\\ODictionary\\JSON\\test.json"));
            JSONObject jsonObject = (JSONObject) obj;

            String name = (String) jsonObject.get("name");
            System.out.println(name);

            String participant = (String) jsonObject.get("participant");
            System.out.println(participant);

            long age = (Long) jsonObject.get("age");
            System.out.println(age);

		// loop array
            JSONArray msg = (JSONArray) jsonObject.get("messages");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}
    }
    
    public static void main(String[] args) throws IOException {
        new JsonFromFile();
    }
}