package com.hajea.date;

import java.text.ParseException;
import java.util.Date;

public class StampTime {

	public static void main(String[] args) throws  Exception {
		// Date d = new Date();
		 String s = "2015-05-01 17:20:10";
		 Date d =  DateUtils.parse(s);
		 Long t = d.getTime();		 
		 System.out.println(currTimeDesc(t)); 
	}
	public static String currTimeDesc(Long ms){
		Long cms = new Date().getTime();
		Long s = (cms - ms ) / 1000;
		
		Long s_m = 60L;
		Long s_h = s_m * 60L;
		Long s_d = s_h * 24L ;
		Long s_M = s_d * 30L;
		Long s_Y = s_M * 12L;
		
		int t_m = (int)(s / s_m);
		int t_h = (int)(s / s_h);
		int t_d = (int)(s / s_d);
		int t_M = (int)(s / s_M);
		int t_Y = (int)(s / s_Y);
		
		String rs = "";
		if( s < s_m ){
			rs = s + "秒前";
		}else if( s >= s_m && s < s_h ){
			rs = t_m + "分前";
		}else if( s >= s_h && s < s_d ){
			rs = t_h + "小时前";
		}else if( s >= s_d && s < s_M ){
			rs = t_d + "天前";
		}else if( s >= s_M && s < s_Y ){
			rs = t_M + "月前";
		}else if( s >= s_Y ){
			rs = t_Y + "年前";
		}
		return rs;
	}
	

}
