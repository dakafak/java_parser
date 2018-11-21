package test.tokenizer;

import main.tokenizer.Tokenizer;
import main.tokenizer.tokens.Token;
import main.tokenizer.tokens.TokenType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static main.tokenizer.tokens.TokenType.*;

public class TokenizerTest {

	Tokenizer tokenizer = new Tokenizer();

	String variableTokens = "	int testVariable = 5;";

	@Test
	public void testVariableToken(){
		TokenType[] expectedTokenTypes = new TokenType[]{
				TYPE, OBJECT_OR_VARIABLE_NAME, EQUALS, LITERAL_VALUE, SEMI_COLON
		};

		List<Token> tokens = tokenizer.tryTokenizerForCurrentString(variableTokens);

		checkAssertions("Variable tokens 1", expectedTokenTypes, tokens);
	}

	private void checkAssertions(String testName, TokenType[] expectedTypes, List<Token> tokens){
		System.out.println(testName + ": " + tokens);
		Assert.assertEquals(expectedTypes.length, tokens.size());
		for(int i = 0; i < expectedTypes.length; i++){
			Assert.assertEquals(expectedTypes[i], tokens.get(i).getTokenType());
		}
	}

}
