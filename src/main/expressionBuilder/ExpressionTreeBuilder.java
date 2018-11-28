package main.expressionBuilder;

import main.expressionBuilder.expressions.Expression;
import main.expressionBuilder.expressions.ExpressionType;
import main.tokenizer.tokens.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.expressionBuilder.expressions.ExpressionType.CLASS;

public class ExpressionTreeBuilder {

	List<ExpressionType> expressionTypesToCheck;

    public Expression buildExpressionTreeFromTokenList(List<Token> tokens) {
    	expressionTypesToCheck = new ArrayList<>();
    	expressionTypesToCheck.add(CLASS);

    	Expression rootProjectExpression = new Expression("Root");
        recurrsivelyBuildExpressionTree(rootProjectExpression, getTokenStringFromListOfTokens(tokens));

        return rootProjectExpression;
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
    private void recurrsivelyBuildExpressionTree(Expression parentExpression, String remainingTokenString) {//TODO change list type so it can support faster removal of nodes
		List<Expression> expressions = new ArrayList<>();
		boolean foundChildNodes = false;

		// Find expression types for parent expressionn
		for(ExpressionType expressionType : expressionTypesToCheck){
			Matcher tokenMatcher = expressionType.getRegex().matcher(remainingTokenString);
			if (tokenMatcher.lookingAt()) {
				foundChildNodes = true;

				String matchedPiece = tokenMatcher.group(0);//TODO should look for middle of expression data and see if that/those strings are empty
				Expression matchedExpression = new Expression(matchedPiece);
				expressions.add(matchedExpression);

				String remainingTokenStringInsideFoundExpression = tokenMatcher.group(1);

				if(foundChildNodes && !remainingTokenStringInsideFoundExpression.isEmpty()){
					for(Expression childExpression : expressions){
						recurrsivelyBuildExpressionTree(childExpression, remainingTokenStringInsideFoundExpression);
					}
				}

				remainingTokenString = tokenMatcher.replaceFirst("");
			}
		}



		parentExpression.setChildExpressions(expressions);
    }

    private String tryMatchingTokenWithString(ExpressionType expressionType, String currentString){
        // Create Matcher
        Matcher tokenMatcher = expressionType.getRegex().matcher(currentString);
        if (tokenMatcher.lookingAt()) {
            String matchedPiece = tokenMatcher.group(0);
//            allTokenTypes.add(new Expression(expressionType, matchedPiece));
            return tokenMatcher.replaceFirst("");
        }
        return "";
    }

}
