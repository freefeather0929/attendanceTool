package com.sf.attendance.model;
/**
 * 应出勤计算结果
 * @author zhangxiaoyu - 2020-01-06
 *
 */
public class OnDutyResult {
	/**
	 * 考勤类型
	 */
	private AttendanceType attendanceType;
	/**
	 * 应出勤天数
	 */
	private Integer onDutyDays;
	/**
	 * 全月天数
	 */
	private Integer daysOfMonth;
	/**
	 * 法定节假日天数
	 */
	private Integer daysOfLegalHoliday;
	/**
	 * 调整上班日天数
	 */
	private Integer daysOfAjustedWorkDay;
	/**
	 * 调整休息日天数
	 */
	private Integer daysOfAjustedRestDay;
	/**
	 * 正常工作日天数
	 */
	private Integer daysOfWeekday;
	/**
	 * 正常周末休息天数
	 */
	private Integer daysOfWeekend;

	public AttendanceType getAttendanceType() {
		return attendanceType;
	}

	public void setAttendanceType(AttendanceType attendanceType) {
		this.attendanceType = attendanceType;
	}

	public Integer getOnDutyDays() {
		return onDutyDays;
	}

	public void setOnDutyDays(Integer onDutyDays) {
		this.onDutyDays = onDutyDays;
	}

	public Integer getDaysOfMonth() {
		return daysOfMonth;
	}

	public void setDaysOfMonth(Integer daysOfMonth) {
		this.daysOfMonth = daysOfMonth;
	}

	public Integer getDaysOfLegalHoliday() {
		return daysOfLegalHoliday;
	}

	public void setDaysOfLegalHoliday(Integer daysOfLegalHoliday) {
		this.daysOfLegalHoliday = daysOfLegalHoliday;
	}

	public Integer getDaysOfAjustedWorkDay() {
		return daysOfAjustedWorkDay;
	}

	public void setDaysOfAjustedWorkDay(Integer daysOfAjustedWorkDay) {
		this.daysOfAjustedWorkDay = daysOfAjustedWorkDay;
	}

	public Integer getDaysOfAjustedRestDay() {
		return daysOfAjustedRestDay;
	}

	public void setDaysOfAjustedRestDay(Integer daysOfAjustedRestDay) {
		this.daysOfAjustedRestDay = daysOfAjustedRestDay;
	}

	public Integer getDaysOfWeekday() {
		return daysOfWeekday;
	}

	public void setDaysOfWeekday(Integer daysOfWeekday) {
		this.daysOfWeekday = daysOfWeekday;
	}

	public Integer getDaysOfWeekend() {
		return daysOfWeekend;
	}

	public void setDaysOfWeekend(Integer daysOfWeekend) {
		this.daysOfWeekend = daysOfWeekend;
	}

	@Override
	public String toString() {
		return "OnDutyResult [attendanceType=" + attendanceType + ", onDutyDays=" + onDutyDays + ", daysOfMonth="
				+ daysOfMonth + ", daysOfLegalHoliday=" + daysOfLegalHoliday + ", daysOfAjustedWorkDay="
				+ daysOfAjustedWorkDay + ", daysOfAjustedRestDay=" + daysOfAjustedRestDay + ", daysOfWeekday="
				+ daysOfWeekday + ", daysOfWeekend=" + daysOfWeekend + "]";
	}
	
	
}
