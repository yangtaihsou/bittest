/**
 * Copyright(c) 2004- www.360buy.com
 * com.bittest.platform.bg.export.vo.NotifyInfo.java
 */
package com.bittest.platform.bg.export.vo;

import java.io.Serializable;
 import java.util.Date;

public class NotifyInfoReqVo implements Serializable {



    /**
     * id
     */

	    private Long id;

	


    /**
     * 通知内容的关键字段名
     */

	    private String fieldKey;

	


    /**
     * 通知内容的关键字段值
     */

	    private String fieldId;

	


    /**
     * 通知内容
     */

	    private String context;

	


    /**
     * 修改时间
     */

	    private Date updateTime;

	


    /**
     * 创建时间
     */

	    private Date createTime;

	
	
	

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	

	public String getFieldKey() {
		return fieldKey;
	}
	
	public void setFieldKey(String fieldKey) {
		this.fieldKey = fieldKey;
	}
	

	public String getFieldId() {
		return fieldId;
	}
	
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	

	public String getContext() {
		return context;
	}
	
	public void setContext(String context) {
		this.context = context;
	}
	

	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	

	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

}