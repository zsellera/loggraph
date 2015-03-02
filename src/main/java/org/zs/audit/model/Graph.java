package org.zs.audit.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class Graph {
	private long nodeCntr = 0;
	private long edgeCntr = 0;
	private final Map<Entity, EntityNode> entities = new HashMap<>();
	private final Map<String, ConnectionNode> connectors = new HashMap<>();
	
	public void addLog(LogEvent l) {
		List<EntityNode> enodes = getNodes(l);
		Collections.sort(enodes);
		String connectionKey = ConnectionNode.createKey(l.getType(), enodes);
		ConnectionNode cn = connectors.get(connectionKey);
		if (cn == null) {
			cn = new ConnectionNode(++nodeCntr, l.getType(), enodes, edgeCntr);
			connectors.put(connectionKey, cn);
			edgeCntr += enodes.size();
		}
		cn.addMessage(new LogMessage(l.getDescription()));
	}
	
	protected ArrayList<EntityNode> getNodes(LogEvent l) {
		ArrayList<EntityNode> enodes = new ArrayList<EntityNode>(l.getReferences().size());
		for (Entity ref : l.getReferences()) {
			EntityNode en = entities.get(ref);
			if (en == null) {
				en = new EntityNode(++nodeCntr, ref);
				entities.put(en.getKey(), en);
			}
			enodes.add(en);
		}
		return enodes;
	}
	
	public Set<Node> getNodes() {
		Set<Node> nodes = new TreeSet<Node>(entities.values());
		nodes.addAll(connectors.values());
		return nodes;
	}
	
	public Set<Edge> getEdges() {
		Set<Edge> edges = new HashSet<Edge>();
		for (ConnectionNode cn : connectors.values()) {
			edges.addAll(cn.getEdges());
		}
		return edges;
	}

	public List<LogMessage> getLogs(long nodeId) {
		List<LogMessage> messages = new LinkedList<>();
		for (ConnectionNode cn: connectors.values()) {
			if (nodeId == cn.getId() || cn.linksTo(nodeId)) {
				messages.addAll(cn.getMessages());
			}
		}
		return messages;
	}
	
	public void cleanUp(Date until) {
		List<String> removeConnection = new LinkedList<>();
		Set<Entity> preserveEntities = new HashSet<>(entities.keySet());
		for (Entry<String, ConnectionNode> entry : connectors.entrySet()) {
			entry.getValue().removeOldMessages(until);
			if (entry.getValue().getMessages().size() == 0) {
				removeConnection.add(entry.getKey());
			} else {
				for (EntityNode en : entry.getValue().getLinks()) {
					preserveEntities.remove(en.getKey());
				}
			}
		}
		for (String connKey : removeConnection) {
			connectors.remove(connKey);
		}
		for (Entity e : preserveEntities) {
			entities.remove(e);
		}
	}
}
