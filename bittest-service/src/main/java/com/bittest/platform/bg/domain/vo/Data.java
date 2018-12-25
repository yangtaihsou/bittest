package com.bittest.platform.bg.domain.vo;

import java.util.List;
import java.util.Map;

/**
 * 解析jsf信息 data实体类
 * 2018-08-20.
 */
public class Data {

    //resource地址
    private String interfaceName;

    //别名
    private List<String> aliases;

    //方法名
    private List<String> methods;

    //服务地址
    private Map<String, List<String>> addrs;


    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    public Map<String, List<String>> getAddrs() {
        return addrs;
    }

    public void setAddrs(Map<String, List<String>> addrs) {
        this.addrs = addrs;
    }
}
