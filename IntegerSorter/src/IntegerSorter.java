/*
 * Interger Sorter
 * By: Jiafang Han and Curtis Jameson
 * April 13th 2011
 */

import java.util.*;
import java.io.*;

public class IntegerSorter implements Sorter{
  int array[];
  public static void main (String[] args){
    long x = 0, y = 0;
    try{
      int readList[]; //defines the array of the list of numbers
      //the following is used to read the random numbers from a text file
      BufferedReader in = new BufferedReader (new FileReader("out.txt"));
      String str;
      while ((str = in.readLine()) != null){
        StringTokenizer st = new StringTokenizer(str);
        int count = st.countTokens();
        readList = new int[count];
        int i = 0;
        while (st.hasMoreTokens()){
          readList[i++] = Integer.decode (st.nextToken());
        }
        for (int i2 = 0; i2 < readList.length; i2++){
          System.out.print (readList[i2] + " ");
        }
        //defining new integers sorters to be sorted using the 3 types
        IntegerSorter a = new IntegerSorter(readList.length);
        IntegerSorter b = new IntegerSorter(readList.length);
        IntegerSorter c = new IntegerSorter(readList.length);
        
        //for the first sorting method
        a.setList(readList);//the list to be used
        x = System.currentTimeMillis();//initial time
        a.sort(1);//sort using method 1
        y = System.currentTimeMillis();//the final time
        System.out.println("");//print statements to show results
        System.out.println ("Sorting method 1: \n" + a);
        System.out.println ("Time elapsed: " + (y - x));
        
        //for the second sorting method
        b.setList(readList);//the list to be used
        x = System.currentTimeMillis();//the initial time
        b.sort(2);//sort using method 2
        y = System.currentTimeMillis();//the final time
        System.out.println ("Sorting method 2: \n" + b);//print to show results
        System.out.println ("Time elapsed: " + (y - x));
        
        c.setList(readList);//the list to be sorted
        x = System.currentTimeMillis();//initial time
        c.sort(2);//make it sorted using 2
        c.sort(3);//divide and sort using method 3
        y = System.currentTimeMillis();//final time
        System.out.println ("Sorting method 3: \n" + c);//print out results
        System.out.println ("Time elapsed: " + (y - x));
      }
      in.close();
    }catch(IOException e){}
    
  }
  
  //constructor class for when new intereger sorters are defined
  public IntegerSorter(int x){
    array = new int[x];
  }
  
  //called upon when a list is set for an integer sorter
  public void setList (int[] list){
    for (int i = 0; i < list.length; i++){
      array[i] = list[i];
    }
  }
  
  //used to sort an array using one of the 3 possible methods
  public void sort (int type){
    int storeHigh,storeLow = 0;//the high and low numbers
    if (type == 1){//first sorting method
      for (int i = 0; i < array.length; i++){//cycles through each element of the array
        for (int i2 = 1; i2 < array.length; i2++){//the nested loop that goes through each element 
          storeHigh = Math.max (array[i2], array[i2 - 1]);//highest of the two numbers
          storeLow = Math.min (array[i2], array[i2 - 1]);//smallest of the two numbers
          array[i2] = storeHigh;//second one becomes bigger number
          array[i2 - 1] = storeLow;//first one becomes smaller number
        }
      }
    }
    
    //the second sorting method
    if (type == 2){
      for (int i = 0; i < array.length; i++){//cycles through each element of the array
        for (int i2 = array.length-1; i2 > 0; i2--){//the nested loop which cycles through the array backwards
          storeHigh = Math.max (array[i2 - 1], array [i2]);//stores the bigger of the two numberrs
          storeLow = Math.min (array[i2 - 1], array [i2]);//stores the smaller number
          array [i2 - 1] = storeLow;//sets the smaller number
          array [i2] = storeHigh;//sets the bigger number
        }
      }
    }
    
    //the third sorting method
    if (type == 3){
      //TO CODE
      int i = array.length / 2;//half the length of the array
      int i2 = array.length - i;//the second half of the array
      int[] array1 = new int[i];//new array that is half the length of the original
      for (int loop = 0; loop < array1.length; loop++){//cycles through each element of the array
        array1[loop] = array[loop];//defines it to be the first half of the original array
      }
      int[] array2 = new int[i2];//the second half of the original
      for (int loop1 = 0; loop1 < i2; loop1++){//cycles through the arrray
        array2[loop1] = array[i2 - 1 + loop1];//defines the second half of the array is set
      }
      array = combineArray(array1, array2); //combines the two new arrays that are half of the original
    }
  }
  
  //used to combine two sorted arrays and sort the resulting array
  public int[] combineArray (int[] a, int[] b){
    int m = a.length + b.length;//to create array of the length of both arrays combined
    int[] c = new int[m];//defines new array
    int aCount = 0;//counnters
    int bCount = 0;
    for (int i = 0; i < m; i++){//cycles through each element of the new array
      c[i] = Math.min (a[aCount], b[bCount]);//gets the smaller of the two elements 
      if (aCount == a.length - 1){//when the first array reaches the end with elements still left in the second array, it copies the rest of the second array into the main array.
    	  while (bCount < b.length && i < m){
    		  c[i] = b[bCount];
    		  i++;
    		  bCount++;
    	  }
    	  break;
      }
      else if (bCount == b.length - 1){//same function as the previous if statement
    	  while (aCount < a.length && i < m){
    		  c[i] = a[aCount];
    		  i++;
    		  aCount++;
    	  }
    	  break;
      }
      if (a[aCount] < b[bCount] && aCount < a.length - 1){//raises the counter of the first array
        aCount++;
      }
      else if (a[aCount] > b[bCount] && bCount < b.length - 1){//raises the counter of the second array
        bCount++;
      }
      else if (a[aCount] == b[bCount] && aCount < a.length - 1 && bCount < b.length - 1){//raises the counter of both arrays if the elements being compared are equal
        aCount++;
        bCount++;
      }
      if (a.length > b.length && bCount == b.length - 2){//copies the rest of longer array into the main array if the shorter array ended
        while (i < m && aCount < a.length){
          c[i] = a[aCount];
          i++;
          aCount++;
        }
        break;
      }
      if (b.length > a.length && aCount == a.length - 2){//same function as the previous if statement
        while (i < m && bCount < b.length){
          c[i] = b[bCount];
          i++;
          bCount++;
        }
        break;
      }
    }
    c[c.length - 1] = array[array.length - 1];
    return c;
  }
  
  //converts the integersorter into a string so that i can be outputted
  public String toString (){
    String s = "";//string to be returned
    for (int i = 0; i < array.length; i++){//through each element of the array
      s = s + array[i] + " ";//adds it to the string
    }
    return s;// returns the new string which contains the array
  }  
}
