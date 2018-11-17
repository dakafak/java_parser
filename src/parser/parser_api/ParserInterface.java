package parser.parser_api;

import parser.nodes.ClassNode;
import parser.nodes.Node;
import parser.nodes.ProjectNode;
import parser.nodes.type.NodeType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserInterface {

	public static ProjectNode getParsedProject(final File projectDirectory){
		ParserInterface parserInterface = new ParserInterface();

		ProjectNode projectNode = new ProjectNode(projectDirectory);
		projectNode.setProjectName(projectDirectory.getName());
		projectNode.setClassesForProject(parserInterface.getClassesForProject(projectNode));
		return projectNode;
	}

	private List<Node> getClassesForProject(ProjectNode projectNode){
		List<Node> allClasses = new ArrayList<>();

		List<File> allFilesToParse = getAllFilesForProjectByParentDirectory(projectNode.getProjectDirectory());
		for(File file : allFilesToParse){
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));

				StringBuilder currentReadData = new StringBuilder();
				String readLine;
				while((readLine = br.readLine()) != null){
					String whiteSpaceRemovedReadLine = readLine.trim();
					currentReadData.append(whiteSpaceRemovedReadLine);
				}

				//TODO reformat this to try building for other types of nodes and build nodes until string is empty
				Node classNodeForFile = tryToBuildNodeFromCurrentString(currentReadData.toString());
				if(classNodeForFile != null && NodeType.CLASS.equals(classNodeForFile.getNodeType())){
					allClasses.add(classNodeForFile);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return allClasses;
	}

	private List<File> getAllFilesForProjectByParentDirectory(File parentDirectory){
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

	Pattern classRegex = Pattern.compile("(package.*?;)?(import.*?;)*(((public|private|static)*?\\s)class(.+?\\s))\\{.*\\}");//TODO update this to handle extends and implements
	private Node tryToBuildNodeFromCurrentString(String currentString){
		Matcher matcher = classRegex.matcher(currentString);
		if(matcher.matches()){
			ClassNode classNode = new ClassNode();
			classNode.setClassName(matcher.group(6));
//			classNode.setExtendsForClass();
//			classNode.setImplementsForClass();
			return classNode;
		}

		return null;
	}

	public static void printProject(ProjectNode projectNode){
		System.out.println(projectNode);
	}

}
