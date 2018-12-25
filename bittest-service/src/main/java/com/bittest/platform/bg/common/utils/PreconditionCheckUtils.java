package com.bittest.platform.bg.common.utils;

import com.bittest.platform.bg.domain.po.CheckPoint;

import java.util.List;
import java.util.Map;

public class PreconditionCheckUtils {
    //检查前置触发条件，and逻辑
    public static Boolean check(List<CheckPoint> preconditionCheckList, Map<String, String> caseParam,Map<String, String> taskParam){
        Boolean result = Boolean.TRUE;
        if(preconditionCheckList!=null&&preconditionCheckList.size()>0) {
                for (CheckPoint checkPoint : preconditionCheckList) {
                    String variable = checkPoint.getCheckVar();
                    Integer checkMethod = checkPoint.getCheckMethod();
                    String checkValue = checkPoint.getCheckValue();
                    String value = ParameterReplaceUtils.getValueFromcontext(variable);
                    if(value==null&&caseParam!=null){
                        value = caseParam.get(variable);
                    }
                    if(value==null&&taskParam!=null){
                        value = taskParam.get(variable);
                    }
                    if(!CheckUtils.checkData(checkValue,value,checkMethod)){
                        checkPoint.setResult(false);
                        result = Boolean.FALSE;
                    }else{
                        checkPoint.setResult(true);
                    }
                }
        }
        return result;
    }
}
