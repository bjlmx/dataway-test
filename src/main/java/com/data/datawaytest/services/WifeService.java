package com.data.datawaytest.services;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.data.datawaytest.entity.MyWife;

import java.util.List;

/** @author Baijl
 * @date 2020-04-21
 * @time 10:46
 * @description
 */
public interface WifeService extends IService<MyWife> {
    List<MyWife> getOneByInput(MyWife wife);

    List<MyWife> getByAll(MyWife wife);

    List<MyWife> selectByPage(Page<MyWife> myWifePage, MyWife wife);

    int deleteOne(List<MyWife> myWife);

    int saveOrU(MyWife myWife);
}
