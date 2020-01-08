package com.sf.attendance.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sf.attendance.model.AttendanceConfigInfo;
import com.sf.attendance.model.AttendanceType;
import com.sf.attendance.model.LegalHolidayConfig;
import com.sf.attendance.model.ModifyDayConfig;
import com.sf.attendance.model.OnDutyResult;
import com.sf.attendance.model.ScheduleType;
import com.sf.attendance.util.AttendanceUtil;

@RestController
public class OndutyDayController {
	
	@ResponseBody
    @RequestMapping(value="/getconfig", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String countOndutyDay(@RequestBody JSONObject param) throws ParseException {
		
		JSONObject mapModifyDays = param.getJSONObject("mapModifyDays");
    	String month = param.getString("month");
    	String startDate = param.getString("startDate");
    	String endDate = param.getString("endDate");
    	
    	//法定节假日配置
    	JSONArray legalHolidays = param.getJSONArray("listLegalHoliday");
    	LegalHolidayConfig lhc = new LegalHolidayConfig();
		List<String> lhc_Day = new ArrayList<String>();
		for(int i=0;i<legalHolidays.size();i++) {
			lhc_Day.add(legalHolidays.getString(i));
		}
		lhc.setListLegalHoliday(lhc_Day);
    	
    	//计算 标准工作制 应出勤天数
		//调整上班日配置
		
		JSONObject jsonObj = mapModifyDays.getJSONObject("jwd_s"); //标准工作制 - 调整上班日期数据
    	JSONArray jsonArray = jsonObj.getJSONArray("listModifyDays");
		
		ModifyDayConfig mdc_work = new ModifyDayConfig();
		mdc_work.setAttendanceType(AttendanceType.STANDARD);
		mdc_work.setScheduleType(ScheduleType.ADJUSTWORKDAYS);
		List<String> mdc_Work_Day = new ArrayList<String>();
		for(int i = 0; i<jsonArray.size();i++ ) {
			mdc_Work_Day.add(jsonArray.getString(i));
		}
		
		mdc_work.setListModifyDays(mdc_Work_Day);
    	
		//调整休息日配置
		jsonObj = mapModifyDays.getJSONObject("jrd_s"); //标准工作制 - 调整休息日期数据
    	jsonArray = jsonObj.getJSONArray("listModifyDays");
		//System.out.println("jsonArray :: " + jsonArray);
		ModifyDayConfig mdc_rest = new ModifyDayConfig();
		mdc_rest.setAttendanceType(AttendanceType.STANDARD);
		mdc_rest.setScheduleType(ScheduleType.ADJUSTRESTDAYS);
		
		List<String> mdc_Rest_Day = new ArrayList<String>();
		
		for(int i = 0; i<jsonArray.size();i++ ) {
			mdc_Rest_Day.add(jsonArray.getString(i));
		}
		mdc_rest.setListModifyDays(mdc_Rest_Day);
		
		Map<ScheduleType,ModifyDayConfig> map_Mdc = new HashMap<ScheduleType,ModifyDayConfig>();
		map_Mdc.put(ScheduleType.ADJUSTRESTDAYS,mdc_rest);
		map_Mdc.put(ScheduleType.ADJUSTWORKDAYS,mdc_work);
		
		AttendanceConfigInfo ac = new AttendanceConfigInfo();
		ac.setMonth(month);
		ac.setStartDate(startDate);
		ac.setEndDate(endDate);
		ac.setLegalHolidayConfig(lhc);
		ac.setMapModifyDays(map_Mdc);
		ac.setAttendanceType(AttendanceType.STANDARD);
		
		OnDutyResult result_s = AttendanceUtil.judgeAttendanceType(ac);
		
		//计算 综合工作制 应出勤天数
		//调整上班日配置
		
		jsonObj = mapModifyDays.getJSONObject("jwd_n"); //综合工作制 - 调整上班日期数据
		jsonArray = jsonObj.getJSONArray("listModifyDays");
		
		mdc_work = new ModifyDayConfig();
		mdc_work.setAttendanceType(AttendanceType.NON_FREQUENC);
		mdc_work.setScheduleType(ScheduleType.ADJUSTWORKDAYS);
		mdc_Work_Day.clear();
		for(int i = 0; i<jsonArray.size();i++ ) {
			mdc_Work_Day.add(jsonArray.getString(i));
		}
		
		mdc_work.setListModifyDays(mdc_Work_Day);
    	
		//调整休息日配置
		jsonObj = mapModifyDays.getJSONObject("jrd_n"); //标准工作制 - 调整上班日期数据
    	jsonArray = jsonObj.getJSONArray("listModifyDays");
		
		mdc_rest = new ModifyDayConfig();
		mdc_rest.setAttendanceType(AttendanceType.NON_FREQUENC);
		mdc_rest.setScheduleType(ScheduleType.ADJUSTRESTDAYS);
		
		mdc_Rest_Day.clear();
		for(int i = 0; i<jsonArray.size();i++ ) {
			mdc_Rest_Day.add(jsonArray.getString(i));
		}
		mdc_rest.setListModifyDays(mdc_Rest_Day);
		
		map_Mdc = new HashMap<ScheduleType,ModifyDayConfig>();
		map_Mdc.put(ScheduleType.ADJUSTRESTDAYS,mdc_rest);
		map_Mdc.put(ScheduleType.ADJUSTWORKDAYS,mdc_work);
		
		ac = new AttendanceConfigInfo();
		ac.setMonth(month);
		ac.setStartDate(startDate);
		ac.setEndDate(endDate);
		ac.setLegalHolidayConfig(lhc);
		ac.setMapModifyDays(map_Mdc);
		ac.setAttendanceType(AttendanceType.NON_FREQUENC);
		
		OnDutyResult result_n = AttendanceUtil.judgeAttendanceType(ac);
		
		JSONObject resultJson = new JSONObject();
		
		resultJson.put("result_s", result_s);
		resultJson.put("result_n", result_n);
		
		return resultJson.toString();
	}
	
}
