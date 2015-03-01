package org.zs.audit.model;

import java.util.Set;

public class GraphDto {
	private Set<Node> nodes;
	private Set<Edge> edges;
	
	public GraphDto() {
		super();
	}
	
	public GraphDto(Set<Node> nodes, Set<Edge> edges) {
		super();
		this.nodes = nodes;
		this.edges = edges;
	}

	public Set<Node> getNodes() {
		return nodes;
	}
	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}
	public Set<Edge> getEdges() {
		return edges;
	}
	public void setEdges(Set<Edge> edges) {
		this.edges = edges;
	}
	
}
