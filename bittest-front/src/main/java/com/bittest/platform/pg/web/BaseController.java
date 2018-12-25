package com.bittest.platform.pg.web;

import com.bittest.platform.pg.tag.ErpCustomDateEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yechao1
 */
public class BaseController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());


    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class, new ErpCustomDateEditor(null, true));// 使用正则匹配，做默认的日期格式支持
    }

    protected String getPin() {
        String pin = "admin";// UserUtil.getPin();
        return pin;
    }


    protected String getRole() {
/*
		String pin = UserUtil.getPin();

		List<Roles> roles = authCheck.getRoles(pin);

		StringBuffer sb = new StringBuffer("");
		for(int i = 0 ;i < roles.size() ; i++){

			Roles role = roles.get(i);

			if(i == 0 ){
				sb.append(role.getRoleCode());
			}else{
				sb.append(",").append(role.getRoleCode());
			}
		}

		return sb.toString();*/
        return null;
    }


    // 获取当天日期
    protected String getDate() {
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.format(new Date());
        return date;
    }

    // 获取昨天日期
    protected String getYesterDay() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String date = "";
        date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return date;
    }

}
