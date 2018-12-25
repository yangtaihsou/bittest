package com.bittest.platform.bg.export.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Map类型返回结果类<br>
 * User: laitao<br>
 * Date: 15-3-18<br>
 * Time: 下午3:03<br>
 */
public class MapResult<T> extends Result implements Serializable {
    private static final long serialVersionUID = -6735175977188647965L;

    private Map<String, T> map = new HashMap<String, T>();

    public MapResult() {
    }

    public MapResult(ResultInfoEnum info) {
        super(info);
    }

    public MapResult(String code, String message) {
        super(code, message);
    }

    public Map<String, T> getMap() {
        return map;
    }

    public void setMap(Map<String, T> map) {
        this.map = map;
    }

    public T put(String key, T value) {
        return this.map.put(key, value);
    }

    public T remove(String key) {
        return this.map.remove(key);
    }

    public T get(String key) {
        return this.map.get(key);
    }
}
