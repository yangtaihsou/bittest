package com.bittest.platform.pg.util.datatable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 分页数据组装费的帮助类
 *
 *
 *
 */
public class DataTableHelper {

    public static DataTableParameter buildDataTableParameter(String aoData) {
        Map<String, Object> map = covertJsonStringToHashMap(aoData);
        int sEcho = map.get("sEcho") == null ? 0 : (Integer) map.get("sEcho");
        int iDisplayStart = map.get("iDisplayStart") == null ? 0 : (Integer) map.get("iDisplayStart");
        int iDisplayLength = map.get("iDisplayLength") == null ? 0 : (Integer) map.get("iDisplayLength");
        int iColumns = map.get("iColumns") == null ? 0 : (Integer) map.get("iColumns");
        int iSortingCols = map.get("iSortingCols") == null ? 0 : (Integer) map.get("iSortingCols");

        List<String> mDataProps = Lists.newArrayList();
        List<Boolean> bSortables = Lists.newArrayList();
        for (int i = 0; i < iColumns; i++) {
            String dataProp = (String) map.get("mDataProp_" + i);
            Boolean sortable = (Boolean) map.get("bSortable_" + i);
            mDataProps.add(dataProp);
            bSortables.add(sortable);
        }

        List<Integer> iSortCols = Lists.newArrayList();
        List<String> sSortDirs = Lists.newArrayList();
        List<String> iSortColsName = Lists.newArrayList();
        for (int i = 0; i < iSortingCols; i++) {
            Integer sortCol = (Integer) map.get("iSortCol_" + i);
            String sortColName = mDataProps.get(sortCol);
            String sortDir = (String) map.get("sSortDir_" + i);
            iSortCols.add(sortCol);
            sSortDirs.add(sortDir);
            iSortColsName.add(sortColName);
        }
        return new DataTableParameter(sEcho, iDisplayStart, iDisplayLength, iColumns, mDataProps, bSortables,
                iSortingCols, iSortCols, sSortDirs, iSortColsName);
    }

    public static Map<String, Object> covertJsonStringToHashMap(String aoData) {
        JSONArray jsonArray = JSONArray.parseArray(aoData);
        Map<String, Object> map = Maps.newHashMap();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            map.put(jsonObj.getString("name"), jsonObj.get("value"));
        }
        return map;
    }

}
