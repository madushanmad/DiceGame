/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1.cw2;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author madus
 */
public class Dice {
    private final Die S1 = new Die(1);
    private final Die S2 = new Die(2);
    private final Die S3 = new Die(3);
    private final Die S4 = new Die(4);
    private final Die S5 = new Die(5);
    private final Die S6 = new Die(6);
    
    public Die[] roll(){
        
        Die[] dieArray = {S1,S2,S3,S4,S5,S6};
        Die[] shufDieArry = new Die[5];
        
        shuffleArray(dieArray);
        shufDieArry [0]= dieArray[0] ;
        shuffleArray(dieArray);
        shufDieArry [1]= dieArray[0] ;
        shuffleArray(dieArray);
        shufDieArry [2]= dieArray[0] ;
        shuffleArray(dieArray);
        shufDieArry [3]= dieArray[0] ;
        shuffleArray(dieArray);
        shufDieArry [4]= dieArray[0] ;

        return shufDieArry;           
        
    }
    
  private static void shuffleArray(Die[] array)
  {
    Random rnd = ThreadLocalRandom.current();
    for (int i = array.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      Die a = array[index];
      array[index] = array[i];
      array[i] = a;
    }
  }
 
  
}
