import java.awt.*;



import java.awt.event.*;

import java.applet.Applet;

public class AZERTY extends Applet implements KeyListener{
   
   //private Rectangle rect;//window
   public String strInput = "";
   char sticky;
   
  public void init(){
  
   this.addKeyListener(this);
   //rect = new Rectangle(0,0,50,50);
 
  }
   
   public void paint(Graphics g){
      //paints window to the screen
      setSize(500,500);
      g.drawString("Zelcome to the Zonderful of AZERTY, 'H' is your sticky key, SHITFT is H ", 50, 50);
      g.drawString(strInput, 50, 100);
   }
   
   
@Override
   public void keyPressed(KeyEvent e){
      if(e.getKeyCode()==KeyEvent.VK_Q){
    	 
    	  if(sticky == 'h'){
    		  //keys.remove(0);
    		  strInput= strInput + Character.toUpperCase('a');
    		  sticky='p';
    	  }
    	  else{
    		  strInput=strInput+ "a";
    	  }
      }
      else if(e.getKeyCode()==KeyEvent.VK_A){
    	  if(sticky == 'h'){
    		  //keys.remove(0);
    		  strInput= strInput + Character.toUpperCase('q');
    		  sticky='p';
    	  }
    	  else{
    		  strInput=strInput+ "q";
    	  }
      }
      else if(e.getKeyCode()==KeyEvent.VK_W){
    	  if(sticky == 'h'){
    		  //keys.remove(0);
    		  strInput= strInput + Character.toUpperCase('z');
    		  sticky='p';
    	  }
    	  else{
    		  strInput=strInput+ "z";
    	  }
      }
      else if(e.getKeyCode()==KeyEvent.VK_Z){
    	  if(sticky == 'h'){
    		  //keys.remove(0);
    		  strInput= strInput + Character.toUpperCase('w');
    		  sticky='p';
    	  }
    	  else{
    		  strInput=strInput+ "w";
    	  }
      }
      else if(e.getKeyCode()== KeyEvent.VK_H){
           sticky='h';
           System.out.println(sticky);
      }
      else if(e.getKeyCode()== KeyEvent.VK_SHIFT){
    	  strInput=strInput+ "h";
    	  //sticky='p';
         
     }
      else{
    	  if(sticky == 'h'){
    		  //keys.remove(0);
    		  strInput= strInput + Character.toUpperCase(e.getKeyChar());
    		  sticky='p';
    	  }
    	  else{
    		  strInput= strInput + e.getKeyChar();
    		  //System.out.println((int)e.getKeyChar());
    		  //System.out.println(e.isShiftDown());
    		  //sticky='p';
    	  }
      }
      
      repaint();
   }
   @Override
    public void keyReleased(KeyEvent e){
      //TODO
   }
   @Override
    public void keyTyped(KeyEvent e){
      //TODO
   }
   


}