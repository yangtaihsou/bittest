package com.bittest.platform.bg.service.impl;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.domain.po.CaseInfo;
import com.bittest.platform.bg.domain.po.Systems;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.SystemReqVo;
import com.bittest.platform.bg.export.vo.SystemResVo;
import com.bittest.platform.bg.manager.CaseInfoManager;
import com.bittest.platform.bg.manager.SystemsManager;
import com.bittest.platform.bg.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 2018-08-25.
 */
@Service("systemService")
public class SystemServiceImpl implements SystemService {

    private static final Logger log = LoggerFactory.getLogger(SystemServiceImpl.class);

    @Autowired
    private SystemsManager systemsManager;

    @Autowired
    private CaseInfoManager caseInfoManager;

    @Override
    public GenericResult<SystemResVo> querySystem(SystemReqVo systemReqVo) {
        GenericResult<SystemResVo> result = new GenericResult<SystemResVo>();
        try {
            Systems systems = JSON.parseObject(JSON.toJSONString(systemReqVo), Systems.class);
            Systems res = systemsManager.queryObject(systems);
            if (null != res) {
                SystemResVo resVo = JSON.parseObject(JSON.toJSONString(res), SystemResVo.class);
                result.setValue(resVo);
                result.setInfo(ResultInfoEnum.SUCCESS);
            } else {
                log.error("查询的系统信息不存在");
                result.setInfo(ResultInfoEnum.ERROR);
            }
        } catch (Exception e) {
            log.error("查询的系统信息异常:{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
        } finally {
            return result;
        }
    }

    @Override
    public BasicResult saveSystem(SystemReqVo systemReqVo) {
        BasicResult result = new BasicResult();
        try {
            Systems req = JSON.parseObject(JSON.toJSONString(systemReqVo), Systems.class);
            systemsManager.save(req);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("保存系统信息异常:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public BasicResult updateSystem(SystemReqVo systemReqVo) {
        BasicResult result = new BasicResult();
        try {
            Systems req = JSON.parseObject(JSON.toJSONString(systemReqVo), Systems.class);
            systemsManager.update(req);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("修改系统信息异常:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public PaginationResult<SystemResVo> querySystemPage(PaginationQuery<SystemReqVo> query) {
        PaginationResult<SystemResVo> result = new PaginationResult<SystemResVo>();
        Pagination pagination = query.getPagination();
        if (null == pagination) {
            log.info("未传分页，查默认分页，首页10条");
            pagination = new Pagination(10, 1);
        }
        try {
            Systems systems = JSON.parseObject(JSON.toJSONString(query.getQuery()), Systems.class);
            systems.setPageSize(pagination.getPageSize());
            systems.setStartNo((pagination.getpageNo() - 1) * pagination.getPageSize());
            int totalRecord = systemsManager.queryTotal(systems);
            List<SystemResVo> res = new ArrayList<SystemResVo>();
            if (totalRecord > 0) {
                List<Systems> systemsesList = systemsManager.queryList(systems);
                SimpleDateFormat stf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (Systems t : systemsesList) {
                    SystemResVo resVo = JSON.parseObject(JSON.toJSONString(t), SystemResVo.class);
                    resVo.setCreateTimeStr(stf.format(resVo.getCreateTime()));
                    resVo.setUpdateTimeStr(stf.format(resVo.getUpdateTime()));
                    res.add(resVo);
                }
            } else {
                log.info("分页查询系统列表为空");
            }
            result.setList(res);
            result.setPagination(new Pagination(totalRecord, pagination.getPageSize(), pagination.getpageNo()));
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("分页查询系统列表失败:{}", e.toString());
        }
        return result;
    }

    @Override
    public ListResult<SystemResVo> querySystemList(SystemReqVo systemReqVo) {
        ListResult<SystemResVo> result = new ListResult<SystemResVo>();
        List<SystemResVo> resVoList = new ArrayList<SystemResVo>();
        try {
            Systems req = JSON.parseObject(JSON.toJSONString(systemReqVo), Systems.class);
            List<Systems> resList = systemsManager.querySystemList(req);
            if (null != resList) {
                for (Systems s : resList) {
                    resVoList.add(JSON.parseObject(JSON.toJSONString(s), SystemResVo.class));
                }
            }
            result.setList(resVoList);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            log.error("查询用户系统信息集合异常：{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
        } finally {
            return result;
        }
    }

    @Override
    public BasicResult deleteSystem(SystemReqVo systemReqVo) {
        BasicResult result = new BasicResult();
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setSysId(systemReqVo.getSystemId());
            int total = caseInfoManager.queryCaseBySystemTotal(caseInfo);
            if (total > 0) {
                result.setCode("999999");
                String message = "当前系统信息已关联" + total + "条用例信息，不能删除！";
                result.setMessage(message);
                return result;
            } else {
                Systems req = JSON.parseObject(JSON.toJSONString(systemReqVo), Systems.class);
                systemsManager.delete(req);
            }
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("删除系统信息异常:{}", e.toString());
        } finally {
            return result;
        }
    }
}
