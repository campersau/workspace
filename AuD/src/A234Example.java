/*****************
answer a)
Ein 2-3-4 Baum ist ein Baum mit 1-2-3 Dattenelementen und dementsprechend ist 
der Name mit deren 2-3-4 Verzweigungen bennant.

answer b)

2,6,4,14,16,8,12,20,10,18 top down

2

2|6

2|4|6

   4
 2   6|14
 
   4
 2  6|14|16

     4|14
  2  6|8  16 

     4|14
  2 6|8|12 16 
  
     4|14
  2 6|8|12 16|20
  
     4|8|14
   2 6 10|12 16|20
   
         8
    4         14
 2    6  10|12 16|18|20
  
  
  
2,4,6,8,10,12,14,16,18,20 top down

2

2|4

2|4|6

   4
 2   6|8
 
   4
 2   6|8|10

   4|8
 2  6  10|12  

    4|8
 2   6   10|12|14
 
    4|8|12
 2   6  10  14|16
    
      8
   4    12
 2  6  10  14|16|18
 
      8
   4    12|16
 2  6  10  14|18|20

2, 6, 4, 14, 16, 8, 12, 20, 10 und 18

bottom up

2

2|6

2|4|6

  4
2   6|14

  4
2   6|14|16

    4|14
2    6|8        16

    4|14
2    6|8|12        16

    4|14
2    6|8|12        16|20

    4|8|14
2    6    10|12        16|20

    4|8|14
2    6    10|12        16|18|20

2, 4, 6, 8, 10, 12, 14, 16, 18 und 20

bottom-up

    2
    
    2|4
    
    2|4|6
    
    4
2        6|8

    4
2        6|8|10

    4|8
2    6    10|12

    4|8
2    6    10|12|14

    4|8|12
2    6    10    14|16

    4|8|12
2    6    10    14|16|18

            8
    4                12|16
2        6        10    14    18|20

2-3-4 leuft mit bottom up
*********************/

import aud.A234Tree;
import aud.util.DotViewer;


public class A234Example {
   public static void main(String[] args) {
       A234Tree<Integer> treetd = new A234Tree<Integer> ();
       A234Tree<Integer> treetd2 = new A234Tree<Integer> ();
       
       treetd.insert(2);
       treetd.insert(6);
       treetd.insert(4);
       treetd.insert(14);
       treetd.insert(16);
       treetd.insert(8);
       treetd.insert(12);
       treetd.insert(20);
       treetd.insert(10);
       treetd.insert(18);
       
       treetd2.insert(2);
       treetd2.insert(4);
       treetd2.insert(6);
       treetd2.insert(8);
       treetd2.insert(10);
       treetd2.insert(12);
       treetd2.insert(14);
       treetd2.insert(16);
       treetd2.insert(18);
       treetd2.insert(20);
       
       DotViewer.displayWindow(treetd, "2-3-4 Baum");
       DotViewer.displayWindow(treetd2, "2-3-4 Baum2");
       
   }
}