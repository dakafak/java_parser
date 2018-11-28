package main.expressionBuilder.expressions;

import java.util.List;

public class Expression {

	//TODO add object to hold data from token
	String expressionName;
    List<Expression> childExpressions;

    public Expression(String expressionName){
    	this.expressionName = expressionName;
	}

	public String getExpressionName() {
		return expressionName;
	}

	public void setExpressionName(String expressionName) {
		this.expressionName = expressionName;
	}

	public List<Expression> getChildExpressions() {
		return childExpressions;
	}

	public void setChildExpressions(List<Expression> childExpressions) {
		this.childExpressions = childExpressions;
	}
}
