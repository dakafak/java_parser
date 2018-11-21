package main.tokenizer.tokens;

public class Token {

	private String tokenData;
	private TokenType tokenType;

	public Token(TokenType tokenType, String tokenData){
		this.tokenType = tokenType;
		this.tokenData = tokenData;
	}

	public String getTokenData() {
		return tokenData;
	}

	public void setTokenData(String tokenData) {
		this.tokenData = tokenData;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	@Override
	public String toString(){
		return tokenType.toString();
	}
}
