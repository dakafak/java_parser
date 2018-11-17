package parser.nodes;

import parser.nodes.type.NodeType;

import java.io.File;
import java.util.List;

public class ProjectNode extends Node {

    String projectName;
    File projectDirectory;
    List<Node> classesForProject;

    public ProjectNode(File projectDirectory){
        this.projectDirectory = projectDirectory;

    }

    public File getProjectDirectory() {
        return projectDirectory;
    }

    public void setProjectDirectory(File projectDirectory) {
        this.projectDirectory = projectDirectory;
    }

    public List<Node> getClassesForProject() {
        return classesForProject;
    }

    public void setClassesForProject(List<Node> classesForProject) {
        this.classesForProject = classesForProject;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString(){
        StringBuilder toStringBuilder = new StringBuilder();
        toStringBuilder.append(projectName);
        toStringBuilder.append(System.lineSeparator());
        for(Node classNode : classesForProject){
            toStringBuilder.append(classNode);
            toStringBuilder.append(System.lineSeparator());
        }
        return toStringBuilder.toString();
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.PROJECT;
    }
}
