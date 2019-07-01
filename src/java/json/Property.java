package json;

import java.util.ArrayList;

/**
 * @author Vasyl
 */

// The structure of properties
public class Property {
    
    public String pname = null;
    public boolean state = false;
    public boolean multiAllowed = false;
    public ArrayList<String> types = new ArrayList<String>();
    public ArrayList<String> ePresent = new ArrayList<String>();
    public ArrayList<String> baseLocation = new ArrayList<String>();

    Property() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        getPName();
    }

    // To name the property
    //=======================================
    public void setPName(String pn) {
        pname = pn;    
    }

    // To get name of property
    //=======================================
    public String getPName() {
        return pname;    
    }

    // To mark the property state
    //=======================================
    public void setType(boolean tp) {
        state = tp;    
    }

    // To get the mark of property state
    //=======================================
    public boolean isState() {
        return state;    
    }

    // To set info about multiallowed datafields
    //=======================================
    public void setMultiAllowed(boolean tf) {
        multiAllowed = tf;    
    }

    // To get info about multiallowed datafields
    //=======================================
    public boolean isMultiAllowed() {
        return multiAllowed;    
    }

    // To add allowed types of data
    //=======================================
    public void setFieldType(String dt) {
        if (!types.contains(dt)) {types.add(dt);}    
    }

    // To get allowed types of data
    //=======================================
    public ArrayList getFieldType() {
        return types;    
    }

    // To add location into entities
    //=======================================
    public void setEntityLocation(String dt) {
        if (!ePresent.contains(dt)) {ePresent.add(dt);}    
    }

    // To get a list of entities where is located
    //=======================================
    public ArrayList getEntityLocation() {
        return ePresent;
    }

    // To add location into database
    //=======================================
    public void setBaseLocation(String dt) {
        if (!baseLocation.contains(dt)) {baseLocation.add(dt);}    
    }

    // To get a list of entities where is located
    //=======================================
    public ArrayList getBaseLocation() {
        return baseLocation;
    }

    // To fill properties from file
    //=======================================
    public void setFromFile() {

    }
}
