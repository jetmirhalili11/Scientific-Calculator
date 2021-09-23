import java.util.*;
import java.math.BigDecimal;


/** 
*@author Jetmir Halili
* This class is used after ured press equal button ie it calculate everithing
*/
public class Calculate{
   private BigDecimal b;
   protected double rad=Math.PI/180;
   
   // this method does the calculations after printing the equation for each sign   
   protected void llogaritje(int i, List<String> sine, List<Double> num){
      String s= sine.get(i);
      if(s.equals("+")){ 
         num.set(i,num.get(i)+num.get(i+1));
      }
      else if(s.equals("–")){
         num.set(i,num.get(i)-num.get(i+1));
      }
      else if(s.equals("×")){
         num.set(i,num.get(i)*num.get(i+1));
      }
      else if(s.equals("÷")){
         num.set(i,num.get(i)/num.get(i+1));
      }
      else if(s.equals(" mod ")){
         num.set(i,num.get(i)%num.get(i+1));
      }
      else { sience(i,sine,num); }
      try{ b= new BigDecimal(num.get(i),new java.math.MathContext(20,java.math.RoundingMode.HALF_UP)); }     // tries to round the result
      catch(Exception e){ b=null; }                                                                       // if such a thing does not happen it preserves the value as null
      sine.remove(i);
      num.remove(i+1);
   }
   
   // this is a continuation of the auxiliary method of the above method
   private void sience(int i, List<String> sine, List<Double> num){
      String s= sine.get(i);
      if(s.equals("sqroot")){
         num.set(i,Math.sqrt(num.get(i)));
      }
      else if(s.equals("cuberoot")){
         num.set(i,Math.cbrt(num.get(i)));
      } 
      else if(s.equals("sqr")){
         num.set(i,Math.pow(num.get(i),2));
      }
      else if(s.equals("cube")){
         num.set(i,Math.pow(num.get(i),3));
      }
      else if(s.equals("10^")){
         num.set(i,Math.pow(10,num.get(i)));
      }
      else if(s.equals("^")){
         num.set(i,Math.pow(num.get(i),num.get(i+1)));
      }
      else if(s.equals("yroot")){
         num.set(i,Math.pow(num.get(i),1/num.get(i+1)));
      }
      else if(s.equals("2^")){
         num.set(i,Math.pow(2,num.get(i)));
      }
      else if(s.equals("ln")){
         num.set(i,Math.log(num.get(i)));
      }
      else if(s.equals("e^")){
         num.set(i,Math.exp(num.get(i)));
      }
      else if(s.equals("log")){
         num.set(i,Math.log10(num.get(i)));
      }
      else if(s.equals("base log")){
         num.set(i,Math.log(num.get(i+1))/Math.log(num.get(i)));
      }
      else if(s.equals("sin")){
         double rez=Math.sin(num.get(i)*rad);
         if(rez==1.2246467991473532E-16){ rez=0; }                                       // the case when the week makes releases because the number is too small
         num.set(i,rez);                                                                
      }
      else if(s.equals("cos")){
         double rez=Math.cos(num.get(i)*rad);
         if(rez==6.123233995736766E-17 || rez==-1.8369701987210297E-16){ rez=0; }
         num.set(i,rez);
      }
      else if(s.equals("tan")){
         double rez=Math.tan(num.get(i)*rad);
         if(rez==1.633123935319537E16){ rez=Double.POSITIVE_INFINITY; }
         else if(rez==5.443746451065123E15){ rez=Double.NEGATIVE_INFINITY; }
         num.set(i,rez);
      }
      else if(s.equals("cot")){
         double rez=1/Math.tan(num.get(i)*rad);
         if(rez==6.123233995736766E-17){ rez=Double.POSITIVE_INFINITY; }
         else if(rez==1.83697019872103E-16){ rez=Double.NEGATIVE_INFINITY; }
         num.set(i,rez);
      }
      else if(s.equals("asin")){
         num.set(i,(Math.asin(num.get(i)))/rad);
      } 
      else if(s.equals("acos")){
         num.set(i,Math.acos(num.get(i))/rad);
      }       
      else if(s.equals("atan")){
         num.set(i,Math.atan(num.get(i))/rad);
      }       
      else if(s.equals("acot")){
         num.set(i,Math.atan(1/num.get(i))/rad);
      } 
      else if(s.equals("abs")){
         num.set(i,Math.abs(num.get(i)));
      }      
      else if(s.equals("floor")){
         num.set(i,Math.floor(num.get(i)));
      }      
      else if(s.equals("ceil")){
         num.set(i,Math.ceil(num.get(i)));
      }
   }
   
   // this method is executed after pressing the equalize button so it executes all the necessary methods to give the result
   protected double barazim(List<String> sine, List<Double> num){ 
      while(sine.contains("(")){                                                // if there are parentheses then first act only with the actions inside the parentheses
         barazim(sine.subList(sine.indexOf("(")+1, nearest(sine)), num.subList(sine.indexOf("("), nearest(sine)+2-kllapat(sine,"(",")",nearest(sine)+1)));
         if(sine.contains("(") && sine.indexOf("(")+1==nearest(sine)){
            sine.remove("(");
            sine.remove(")");
         }
      }
      llogaritje2(sine,num,1);                              // first the calculations of functions such as sine logarithm empowerment etc. are executed
      llogaritje2(sine,num,2);                              // then the operations of multiplication, division and remainder (module) are executed
      llogaritje2(sine,num,3);                              // and finally the addition and subtraction action
      double rez=num.get(0);
      if(b!=null){ rez=b.doubleValue(); }
      return rez;
   }
   
   // this method finds the closed bracket of any open bracket, used during functions
   private int nearest(List<String> sine){
      int k1=0;
      int k2=0;
      for(int i=0; i!=sine.size(); i++){
         if(sine.get(i).equals("(")){ k1++; }
         else if(sine.get(i).equals(")")){ k2++; }
         if(k1!=0 && k1==k2){ 
            return i; }
      }
      return sine.lastIndexOf(")");
   }
   
   // this method executes functions or signs based on input parameters and their order
   protected void llogaritje2(List<String> sine, List<Double> num, int j){
      for(int i=0; i!=sine.size(); i++){
         if(j==1 && !sine.get(i).equals("+") && !sine.get(i).equals("–") && !sine.get(i).equals("×") && !sine.get(i).equals("÷") && !sine.get(i).equals(" mod ")){
            llogaritje(i,sine,num); 
            i--;
         }
         else if(j==2 && !sine.get(i).equals("+") && !sine.get(i).equals("–")){
            llogaritje(i,sine,num); 
            i--;
         }
         else if(j==3 && (sine.get(i).equals("+") || sine.get(i).equals("–"))){ 
            llogaritje(i,sine,num); 
            i--;
         }
      }
   }
   
   // counts two characters in the whole list, usually used to count parentheses
   protected int kllapat(List<String> sine, String s, String h, int j){
      int rez=0;
      if(j==-1){ j=sine.size(); }
      for(int i=0; i!=j; i++){
         if(sine.get(i).equals(s) || sine.get(i).equals(h)){
            rez++;
         }
      }
      return rez;
   }
}