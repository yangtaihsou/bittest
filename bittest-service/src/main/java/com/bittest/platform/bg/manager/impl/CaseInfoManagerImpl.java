package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.CaseInfoMapper;
import com.bittest.platform.bg.domain.po.CaseInfo;
import com.bittest.platform.bg.manager.CaseInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2018-08-26.
 */
@Service("caseInfoManager")
public class CaseInfoManagerImpl implements CaseInfoManager {
    @Autowired
    private CaseInfoMapper caseInfoMapper;

    @Override
    public int save(CaseInfo caseInfo) {
        return caseInfoMapper.save(caseInfo);
    }

    @Override
    public int delete(String caseId) {
        return caseInfoMapper.delete(caseId);
    }

    @Override
    public CaseInfo queryObject(CaseInfo caseInfo) {
        return caseInfoMapper.queryObject(caseInfo);
    }

    @Override
    public int update(CaseInfo caseInfo) {
        return caseInfoMapper.update(caseInfo);
    }

    @Override
    public List<CaseInfo> queryCaseListByTask(CaseInfo caseInfo) {
        return caseInfoMapper.queryCaseListByTask(caseInfo);
    }

    @Override
    public int queryCaseTotalByTask(CaseInfo caseInfo) {
        return caseInfoMapper.queryCaseTotalByTask(caseInfo);
    }

    @Override
    public List<CaseInfo> queryList(CaseInfo caseInfo) {
        return caseInfoMapper.queryList(caseInfo);
    }

    @Override
    public int queryTotal(CaseInfo caseInfo) {
        return caseInfoMapper.queryTotal(caseInfo);
    }

    @Override
    public List<CaseInfo> queryCaseInfoPageNoFetch(CaseInfo caseInfo) {
        return caseInfoMapper.queryCaseInfoPageNoFetch(caseInfo);
    }

    @Override
    public int queryCaseInfoPageNoFetchTotal(CaseInfo caseInfo) {
        return caseInfoMapper.queryCaseInfoPageNoFetchTotal(caseInfo);
    }

    @Override
    public int queryCaseBySystemTotal(CaseInfo caseInfo) {
        return caseInfoMapper.queryCaseBySystemTotal(caseInfo);
    }

    @Override
    public List<CaseInfo> queryCaseChart(CaseInfo caseInfo) {
        return caseInfoMapper.queryCaseChart(caseInfo);
    }

    @Override
    public List<CaseInfo> queryCaseByName(CaseInfo caseInfo) {
        return caseInfoMapper.queryCaseByName(caseInfo);
    }

    @Override
    public int queryCaseTotal() {
        return caseInfoMapper.queryCaseTotal();
    }

}
