/*****************
answer a)
Fuer jeden (inneren) Knoten ist der absolute Betrag der 
Differenz der Hoehe des linken und rechten Teilbaumes maximal 1.
Um die Tiefensuche zu vereinfachen.

answer b)

1.Einfuegen in linken Teilbaum des linken Kindes
2.Einfuegen in rechten Teilbaum des linken Kindes
3.Einfuegen in rechten Teilbaum des rechten Kindes
4.Einfuegen in linken Teilbaum des rechten Kindes

Rotationsregeln:

-Einfuegen in linken Teilbaum des linken Kindes -> Rotation mit linkem Kind
-Einfuegen in rechten Teilbaum des linken Kindes -> Doppelrotatiion mit linkem Kind
-Einfuegen in rechten Teilbaum des rechten Kindes -> Rotation mit rechtem Kind
-Einfuegen in linken Teilbaum des rechten Kindes -> Doppelrotation mit rechtem Kind

*********************/
import aud.AVLTree;
import aud.util.DotViewer;

public class AVLExample {
   public static void main(String[] args) {
      
	   AVLTree<Integer,Object> tree=new AVLTree<Integer,Object> ();
	   
	   int a,b,c,d,e,f,g;
	   a = 14;
	   b = 15;
	   c = 17;
	   d = 7;
	   e = 5;
	   f = 10;
	   g = 16;
	   
	   tree.insert(a, null);
	   tree.insert(c, null);
	   tree.insert(d, null);
	   tree.insert(e, null);
	   tree.insert(f, null);
	   tree.insert(g, null);
	   
	   
	   DotViewer.displayWindow(tree, "abc");
	   

   }
}
