/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop1.cw2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author 2014151
 */
public class StartUI extends JFrame implements Runnable , ActionListener{
    JFrame f;  
    
    JButton startButt=new JButton("Throw Dices Now!");//create button 
    JButton restart=new JButton("Restart Game");//create button 
    static JLabel  winningscore  ;
    String currentLoc = System.getProperty("user.dir");
    JLabel picLabel[][] = new JLabel[2][5] ;
    JTextField userText ;
    JLabel mainpic = new JLabel();
    JLabel rules ;
    BufferedImage myPicture;
    JLabel wins;
    File path;
    OutputStream outStream;
    BufferedReader reader;
    String scores;
    String userWins;
    String userLosses;
    String MachineWins;
    String MachineLosses;
    private static int scorepoint;
    
    public StartUI(String x){
    }
   
    public StartUI() throws IOException 
    {

            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
            DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
            decimalFormat.setGroupingUsed(false);
            userText = new JFormattedTextField(decimalFormat);
            userText.setText("101");
            userText.setColumns(15);
        String onFile = null;
        path = new File("score.txt");
        if(!path.exists()){
        outStream = new FileOutputStream(path);
        String gotoTxt = "0-0-0-0";
        outStream = new FileOutputStream(path);
        outStream.write(gotoTxt.getBytes());
        outStream.close();
        }else{
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            onFile = reader.readLine();
            reader.close();
        }
        
        String[] parts = onFile.split("-");
        userWins = parts[0];
        userLosses = parts[1];
        MachineWins = parts[2];
        MachineLosses = parts[3];
        
        scores = "<html>User: Wins:"+userWins+"   Losses:"+userLosses+"<br>Machine: Wins:"+MachineWins+"   Losses:"+MachineLosses+"</html>";
        
        
        wins = new JLabel(scores);
        
        
        myPicture = ImageIO.read(new File(currentLoc+ "\\Images\\Test.png"));
        mainpic = new JLabel(new ImageIcon(myPicture));
        rules = new JLabel("<html>Rules:<br>1:First Person to Reach the Score Limt Wins(Default Limit is 101). &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Set Score Limit:"
                + "<br>2:Each Person Gets 2 ReRolls while keeping the selected Dices</html>");
        
        userText.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                char input = e.getKeyChar();
                
                if((input< '0'|| input>'9')&& input!='\b'){
                    e.consume();
                    JOptionPane.showMessageDialog(f, "Enter Only Numbers between 30-999");
                }
            }
        });
        startButt.addActionListener(this);
        
        startButt.setBounds(250,300,200, 70);
        wins.setBounds(500, 5, 250, 80);
        rules.setBounds(50, 300, 500, 200);
        userText.setBounds(545, 385, 50, 25);
        mainpic.setBounds(200, 10, 300, 350);
        add(startButt);//adding button on frame  
        add(wins);
        add(rules);
        add(userText);
        add(mainpic);
        setSize(700,500);  
        setTitle("Dices 101");
        setLayout(null);  
        setVisible(true); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    int rerolls;
    int autoRerolls;
    boolean tie =false;
    boolean ntviewd = true;
    @Override
      public void actionPerformed(ActionEvent e){
          
      if(e.getSource()==startButt){
          int ammount = Integer.parseInt(userText.getText());
          if (ammount<999 && ammount>30){
                winningscore = new JLabel("Winning Score ="+ammount );
//                winningscore.setBounds(150, 10, 200, 30);
                setVisible(false);
                StartGame();
//                add(restart);
//                add(winningscore);
                scorepoint = ammount;
          }else{
                JOptionPane.showMessageDialog(f, "Enter Only Numbers between 30-999");
          }
      }
      
      if(e.getSource()==restart){
          newgame();
      
      }
       
      if(e.getSource() ==  reRollBut){
          
          
          if(rerolls < 3){
            if (!checkbox[0][0].isSelected()&&!checkbox[0][1].isSelected()&&!checkbox[0][2].isSelected()&&!checkbox[0][3].isSelected()&&!checkbox[0][4].isSelected()){
              remove(player[0]);
              remove(totalInSet[0]);
              remove(totalScore[0]);
              remove(picLabel[0][0]);
              remove(picLabel[0][1]);
              remove(picLabel[0][2]);
              remove(picLabel[0][3]);
              remove(picLabel[0][4]);
            buttonclick("USER",0);
            
            }else{
              remove(picLabel[0][0]);
              remove(picLabel[0][1]);
              remove(picLabel[0][2]);
              remove(picLabel[0][3]);
              remove(picLabel[0][4]);
                reROll(0);
                buttonclick("USER",1);
                //repaint();
      }
      }
      }
      
      if(e.getSource() == score){
          ntviewd = false;
             do{
        System.out.println("auto Rolling");
        autoRoll(ar[1]);
        }while ( autoRerolls<=2);
                     
                     
      totalUser = totalUser + UserTemp;
      totalMachine = totalMachine + MachineTemp;
          if(totalMachine>=scorepoint||totalUser>=scorepoint){
          if(totalUser==totalMachine){
              
          rerolls = 3;
          autoRerolls =3;
          tie= true;
          }else{
          if(totalUser>totalMachine){if(totalUser >= scorepoint){
          JOptionPane.showMessageDialog(f, "<html>You Won!<br>Scores: You :"+totalUser+"<br> Machine: "+totalMachine+"<br> Thanks For Playing.</html>");
              try {
                  outStream = new FileOutputStream(path);
                  outStream = new FileOutputStream(path);
                  int uWins = Integer.parseInt(userWins);
                  int mLosses = Integer.parseInt(MachineLosses);
                  mLosses++;
                  uWins++;
                  String gotoTxt = uWins+"-"+userLosses+"-"+MachineWins+"-"+mLosses;
              try {
                  outStream.write(gotoTxt.getBytes());
                  
              } catch (IOException ex) {
                  Logger.getLogger(StartUI.class.getName()).log(Level.SEVERE, null, ex);
              }
              try {
                  outStream.close();
              } catch (IOException ex) {
                  Logger.getLogger(StartUI.class.getName()).log(Level.SEVERE, null, ex);
              }
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(StartUI.class.getName()).log(Level.SEVERE, null, ex);
              }
              System.exit(0);
          //dispose();
          }}
          
          if(totalMachine > totalUser){if(totalMachine >= scorepoint){
          JOptionPane.showMessageDialog(f, "<html>You Lost!<br>Scores: Machine :"+totalMachine+"<br> You: "+totalUser+"<br> Thanks For Playing.</html>");
          try {
                  outStream = new FileOutputStream(path);
                  outStream = new FileOutputStream(path);
                  int uLosses = Integer.parseInt(userLosses);
                  int mWins = Integer.parseInt(MachineWins);
                  uLosses++;
                  mWins++;
                  String gotoTxt = userWins+"-"+uLosses+"-"+mWins+"-"+MachineLosses;
              try {
                  outStream.write(gotoTxt.getBytes());
                  
              } catch (IOException ex) {
                  Logger.getLogger(StartUI.class.getName()).log(Level.SEVERE, null, ex);
              }
              try {
                  outStream.close();
              } catch (IOException ex) {
                  Logger.getLogger(StartUI.class.getName()).log(Level.SEVERE, null, ex);
              }
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(StartUI.class.getName()).log(Level.SEVERE, null, ex);
              }
          System.exit(0);
          //dispose();
          }}
          }}else{
            rerolls = 0;
            autoRerolls = 0;
            ntviewd = true;
          }
      
      remove(player[0]);
      remove(totalInSet[0]);
      remove(totalScore[0]);
      remove(player[1]);
      remove(totalInSet[1]);
      remove(totalScore[1]);
      remove(picLabel[0][0]);
      remove(picLabel[0][1]);
      remove(picLabel[0][2]);
      remove(picLabel[0][3]);
      remove(picLabel[0][4]);
      remove(picLabel[1][0]);
      remove(picLabel[1][1]);
      remove(picLabel[1][2]);
      remove(picLabel[1][3]);
      remove(picLabel[1][4]);
      synchronized(this){
            notify();
            buttonclick("USER", 0);
      }
      }
      
      }
    
 public void newgame(){
    dispose();
    StartGame();
    JOptionPane.showMessageDialog(f, "New Game Started...");
 }
