package test.parser.parser_api;

import main.parser.parser_api.ParserInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	ParserInterface parserInterface;

	@Before
	public void init(){
		parserInterface = new ParserInterface();
	}

	@Test
	public void parseClassStringTest() {

	}

	@Test
	public void parseSingleLineCommentsTest(){
//		Assert.assertNotNull(parserInterface.parseSingleLineCommentNode(testString));
	}

}
