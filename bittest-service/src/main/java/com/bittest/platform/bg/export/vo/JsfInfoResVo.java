package com.bittest.platform.bg.export.vo;

import java.util.List;

/**
 * 2018-03-21.
 */
public class JsfInfoResVo {

    private List<String> alias;

    private List<String> ips;

    private List<String> methods;

//    private JsfMethods methods;

//    public JsfMethods getMethods() {
//        return methods;
//    }
//
//    public void setMethods(JsfMethods methods) {
//        this.methods = methods;
//    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public List<String> getIps() {
        return ips;
    }

    public void setIps(List<String> ips) {
        this.ips = ips;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }
}
