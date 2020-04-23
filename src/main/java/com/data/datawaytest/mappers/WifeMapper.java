package com.data.datawaytest.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.data.datawaytest.entity.MyWife;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** @author Baijl
 * @date 2020-04-21
 * @time 10:40
 * @description
 */
public interface WifeMapper extends BaseMapper<MyWife> {
    List<MyWife> getByAll(MyWife wife);

    IPage<MyWife> selectByPage(Page<MyWife> myWifePage,@Param(("wife")) MyWife wife);
}
