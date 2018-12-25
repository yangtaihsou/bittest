package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.CaseInterfaceRelastionMapper;
import com.bittest.platform.bg.domain.po.CaseInterfaceRelastion;
import com.bittest.platform.bg.manager.CaseInterfaceRelastionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2018-08-22.
 */
@Service("caseInterfaceRelastionManager")
public class CaseInterfaceRelastionManagerImpl implements CaseInterfaceRelastionManager {

    @Autowired
    private CaseInterfaceRelastionMapper caseInterfaceRelastionMapper;

    @Override
    public int save(CaseInterfaceRelastion caseInterfaceRelastion) {
        return caseInterfaceRelastionMapper.save(caseInterfaceRelastion);
    }

    @Override
    public int update(CaseInterfaceRelastion caseInterfaceRelastion) {
        return caseInterfaceRelastionMapper.update(caseInterfaceRelastion);
    }

    @Override
    public int delete(CaseInterfaceRelastion caseInterfaceRelastion) {
        return caseInterfaceRelastionMapper.delete(caseInterfaceRelastion);
    }

    @Override
    public int deleteByCase(CaseInterfaceRelastion caseInterfaceRelastion) {
        return caseInterfaceRelastionMapper.deleteByCase(caseInterfaceRelastion);
    }

    @Override
    public int queryRelationOrder(CaseInterfaceRelastion caseInterfaceRelastion) {
        return caseInterfaceRelastionMapper.queryRelationOrder(caseInterfaceRelastion);
    }

    @Override
    public int queryInterfaceCount(CaseInterfaceRelastion caseInterfaceRelastion) {
        return caseInterfaceRelastionMapper.queryInterfaceCount(caseInterfaceRelastion);
    }
}
