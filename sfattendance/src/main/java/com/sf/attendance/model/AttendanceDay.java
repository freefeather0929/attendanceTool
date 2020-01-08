package com.sf.attendance.model;

/**
 * 考勤日期
 * @author zhangxiaoyu - 2020-01-06
 * 
 */

public class AttendanceDay {
	private String date;
	private ScheduleType type;
	
	public AttendanceDay(String date, ScheduleType type) {
		this.date = date;
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public ScheduleType getType() {
		return type;
	}
	public void setType(ScheduleType type) {
		this.type = type;
	}
	
	
}
