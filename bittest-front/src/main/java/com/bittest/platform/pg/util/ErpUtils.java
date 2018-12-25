package com.bittest.platform.pg.util;

import com.bittest.platform.pg.domain.KeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ErpUtils {

    final static Logger logger = LoggerFactory.getLogger(ErpUtils.class);

	
/*	private static DataDictionaryCacheResource dataDictionaryCacheResource;
	@Resource
	public void setDataDictionaryCacheResource(DataDictionaryCacheResource dataDictionaryCacheResource){
		ErpUtils.dataDictionaryCacheResource = dataDictionaryCacheResource;
	}
	*/

    /**
     * 根据组key获取字典值
     *
     * @param groupKey 组key
     * @return
     */
    public static List<KeyValue> getGroupDictionary(String groupKey) {

        List<KeyValue> kvs = new ArrayList<KeyValue>();
	/*	DataDictionaryReq dataDictionaryReq = new DataDictionaryReq();
		dataDictionaryReq.setKey(groupKey);
		DataDictionaryListRes dataDictionaryListRes= ErpUtils.dataDictionaryCacheResource.queryDataDictionary(dataDictionaryReq);
		List<DataDictionaryVo> vos = dataDictionaryListRes.getDataDictionaryVos();
		for(DataDictionaryVo com.bittest.platform.bg.domain.vo:vos){
			kvs.add(new KeyValue(com.bittest.platform.bg.domain.vo.getTypeCode(), com.bittest.platform.bg.domain.vo.getTypeName()));
		}*/

        kvs.add(new KeyValue("1", "ceshi"));
        return kvs;
    }


    /*	*//**
     *
     * @param groupKey
     * @param dataKey
     * @return
     *//*
	public static KeyValue getDictionary(String groupKey ,String dataKey){
		
		if(groupKey == null || dataKey == null || "".equals(groupKey) || "".equals(dataKey)){
			return new KeyValue("" , "");
		}
		DataDictionaryReq dataDictionaryReq = new DataDictionaryReq();
		dataDictionaryReq.setKey(groupKey);
		DataDictionaryListRes dataDictionaryListRes= ErpUtils.dataDictionaryCacheResource.queryDataDictionary(dataDictionaryReq);
		List<DataDictionaryVo> vos = dataDictionaryListRes.getDataDictionaryVos();
		KeyValue kv = null;
		for(DataDictionaryVo com.bittest.platform.bg.domain.vo:vos){
			if(com.bittest.platform.bg.domain.vo.getTypeCode().equals(dataKey)){
				 kv = new KeyValue(dataKey, com.bittest.platform.bg.domain.vo.getTypeName());
			}
		}
		return kv;
	}
	*/

}
