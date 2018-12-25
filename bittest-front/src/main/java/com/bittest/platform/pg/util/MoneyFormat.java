package com.bittest.platform.pg.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneyFormat {

    public static String format(BigDecimal money) {

        if (money == null) {
            return "";
        }
        if (money.doubleValue() == 0) {
            return "0";
        }
        DecimalFormat format = new DecimalFormat("###,##0.00");
        String formatMoney = format.format(money);
        return formatMoney;
    }

    public static String format8(BigDecimal money) {

        if (money == null) {
            return "";
        }
        if (money.doubleValue() == 0) {
            return "0";
        }
        DecimalFormat format = new DecimalFormat("###,##0.00000000");
        String formatMoney = format.format(money);
        return formatMoney;
    }

    public static String format10(BigDecimal money) {

        if (money == null) {
            return "";
        }
        if (money.doubleValue() == 0) {
            return "0";
        }
        DecimalFormat format = new DecimalFormat("###,##0.0000000000");
        String formatMoney = format.format(money);
        return formatMoney;
    }


    public static void main(String[] args) {
        System.out.println(MoneyFormat.format(new BigDecimal("0.12")));
        System.out.println(MoneyFormat.format8(new BigDecimal("0.12")));

    }

}
