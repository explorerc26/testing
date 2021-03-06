package com.samples.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.samples.selenium.AccessAccount;

public class SimpleTrigger {
	public static void main(String[] args) throws Exception {

		JobDetail job = JobBuilder.newJob(AccessAccount.class).withIdentity("dummyJobName", "group1").build();
		Trigger trigger = TriggerBuilder
			.newTrigger()
			.withIdentity("dummyTriggerName", "group1")
			.withSchedule(
				SimpleScheduleBuilder.simpleSchedule()
					.withIntervalInMinutes(5).repeatForever())
			.build();

		// schedule it
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);

	}
}
