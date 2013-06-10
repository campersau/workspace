package aud.example.expr;

/** Superclass for data associated with a tree node.
    @see ExpressionTree
 */
public abstract class AtomicExpression {
  /** uplink reference to node: {@code node_.getData()==this} <p>

      Set by constructor of {@link ExpressionTree}. <em>This is not
      good style but alllows for reusing {@link aud.BinaryTree} as
      is.</em>
   */
  protected ExpressionTree node_ = null;

  /** node represents operator */
  public boolean isOperator() { return !isTerminal(); }
  /** node represents number or symbol */
  public boolean isTerminal() { return node_.isLeaf(); }  

  /** type identifiers returned by {@link #getType} */
  public enum Type {
    OpPlus, OpMinus, OpUnaryMinus, OpTimes, OpDivide, OpPower,
    TNumber, TSymbol
  }
  /** Get type identifier. */
  public abstract Type getType();
  
  /** get value
      @throws UnsupportedOperationException if value cannot be determined
   */
  public abstract double getValue();
}
