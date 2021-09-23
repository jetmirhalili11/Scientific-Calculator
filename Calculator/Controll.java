import javax.swing.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/** 
*@author Jetmir Halili
* This class is the controll part of the application
*/
public class Controll{
   private final Window v;
   private final Model n;
   private final ArrayList<String> sine;
   private final ArrayList<Double> num;
   private final JButton b[][];
   private final JTextField f;
   private final JLabel f1;
   
   public Controll(){
      num= new ArrayList<>();
      sine= new ArrayList<>();
      b= new JButton[5][8];
      f= new JTextField("0");
      f1= new JLabel();
      n= new Model(num,sine,f,f1);
      v= new Window(num,sine,f,f1,b);
      fieldAction();
      addKllapat();
      addScience();
      addE_PI();
      nd2();
      DR();
      addSineNum();
      night();
      b[0][3].addActionListener(e -> addActionS2(0,3));
      v.setVisible(true);
   }
   
   // this method sets the keyboard action in the application
   public void fieldAction(){
      f.addKeyListener(
            new KeyListener(){
               public void keyPressed(KeyEvent e){
                  for(int i=1; i!=5; i++){
                     for(int j=0; j!=5; j++){
                        if(b[i][j].getText().equals(e.getKeyChar()+"")){ 
                           b[i][j].doClick(); 
                           break;
                        }
                     }
                  }
                  if(e.getKeyChar()=='%')
                     b[4][5].doClick(); 
                  else if(e.getKeyChar()=='/') 
                     b[4][4].doClick(); 
                  else if(e.getKeyChar()=='*') 
                     b[4][3].doClick(); 
                  else if(e.getKeyChar()=='-') 
                     b[4][2].doClick(); 
                  else if((e.getKeyChar()+"").toLowerCase().equals("a") || (e.getKeyChar()+"").toLowerCase().equals("c")) 
                     b[4][6].doClick(); 
                  else if(e.getKeyCode()==KeyEvent.VK_ENTER) 
                     b[4][0].doClick(); 
                  else if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) 
                     b[4][7].doClick(); 
                  else if((e.getKeyChar()+"").toLowerCase().equals("e")) 
                     b[3][6].doClick(); 
                  else if((e.getKeyChar()+"").toLowerCase().equals("p")) 
                     b[3][7].doClick(); 
                  else if((e.getKeyChar()+"").toLowerCase().equals("n")) 
                     b[1][0].doClick(); 
                        
                  e.consume();
               }
            
               public void keyTyped(KeyEvent e) { e.consume(); }
               public void keyReleased(KeyEvent e) { e.consume(); }
            }
         );    
   }
   
   
   // this method is used to insert the action of the buttons that contain the parentheses
   public void addKllapat(){
      b[1][4].addActionListener(
         e ->{
            if(Double.parseDouble(f.getText())==0){
               f1.setText(f1.getText()+"(");
               sine.add("(");                                       
            }
         });
      b[2][4].addActionListener(e -> n.kllapa(")"));
   }
   
   // this method is used to insert the action of all buttons that contain functions
   public void addScience(){
      for(int i=0; i!=6; i++){ 
         if(i!=3){ setScience(0,i); } 
      }
      for(int i=1; i!=3; i++){
         for(int j=5; j!=8; j++){
            setScience(i,j);
         }
      }
      setScience(3,5);
   }
   
   // this is as an auxiliary method of inserting button action
   private void setScience(int i, int j){
      b[i][j].addActionListener(e -> addActionS1(i,j));
   }
   
   // this method serves for the button to change the randians to degree or vice versa
   public void DR(){
      b[0][7].addActionListener(
         e ->{ 
            if(n.rad!=1){
               n.rad=1;
               b[0][7].setText("Rad");
               if(v.night.equals("w")){
                  b[0][7].setBackground(new Color (153-40,204-40,255-40));
                  b[0][7].setForeground(Color.BLACK);
               }
               else{
                  b[0][7].setBackground(new Color (102+40,51+40,40));
                  b[0][7].setForeground(Color.WHITE);
               }
            }
            else {
               n.rad=Math.PI/180;
               b[0][7].setText("Deg");
               if(v.night.equals("w")){
                  b[0][7].setBackground(new Color(240-40,240-40,240-40));
                  b[0][7].setForeground(new Color(80,150,255));
               }
               else{
                  b[0][7].setBackground(new Color(15+40,15+40,15+40));
                  b[0][7].setForeground(new Color(255-80,255-150,0));                   
               }
            }
         });
   }
   
   // this method serves the function of the 'pi' and 'e' buttons
   public void addE_PI(){
      b[3][7].addActionListener(e -> n.addS("pi",0));
      b[3][6].addActionListener(e -> n.addS("e",0));
   }
   
   // this method puts the action on the button to change the appearance of the app (change theme)
   public void night(){
      b[3][4].addActionListener(
         e -> { 
            if(v.night.equals("w")){
               v.night="b";
            }
            else{
               v.night="w";
            }
            v.setColor();
         });
   }
   
   // serves for the 2nd button function
   private void nd2(){
      b[0][6].addActionListener(
         e ->{
            if(v.nd.equals("f")){
               v.nd="s";
               v.second();
               b[0][1].removeActionListener(b[0][1].getActionListeners()[0]);
               b[0][1].addActionListener(r-> addActionS2(0,1));
               if(v.night.equals("w")){
                  b[0][6].setBackground(new Color (153-40,204-40,255-40));
                  b[0][6].setForeground(Color.BLACK);
               }
               else{
                  b[0][6].setBackground(new Color (102+40,51+40,40));
                  b[0][6].setForeground(Color.WHITE);
               }
            }
            else {
               v.nd="f";
               v.first();
               b[0][1].removeActionListener(b[0][1].getActionListeners()[0]);
               b[0][1].addActionListener(r-> addActionS1(0,1));
               if(v.night.equals("w")){
                  b[0][6].setBackground(new Color(240-40,240-40,240-40));
                  b[0][6].setForeground(new Color(80,150,255));
               }
               else{
                  b[0][6].setBackground(new Color(15+40,15+40,15+40));
                  b[0][6].setForeground(new Color(255-80,255-150,0));                   
               }
            }
         });
   }
   
   // this method is used for functions that use two numbers is eg x ^ y
   private void addActionS2(int i, int j){
      String s=takeFunction(i,j);
      sine.add(s);
      sine.add("(");
      if(!f1.getText().isBlank() && f1.getText().charAt(f1.getText().length()-1)=='='){
         f1.setText("");
         n.sineBaraz(s+"(",false);
      }
      n.sineBaraz(s+"(",true);
      f.setText("0");
   }
   
   // this method is used for functions that use only one number such as square root, square power, etc.
   private void addActionS1(int i, int j){
      String s=takeFunction(i,j);
      sine.add(s);
      if(f1.getText().isBlank() || f1.getText().charAt(f1.getText().length()-1)!=')'){
         if(f1.getText().isBlank() || f1.getText().charAt(f1.getText().length()-1)!='='){
            f1.setText(f1.getText()+s+"("+f.getText()+")");
         }
         else{ 
            f1.setText(s+"("+f.getText()+")");
            num.clear();
         }
         if(f.getText().equals("pi") || f.getText().equals("e")){ n.EPI(); }
         else { num.add(Double.parseDouble(f.getText()));  }
      }
      else {
         String h=f1.getText();
         f1.setText(h.substring(0,n.ks(h))+s+"("+h.substring(n.ks(h),h.length())+")");
      }
      num.add(0.0);
      f.setText("0");
   }
   
   // this method is used to add the action of numbers
   private void setActionsNum(int i, int j){
      b[i][j].addActionListener(e -> n.addS(b[i][j].getText(),0));
   }
   
   // used to add the action of the signs +, -, *, /, mod
   private void setSineAction(int j, int i){
      b[j][i].addActionListener(e -> n.addS(b[j][i].getText(),1));
   }
   
   // add action of signs, decimal point, equalizer, AC, <<, +/-
   private void addSineNum(){
      for(int i=1; i!=6; i++){
         setSineAction(4,i);
      }
      for(int i=1; i!=4; i++){
         for(int j=1; j!=4; j++){
            setActionsNum(i,j);
         }
      }
      setActionsNum(2,0);
      b[3][0].addActionListener(e -> n.addPoint());
      b[4][0].addActionListener(
         e->{
            if(!sine.isEmpty()){
               n.sineBaraz("=",true);
               for(int i=0; n.kllapat(sine,"(","n",-1)!=n.kllapat(sine,")","n",-1); i++){             // if there are more open brackets than closed ones
                  sine.add(")"); 
                  f1.setText(f1.getText().substring(0,f1.getText().length()-1)+")"+"=");
               }
               double rez = n.barazim(sine,num);
               if(rez!=(int)rez){ f.setText(rez+""); }
               else{ f.setText((int)(rez)+""); }
               n.fCh();
            }
         }
         ); 
      b[1][0].addActionListener(e -> n.negative());  
      b[4][6].addActionListener(e -> n.clear());
      b[4][7].addActionListener(e -> n.fR()); 
   }
   
   // returns as a result the name of the function taken from the string of the photo of a button
   private String takeFunction(int i, int j){
      String s= n.getName(b[i][j].getIcon().toString());
      int x=s.length();
      return s.substring(1,x-4);
   }
   
   // executes the entire application
   public static void main(String [] args){
      new Controll();
   }
}