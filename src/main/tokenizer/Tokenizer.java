package main.tokenizer;

import main.FileManagement.RecursiveFileRetriever;
import main.tokenizer.tokens.Token;
import main.tokenizer.tokens.TokenType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static main.tokenizer.tokens.TokenType.*;

public class Tokenizer {

	RecursiveFileRetriever recursiveFileRetriever;

	public Tokenizer(){
		recursiveFileRetriever = new RecursiveFileRetriever();
	}

	public void tokenizeFilesForParent(File parentDirectory){
		List<File> allJavaFilesToTokenize = recursiveFileRetriever.getAllJavaFilesForProjectByParentDirectory(parentDirectory);
		for(File file : allJavaFilesToTokenize){
			tokenizeFile(file);
		}
	}

	public List<Token> tokenizeFile(File fileToTokenize){
		try {
			String totalReadString = "";

			BufferedReader br = new BufferedReader(new FileReader(fileToTokenize));

			String readLine;//TODO try changing this to be tokenize one line at a time from the file
			while((readLine = br.readLine()) != null){
				totalReadString += readLine.trim() + '\n';
			}

			return tryTokenizerForCurrentString(totalReadString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			new ArrayList<>();
		}

		return new ArrayList<>();
	}

	Pattern leadingWhitespaceOrNewLineRegex = Pattern.compile("(\\s|\\\\n)+");

	boolean foundATokenMatch = false;
	public List<Token> tryTokenizerForCurrentString(String currentString){
		List<Token> allTokenTypes = new ArrayList<>();

		while(!currentString.isEmpty()){
			foundATokenMatch = false;
			// Test regexes
			currentString = tryMatchingTokenWithString(CLASS_IMPORT, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(MULTI_LINE_COMMENT, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(SINGLE_LINE_COMMENT, currentString, allTokenTypes);

			currentString = tryMatchingTokenWithString(TYPE, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(OBJECT_OR_VARIABLE_NAME, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(EQUALS, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(LITERAL_VALUE, currentString, allTokenTypes);

			currentString = tryMatchingTokenWithString(SEMI_COLON, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(COMMA, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(OPENING_CURLY_BRACE, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(CLOSING_CURLY_BRACE, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(OPENING_SQUARE_BRACE, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(CLOSING_SQUARE_BRACE, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(OPENING_ANGLE_BRACE, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(CLOSING_ANGLE_BRACE, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(OPENING_PARENTHESIS, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(CLOSING_PARENTHESIS, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(PERIOD, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(EXCLAMATION_POINT, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(OR, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(AND, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(AT, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(PERCENT, currentString, allTokenTypes);

			currentString = tryMatchingTokenWithString(PLUS, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(MINUS, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(STAR, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(FORWARD_SLASH, currentString, allTokenTypes);

			// Check if error and should stop
			if(!foundATokenMatch){
				System.out.println("Could not find a match for the remaining:");
				System.out.println(currentString);
				break;
			}
		}

		return allTokenTypes;
	}

	private String tryMatchingTokenWithString(TokenType tokenType, String currentString, List<Token> allTokenTypes){
		// Replace leading whitespace
		if(!foundATokenMatch) {
			currentString = currentString.trim();

			// Create Matcher
			Matcher tokenMatcher = tokenType.getRegex().matcher(currentString);
			tokenMatcher.
			if (tokenMatcher.lookingAt()) {
				foundATokenMatch = true;
				String matchedPiece = tokenMatcher.group(0);
				allTokenTypes.add(new Token(tokenType, matchedPiece));
				return tokenMatcher.replaceFirst("");
			}
		}

		return currentString;
	}

}
