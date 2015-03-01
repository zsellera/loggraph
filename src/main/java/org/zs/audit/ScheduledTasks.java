package org.zs.audit;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.zs.audit.model.Graph;

@EnableScheduling
public class ScheduledTasks {
	final static Logger LOGGER = Logger.getLogger(ScheduledTasks.class);
	
	@Autowired
	Graph graph;
	
	@Scheduled(cron="*/5 * * * * *")
	public void cleanupGraph() {
		Calendar untilCalendar = Calendar.getInstance();
		untilCalendar.setTime(new Date());
		untilCalendar.add(Calendar.MINUTE, -1);
		Date until = untilCalendar.getTime();
		synchronized (graph) {
			LOGGER.info("Cleaning up logs older than " + until);
			graph.cleanUp(until);
		}
	}
}
