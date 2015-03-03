package org.zs.audit.model;

public abstract class Node implements Comparable<Node> {
	public static final String BOX = "box";
	public static final String CIRCLE = "circle";
	public static final String ELLIPSE = "ellipse";
	public static final String SQUARE = "square";
	public static final String DOT = "dot";
	
	private final long id;
	private final String label;
	private final String shape;
	private final int size = 2;
	
	protected Node(long id, String label, String shape) {
		super();
		this.id = id;
		this.label = label;
		this.shape = shape;
	}
	
	@Override
	public int compareTo(Node that) {
		return Long.compare(this.id, that.id);
	}

	public long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public String getShape() {
		return shape;
	}

	public int getSize() {
		return size;
	}
}
