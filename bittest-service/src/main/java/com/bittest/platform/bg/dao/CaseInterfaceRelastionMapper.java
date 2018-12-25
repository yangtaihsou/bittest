package com.bittest.platform.bg.dao;


import com.bittest.platform.bg.domain.po.CaseInterfaceRelastion;

/**
 * 用例接口关联关系表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-31 15:52:54
 */
public interface CaseInterfaceRelastionMapper extends BaseMapper<CaseInterfaceRelastion> {

    int queryRelationOrder(CaseInterfaceRelastion caseInterfaceRelastion);

    int deleteByCase(CaseInterfaceRelastion caseInterfaceRelastion);

    int queryInterfaceCount(CaseInterfaceRelastion caseInterfaceRelastion);

}
