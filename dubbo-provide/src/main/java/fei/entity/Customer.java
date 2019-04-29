package fei.entity;

import javax.persistence.*;

@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "cust_id")
    private Integer custId;

    @Column(name = "cust_name")
    private String custName;

    @Column(name = "cust_profession")
    private String custProfession;

    @Column(name = "cust_phone")
    private String custPhone;

    private String email;

    /**
     * @return cust_id
     */
    public Integer getCustId() {
        return custId;
    }

    /**
     * @param custId
     */
    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    /**
     * @return cust_name
     */
    public String getCustName() {
        return custName;
    }

    /**
     * @param custName
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * @return cust_profession
     */
    public String getCustProfession() {
        return custProfession;
    }

    /**
     * @param custProfession
     */
    public void setCustProfession(String custProfession) {
        this.custProfession = custProfession;
    }

    /**
     * @return cust_phone
     */
    public String getCustPhone() {
        return custPhone;
    }

    /**
     * @param custPhone
     */
    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}