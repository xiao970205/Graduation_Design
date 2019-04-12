package com.znck.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 静态类方法
 * 
 * @author 肖舒翔
 * 2019-03-01
 * @version 1.0
 */
public class PublicMethods {

	/**
	 * 获得当前时间
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
		Date date = new Date();
		String dateStr = format.format(date);
		Date date2 = format.parse(dateStr);
		return date2;
	}
	
	/**
	 * 获得自生成id
	 * @return
	 */
	public static String getId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	
	/**
	 * 获得今天的时间用于切割时间（月，日）
	 * @return
	 * @throws ParseException
	 */
	public static Date getTodayDateToSplitData() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd-HH");
		Date date = new Date();
		String dateStr = format.format(date);
		Date date2 = format.parse(dateStr);
		return date2;
	}
	
	public static Date randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(beginDate);
			// 构造开始日期
			Date end = format.parse(endDate);
			// 构造结束日期
			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}


}
