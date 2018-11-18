package main.tokenizer;

import main.FileManagement.RecursiveFileRetriever;

import java.io.File;
import java.util.List;

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

	public void tokenizeFile(File fileToTokenize){

	}

}
