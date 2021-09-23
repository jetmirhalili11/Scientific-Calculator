import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;

/** 
*@author Jetmir Halili
* This class deals with view of the application
*/
public class Window extends JFrame{
   private final int space=5, wPlus=16, hPlus=39;
   private int width=60, height=45;
   private final Color c1= new Color(240,240,240);
   private final Color c2= new Color(250,250,250);
   private final ArrayList<String> sine;
   private final ArrayList<Double> num;
   protected String night="w", nd="f";
   private final JTextField f;
   private final JLabel f1;
   private final JButton b[][];
   
   // the constructor initializes the lists, fields and buttons that it takes as parameters and adjusts the JFrame (Graphic Window)
   public Window(ArrayList<Double> num, ArrayList<String> sine, JTextField f, JLabel f1, JButton b[][]){ 
      this.b= b;
      this.f= f;
      this.f1= f1;
      this.sine= sine;
      this.num= num;
            
      for(int i=0; i!=b.length; i++){
         for(int j=0; j!=b[i].length; j++){
            this.b[i][j]= new JButton();
            this.b[i][j].setCursor(new Cursor(HAND_CURSOR));
            this.b[i][j].setRequestFocusEnabled(false);
            if(i!=3 || j!=4){ background(i,j); }
         }
      }
      setSize(5*width+6*space+wPlus,280+4*height+3*space+hPlus);
      addField();
      addAll();
      setDimensions();
      setTitle("Calculator_JH");
      first();
      setLocationRelativeTo(null);
      setLayout(null);
      setIconImage(new ImageIcon(getClass().getResource("Images//logo.png")).getImage());
      addComponent();
      getContentPane().setBackground(new Color(225,225,225)); 
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
      
   // this method deals with the part where the mouse is over the button and changes the background of the button
   public void background(int i, int j){
      b[i][j].addMouseListener(
               new java.awt.event.MouseAdapter() {
                  // when the mouse enters the button area
                  public void mouseEntered(MouseEvent e) {
                     Color c = b[i][j].getBackground();
                     int t=40;
                     if(c.getGreen()<125){ t=-t; }
                     b[i][j].setBackground(new Color(c.getRed()-t,c.getGreen()-t,c.getBlue()-t));
                  }
                  // when the mouse exited the button area
                  public void mouseExited(MouseEvent e){
                     Color c = b[i][j].getBackground();
                     int t=40;
                     if(c.getGreen()<125){ t=-t; }
                     b[i][j].setBackground(new Color(c.getRed()+t,c.getGreen()+t,c.getBlue()+t));
                  }
               }
               );
   }
   
   // this method deals with field design
   public void addField(){
      f.setHorizontalAlignment(JTextField.RIGHT);
      f.setFont(new Font("Malgun Gothic",Font.BOLD,40));
      f.setBorder(null);
      f.setBackground(null);
      f.setForeground(new Color(0,0,0));
      f1.setForeground(Color.gray);
      f1.setBackground(null);
      f1.setFont(new Font("Malgun Gothic",Font.BOLD,15));
      f1.setHorizontalAlignment(JLabel.RIGHT);
      f1.setBorder(null);
      add(f1);
      add(f);
   }
   
   // this method deals with the case when changing the size of the Graphic window to change that of the buttons
   private void addComponent(){
      setMinimumSize(new Dimension(5*60+6*space+wPlus,300+4*height+3*space+hPlus));
      getContentPane().addComponentListener(
         new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e){
               Component c = (Component) e.getSource();
               width=(c.getWidth()-6*space)/5;
               height=45*(c.getHeight())/(280+4*height+3*space+hPlus);
               setDimensions2();
            }
         }
         );
   }
   
   // this method inserts all the buttons in the graphic window
   public void addAll(){
      for(int i=0; i!=b.length; i++){
         for(int j=0; j!=b[i].length; j++){
            b[i][j].setFont(new Font("Gadugi",Font.BOLD,20));
            b[i][j].setBorder(null);
            b[i][j].setBackground(c1);
            b[i][j].setForeground(new Color(0,0,0));
            add(b[i][j]);
         }
      }
      b[3][4].setBackground(null);
   }
   
   // This method deals with setting the color of the entire application (backround, buttons, text, fields)
   // Used when we want to change the theme in dark or light
   public void setColor(){
      getContentPane().setBackground(new Color(255-getContentPane().getBackground().getRed(),255-getContentPane().getBackground().getGreen(),255-getContentPane().getBackground().getBlue()));
      f.setForeground(new Color(255-f.getForeground().getRed(),255-f.getForeground().getGreen(),255-f.getForeground().getBlue()));
      f1.setForeground(new Color(255-f1.getForeground().getRed(),255-f1.getForeground().getGreen(),255-f1.getForeground().getBlue()));
      for(int i=0; i!=b.length; i++){
         for(int j=0; j!=b[i].length; j++){
            if(i!=4 || j!=0){
               b[i][j].setBackground(new Color(255-b[i][j].getBackground().getRed(),255-b[i][j].getBackground().getGreen(),255-b[i][j].getBackground().getBlue()));
               b[i][j].setForeground(new Color(255-b[i][j].getForeground().getRed(),255-b[i][j].getForeground().getGreen(),255-b[i][j].getForeground().getBlue()));
            }
         }
      }  
      b[3][4].setBackground(null);
      
      if(nd.equals("f"))
         first(); 
      else
         second();
      third();
   }
   
   // this method deals with the appearance of some of the buttons (those who do not have photos but text)
   public void setDimensions(){
      for(int i=0; i!=b.length; i++){
         for(int j=0; j!=b[i].length; j++){
            if(i<3 && j<4 && j>0){
               b[j][i+1].setText(3*i+j+"");
               b[j][i+1].setBackground(c2);                      
            }
         }
      }
      b[2][0].setText("0");
      b[2][0].setBackground(c2);
      b[3][0].setText(".");
      b[3][0].setBackground(c2);
      b[1][0].setText("±");
      b[1][0].setBackground(c2);
      b[4][1].setText("+");
      b[4][0].setText("=");
      b[4][0].setBackground(new Color(153,204,255));
      b[4][2].setText("–");
      b[4][3].setText("×");
      b[4][6].setText("AC");
      b[4][6].setForeground(new Color(230,100,100));
      b[4][4].setText("÷");      
      b[4][7].setText("«");
      b[4][7].setForeground(new Color(230,100,100));
      b[0][6].setText("2nd");
      b[0][6].setFont(new Font("Malgun Gothic",Font.BOLD,15));
      b[0][6].setForeground(new Color(80,150,255));
      b[0][7].setText("Deg");
      b[0][7].setFont(new Font("Malgun Gothic",Font.BOLD,15));
      b[0][7].setForeground(new Color(80,150,255));
      b[1][4].setText("(");
      b[2][4].setText(")");
      b[4][5].setText(" mod ");
      b[4][5].setFont(new Font("Malgun Gothic",Font.BOLD,15));
      third();
   }
   
   // this method determines the dimensions of all buttons as well as fields
   public void setDimensions2(){
      for(int i=0; i!=b.length; i++){
         for(int j=0; j!=b[i].length; j++){
            b[i][j].setBounds(space*(i+1)+width*i,getHeight()-hPlus-space*(j+1)-height*(j+1),width,height);
         }
      }
      b[3][4].setBounds(5, 5, 20, 20);
      b[1][4].setBounds(width+2*space,b[1][4].getY(),width+(width+space)/2,height);
      b[2][4].setBounds(2*(width+space)+(width+space)/2+space,b[2][4].getY(),width+(width+space)/2,height);
      f1.setBounds(space,height/2,getWidth()-wPlus-2*space,2*height/3);
      f.setBounds(space,height/2+height/2+5,getWidth()-wPlus-2*space,height+height/4);
   }
   
   // this method changes the appearance of the buttons while pressing the 2nd button
   public void first(){
      b[0][0].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"ln.png")));
      b[0][1].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"log.png")));
      b[0][2].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"10^.png")));
      b[0][3].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"^.png")));
      b[0][4].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"cube.png")));
      b[0][5].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"sqr.png")));
      b[1][7].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"sin.png")));
      b[2][7].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"cos.png")));
      b[1][6].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"tan.png")));
      b[2][6].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"cot.png")));
   }
   
   // this method changes the appearance of the buttons while pressing the 2nd button
   public void second(){
      b[0][0].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"e^.png")));
      b[0][1].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"base log.png")));
      b[0][2].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"2^.png")));
      b[0][3].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"yroot.png")));
      b[0][4].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"cuberoot.png")));
      b[0][5].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"sqroot.png")));
      b[1][7].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"asin.png")));
      b[2][7].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"acos.png")));
      b[1][6].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"atan.png")));
      b[2][6].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"acot.png")));
   }
   
   // this method puts the photos in the corresponding buttons
   public void third(){
      b[3][4].setIcon(new ImageIcon(getClass().getResource("Images//"+night+".png")));
      b[1][5].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"floor.png")));
      b[2][5].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"abs.png")));
      b[3][5].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"ceil.png")));
      b[3][7].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"pi.png")));
      b[3][6].setIcon(new ImageIcon(getClass().getResource("Images//"+night+"e.png")));
   }
}