package com.pvminecraft.FlatDB;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author s0lder
 */
public class Row {
    private String index;
    private HashMap<String,String> values;
    
    public Row(String id) {
        index = id;
        values = new HashMap<String,String>();
    }
    
    public Row(String key, List<String> row) {
        index = key;
        values = extractHashMap(row);
    }
    
    public HashMap<String,String> extractHashMap(List<String> row) {
        HashMap<String,String> map = new HashMap<String, String>();
        String in, val;
        String[] data;
        for(String element : row) {
            data = element.split(":");
            in = data[0];
            val = data[1];
            map.put(in, val);
        }
        return map;   
    }
    
    
    public String getIndex() {
        return index;
    }
    
    public String getElement(String in) {
        return values.get(in);
    }
    
    public void addElement(String key, String val) {
        values.put(key, val);
    }
    
    @Override
    public String toString() {
        String ret = "";
        Iterator it = values.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            String val = (String) pairs.getValue();
            String in = (String) pairs.getKey();
            ret += in + ":" + val + " ";
        }
        return ret;
    }
}
