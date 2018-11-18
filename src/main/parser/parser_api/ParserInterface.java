package main.parser.parser_api;

import main.parser.nodes.ClassNode;
import main.parser.nodes.Node;
import main.parser.nodes.ProjectNode;
import main.parser.nodes.SingleLineCommentNode;
import main.parser.nodes.type.NodeType;

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

	//TODO add unit test for this
	//TODO move parsing to new class

	Pattern classRegex = Pattern.compile("(package.*?;)?(import.*?;)*((public|private|static)*?\\s)(class\\s([a-zA-Z0-9]+))(\\sextends\\s([a-zA-Z0-9]+))?(\\simplements\\s([a-zA-Z0-9,\\s]+))?\\s*\\{(.*)\\}");

	protected Node tryToBuildNodeFromCurrentString(String currentString){
		Matcher matcher = classRegex.matcher(currentString);

		if(matcher.matches() && matcher.groupCount() >= 6 && matcher.group(6) != null){
			ClassNode classNode = new ClassNode();
			classNode.setClassName(matcher.group(6));
			if(matcher.groupCount() >= 8 && matcher.group(8) != null) {
				classNode.setExtendsForClass(matcher.group(8));
			}
			if(matcher.groupCount() >= 10 && matcher.group(10) != null) {
				classNode.setImplementsForClass(matcher.group(10).replaceAll("\\s", "").split(","));
			}
			if(matcher.groupCount() >= 11 && matcher.group(11) != null) {
				String remainingString = matcher.group(11);//TODO need to reformat this to return this group so that this can be parsed after the class core
				addInnerClassNodesToClass(classNode, remainingString);
			}
			return classNode;
		}

		return null;
	}

	private void addInnerClassNodesToClass(ClassNode classNode, String remainingStringToParse){

	}

	Pattern singleLineCommentRegex = Pattern.compile("\\/\\/.*");
	public SingleLineCommentNode parseSingleLineCommentNode(String stringToParse){
		Matcher singleLineCommentMatcher = singleLineCommentRegex.matcher(stringToParse);

		if(singleLineCommentMatcher.matches()){
			SingleLineCommentNode singleLineCommentNode = new SingleLineCommentNode();
			return singleLineCommentNode;
		}

		return null;
	}

	public static void printProject(ProjectNode projectNode){
		System.out.println(projectNode);
	}

}
