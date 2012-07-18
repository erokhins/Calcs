package org.hanuna.calcs.parser;

/**
 * @author erokhins
 */
public interface ParserNode {
    <T> T accept(ExpressionVisitor<T> visitor);
}


