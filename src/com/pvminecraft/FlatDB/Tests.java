/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pvminecraft.FlatDB;
/**
 *
 * @author s0lder
 */
public class Tests {
    public static void main(String[] args) {
        FlatDB flat = new FlatDB(".", "db.db");
        Row row = flat.getRow("0");
        String c = row.getElement("x");
        System.out.println(row);
    }
}
