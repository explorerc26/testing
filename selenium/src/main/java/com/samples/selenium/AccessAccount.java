package com.samples.selenium;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AccessAccount  implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("starting .. ");
		SimplePrintScreen printScreen = new SimplePrintScreen();
		try {
			printScreen.print();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ending .. ");
	}

}
