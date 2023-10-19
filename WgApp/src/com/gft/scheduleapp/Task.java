package com.gft.scheduleapp;

import java.time.DayOfWeek;

public class Task {

	private String taskName;
	private DayOfWeek dayOfWeek;
	private String whoDoesTask;
	private String detailsOfTask;
	
	// recurring
	

	public Task(String taskTodo, String whoDoesTask, String detailsOfTask, DayOfWeek dayOfWeek) {
		this.taskName = taskTodo;
		this.whoDoesTask = whoDoesTask;
		this.detailsOfTask = detailsOfTask;
		this.setDayOfWeek(dayOfWeek);
	}

	public String getTaskToDo() {
		return taskName;
	}

	public void setTaskToDo(String taskToDo) {
		this.taskName = taskToDo;
	}

	public String getWhoDoesTask() {
		return whoDoesTask;
	}

	public void setWhoDoesTheTask(String whoDoesTask) {

		this.whoDoesTask = whoDoesTask;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getDetailsOfTask() {
		return detailsOfTask;
	}

	public void setDetailsOfTask(String detailsOfTask) {
		this.detailsOfTask = detailsOfTask;
	}

	

}
