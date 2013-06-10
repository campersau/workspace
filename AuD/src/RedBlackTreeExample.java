/**********************
 a)
 Ein Rot-Schwarz-Baum (red-black tree) ist ein binaerer Suchbaum,
in dem jedem Konten ein Farbattribut zugeordnet ist, so dass gilt
    1 Ein Konten ist entweder rot oder schwarz.
    2 Die Wurzel ist schwarz.
    3 Alle Blaetter sind schwarz.
    4 Beide Kinder eines roten Knotens sind schwarz.
    5 Jeder Pfad von einem gegebenen Knoten zu einem
         erreichbaren Blatt enthält dieselbe Anzahl schwarzer Knoten.

b) siehe Papier
Rot-Rot-Verstoesse:  Wenn wir ein Element an einen untersten Teilbaum anhaengen, welcher bereits
                    Rot ist. Beispie: anhaengen der 1

c) siehe papier
    gleicher Aufbau des Baumes, bis Rot-Rot-Verstoss bzw. Unausgeglichenheit
    
d)    Das mittlere Element des 2-3-4 Baumes ist ein schwarzer Knoten des ROT-SCHWARZ-Baumes.
 *********************/
import aud.RedBlackTree;
import aud.AVLTree;
import aud.util.DotViewer;
import aud.util.SingleStepper;

public class RedBlackTreeExample {

    public static void main(String[] args) {
        RedBlackTree<Integer, Object> tree = new RedBlackTree<Integer, Object>();
        AVLTree<Integer, Object> tree2 = new AVLTree<Integer, Object> ();

        // [6,7,3,4,2,1]

        tree.insert(6, null);
        tree.insert(7, null);
        tree.insert(3, null);
        tree.insert(4, null);
        tree.insert(2, null);
        tree.insert(1, null);
        
        tree2.insert(6, null);
        tree2.insert(7, null);
        tree2.insert(3, null);
        tree2.insert(4, null);
        tree2.insert(2, null);
        tree2.insert(1, null);

        DotViewer.displayWindow(tree, "RS-Baum");
        DotViewer.displayWindow(tree2, "AVL-Baum");
    }
}