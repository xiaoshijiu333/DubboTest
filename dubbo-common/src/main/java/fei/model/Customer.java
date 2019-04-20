package fei.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


/**
 * @author xiaoshijiu
 * @function 客户实体类
 * @date 2019/4/16
 */

@Setter @Getter @ToString
public class Customer implements Serializable {
    private Integer custId;
    private String custName;
    private String custProfession;
    private String custPhone;
    private String custEmail;
}