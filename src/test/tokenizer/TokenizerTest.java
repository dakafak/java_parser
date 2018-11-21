package test.tokenizer;

import main.tokenizer.Tokenizer;
import main.tokenizer.tokens.TokenType;
import org.junit.Test;

import static main.tokenizer.tokens.TokenType.*;

public class TokenizerTest {

	String variableTokens = "	int testVariable = 5;";

	@Test
	public void testVariableToken(){
		TokenType[] expectedTokenTypes = new TokenType[]{
				TYPE, OBJECT_OR_VARIABLE_NAME, EQUALS, LITERAL_VALUE, SEMI_COLON
		};

		Tokenizer tokenizer = new Tokenizer();
		System.out.println(tokenizer.tryTokenizerForCurrentString(variableTokens));
	}

}
