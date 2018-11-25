package main.expressionBuilder.expressions;

import java.util.regex.Pattern;

public enum ExpressionType {
    CLASS(""),
    METHOD("");

    Pattern regex;
    private ExpressionType(String expressionRegex){
        this.regex = Pattern.compile(expressionRegex);
    }

}
