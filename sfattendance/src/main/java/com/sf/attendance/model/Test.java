package com.sf.attendance.model;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	private final String st;
	private final long id;
	
	public Test(long id, String st) {
		this.st = st;
		this.id = id;
	}
	
	public String getSt() {
		return st;
	}
	
	public long getId() {
		return id;
	}
	
	public static void main(String[] args) {
		System.out.println(AttendanceType.NON_FREQUENC.getIndex());
		
		Map<AttendanceType,String> map = new HashMap<AttendanceType,String>();
		map.put(AttendanceType.STANDARD, "123");
		map.put(AttendanceType.NON_FREQUENC, "456");
		System.out.println(map.get(AttendanceType.STANDARD));
		System.out.println(map.get(AttendanceType.NON_FREQUENC));
	}

	
}
