package com.sf.attendance.model;
/**
 * 考勤类型
 * <p>公司考勤类型分为2类：标准工时制（0）、综合工时制（1）
 * @author zhangxiaoyu - 2020-01-06
 * 
 */
public enum AttendanceType {
	
	STANDARD("标准",0), NON_FREQUENC("综合",1);
	
	private String name;
	
	private int index;

	private AttendanceType(String name, int index) {
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
