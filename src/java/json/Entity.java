/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.util.ArrayList;
import json.Property;

public class Entity {
    public String name;
    public int uid;
    public ArrayList<String> property = new ArrayList<String>();
//    public ArrayList<Property> fProperty = new ArrayList<Property>();
    public ArrayList<String> isInherit = new ArrayList<String>();
    public ArrayList<String> hasInherit = new ArrayList<String>();

    public Entity(String entn) {
        setEntityName(entn);
    }

    // To name the entity
    //=======================================
    public void setEntityName(String ent) {
        name = ent;    
    }

    // To get name of Entity
    //=======================================
    public String getEntityName() {
        return name;    
    }

    // To set order in general list of entities
    //=======================================
    public void setID(int id) {
        uid = id;    
    }

    // To get order in general list of entities
    //=======================================
    public int getID() {
        return uid;    
    }

    // To set isInherit of entities
    //=======================================
    public void setIsInherit(String inent) {
        if (!isInherit.contains(inent)) {isInherit.add(inent);}    
    }

    // To get a list of isInherit
    //=======================================
    public ArrayList getIsInherit() {
        return isInherit;    
    }

    // To set hasInherit of entities
    //=======================================
    public void setHasInherit(String inent) {
        if (!hasInherit.contains(inent)) {hasInherit.add(inent);}    
    }

    // To get a list of isInherit
    //=======================================
    public ArrayList getHasInherit() {
        return hasInherit;    
    }

    // To add property to list 
    //=======================================
    public void addListProperty(String prp) {
        if (!property.contains(prp)) {property.add(prp);}    
    }

    // To get list of properties 
    //=======================================
    public ArrayList getListProperty() {
        return property;
    }

    // To get list of full property 
    //=======================================
    public ArrayList getFullProperty(String fprp) {
        Property pp = new Property();
//        for(int i=0; i<Property.class.getName())
        
        
        return property;
    }



    // The main 
    //===========================================
//    public static void main(String[] args) {
//        Entity ent = new Entity("111");
//	System.out.println("Entity :" + ent.name);
//    }

}
