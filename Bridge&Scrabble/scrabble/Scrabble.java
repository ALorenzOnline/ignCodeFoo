import java.util.*;
import java.io.*;
public class Scrabble{
public static void main(String[] args){
   //Scrabble challenge!!!!!
   
   String onePoint = "aeioulnstr";
   String twoPoint = "dg";
   String threePoint = "bcmp";
   String fourPoint = "fhvwy";
   String fivePoint = "k";
   String eightPoint = "jx";   
   
   File file = new File("scrabbleWords.txt");
   
   try{
         /*
         @@ reads in a new file then check it one word at a time.
         @@ It then checks each letter of the word one at a time
         @@ then it sees if the letter is located in one of the 
         @@ strings declared above. It then assigns points based
         @@ on which string the character is located in. 
         */
         Scanner read = new Scanner(file);
         int highestValue = 0;
         int checkValue = 0;
         String winner ="";
         String check = "";
         char holder;
         while(read.hasNextLine()){
            check=read.next();
            for(int i=0;i<check.length();i++){
               holder=check.charAt(i);
               
               if(onePoint.indexOf(holder)>=0){
                 checkValue++;
               }
               else if(twoPoint.indexOf(holder)>=0){
                 checkValue=checkValue+2;
               
               }
               else if(twoPoint.indexOf(holder)>=0){
                 checkValue=checkValue+2;
                 
               } 
               else if(threePoint.indexOf(holder)>=0){
                 checkValue=checkValue+3;
                 
               }
               else if(fourPoint.indexOf(holder)>=0){
                 checkValue=checkValue+4;
                 
               }
                else if(fivePoint.indexOf(holder)>=0){
                 checkValue=checkValue+5;
               
               }
               else if(eightPoint.indexOf(holder)>=0){
                 checkValue=checkValue+8;
                 
               } 
               else{
                 checkValue=checkValue+10;
                 
               }
              
               
            
            }
            if(checkValue>highestValue){
               winner=check;
               highestValue=checkValue;
            }
            //System.out.println(check + " " + checkValue );
            checkValue=0;
         }  
         System.out.println(winner + " had the highest value in the file with " + highestValue + " points" );
      }
      catch(FileNotFoundException e){
         e.printStackTrace();
      }
   
   }
}