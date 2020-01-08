package com.sf.attendance.model;

import java.util.List;
/**
 * 调整日期配置
 * <p>为计算应出勤而配置的调整上班或休息日期的信息
 * @author zhangxiaoyu - 2020-01-06
 * 
 */
public class ModifyDayConfig {
	/**
	 * 考勤日期类型
	 */
	private ScheduleType scheduleType;
	/**
	 * 考勤类型
	 */
	private AttendanceType attendanceType;
	/**
	 * 调整日期列表
	 */
	private List<String> listModifyDays;

	public ScheduleType getScheduleType() {
		return scheduleType;
	}
	
	public void setScheduleType(ScheduleType scheduleType) {
		this.scheduleType = scheduleType;
	}

	public AttendanceType getAttendanceType() {
		return attendanceType;
	}
	
	public void setAttendanceType(AttendanceType attendanceType) {
		this.attendanceType = attendanceType;
	}

	public List<String> getListModifyDays() {
		return listModifyDays;
	}

	public void setListModifyDays(List<String> listModifyDays) {
		this.listModifyDays = listModifyDays;
	}
	
	
}
