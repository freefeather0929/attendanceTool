package com.sf.attendance.model;
/**
 * 考勤日期类型
 * <p>为了准确计算每个考勤周期中的应出勤天数，我们将每个日期归类于以下类型中的某一种：
 * <p>工作日（<b>WEEKDAY</b>，1）
 * <br>调整上班日（<b>ADJUSTWORKDAYS</b>，2）
 * <br>法定节假日（<b>LEGALHOLIDAYS</b>，3）
 * <br>调整休息日（<b>ADJUSTRESTDAYS</b>，4）
 * <br>周末休息日（<b>WEEKEND</b>，5）
 * 
 * @author zhangxiaoyu - 2020-01-06
 *
 */
public enum ScheduleType {

	WEEKDAY("工作日",1), ADJUSTWORKDAYS("调整上班日", 2), LEGALHOLIDAYS("法定节假日", 3), ADJUSTRESTDAYS("调整休息日", 4), WEEKEND("周末休息日",5);
	
	private String name;
	
	private int index;

	private ScheduleType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
