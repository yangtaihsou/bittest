package com.bittest.platform.bg.manager;


import com.bittest.platform.bg.domain.po.Systems;

import java.util.List;

/**
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-25 11:16:35
 */
public interface SystemsManager {

    Systems queryObject(Systems systems);

    List<Systems> queryList(Systems systems);

    int queryTotal(Systems systems);

    int save(Systems systems);

    int update(Systems systems);

    int delete(Systems systems);

    List<Systems> querySystemList(Systems systems);

}
