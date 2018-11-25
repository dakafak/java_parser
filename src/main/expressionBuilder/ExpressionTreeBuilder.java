package main.expressionBuilder;

import main.expressionBuilder.expressions.Expression;
import main.tokenizer.tokens.Token;

import java.util.List;
import java.util.regex.Pattern;

public class ExpressionTreeBuilder {

    Pattern classTokenRegex = Pattern.compile("");

    public Expression buildExpressionTreeFromTokenList(List<Token> tokens) {
        Expression expressionTree = recurrsivelyBuildExpressionTree(getTokenStringFromListOfTokens(tokens));

        return expressionTree;
    }

    private String getTokenStringFromListOfTokens(List<Token> tokens){
        StringBuilder tokenStringBuilder = new StringBuilder();

        for(Token token : tokens){
            tokenStringBuilder.append(token);
        }

        return tokenStringBuilder.toString();
    }

    //TODO can regex be used instead of traversing and checking a token list?
    //TODO Maybe make a string with letters repressenting all tokens in the list.
    //TODO  could use regexes to build trees of expressions
    //TODO  can translate back to building an expression tree with the index identification of each token
    private Expression recurrsivelyBuildExpressionTree(String remainingTokenString) {//TODO change list type so it can support faster removal of nodes

    }

}
