package com.bittest.platform.bg.common.context;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储本次线程的数据
 */
public class CaseParaApplicationContext {
    public  static ThreadLocal<CaseParaApplicationContext> aplicationContext = new ThreadLocal<CaseParaApplicationContext>();

    /**
     * Returns the ApplicationContext specific to the current thread.
     *
     * @return the ApplicationContext for the current thread, is never <tt>null</tt>.
     */
    public static CaseParaApplicationContext getContext() {
        return aplicationContext.get();
    }

    /**
     * Sets the action context for the current thread.
     *
     * @param context the action context.
     */
    public static void setContext(CaseParaApplicationContext context) {
        aplicationContext.set(context);
    }
    /**
     * Creates a new ApplicationContext initialized with another context.
     */
    public CaseParaApplicationContext() {
    }

    private Map<String, Object> context = new HashMap<String, Object>();

    /**
     * Returns a value that is stored in the current ActionContext by doing a lookup using the value's key.
     *
     * @param key the key used to find the value.
     * @return the value that was found using the key or <tt>null</tt> if the key was not found.
     */
    public Object get(String key) {
        return context.get(key);
    }

    /**
     * Stores a value in the current ActionContext. The value can be looked up using the key.
     *
     * @param key   the key of the value.
     * @param value the value to be stored.
     */
    public void put(String key, Object value) {
        context.put(key, value);
    }

    public Map<String, Object> paraMap(){
        return context;
    }
}
