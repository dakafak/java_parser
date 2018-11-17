package parser.nodes;

import parser.nodes.type.NodeType;

import java.util.List;

public class ClassNode extends Node {

    String className;
    String extendsForClass;
    List<String> implementsForClass;

    public String getExtendsForClass() {
        return extendsForClass;
    }

    public void setExtendsForClass(String extendsForClass) {
        this.extendsForClass = extendsForClass;
    }

    public List<String> getImplementsForClass() {
        return implementsForClass;
    }

    public void setImplementsForClass(List<String> implementsForClass) {
        this.implementsForClass = implementsForClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("->");
        stringBuilder.append(className);
        stringBuilder.append(System.lineSeparator());
        return stringBuilder.toString();
    }

    @Override
    public NodeType getNodeType() {
        return NodeType.CLASS;
    }
}
