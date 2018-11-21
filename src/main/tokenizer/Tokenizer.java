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
				totalReadString += readLine.trim();
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

	Pattern leadingWhitespaceRegex = Pattern.compile("^\\s+");

	boolean foundATokenMatch = false;
	public List<Token> tryTokenizerForCurrentString(String currentString){
		List<Token> allTokenTypes = new ArrayList<>();

		foundATokenMatch = false;
		while(!currentString.isEmpty()){
			// Test regexes
			currentString = tryMatchingTokenWithString(TYPE, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(OBJECT_OR_VARIABLE_NAME, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(EQUALS, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(LITERAL_VALUE, currentString, allTokenTypes);
			currentString = tryMatchingTokenWithString(SEMI_COLON, currentString, allTokenTypes);

			// Check if error and should stop
			if(!foundATokenMatch && !currentString.isEmpty()){
				System.out.println("Coulld not find a match for the remaining:");
				System.out.println(currentString);
				break;
			}
		}



		return allTokenTypes;
	}

	private String tryMatchingTokenWithString(TokenType tokenType, String currentString, List<Token> allTokenTypes){
		// Replace leading whitespace
		currentString = currentString.replaceFirst("^\\s+", "");

		// Create Matcher
		Matcher tokenMatcher = tokenType.getRegex().matcher(currentString);

		System.out.println("trying to match: " + currentString);
		if(tokenMatcher.lookingAt()){
			foundATokenMatch = true;
			String matchedPiece = tokenMatcher.group(0);
			System.out.println("matched piece: " + matchedPiece);
			allTokenTypes.add(new Token(tokenType, matchedPiece));

			return currentString.replaceFirst(matchedPiece, "");
		}

		return currentString;
	}

}
