package com.bittest.platform.bg.dao;

import com.bittest.platform.bg.domain.po.Systems;

import java.util.List;

/**
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-25 11:16:35
 */
public interface SystemsMapper extends BaseMapper<Systems> {

    List<Systems> querySystemList(Systems systems);

}
