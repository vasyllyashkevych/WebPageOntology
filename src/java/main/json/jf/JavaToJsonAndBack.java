/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.json.jf;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 
public class JavaToJsonAndBack {
 
    public static void main(String[] args) {
        Albums albums = new Albums();
        albums.context = "http://schema.org";
        albums.message = "";
        albums.total = "11259";
        albums.total_pages = 2252;
        albums.page = 1;
        albums.limit = "5";
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(albums));
         
    }
    
}
class Albums {
    public String context;
    public String message;
    public String[] errors = new String[]{};
    public String total;
    public int total_pages;
    public int page;
    public String limit;
}    
    
