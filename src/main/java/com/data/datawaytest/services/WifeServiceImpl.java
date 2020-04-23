package com.data.datawaytest.services;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.data.datawaytest.entity.MyWife;
import com.data.datawaytest.mappers.WifeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/** @author Baijl
 * @date 2020-04-21
 * @time 11:01
 * @description
 */
@Service
public class WifeServiceImpl extends ServiceImpl<WifeMapper, MyWife> implements WifeService{
    @Override
    public List<MyWife> getOneByInput(MyWife wife) {
        QueryWrapper<MyWife> myWifeQueryWrapper = new QueryWrapper<>();
        if(wife.getName()!=null&&wife.getName()!=""){
            myWifeQueryWrapper.like("name", wife.getName());
        }
        if(wife.getAddress()!=null&&wife.getAddress()!=""){
            myWifeQueryWrapper.like("address", wife.getAddress());
        }
        if(wife.getAge()!=null){
            myWifeQueryWrapper.like("age", wife.getAge());
        }
        if(wife.getSchool()!=null&&wife.getSchool()!=""){
            myWifeQueryWrapper.like("school", wife.getSchool());
        }
        if(wife.getEmail()!=null&&wife.getEmail()!=""){
            myWifeQueryWrapper.like("email", wife.getEmail());
        }
        return this.baseMapper.selectList(myWifeQueryWrapper);
    }

    @Override
    public List<MyWife> getByAll(MyWife wife) {
       return this.baseMapper.getByAll(wife);
    }

    @Override
    public List<MyWife> selectByPage(Page<MyWife> myWifePage, MyWife wife) {
        QueryWrapper<MyWife> myWifeQueryWrapper = new QueryWrapper<>();

       return baseMapper.selectByPage(myWifePage,wife).getRecords();
        //return baseMapper.selectPage(myWifePage,myWifeQueryWrapper).getRecords();
    }

    @Override
    public int deleteOne(List<MyWife> myWife) {
        QueryWrapper<MyWife> myWifeQueryWrapper = new QueryWrapper<>();
        List<String> collect = myWife.stream().map(x -> x.getId()).collect(Collectors.toList());
       return baseMapper.deleteBatchIds(collect);
    }

    @Override
    public int saveOrU(MyWife myWife) {

        return baseMapper.updateById(myWife);
    }
}
