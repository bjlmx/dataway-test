package com.data.datawaytest.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.data.datawaytest.common.Result;
import com.data.datawaytest.common.ResultGenerator;
import com.data.datawaytest.common.utils.IdUtil;
import com.data.datawaytest.entity.MyPage;
import com.data.datawaytest.entity.MyWife;
import com.data.datawaytest.entity.MyWifeVO;
import com.data.datawaytest.mappings.WifeCover;
import com.data.datawaytest.services.WifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** @author Baijl
 * @date 2020-04-20
 * @time 10:40
 * @description
 */
@RestController
public class WifeController {
    @Autowired
    private WifeService wifeService;
    @PostMapping("/GET/getWife")
    public Result getWife(@RequestBody @Validated MyWifeVO wife){
        System.out.println(wife);

        return ResultGenerator.genSuccessResult(wife);
    }

    @GetMapping("GET/wife")
    public Result getWife(){
        MyWife myWife = new MyWife("老婆", "邹城市九龙小区", "邹城市实验中学", 20, "13853119765@qq.com");
        boolean save = wifeService.save(myWife);
        if(save){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("插入失败");
        }
    }

    /**
     * 获取所有记录
     * @return
     */
    @GetMapping("GET/all")
    public Result getList() {
        List<MyWife> list = wifeService.list();
        if(!list.isEmpty()){
           return ResultGenerator.genSuccessResult(list);
        }else {
           return ResultGenerator.genFailResult("查询失败");
        }
    }

    /**
     * 增加记录
     * @param wife
     * @return
     */
    @PostMapping("POST/wife")
    public Result addWife(@RequestBody MyWife wife){
        wife.setId(IdUtil.getId());
        if(wifeService.save(wife)){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("插入失败");
        }
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/GET/{id}")
    public Result getWife(@PathVariable("id") int id){
        MyWife byId = wifeService.getById(id);
        if(byId != null){
           return ResultGenerator.genSuccessResult(byId);
        }else {
           return ResultGenerator.genFailResult("记录不存在");
        }
    }


    /**
     * 多条件模糊查询（代码版）
     * @param wife
     * @return
     */
    @PostMapping("/GET")
    public Result getOneByInput(@RequestBody MyWife wife){

        List<MyWife> oneByInput = wifeService.getOneByInput(wife);
        if(!oneByInput.isEmpty()){
            return ResultGenerator.genSuccessResult(oneByInput);
        }else {
            return ResultGenerator.genFailResult("没有任何记录");
        }
    }

    /**
     * 多条件查询（手写SQL）
     * @param wife
     * @return
     */
    @PostMapping("/GET/ones")
    public Result getByAll(@RequestBody MyWife wife){
        List<MyWife> byAll = wifeService.getByAll(wife);
        if(!byAll.isEmpty()){
            return ResultGenerator.genSuccessResult(byAll);
        }else {
            return ResultGenerator.genFailResult("查询失败");
        }
    }

    /**
     * 多条件分页查询
     * @param wife
     * @param page
     * @return
     */
    @PostMapping("/GET/page")
    public Result getWife(@RequestBody MyWifeVO wife, MyPage page){
        System.out.println(page);
        System.out.println(wife);
        Page<MyWife> myWifePage = new Page<>(wife.getPageIndex(),wife.getPageSize());
        MyWife myWife = WifeCover.INSTANCE.toCoverMyWifeV0(wife);
        List<MyWife> myWives = wifeService.selectByPage(myWifePage, myWife);
        System.out.println(myWife);
        return ResultGenerator.genSuccessResult(myWives);
    }

    /**
     * 批量删除
     * @param myWifeVOList
     * @return
     */
    @PostMapping("/DELETE/one")
    public Result deleteOne(@RequestBody List<MyWifeVO> myWifeVOList){
        System.out.println(myWifeVOList);
        List<MyWife> myWife = WifeCover.INSTANCE.toCoverMyWifeV0List(myWifeVOList);
        System.out.println(myWife);
       if( wifeService.deleteOne(myWife)>0){
           return ResultGenerator.genSuccessResult();
       }else {
           return ResultGenerator.genFailResult("删除失败");
       }
    }

    @PostMapping("/UPDATE/one")
    public Result updateOne(@RequestBody MyWifeVO myWifeVO){
        MyWife myWife = WifeCover.INSTANCE.toCoverMyWifeV0(myWifeVO);
        if(wifeService.saveOrU(myWife)>0){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("更新失败");
        }
    }

}
