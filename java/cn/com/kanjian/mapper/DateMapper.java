package cn.com.kanjian.mapper;

import java.util.Date;

public interface DateMapper {

	public Date getNow();

	public Date afterHour(int hour);

	public Date afterMinute(int minute);

	public Date afterSecond(int second);

	public Date afterDay(int day);

}
