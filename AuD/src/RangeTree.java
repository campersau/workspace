import java.util.Iterator;
import java.util.NoSuchElementException;
public class RangeTree<T extends Comparable<T>> extends SimpleTree<T>
implements Iterable<T> {
	public T begin_, end_;
	public SimpleTree.Node next;

	public RangeTree(T b, T e) {
		super();
		begin_ = b;
		end_ = e;
	}

	public void setRange(T b, T e) {
		// Do not change (used for backend control)
		begin_ = b;
		end_ = e;
	}

	public class TreeIterator implements Iterator<T> {

		public TreeIterator(RangeTree<T> rangeTree) {
			next = getRoot();
			if(next == null)
				return;
			while (next.left != begin_)
				next = next.left;
		}

		@Override
		public boolean hasNext() {
			return this.next() != null;
		}

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			Node r = next;
			// if you can walk right, walk right, then fully left.
			// otherwise, walk up until you come from left.
			if(next.right != end_){
				next = next.right;
				while (next.left != begin_)
					next = next.left;
			}else while(true){
				if(next.parent == null){
					next = null;
					return (T) r;
				}
				if(next.parent.left == next){
					next = next.parent;
					return (T) r;
				}
				next = next.parent;
			}
			return null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}
		// TODO: implementation

	}

	public Iterator<T> iterator() {
		return new TreeIterator(this);
	}

	public static void main(String[] args) {
		// TODO: test	
	}
}
