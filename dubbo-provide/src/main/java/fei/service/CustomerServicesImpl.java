package fei.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fei.mapper.CustomerMapper;
import fei.model.Customer;
import fei.CustomerServices;
import fei.utils.BasePage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xiaoshijiu
 * @function Dubbo服务提供方
 * @date 2019/4/16
 */

@Service
public class CustomerServicesImpl implements CustomerServices {

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * @function: 查询所有客户
     */
    @Override
    public PageInfo<Customer> getAllCustomer(BasePage basePage) {
        PageHelper.startPage(basePage.getPageNum(),basePage.getPageSize());
        List<Customer> customers = customerMapper.getAllCustomer();
        PageInfo<Customer> pageInfo = new PageInfo<>(customers);
        return pageInfo;
    }
}
