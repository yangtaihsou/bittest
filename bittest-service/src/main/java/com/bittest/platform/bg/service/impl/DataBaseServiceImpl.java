package com.bittest.platform.bg.service.impl;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.common.utils.DbHelper;
import com.bittest.platform.bg.dao.DatabaseMapper;
import com.bittest.platform.bg.domain.po.DataBase;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.DataBaseReqVo;
import com.bittest.platform.bg.export.vo.DataBaseResVo;
import com.bittest.platform.bg.manager.InterfaceManager;
import com.bittest.platform.bg.service.DataBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 2018-08-22.
 */
@Service("dataBaseService")
public class DataBaseServiceImpl implements DataBaseService {

    private static final Logger log = LoggerFactory.getLogger(DataBaseServiceImpl.class);

    @Autowired
    private DatabaseMapper databaseMapper;

    @Autowired
    private InterfaceManager interfaceManager;

    @Override
    public GenericResult<DataBaseResVo> queryDataBase(DataBaseReqVo dataBaseReqVo) {
        GenericResult<DataBaseResVo> result = new GenericResult<DataBaseResVo>();
        try {
            DataBase reqVo = JSON.parseObject(JSON.toJSONString(dataBaseReqVo), DataBase.class);
            DataBase res = databaseMapper.queryObject(reqVo);
            DataBaseResVo resVo;
            if (null != res) {
                resVo = JSON.parseObject(JSON.toJSONString(res), DataBaseResVo.class);
                result.setValue(resVo);
            } else {
                log.error("数据库信息不存在");
            }
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            log.error("查询数据库信息异常:{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
        } finally {
            return result;
        }

    }

    @Override
    public BasicResult saveDataBase(DataBaseReqVo dataBaseReqVo) {
        BasicResult result = new BasicResult();
        try {
            DataBase reqVo = JSON.parseObject(JSON.toJSONString(dataBaseReqVo), DataBase.class);
            databaseMapper.save(reqVo);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (DuplicateKeyException e) {
            result.setInfo(ResultInfoEnum.SUCCESS);
            log.info("重复保存数据库信息:{}", JSON.toJSONString(dataBaseReqVo));
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("保存数据库信息异常:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public BasicResult updateDataBase(DataBaseReqVo dataBaseReqVo) {
        BasicResult result = new BasicResult();
        try {
            DataBase reqVo = JSON.parseObject(JSON.toJSONString(dataBaseReqVo), DataBase.class);
            databaseMapper.update(reqVo);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("修改数据库信息异常:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public PaginationResult<DataBaseResVo> queryDataBasePage(PaginationQuery<DataBaseReqVo> query) {
        PaginationResult<DataBaseResVo> result = new PaginationResult<DataBaseResVo>();
        Pagination pagination = query.getPagination();
        if (null == pagination) {
            log.info("未传分页，查默认分页，首页10条");
            pagination = new Pagination(10, 1);
        }
        int totalRecord = 0;
        List<DataBaseResVo> resTask = new ArrayList<DataBaseResVo>();
        try {
            DataBase dataBase = JSON.parseObject(JSON.toJSONString(query.getQuery()), DataBase.class);
            dataBase.setPageSize(pagination.getPageSize());
            dataBase.setStartNo((pagination.getpageNo() - 1) * pagination.getPageSize());
            totalRecord = databaseMapper.queryTotal(dataBase);
            if (totalRecord > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                List<DataBase> resList = databaseMapper.queryList(dataBase);
                for (DataBase res : resList) {
                    DataBaseResVo resVo = JSON.parseObject(JSON.toJSONString(res), DataBaseResVo.class);
                    resVo.setCreateTimeStr(sdf.format(resVo.getCreateTime()));
                    resVo.setUpdateTimeStr(sdf.format(resVo.getUpdateTime()));
                    resTask.add(resVo);
                }

            } else {
                log.info("查询数据库列表为空");
            }
            result.setList(resTask);
            result.setPagination(new Pagination(totalRecord, pagination.getPageSize(), pagination.getpageNo()));
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("分页查询数据库列表失败:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public ListResult<DataBaseResVo> queryTotalDataBase(DataBaseReqVo dataBaseReqVo) {
        ListResult<DataBaseResVo> result = new ListResult<DataBaseResVo>();
        List<DataBaseResVo> resVoList = new ArrayList<DataBaseResVo>();
        try {
            DataBase reqVo = JSON.parseObject(JSON.toJSONString(dataBaseReqVo), DataBase.class);
            List<DataBase> resList = databaseMapper.queryTotalList(reqVo);
            if (null != resList && resList.size() > 0) {
                for (DataBase dataBase : resList) {
                    DataBaseResVo resVo = JSON.parseObject(JSON.toJSONString(dataBase), DataBaseResVo.class);
                    resVoList.add(resVo);
                }
            } else {
                log.info("获取数据库列表信息为空");
            }
            result.setList(resVoList);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("查询数据库列表信息异常:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public BasicResult connectDataBase(DataBaseReqVo dataBaseReqVo) {
        BasicResult result = new BasicResult();
        Connection connection = null;
        try {
            connection = DbHelper.getConnection(dataBaseReqVo.getUrl(), dataBaseReqVo.getUsername(), dataBaseReqVo.getPassword());
            if (null != connection) {
                result.setInfo(ResultInfoEnum.SUCCESS);
                DbHelper.closeConnection(connection);
            }
        } catch (Exception e) {
            log.error("数据库连接异常:{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
        } finally {
            return result;
        }
    }

    @Override
    public BasicResult deleteDataBase(DataBaseReqVo dataBaseReqVo) {
        BasicResult result = new BasicResult();
        try {
            int total = interfaceManager.queryInterfaceByDataBaseCount(dataBaseReqVo.getDatabaseId());
            if (total > 0) {
                result.setCode("999999");
                String message = "当前数据库已关联" + total + "条接口信息，不能删除！";
                result.setMessage(message);
                return result;
            } else {
                DataBase reqVo = JSON.parseObject(JSON.toJSONString(dataBaseReqVo), DataBase.class);
                databaseMapper.delete(reqVo);
            }
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("删除数据库信息异常:{}", e.toString());
        } finally {
            return result;
        }
    }
}
