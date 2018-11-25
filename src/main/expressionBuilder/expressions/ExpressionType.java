package main.expressionBuilder.expressions;

import java.util.regex.Pattern;

public enum ExpressionType {
    CLASS("(CLASS_IMPORT)*(OBJECT_OR_VARIABLE_NAME)*OPENING_CURLY_BRACE(.*)CLOSING_CURLY_BRACE"),
    METHOD("");

    Pattern regex;
    private ExpressionType(String expressionRegex){
        this.regex = Pattern.compile(expressionRegex);
    }

    public Pattern getRegex(){
        return regex;
    }

}
