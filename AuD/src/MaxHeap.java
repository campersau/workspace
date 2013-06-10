import java.util.*;
public class MaxHeap<T extends Comparable<T>> {
   private ArrayList<T> heap;
   public MaxHeap() {
      // TODO: implementation
   }

   public MaxHeap(T[] a) {
	// TODO: construct a heap from array a (use downHeap)
   }	
 
   public ArrayList<T> getHeap(){
      // do not change because of backend-control
      return heap;
   }
   public int getSize() {
	return 0;
      // TODO: implementation
   }
   public boolean isEmpty() {
	return false;      
      // TODO: implementation
   }
   public void downHeap(int k) {
      // TODO: implementation
   }
   public void insert(T obj) {
      // TODO: implementation, use upHeap
   }
   private void upHeap(int k) {
      // TODO: implementation
   }
	
   public String toString() {
      // do not change because of backend-control
      return heap.toString();
   }
   public static void main(String[] args) {
      // TODO: test
   }
}
