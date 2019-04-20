package fei.mapper;

import fei.model.Customer;

import java.util.List;

/**
 * @author xiaoshijiu
 * @function 客户数据库接口
 * @date 2019/4/16
 */
public interface CustomerMapper {

    /**
     * @function: 查询所有客户
     */
    List<Customer> getAllCustomer();
}
