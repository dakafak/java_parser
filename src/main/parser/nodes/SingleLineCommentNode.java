package main.parser.nodes;

import main.parser.nodes.type.NodeType;

public class SingleLineCommentNode extends Node {
	@Override
	public NodeType getNodeType() {
		return NodeType.SINGLE_LINE_COMMENT;
	}
}
