/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.json.jf;
import java.io.IOException;
import java.io.StringWriter;
import org.json.simple.JSONObject;

class JsonEncode2 {

   public static void main(String[] args) throws IOException{
	
      JSONObject obj = new JSONObject();

      obj.put("name", new String("Hello"));
      obj.put("num",new Integer(100));
      obj.put("balance",new Double(1000.21));
      obj.put("is_vip",new Boolean(true));

      StringWriter out = new StringWriter();
      obj.writeJSONString(out);
      
      String jsonText = out.toString();
      System.out.print(jsonText);
   }
}