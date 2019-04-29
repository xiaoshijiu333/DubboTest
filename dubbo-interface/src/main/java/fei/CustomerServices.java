package fei;

import com.github.pagehelper.PageInfo;
import fei.model.CustomerModel;
import fei.utils.BasePage;


/**
 * @author xiaoshijiu
 * @function Dubbo服务对外接口
 * @date 2019/4/16
 */
public interface CustomerServices {

    /**
     * @function: 查询所有客户
     */
    PageInfo<CustomerModel> getAllCustomer(BasePage basePage);
}
