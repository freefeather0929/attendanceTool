package com.sf.attendance.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 法定节假日
 * @author zhangxiaoyu - 2020-01-06
 * 
 */
public class LegalHolidayConfig {
	/**
	 * 法定节假日日期列表
	 */
	private List<String> listLegalHoliday = new ArrayList<String>();

	public List<String> getListLegalHoliday() {
		return listLegalHoliday;
	}
	
	public void setListLegalHoliday(List<String> listLegalHoliday) {
		this.listLegalHoliday = listLegalHoliday;
	}
	
	
}
