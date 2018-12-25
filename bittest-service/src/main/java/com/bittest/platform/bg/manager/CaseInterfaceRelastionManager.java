package com.bittest.platform.bg.manager;

import com.bittest.platform.bg.domain.po.CaseInterfaceRelastion;

/**
 * 2018-08-22.
 */
public interface CaseInterfaceRelastionManager {
    /**
     * 保存接口及用例关系
     *
     * @param caseInterfaceRelastion
     * @return
     */
    int save(CaseInterfaceRelastion caseInterfaceRelastion);

    /**
     * 编辑排序
     *
     * @param caseInterfaceRelastion
     * @return
     */
    int update(CaseInterfaceRelastion caseInterfaceRelastion);

    /**
     * 删除接口及用例关系(根据用例编号及接口编号)
     *
     * @param caseInterfaceRelastion
     * @return
     */
    int delete(CaseInterfaceRelastion caseInterfaceRelastion);

    /**
     * 删除接口及用例关系(根据用例编号)
     *
     * @param caseInterfaceRelastion
     * @return
     */
    int deleteByCase(CaseInterfaceRelastion caseInterfaceRelastion);

    /**
     * 查询接口最大排序
     *
     * @param caseInterfaceRelastion
     * @return
     */
    int queryRelationOrder(CaseInterfaceRelastion caseInterfaceRelastion);

    /**
     * 查询接口是否关联用例
     *
     * @param caseInterfaceRelastion
     * @return
     */
    int queryInterfaceCount(CaseInterfaceRelastion caseInterfaceRelastion);

}
