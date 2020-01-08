package com.sf.attendance.model;

import java.util.Map;
/**
 * 考勤配置信息
 * 
 * 用于封装自前端传来的用于计算应出勤的页面配置信息
 * 
 * <p>考勤日期配置包括：
 * <p>1. 考勤月份
 * <p>2. 考勤周日的起止日期
 * <p>3. 法定节假日日期列表
 * <p>4. 调整上班、休息日期信息
 * @author zhangxioayu - 2020-01-06
 *
 */
public class AttendanceConfigInfo {
	/**
	 * 月份
	 */
	private String month;
	
	/**
	 * 开始日期
	 */
	private String startDate;
	
	/**
	 * 结束日期
	 */
	private String endDate;
	
	/**
	 * 考勤类型
	 */
	private AttendanceType attendanceType;
	
	/**
	 * 法定节假日
	 */
	private LegalHolidayConfig legalHolidayConfig;
	
	/**
	 * 调整日期信息
	 */
	private Map<ScheduleType, ModifyDayConfig> mapModifyDays;
	
	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public LegalHolidayConfig getLegalHolidayConfig() {
		return legalHolidayConfig;
	}

	public void setLegalHolidayConfig(LegalHolidayConfig legalHolidayConfig) {
		this.legalHolidayConfig = legalHolidayConfig;
	}

	public AttendanceType getAttendanceType() {
		return attendanceType;
	}

	public void setAttendanceType(AttendanceType attendanceType) {
		this.attendanceType = attendanceType;
	}

	public Map<ScheduleType, ModifyDayConfig> getMapModifyDays() {
		return mapModifyDays;
	}

	public void setMapModifyDays(Map<ScheduleType, ModifyDayConfig> mapModifyDays) {
		this.mapModifyDays = mapModifyDays;
	}
	
	
}
