package org.zs.audit.model;

public class Edge {
	private final long id;
	private final long from;
	private final long to;
	
	public Edge(long id, long from, long to) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
	}
	
	public long getId() {
		return id;
	}
	
	public long getFrom() {
		return from;
	}
	
	public long getTo() {
		return to;
	}
}
