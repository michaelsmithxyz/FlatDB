package com.pvminecraft.FlatDB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author s0lder
 */
public class FlatDB {
    private String path;
    private File file;
    private HashMap<String,Row> rows;
    public FlatDB(String p, String f) {
        path = p;
        file = new File(path + File.separator + f);
        // Create Database if it doesn't exist
        new File(path).mkdirs();
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        rows = getAllRows();
    }
    
    public Row getRow(String index) {
        return (Row) rows.get(index);
    }
    
    public HashMap getAllRows() {
        HashMap<String,Row> table = new HashMap<String,Row>();
        String line;
        String[] values;
        String key;
        ArrayList<String> row;
        Row add;
        
        try {
            BufferedReader dbIn = new BufferedReader(new FileReader(file));
            line = dbIn.readLine();
            while(line != null) {
                values = line.split(" ");
                key = values[0];
                row = new ArrayList(values.length - 1);
                for(int x = 1; x < values.length; x++) {
                    row.add(values[x]);
                }
                add = new Row(key, row);
                table.put(key, add);
                line = dbIn.readLine();
            }
            dbIn.close();
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return table;
    }
    
    public void addRow(String in, Row val) {
        rows.put(in, val);
    }
    
    public void removeRow(String in) {
        rows.remove(in);
    }
    
    public void update() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            String output;
            String rowString;
            Iterator it = rows.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry pairs = (Map.Entry)it.next();
                Row r = (Row) pairs.getValue();
                String k = (String) pairs.getKey();
                rowString = r.toString();
                output = k + " " + rowString + "\n";
                System.out.println("Adding: " + output);
                out.write(output);
            }
            out.flush();
            out.close();
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}