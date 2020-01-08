package com.sf.attendance.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.sf.attendance.model.AttendanceConfigInfo;
import com.sf.attendance.model.AttendanceDay;
import com.sf.attendance.model.AttendanceType;
import com.sf.attendance.model.LegalHolidayConfig;
import com.sf.attendance.model.ModifyDayConfig;
import com.sf.attendance.model.OnDutyResult;
import com.sf.attendance.model.ScheduleType;
/**
 * 考勤工具类
 * @author zhangxiaoyu - 2020-01-06 
 * 
 */
public class AttendanceUtil {
	
	/**
	 * 判断日期的类型
	 * @param date - 日期
	 * @param attendanceConfigInfo - 考勤配置信息
	 * @return {AttendanceDay} 考勤日期，包含日期和日期的类型
	 * @throws ParseException
	 */
	public static OnDutyResult judgeAttendanceType(AttendanceConfigInfo attendanceConfigInfo) throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		
		//String month = attendanceConfigInfo.getMonth();
		
		String startDate = attendanceConfigInfo.getStartDate();
		
		String endDate = attendanceConfigInfo.getEndDate();
		
		String tmpDate = startDate;
		
		AttendanceType attendanceType = attendanceConfigInfo.getAttendanceType();
		LegalHolidayConfig legalHolidayConfig = attendanceConfigInfo.getLegalHolidayConfig();
		ModifyDayConfig adjustWorkDay_modifyConfig = attendanceConfigInfo.getMapModifyDays().get(ScheduleType.ADJUSTWORKDAYS);
		ModifyDayConfig adjustRestDay_modifyConfig = attendanceConfigInfo.getMapModifyDays().get(ScheduleType.ADJUSTRESTDAYS);
		int onDutyDays = 0;
		int daysOfMonth = 0;
		int daysOfLegalHoliday = 0;
		int daysOfAjustedWorkDay = 0;
		int daysOfAjustedRestDay = 0;
		int daysOfWeekday = 0;
		int daysOfWeekend = 0;
		
		OnDutyResult onDudyResult = new OnDutyResult();
		
		onDudyResult.setAttendanceType(attendanceType);
		
		while(tmpDate.compareTo(endDate) <= 0) {
			AttendanceDay attendanceDay = new AttendanceDay(tmpDate, null);
			
			Calendar c = new GregorianCalendar().getInstance();
			c.setTime(sd.parse(tmpDate));
			
			int weekIndex = c.get(Calendar.DAY_OF_WEEK);
			daysOfMonth += 1;
			//System.out.println("tmpDate :: " + tmpDate);
			//判断是否是法定节假日
			if(legalHolidayConfig.getListLegalHoliday().contains(tmpDate)) {
				attendanceDay.setType(ScheduleType.LEGALHOLIDAYS);
				daysOfLegalHoliday += 1;
				tmpDate = ScheduleUtil.moveDate(tmpDate, 0, 0, 1);
				continue;
			}
			
			if(adjustWorkDay_modifyConfig.getListModifyDays().contains(tmpDate) == true) { //判断是否为调整上班日
				attendanceDay.setType(ScheduleType.ADJUSTWORKDAYS);
				daysOfAjustedWorkDay += 1;
				onDutyDays += 1;
				tmpDate = ScheduleUtil.moveDate(tmpDate, 0, 0, 1);
				continue;
			}
			
			if(adjustRestDay_modifyConfig.getListModifyDays().contains(tmpDate) == true) {	//判断是否为调整休息日
				attendanceDay.setType(ScheduleType.ADJUSTRESTDAYS);
				daysOfAjustedRestDay += 1;
				tmpDate = ScheduleUtil.moveDate(tmpDate, 0, 0, 1);
				continue;
			}
				
			if(weekIndex == 1) {	//综合及标准工时制周日不计算为应出勤
				attendanceDay.setType(ScheduleType.WEEKEND);
				daysOfWeekend += 1;
				tmpDate = ScheduleUtil.moveDate(tmpDate, 0, 0, 1);
				continue;
			}
			
			if(attendanceConfigInfo.getAttendanceType() == AttendanceType.STANDARD) {	//判断是否为标准工作制
				if(weekIndex == 7) {	//标准工时制周六不计算为应出勤
					attendanceDay.setType(ScheduleType.WEEKEND);
					daysOfWeekend += 1;
					tmpDate = ScheduleUtil.moveDate(tmpDate, 0, 0, 1);
					continue;
				}
			}
			
			daysOfWeekday += 1;
			onDutyDays += 1;
			tmpDate = ScheduleUtil.moveDate(tmpDate, 0, 0, 1);
		}
		
