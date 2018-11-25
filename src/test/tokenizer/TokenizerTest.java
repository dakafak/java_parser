package test.tokenizer;

import main.tokenizer.Tokenizer;
import main.tokenizer.tokens.Token;
import main.tokenizer.tokens.TokenType;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static main.tokenizer.tokens.TokenType.*;

public class TokenizerTest {

	Tokenizer tokenizer = new Tokenizer();

	String variableTokens = "	\n" +
			"int testVariable = 5;";

	String multiLineCommentString = "/* test */";
	String multiLineCommentString2 = 	"/*\n" +
										" * To change this license header, choose License Headers in Project Properties.\n" +
										" * To change this template file, choose Tools | Templates\n" +
										" * and open the template in the editor.\n" +
										" */";
	String multiLineCommentString3 =
			"/* test */\n\n\n" +
			"/*\n" +
			" * To change this license header, choose License Headers in Project Properties.\n" +
			" * To change this template file, choose Tools | Templates\n" +
			" * and open the template in the editor.\n" +
			" */";

	String singleImportLine = "import static Tools.GType.CHUNK_SIZE;";

	@Test
	public void testVariableToken(){
		TokenType[] expectedTokenTypes = new TokenType[]{
				TYPE, OBJECT_OR_VARIABLE_NAME, EQUALS, LITERAL_VALUE, SEMI_COLON
		};

		checkAssertions("Variable tokens 1", expectedTokenTypes, tokenizer.tryTokenizerForCurrentString(variableTokens));
	}

	@Test
	public void testMultiLineComment(){
		TokenType[] expectedTokenTypes = new TokenType[]{
				MULTI_LINE_COMMENT
		};

		checkAssertions("Multi-line token test 1", expectedTokenTypes, tokenizer.tryTokenizerForCurrentString(multiLineCommentString));
	}

	@Test
	public void testMultiLineComment2(){
		TokenType[] expectedTokenTypes = new TokenType[]{
				MULTI_LINE_COMMENT
		};

		checkAssertions("Multi-line token test 2", expectedTokenTypes, tokenizer.tryTokenizerForCurrentString(multiLineCommentString2));
	}

	@Test
	public void testMultiLineComment3(){
		TokenType[] expectedTokenTypes = new TokenType[]{
				MULTI_LINE_COMMENT, MULTI_LINE_COMMENT
		};

		checkAssertions("Multi-line token test 3", expectedTokenTypes, tokenizer.tryTokenizerForCurrentString(multiLineCommentString3));
	}

	@Test
	public void testImportType(){
		TokenType[] expectedTokenTypes = new TokenType[]{
				CLASS_IMPORT
		};

		checkAssertions("Class import test", expectedTokenTypes, tokenizer.tryTokenizerForCurrentString(singleImportLine));
	}

	@Test
	public void testParsingLargeFile(){
		System.out.println(tokenizer.tokenizeFile(new File("src/test/resources/TestParseFile")));
	}

	private void checkAssertions(String testName, TokenType[] expectedTypes, List<Token> tokens){
		System.out.println(testName + ": " + tokens);
		Assert.assertEquals(expectedTypes.length, tokens.size());
		for(int i = 0; i < expectedTypes.length; i++){
			Assert.assertEquals(expectedTypes[i], tokens.get(i).getTokenType());
		}
	}

}
