package org.hanuna.calcs.syntaxtree;

/**
 * @author erokhins
 */
public class SyntaxTreeUtils {

    public static void checkNode(SyntaxTreeNode n) {
        if (n == null) {
            throw new NullPointerException("node of syntax tree is null");
        }
        if (n instanceof SyntaxTreeNodeBinary) {
            if (((SyntaxTreeNodeBinary) n).getLeft() == null) {
                throw new NullPointerException("left children of node is null");
            }
            if (((SyntaxTreeNodeBinary) n).getRight() == null) {
                throw new NullPointerException("right children of node is null");
            }
        }

        if (n instanceof SyntaxTreeNodeUnary) {
            if (((SyntaxTreeNodeUnary) n).getOperand() == null) {
                throw new NullPointerException("operand of node is null");
            }
        }
    }

}
