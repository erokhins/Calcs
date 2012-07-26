package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public interface ParserNode {
    <T, L> T accept(ExpressionVisitor<T, L> visitor, L l);
}


