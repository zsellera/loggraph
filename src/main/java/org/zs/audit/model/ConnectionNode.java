package org.zs.audit.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ConnectionNode extends Node {
	private final List<EntityNode> links;
	private final List<Edge> edges;
	private final List<LogMessage> messages = new LinkedList<>();

	public ConnectionNode(long id, String type, List<EntityNode> links, long startEdgeId) {
		super(id, type, BOX);
		this.links = links;
		this.edges = createEdges(id, links, startEdgeId);
	}
	
	static String createKey(String type, List<EntityNode> nodes) {
		final StringBuilder sb = new StringBuilder();
		sb.append(type);
		for (EntityNode en : nodes) {
			sb.append('|');
			sb.append(en.getId());
		}
		return sb.toString();
	}
	
	static List<Edge> createEdges(long id, List<EntityNode> links, long startCntr) {
		ArrayList<Edge> edges = new ArrayList<Edge>(links.size());
		for (EntityNode e: links) {
			edges.add(new Edge(++startCntr, id, e.getId()));
		}
		return edges;
	}
	
	@JsonIgnore
	public String getKey() {
		return createKey(getLabel(), links);
	}
	
	@JsonIgnore
	public List<Edge> getEdges() {
		return this.edges;
	}

	@JsonIgnore
	public List<LogMessage> getMessages() {
		return this.messages ;
	}
	
	@JsonIgnore
	public List<EntityNode> getLinks() {
		return links;
	}
	
	public boolean linksTo(long nodeId) {
		for (EntityNode en: links) {
			if (en.getId() == nodeId) return true;
		}
		return false;
	}

	public void addMessage(LogMessage logMessage) {
		this.messages.add(logMessage);
	}
}
