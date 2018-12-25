package com.bittest.platform.pg.util;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateTimeConverter;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;


public class BeanUtilsConvert {


    @PostConstruct
    public void convertInit() {
        // ConvertUtils.register(new org.apache.commons.beanutils.locale.converters.DateLocaleConverter(), java.util.Date.class);
        ConvertUtils.register(new org.apache.commons.beanutils.converters.DateConverter(null), java.util.Date.class);
        ConvertUtils.register(new org.apache.commons.beanutils.converters.BigDecimalConverter(null), BigDecimal.class);

    }

    public class MyDateConvert extends DateTimeConverter {

        @Override
        protected Class getDefaultType() {
            return java.util.Date.class;
        }

        @Override
        protected Object convertToType(Class targetType, Object value)
                throws Exception {

            if (value == null) {
                return null;
            }
            String valueStr = value.toString().trim();
            if (valueStr.length() == 0) {
                return null;
            }
            return super.convertToType(targetType, value);
        }


    }


}
