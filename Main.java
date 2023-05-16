package org.example;

import java.util.ArrayList;
import java.util.Hashtable;

import static org.example.HexManager.radius;

public class Main {
    public static void main(String[] args) {
        HexManager hexManager=new HexManager(100);
        System.out.println(hexManager.getGrid().size());
        System.out.println(hexManager.getGrid().containsKey(new cord(0,0)));
        cord c1=new cord(0,0);
        cord c2=new cord(66,33);
        Hashtable<cord,Hex> table=hexManager.drawLine(c1,c2);
        ArrayList<Hex> table2=hexManager.drawLineWithDirection(c1,c2);
        System.out.println(table.toString());
        System.out.println(table2.toString());
    }
}