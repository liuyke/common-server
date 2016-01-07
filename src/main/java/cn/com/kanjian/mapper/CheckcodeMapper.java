package cn.com.kanjian.mapper;

import java.util.Map;

import cn.com.kanjian.model.Checkcode;

public interface CheckcodeMapper {

	void add(Checkcode checkcode);

	/**
	 * 
	 * @param params
	 *            String msisdn 手机号
	 *            int type (@link Dictionary.Checkcode)
	 * @return
	 */
	Checkcode loadLast(Map<String, Object> params);

	int countToday(String msisdn);

}
