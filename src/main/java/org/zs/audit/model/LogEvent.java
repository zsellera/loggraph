package org.zs.audit.model;

import java.util.Set;

public class LogEvent {
	private String type;
	private String description;
	private Set<Entity> references;
	
	
	public LogEvent() {
		super();
	}

	public LogEvent(String type, String description, Set<Entity> references) {
		super();
		this.type = type;
		this.references = references;
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Entity> getReferences() {
		return references;
	}

	public void setReferences(Set<Entity> references) {
		this.references = references;
	}


}
