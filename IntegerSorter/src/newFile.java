//creates a new file to be read
import java.io.*;
import java.util.*;
public class newFile{
  public static void main (String[] args){
    createFile();
  }
  
  public static void createFile () {
    Random generator = new Random();
    try{
      FileWriter fstream = new FileWriter ("out.txt");
      BufferedWriter out = new BufferedWriter(fstream);
      for (int i = 0; i < 100; i++){
        out.write (generator.nextInt(100) + " ");
      }
      out.close();
    }
    catch (Exception e){
      System.err.println ("Error: " + e.getMessage());
    }
  }
}