Thread user;
Thread machine;  
 public void StartGame(){
        try {
            StartUI obj = new StartUI();
            user = new Thread(obj);
            user.setName("User");
            machine = new Thread(obj);
            machine.setName("Machine");
            user.start();
            machine.start();
        } catch (IOException ex) {
            Logger.getLogger(StartUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 
 }  
 
   @Override
public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName);
            if (threadName.equals("User")){buttonclick("USER", 0);}
            if (threadName.equals("Machine")){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(StartUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                buttonclick("MACHINE", 0); }
        

       
}
     
    
Dice diceObj = new Dice(); 
Die ar[][] = new Die[2][5];
CheckboxGroup cBUser = new CheckboxGroup();
JCheckBox checkbox[][] = new JCheckBox[2][5];
JLabel player[] = new JLabel[2];
JLabel[] totalInSet = new JLabel[2];
JLabel[] totalScore = new JLabel[2];
JButton reRollBut=new JButton("ReRoll");
JButton score=new JButton("Score Points");
StartUI test2 ;
StartUI test3 ;
   int totalUser = 0;
   int totalMachine = 0;
   int UserTemp = 0;
   int MachineTemp = 0;
   int list=0;
   int boxCount = 1;
   
   int[] randomnum ={0,1};
   
   
   
   public synchronized void buttonclick (String Player, int ID){
        System.out.println("ButtonClick");
        if(Player.equals("USER")&&ID == 0){
            
            ar[0] = diceObj.roll();
            
        }
        if(Player.equals("MACHINE")&&ID == 0)
        {
            ar[1] = diceObj.roll();
        }
//        winningscore = new JLabel("Winning Score ="+scorepoint );
        remove(startButt);
        remove(rules);
        remove(userText);
        System.out.println(scorepoint);
        int dimention;
        int addY;
        if (Player.equals("USER")){addY = 80;
        dimention = 0;
        remove(mainpic);
        }
        else {addY = 280;
        dimention = 1;
        }
        //remove(winningscore);
        int addX= 50;
        int total= 0;
        int index=0;
        for (Die element : ar[dimention]) {
                        try {
                    
                    String face = element.getDieImage(element);
                    myPicture = ImageIO.read(new File(currentLoc+ face));
                    if(boxCount < 11 ){checkbox[dimention][index] = new JCheckBox(); boxCount++;
                    checkbox[dimention][index].setBounds(addX, addY+70, 50, 50);
                    if(dimention == 0){add(checkbox[dimention][index]);}
                    }
                    picLabel[dimention][index] = new JLabel(new ImageIcon(myPicture));
                    picLabel[dimention][index].setBounds(addX, addY, 70, 70);
                    
                    addX+= 100;
                    total += element.getValue();
                    
                    add(picLabel[dimention][index++]);
                    //setVisible(true);
                    
                } catch (IOException ex) {
                    Logger.getLogger(StartUI.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        }
                    player[dimention] = new JLabel(Player);
                    
                    if (ID == 0&& Player.equals("USER")  ){}
                    
                    totalInSet[dimention] = new JLabel("Total in Set:"+total);
                    
                    
                    int nameloc = addY - 60; 
                    player[dimention].setBounds(50, nameloc, 70, 70);
                    totalInSet[dimention].setBounds(addX-10, addY-5, 110, 70);
                    
                    if (Player.equals("USER")){
                       
                       rerolls++;
                       totalScore[dimention] = new JLabel("<html>Total With Set:"+(totalUser+total) +"<br>Total Scored:"+totalUser+"</html>");
                       UserTemp = total;
                       totalScore[dimention].setBounds(addX-10, addY+20, 120, 70);
                       reRollBut.setBounds(addX, addY+80, 70, 20);
                       restart.setBounds(50, 10, 120, 30);
                       winningscore.setBounds(180, 10, 200, 30);
                       if(list == 0 ){score.addActionListener(this);
                       reRollBut.addActionListener(this);
                       restart.addActionListener(this);
                       list=1;
                       }
                       score.setBounds(addX-20, addY+105, 110, 20);
                       add(reRollBut);
                       remove(winningscore);
                       add(winningscore);
                       add(restart);
                       
                       add(score);
                       
                    }else {
                    totalScore[dimention] = new JLabel("<html>Total With Set:"+(totalMachine+total) +"<br>Total Scored:"+totalMachine+"</html>");
                    totalScore[dimention].setBounds(addX-10, addY+20, 120, 70);
                    MachineTemp = total;
                    autoRerolls++;
//                    if(autoRerolls<=2){
//                        System.out.println("auto Rolling");
//                    autoRoll(ar[dimention]);
//                    }
                    
                    
                    }                                  
                    add(totalInSet[dimention]);
                    add(player[dimention]);
                    add(totalScore[dimention]);
                    String threadName = Thread.currentThread().getName();
                    repaint();
                    
                    if(rerolls>=3 && ntviewd ){
                        if(!tie){
                        JOptionPane.showMessageDialog(f, "<html>2 ReRolls Completed.<br>Current Points will get Scored</html>");
                        score.doClick();
                        }else{
                        JOptionPane.showMessageDialog(f, "Game Tie, No Rerolls anymore.");
                        }
                       // score.doClick();
                    }
                    
        try {
            
            nextRound(threadName);
            
           
        } catch (InterruptedException ex) {
            Logger.getLogger(StartUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
   
   public void autoRoll(Die tet[]){
       int elementnum=0;   
       for(Die ele : tet){
           checkbox[1][elementnum].setSelected(true);
           if(ele.getValue()<4){
//              int rnd = new Random().nextInt(randomnum.length);
//                if(rnd==1){ 
           checkbox[1][elementnum].setSelected(false);
//                }
           
           }
           elementnum++;
       }
        remove(picLabel[1][0]);
        remove(picLabel[1][1]);
        remove(picLabel[1][2]);
        remove(picLabel[1][3]);
        remove(picLabel[1][4]);
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(StartUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        reROll(1);
        buttonclick("MACHINE",1);
       
       
       //RANDOM SELECT
//                for (JCheckBox element : checkbox[1] ) {
//                int rnd = new Random().nextInt(randomnum.length);
//                if(rnd==1){element.setSelected(true);}
//            }
                
   }
   
   
   public void nextRound(String Tname) throws InterruptedException{
        
       if(Tname.equals("Machine")){
           synchronized(this){

               
               
               System.out.println("Machine waiting");
               wait();}
               System.out.println("Machine Resumes");
               Thread.sleep(500);
               buttonclick("MACHINE", 0);
       }
        
   }
   
   
   
   
   
   
    public void reROll(int source){
         System.out.println("ReRolling");
         remove(player[source]);
         remove(totalInSet[source]);
         remove(totalScore[source]);
         //remove(winningscore);
         Die newElement[];

       
        if (!checkbox[source][0].isSelected()) {
                           checkbox[source][0].setSelected(false);
                           System.out.println("done 1");
                           remove(picLabel[source][0]);
                           newElement = diceObj.roll();
                           ar[source][0] = newElement[0];
                           //repaint();
                      }else{System.out.println("incomplete 1");}
        
        if (!checkbox[source][1].isSelected()) {
                           checkbox[source][1].setSelected(false);
                           System.out.println("done 2");
                           remove(picLabel[source][1]);
                           newElement = diceObj.roll();
                           ar[source][1] = newElement[0];
                           //repaint();
                      }else{System.out.println("incomplete 2");}
        
        if (!checkbox[source][2].isSelected()) {
                           checkbox[source][2].setSelected(false);
                           System.out.println("done 3");
                           remove(picLabel[source][2]);
                           newElement = diceObj.roll();
                           ar[source][2] = newElement[0];
                           //repaint();
                      }else{System.out.println("incomplete 3");}
        
        if (!checkbox[source][3].isSelected()) {
                           checkbox[source][3].setSelected(false);
                           System.out.println("done 4");
                           remove(picLabel[source][3]);
                           newElement = diceObj.roll();
                           ar[source][3] = newElement[0];
                           //repaint();
                      }else{System.out.println("incomplete 4");}
        if (!checkbox[source][4].isSelected()) {
                           checkbox[source][4].setSelected(false);
                           System.out.println("done 5");
                           remove(picLabel[source][4]);
                           newElement = diceObj.roll();
                           ar[source][4] = newElement[0];
                           //repaint();
                           
                      }else{System.out.println("incomplete 5");}
               
            
    }


    
}
