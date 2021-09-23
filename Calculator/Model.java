import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

/** 
*@author Jetmir Halili
* This class is the model part of the application
*/
public class Model extends Calculate{
   private final ArrayList<Double> num;
   private final ArrayList<String> sine;
   private final JTextField f;
   private final JLabel f1;
   
   // the constructor initializes the list that will contain the numbers, signs and fields   
   public Model(ArrayList<Double> num, ArrayList<String> sine, JTextField f, JLabel f1){
      this.num= num;
      this.sine= sine;
      this.f=f;
      this.f1=f1;
   }
   
   // adds a number or sign to the field and f1 as well as to their respective lists
   public void addS(String s, int sin){                                
      if(sin==0){                              // if we are dealing with a number
         fCh();
         if((f.getText().equals("0") && (f1.getText().isBlank() || f1.getText().charAt(f1.getText().length()-1)!='=')) || f.getText().equals("e") || f.getText().equals("pi")){
            f.setText(s);
         }
         else if((!f1.getText().isBlank() && f1.getText().charAt(f1.getText().length()-1)=='=') || s.equals("e") || s.equals("pi")){ 
            f.setText(s);
            if(!f1.getText().isBlank() && f1.getText().charAt(f1.getText().length()-1)=='='){
               f1.setText("");
               num.clear();
            }
         }
         else {
            f.setText(f.getText()+s);
         }
      }
      else if(!f1.getText().isBlank() || !f.getText().equals("0")){      // if we are dealing with sign
         if(!f1.getText().isBlank() && f1.getText().charAt(f1.getText().length()-1)=='='){     // when pressing the sign after the equalizer
            f1.setText("");
            sineBaraz(s,false);
         }
         else {  
            sineBaraz(s,true);
         }
         f.setText("0");
         sine.add(s); 
      }
   }
   
   // this method is used when a sign or equal button is pressed
   public void sineBaraz(String s, boolean t){             
      if(!f1.getText().isBlank() && f1.getText().charAt(f1.getText().length()-1)==')'){          // if the last character is the parentheses closed
         f1.setText(f1.getText()+s);
      }                       
      else {
         if(f.getText().charAt(0)=='-'){                           // if the number is negative then the parentheses are added
            f1.setText(f1.getText()+"("+f.getText()+")"+s); 
         }  
         else{                                                               
            f1.setText(f1.getText()+f.getText()+s);
         }
         if(t){
            if(f.getText().equals("pi") || f.getText().equals("e")){       // if the number is "pi" or "e"
               EPI();
            }
            else{
               num.add(Double.parseDouble(f.getText())); 
            } 
         }
      }
   }
   
   // this serves to distinguish whether it is the number 'e' or PI so as not to try to convert from strings to double
   protected void EPI(){
      if(f.getText().equals("pi")){                            // if the number is 'pi'
         num.add(Math.PI);
      }
      else if(f.getText().equals("e")){                        // if the number is 'e'
         num.add(Math.E);
      }
   }
   
   // this method is used to see if the closed bracket can be used
   public void kllapa(String s){                                          
      if(s.equals(")") && kllapat(sine,"(","n",-1)>kllapat(sine,")","n",-1)){
         sineBaraz(")",true);
         sine.add(")");                                  
      }
   }
   
   // removes the last character from the field, it's used when we press the backspace button
   public void fR(){                                                 
      String s = f.getText(); 
      if(!s.equals("0")){
         f.setText(s.substring(0,s.length()-1));
         if(f.getText().isBlank() || f.getText().equals("p")){            // if field is empty or 'p' is left from 'pi' then add 0
            f.setText("0");
         }
      }
   }
   
   // checks if the comma can be added and if it can then it add it
   public void addPoint(){   
      if(!f.getText().equals("pi") && !f.getText().equals("e") && !f.getText().contains(".")){                                              
         f.setText(f.getText()+".");
      }
   }
   
   // makes the number negariv or more precisely adds the sign +/-
   public void negative(){                                                            
      String s= f.getText();
      if(!s.equals("0") && !s.equals("pi") && !s.equals("e")){
         if(s.charAt(0)!='-'){
            f.setText("-"+s);
         }
         else {
            f.setText(s.substring(1,s.length()));
         }
      }
   }
   
   // delete the text from f and f1, and empty the num and sine lists
   public void clear(){                                                 
      f1.setText("");
      f.setText("0");
      num.clear();
      sine.clear();
   }
   
   // checks filed f if it contains many numbers and reduces its size so that each can be seen
   public void fCh(){
      if(f.getText().length()>18){
         f.setFont(new Font("Malgun Gothic",Font.BOLD,20));
      }   
      else if(f.getText().length()>14){
         f.setFont(new Font("Malgun Gothic",Font.BOLD,30));
      }
      else {
         f.setFont(new Font("Malgun Gothic",Font.BOLD,40));
      }
   }
   
   // this method serves to ask where the function should be placed in f1, ie before which bracket or expression
   protected int ks(String s){
      int k1=0;
      int k2=0;
      for(int i=s.length()-1; i!=-1; i--){
         if(s.charAt(i)=='('){ k1++; }
         else if(s.charAt(i)==')'){ k2++; }
         if(k1!=0 && k1==k2){ 
            while(i>0 && s.charAt(i-1)!='+' && s.charAt(i-1)!='–' && s.charAt(i-1)!='×' && s.charAt(i-1)!='÷' && s.charAt(i-1)!=' ' && s.charAt(i-1)!='('){ i--; }
            return i; }
      }
      return 0;
   }
   
   // this method was created because in Netbeans IDE when taking the name of the photos their names were changed
   protected String getName(String h){
      boolean t=true;
      int i=h.length();
      while(t){
         i--;
         if(h.charAt(i)=='/'){ t=false; }
      }
      String s=h.substring(i+1,h.length());
      if(s.equals("w10%5e.png") || s.equals("b10%5e.png")){           // w and b is for images white and black
         s="w10^.png";
      }
      else if(s.equals("w2%5e.png") || s.equals("b2%5e.png")){
         s="w2^.png";
      }
      else if(s.equals("w%5e.png") || s.equals("b%5e.png")){
         s="w^.png";
      }
      else if(s.equals("we%5e.png") || s.equals("be%5e.png")){
         s="we^.png";
      }
      return s;
   }
}