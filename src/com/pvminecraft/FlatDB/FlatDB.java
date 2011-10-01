package com.pvminecraft.FlatDB;

import com.pvminecraft.FlatDB.utils.FileManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author s0lder
 */
public class FlatDB {
    private String path;
    private String name;
    private FileManager fm;
    private LinkedHashMap<String,Row> rows;
    
    public FlatDB(String p, String f) {
        path = p;
        name = f;
        fm = new FileManager(path, f);
        fm.readLines();
        this.getAllRows();
    }
    
    public Row getRow(String index) {
        return (Row) rows.get(index);
    }
    
    private void getAllRows() {
        LinkedHashMap<String,Row> table = new LinkedHashMap<String,Row>();
        LinkedList<String> lines = fm.getLines();
        String[] values;
        String key;
        ArrayList<String> row;
        Row add;
        for(String line : lines) {
            values = line.split(" ");
            key = values[0];
            row = new ArrayList(values.length - 1);
            for(int x = 1; x < values.length; x++) {
                row.add(values[x]);
            }
            add = new Row(key, row);
            table.put(key, add);
        }
        this.rows = table;
    }
    
    public void addRow(Row val) {
        rows.put(val.getIndex(), val);
    }
    
    public void removeRow(String in) {
        rows.remove(in);
    }
    
    public void update() {
        String output;
        String rowString;
        Iterator it = rows.entrySet().iterator();
        fm.clear();
        while(it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            Row r = (Row) pairs.getValue();
            String k = (String) pairs.getKey();
            rowString = r.toString();
            output = k + " " + rowString;
            fm.appendLine(output);            
        }
        fm.write();
    }
}