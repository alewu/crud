package com.ale.controller;

import com.ale.common.page.PageBean;
import com.ale.common.page.PageParam;
import com.ale.common.response.Response;
import com.ale.constants.RestURIConstants;
import com.ale.entity.Dept;
import com.ale.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.ale.constants.RestURIConstants.DEPTS;
import static com.ale.constants.RestURIConstants.DEPT_ID;

/**
 * @author alewu
 * @date 2017/10/30 11:12
 * @description 部门相关
 */
@RestController
@RequestMapping(RestURIConstants.APP_PREFIX)
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping(DEPT_ID)
    public Response getDept(@PathVariable Long deptId){
        //Dept dept = deptService.getDept(deptId);
       // return new Response().success(dept);
        return new Response();
    }

    /**
     * @author alewu
     * @date 2017/10/30 11:14
     * @description 分页查询部门
     */
    @GetMapping(DEPTS)
    public Response listDept(PageParam PageParam){
        PageBean<Dept> pageBean = deptService.listDept(PageParam);
        return new Response().success();
    }

    /**
     * @author alewu
     * @date 2017/10/30 11:13
     * @description 新增单个部门
     */
    @PostMapping(DEPTS)
    public Response addDept(@RequestBody Dept dept){
        //deptService.addDept(dept);
        return new Response().success(dept);
    }

    /**
     * @author alewu
     * @date 2017/10/30 11:14
     * @description 批量新增部门
     */
//    @PostMapping(RestURIConstants.DEPTS)
//    public MyResponse addDepts(@RequestBody List<Dept> depts){
//        deptService.addDepts(depts);
//        return new MyResponse().success(depts);
//    }

    /**
     * @author alewu
     * @date 2017/10/30 11:15
     * @description 删除单个部门
     */
    @DeleteMapping(DEPT_ID)
    public Response deleteDept(@PathVariable Long deptId){
        deptService.deleteOne(deptId);
        return new Response().success();
    }

    /**
     * @author alewu
     * @date 2017/10/30 11:15
     * @description 删除多个部门
     */
//    @DeleteMapping(RestURIConstants.DEPTS)
//    public MyResponse deleteDepts(@RequestBody List<Long> deptIds){
//        // TODO
//
//        deptService.deleteDepts(deptIds);
//        return new MyResponse().success();
//    }

    /**
     * @author alewu
     * @date 2017/10/30 11:15
     * @description 更新部门信息
     */
    @PutMapping(DEPT_ID)
    public Response updateDept(@PathVariable String deptId,Dept dept){
       // int m = deptService.updateDept(dept);
        return new Response().success();
    }
}