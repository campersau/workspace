package aud.example;

import aud.util.*;

import aud.A234Tree;
import aud.KTreeNode;
import java.util.Scanner;

/** example: insert entries 
    @see aud.A234Tree
 */
public class A234TreeExample extends SingleStepper {

  class MyA234Tree extends A234Tree<String> {
    A234TreeExample app_ = null;
    public MyA234Tree(A234TreeExample app) { super(); }
    
    @Override protected void onSplit(KTreeNode<String> node) {
      // TODO decorate node
      halt("insert requires split...");
      super.onSplit(node);
    }
  }
  
  protected MyA234Tree tree_ = null;
  protected DotViewer viewer_ = DotViewer.displayWindow
    ((String) null,"aud.example.A234TreeExample");

  /** create application instance */
  public A234TreeExample() {    
    super("aud.example.A234TreeExample");  
    tree_=new MyA234Tree(this);
  }

  public MyA234Tree getTree() { return tree_; }
  
  @Override protected void onHalt() {
    if (tree_!=null)
      viewer_.display(tree_);
  }   

  /** start interactive example */
  public static void main(String[] args) {   

    final String HELP=
      "usage: java aud.example.A234TreeExample [pause]\n"+
      "       Reads and insert words from standard input.\n"+
      "       'quit' quits.\n"+
      "\tpause [milliseconds] set pause between animation steps\n"+
      "\t      A value of 0 requires to explicitly push the 'continue'\n"+
      "\t      button. The default value is 0 (or the value of then\n"+
      "\t      environment variable 'AUD_TIMEOUT')!\n";
   
    A234TreeExample app=new A234TreeExample();

    if (args.length>0) {
      try {
        app.setTimeout(Integer.parseInt(args[0]));
      } catch (NumberFormatException e) {
        System.err.println(HELP);
        System.exit(-1);
      }
    }

    app.halt("EMPTY TREE");

    Scanner s=new Scanner(System.in);
    s.useDelimiter("\\s+");

    while (s.hasNext()) {
      String key=s.next();
      if (key.compareTo("quit")==0)
        break;      
      else {
        app.getTree().insert(key);
        app.halt("inserted '"+key);
      }
    }
    app.halt("QUIT");
    System.exit(0);
  }
}