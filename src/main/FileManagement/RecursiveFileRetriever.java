package main.FileManagement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecursiveFileRetriever {

	public List<File> getAllJavaFilesForProjectByParentDirectory(File parentDirectory){
		List<File> allChildFiles = recurrsiveGetFiles(parentDirectory);
		return allChildFiles;
	}

	private List<File> recurrsiveGetFiles(File directory){
		List<File> allChildFiles = new ArrayList<>();

		File[] directoryChildren = directory.listFiles();
		if(directoryChildren != null && directoryChildren.length > 0){
			for(File currentFile : directoryChildren){
				if(currentFile.isDirectory()){
					allChildFiles.addAll(recurrsiveGetFiles(currentFile));
				} else if(currentFile.getName().endsWith(".java")){
					allChildFiles.add(currentFile);
				}
			}
		}

		return allChildFiles;
	}

}
