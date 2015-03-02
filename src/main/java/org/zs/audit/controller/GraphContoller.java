package org.zs.audit.controller;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zs.audit.model.Graph;
import org.zs.audit.model.GraphDto;
import org.zs.audit.model.LogEvent;
import org.zs.audit.model.LogMessage;

@RestController
public class GraphContoller {
	final static Logger LOGGER = Logger.getLogger(GraphContoller.class);
	
	@Autowired
	Graph graph;
	
	@RequestMapping("/graph/graph")
	public GraphDto getGraph() {
		synchronized (graph) {
			return new GraphDto(graph.getNodes(), graph.getEdges());
		}
	}
	
	@RequestMapping("/graph/logs")
	public List<LogMessage> getLogs(@RequestParam(value="node") long nodeId) {
		List<LogMessage> logs;
		synchronized (graph) {
			logs = graph.getLogs(nodeId);
		}
		Collections.sort(logs);
		return logs;
	}
	
	@RequestMapping(value="/graph/add", method=RequestMethod.PUT)
	public void addConnection(@RequestBody final LogEvent l) {
		synchronized (graph) {
			graph.addLog(l);
		}
	}
	
	@RequestMapping("/graph/cleanup")
	public void cleanup() {
		synchronized (graph) {
			Calendar untilCalendar = Calendar.getInstance();
			untilCalendar.setTime(new Date());
			untilCalendar.add(Calendar.MINUTE, -1);
			graph.cleanUp(untilCalendar.getTime());
		}
	}
}