		onDudyResult.setDaysOfMonth(daysOfMonth);
		onDudyResult.setDaysOfAjustedRestDay(daysOfAjustedRestDay);
		onDudyResult.setDaysOfAjustedWorkDay(daysOfAjustedWorkDay);
		onDudyResult.setDaysOfLegalHoliday(daysOfLegalHoliday);
		onDudyResult.setDaysOfWeekend(daysOfWeekend);
		onDudyResult.setDaysOfWeekday(daysOfWeekday);
		onDudyResult.setOnDutyDays(onDutyDays);
		
		
		//AttendanceType attendanceType = attendanceConfigInfo.getAttendanceType();
		
		return onDudyResult;	
	}
	
	
	public static void main(String[] args) {
		
		//模拟计算2020年1月份的应出勤天数（标准工时制） 2019年12月26日 至 2020年1月25日
		
		//调整休息日配置
		ModifyDayConfig mdc_rest = new ModifyDayConfig();
		mdc_rest.setAttendanceType(AttendanceType.STANDARD);
		mdc_rest.setScheduleType(ScheduleType.ADJUSTRESTDAYS);
		
		List<String> mdc_Rest_Day = new ArrayList<String>();
		mdc_Rest_Day.add("2020-01-24"); //除夕
		
		mdc_rest.setListModifyDays(mdc_Rest_Day);
		
		//调整上班日
		ModifyDayConfig mdc_work = new ModifyDayConfig();
		mdc_work.setAttendanceType(AttendanceType.STANDARD);
		mdc_work.setScheduleType(ScheduleType.ADJUSTWORKDAYS);
		
		List<String> mdc_Work_Day = new ArrayList<String>();
		mdc_Work_Day.add("2020-01-19"); 
		
		mdc_work.setListModifyDays(mdc_Work_Day);
		
		Map<ScheduleType,ModifyDayConfig> map_Mdc = new HashMap<ScheduleType,ModifyDayConfig>();
		map_Mdc.put(ScheduleType.ADJUSTRESTDAYS,mdc_rest);
		map_Mdc.put(ScheduleType.ADJUSTWORKDAYS,mdc_work);
		
		//法定节假日配置
		LegalHolidayConfig lhc = new LegalHolidayConfig();
		
		List<String> lhc_Day = new ArrayList<String>();
		lhc_Day.add("2020-01-01");
		lhc_Day.add("2020-01-25");
		lhc.setListLegalHoliday(lhc_Day);
		
		AttendanceConfigInfo ac = new AttendanceConfigInfo();
		ac.setAttendanceType(AttendanceType.STANDARD);
		//ac.setAttendanceType(AttendanceType.NON_FREQUENC);
		ac.setMapModifyDays(map_Mdc);
		ac.setLegalHolidayConfig(lhc);
		ac.setMonth("2020-01");
		ac.setStartDate("2019-12-26");
		ac.setEndDate("2020-01-25");
		
		System.out.println(JSONObject.toJSONString(ac));
		
		try {
			OnDutyResult odr = AttendanceUtil.judgeAttendanceType(ac);
			System.out.println(odr.toString());
			System.out.println(JSONObject.toJSONString(odr));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
