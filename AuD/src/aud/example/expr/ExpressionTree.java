package aud.example.expr;

import aud.BinaryTree;

import aud.util.GraphvizDecorator;
import aud.util.SimpleDecorator;

/** Tree representation of arithmetic expression.<p>

    Each node is associated with an {@link AtomicExpression},
    which can be either an {@link Terminal} or an {@link Operator}.
    @see AtomicExpression
 */
public class ExpressionTree extends BinaryTree<AtomicExpression> {   
  /** creare and link tree node */
  public ExpressionTree(AtomicExpression atom,
                        ExpressionTree left,ExpressionTree right) {
    super(atom,left,right);
    atom.node_=this;
  }
  /** create tree node */
  public ExpressionTree(AtomicExpression atom) {
    this(atom,null,null);
  }

  /** compute value of expression 
      @throws UnsupportedOperationException if value cannot be determined
      @return value obtained from {@code getData()}     
      @see AtomicExpression#getValue
   */
  public double getValue() { return getData().getValue(); }

  @Override public String toString() {
    if (getData().isTerminal())
      return getData().toString();

    assert(!isLeaf());
    assert(getData().isOperator());

    String term=(!isRoot() ? "(" : "");
    if (getRight()!=null)
      term+=getLeft().toString()+getData().toString()+getRight().toString();    
    else
      term+=getData().toString()+getLeft().toString(); // unary operator
    
    if (!isRoot()) term+=")";
    
    return term;
  }

  static final GraphvizDecorator decorator = new SimpleDecorator();
  @Override public GraphvizDecorator getDecorator() {
    return decorator;
  }


  /** test and example for usage */
  public static void main(String[] args) {
    ExpressionTree t=new ExpressionTree
      (new Minus(),
       new ExpressionTree
       (new Times(),
        new ExpressionTree(new Plus(),
                           new ExpressionTree(new Number(2)),
                           new ExpressionTree(new Number(3))),         
        new ExpressionTree(new Number(4))
       ),
       new ExpressionTree(new Number(5))
      );   

    System.out.println(t);
    System.out.println(t.getValue());
    System.out.println(t.postorder().toString());   

    t=new ExpressionTree(new Plus(),
                         t,new ExpressionTree(new Symbol("x")));
    System.out.println(t);
    System.out.println(t.getValue()); // throws
  }
}