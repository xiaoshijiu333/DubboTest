package fei.controller.customerController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import fei.CustomerServices;
import fei.model.Customer;
import fei.utils.ApiResult;
import fei.utils.BasePage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static fei.utils.ApiResult.succ;

/**
 * @author xiaoshijiu
 * @location DubboTest/fei
 * @date 2019/4/16
 */
@RestController
@RequestMapping("/Customer")
public class GetAllCustomer {


    @Reference
    private CustomerServices customerServices;

    /**
     * @function: 获取所有的客户
     */
    @RequestMapping(value = "/getAllCustomer", method = RequestMethod.GET)
    public ApiResult<Customer> getAllCustomer(BasePage basePage) {
        PageInfo<Customer> allCustomer = customerServices.getAllCustomer(basePage);
        ApiResult<Customer> succ = ApiResult.succ(allCustomer.getList());
        return succ;
    }
}
