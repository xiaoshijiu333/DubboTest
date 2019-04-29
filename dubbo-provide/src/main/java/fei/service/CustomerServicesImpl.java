package fei.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import fei.entity.Customer;
import fei.mapper.CustomerMapper;
import fei.model.CustomerModel;
import fei.CustomerServices;
import fei.utils.BasePage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoshijiu
 * @function Dubbo服务提供方
 * @date 2019/4/16
 */

@Service
public class CustomerServicesImpl implements CustomerServices {

    private static final Log log= LogFactory.getLog(CustomerServicesImpl.class);

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * @function: 查询所有客户
     */
    @Override
    public PageInfo<CustomerModel> getAllCustomer(BasePage basePage) {
        PageHelper.startPage(basePage.getPageNum(), basePage.getPageSize());

        log.warn("通用Mapper查询开始");

        //使用通用mapper查询
        Example e = new Example(Customer.class);
        e.createCriteria().andEqualTo("custProfession", "法师");
        List<Customer> customers = customerMapper.selectByExample(e);

        log.warn("通用Mapper查询结果：");
        customers.stream().forEach(System.out::println);

        //实体 ——》模型，写的很烂，后期修改
        List<CustomerModel> customerModels = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerModel customerModel = new CustomerModel();
            customerModel.setCustId(customer.getCustId());
            customerModel.setCustName(customer.getCustName());
            customerModel.setCustEmail(customer.getEmail());
            customerModel.setCustPhone(customer.getCustPhone());
            customerModel.setCustProfession(customer.getCustProfession());
            customerModels.add(customerModel);
        }

        PageInfo<CustomerModel> pageInfo = new PageInfo<>(customerModels);

        return pageInfo;
    }
}
