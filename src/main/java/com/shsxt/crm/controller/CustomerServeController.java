package com.shsxt.crm.controller;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.ResultInfo;
import com.shsxt.crm.query.CustomerServeQuery;
import com.shsxt.crm.query.RoleQuery;
import com.shsxt.crm.service.CustomerServeService;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import com.shsxt.crm.vo.CustomerServe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("customer_serve")
public class CustomerServeController extends BaseController {

    @Resource
    private CustomerServeService customerServeService;

    @Resource
    private UserService userService;


    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(CustomerServe customerServe, HttpServletRequest request) {
        customerServe.setCreatePeople(userService.queryById(LoginUserUtil.releaseUserIdFromCookie(request)).getTrueName());
        customerServeService.saveCustomerServe(customerServe);
        return success("服务记录添加成功!");
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCustomersByParams(CustomerServeQuery customerServeQuery, @RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer rows){
        customerServeQuery.setPageSize(rows);
        customerServeQuery.setPageNum(page);
        return customerServeService.queryByParamsForDataGrid(customerServeQuery);
    }



    @RequestMapping("index/{page}")
    public String index(@PathVariable Integer page){
        if(page ==1){
            return "customer_serve_create";
        }else if(page ==2){
            return "customer_serve_assign";
        }else if(page ==3){
            return "customer_serve_proce";
        }else if(page ==4){
            return "";
        }else if(page ==5){
            return "";
        }else{
            return "";
        }
    }


    @RequestMapping("updateCustomerServe")
    @ResponseBody
    public ResultInfo updateCustomerServe(CustomerServe customerServe){
        customerServeService.updateCustomerServe(customerServe);
        return success("服务更新成功!");
    }
}
