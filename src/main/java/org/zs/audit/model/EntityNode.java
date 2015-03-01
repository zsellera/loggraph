package org.zs.audit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class EntityNode extends Node {
	private final Entity key;
	
	public EntityNode(long id, Entity key) {
		super(id, makeLabel(key), makeShape(key));
		this.key = key;
	}
	
	static String makeLabel(Entity e) {
		return e.getType() + ":" + e.getKey();
	}
	
	static String makeShape(Entity e) {
		return ELLIPSE;
	}

	@JsonIgnore
	public Entity getKey() {
		return key;
	}

}
