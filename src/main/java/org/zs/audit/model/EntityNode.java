package org.zs.audit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class EntityNode extends Node {
	private final Entity key;
	
	public EntityNode(long id, Entity key) {
		super(id, makeLabel(key), makeShape(key));
		this.key = key;
	}
	
	static String makeLabel(Entity e) {
		String key = e.getKey();
		int hiddenPart = key.indexOf('|');
		if (hiddenPart > 0) {
			key = e.getKey().substring(0, hiddenPart);
		}
		if (key.length() > 15) {
			key = key.substring(0, 12) + "...";
		}
		return e.getType() + ":" + key;
	}
	
	static String makeShape(Entity e) {
		return BOX;
	}

	@JsonIgnore
	public Entity getKey() {
		return key;
	}

}
