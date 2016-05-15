/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1.cw2;

/**
 *
 * @author madus
 */
public class Die implements DieIntf ,Comparable<Object> {
    private int value;
    private String image;
    
    Die(int value) {
        setValue(value);
        setImage(value);
    }

    Die() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public void setValue(int value) {
       this.value = value; 
    }

    @Override
    public int getValue() {
        return value; 
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDieImage(Die a) {
        return image;
    }

    @Override
    public void setImage(int value) {
        if (value == 1){image = "\\Images\\face1.png";}
        if (value == 2){image = "\\Images\\face2.png";}
        if (value == 3){image = "\\Images\\face3.png";}
        if (value == 4){image = "\\Images\\face4.png";}
        if (value == 5){image = "\\Images\\face5.png";}
        if (value == 6){image = "\\Images\\face6.png";}
    }
    
}
