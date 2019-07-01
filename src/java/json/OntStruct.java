/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

import java.util.ArrayList;

/**
 *
 * @author Vasyl
 */
public class OntStruct {
    public ArrayList<Property> oProperty = new ArrayList<Property>();
    public ArrayList<Entity> oEntity = new ArrayList<Entity>();
//    public ArrayList<String> hasInherit = new ArrayList<String>();

    public OntStruct() {
//        setEntityName(entn);
    }

    // To add property
    //=======================================
    public void addProperty(Property prp) {
        if(!oProperty.contains(prp)) {oProperty.add(prp);}
    }

    // To get full property
    //=======================================
    public Property getProperty(String prp) {
        for (int i=0; i<oProperty.size(); i++)
            if(oProperty.get(i).getPName().equals(prp)){return oProperty.get(i);}
        return oProperty.get(0);
    }

    // To get full entity
    //=======================================
    public Entity getEntity(Entity ent) {
        for (int i=0; i<oEntity.size(); i++)
            if(oEntity.get(i).getEntityName().equals(ent)){return oEntity.get(i);}
        return oEntity.get(0);
    }

    
}
