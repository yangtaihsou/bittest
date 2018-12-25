package com.bittest.platform.bg.export.vo;

import java.util.List;
import java.util.Map;

/**
 * 2018-08-20.
 */
public class JsfQueryResVo {

    //别名
    private List<String> alias;

    //ip地址
    private List<String> ips;

    //方法名
    private Map<String, String> methods;

    public List getAlias() {
        return alias;
    }

    public void setAlias(List alias) {
        this.alias = alias;
    }

    public List getIps() {
        return ips;
    }

    public void setIps(List ips) {
        this.ips = ips;
    }

    public Map<String, String> getMethods() {
        return methods;
    }

    public void setMethods(Map<String, String> methods) {
        this.methods = methods;
    }
}
