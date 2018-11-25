package main.expressionBuilder;

import main.expressionBuilder.expressions.Expression;
import main.expressionBuilder.expressions.ExpressionType;
import main.tokenizer.tokens.Token;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.expressionBuilder.expressions.ExpressionType.CLASS;

public class ExpressionTreeBuilder {

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
        Expression expressionToReturn
        tryMatchingTokenWithString(CLASS, remainingTokenString);
    }

    private String tryMatchingTokenWithString(ExpressionType expressionType, String currentString){
        // Create Matcher
        Matcher tokenMatcher = expressionType.getRegex().matcher(currentString);
        if (tokenMatcher.lookingAt()) {
            String matchedPiece = tokenMatcher.group(0);
//            allTokenTypes.add(new Expression(expressionType, matchedPiece));
            return tokenMatcher.replaceFirst("");
        }
    }

}
