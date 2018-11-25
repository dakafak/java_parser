package main.tokenizer.tokens;

import java.util.regex.Pattern;

public enum TokenType {//TODO change this to enum and have tokens in here instead of new classes for each
	/**
	 * VARIABLES
	 */
	TYPE("int|double|float|String"),
	OBJECT_OR_VARIABLE_NAME("[a-zA-Z]+[a-zA-Z0-9_-]*"),//TODO maybe make this a more generic name
	LITERAL_VALUE("\\d+|'.?'|\".*?\""),

	/**
	 * SYMBOLS
	 */
	EQUALS("="),
	SEMI_COLON(";"),
	COMMA(","),
	OPENING_CURLY_BRACE("\\{"),
	CLOSING_CURLY_BRACE("\\}"),
	OPENING_SQUARE_BRACE("\\["),
	CLOSING_SQUARE_BRACE("\\]"),
	OPENING_ANGLE_BRACE("<"),
	CLOSING_ANGLE_BRACE(">"),
	OPENING_PARENTHESIS("\\("),
	CLOSING_PARENTHESIS("\\)"),
	PERIOD("\\."),
	EXCLAMATION_POINT("\\!"),
	OR("\\|"),
	AND("\\&"),
	AT("@"),
	PERCENT("%"),

	PLUS("\\+"),
	MINUS("\\-"),
	STAR("\\*"),
	FORWARD_SLASH("\\/"),

	/**
	 * COMMENTS
	 */
	SINGLE_LINE_COMMENT("\\/\\/.*?[\\r\\n]"),
	MULTI_LINE_COMMENT("/\\*(.|[\\r\\n])*?\\*/"),
	/**
	 * ADDITIONAL CLASS GARBAGE
	 */
	CLASS_IMPORT("import(\\s)+.*?;")
	;

	public Pattern getRegex() {
		return regex;
	}

	public void setRegex(Pattern regex) {
		this.regex = regex;
	}

	private Pattern regex;
	private TokenType(String regexString){
		this.regex = Pattern.compile(regexString);
	}
}
