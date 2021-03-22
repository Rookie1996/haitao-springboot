package com.xjr.pojo;

import javax.persistence.*;

@Table(name = "users_address")
public class UsersAddress {
    /**
     * 自动递增id
     */
    @Id
    @Column(name = "ua_id")
    private Integer uaId;

    /**
     * 邮编号码
     */
    @Column(name = "ua_postalcode")
    private String uaPostalcode;

    /**
     * 真实姓名
     */
    @Column(name = "ua_name")
    private String uaName;

    @Column(name = "ua_tel")
    private Long uaTel;

    @Column(name = "ua_province")
    private String uaProvince;

    @Column(name = "ua_city")
    private String uaCity;

    @Column(name = "ua_country")
    private String uaCountry;

    @Column(name = "ua_detail")
    private String uaDetail;

    /**
     * 0-不是默认地址，1-为默认地址
     */
    @Column(name = "ua_default")
    private Byte uaDefault;

    /**
     * 外键
     */
    @Column(name = "u_id")
    private String uId;

    /**
     * 获取自动递增id
     *
     * @return ua_id - 自动递增id
     */
    public Integer getUaId() {
        return uaId;
    }

    /**
     * 设置自动递增id
     *
     * @param uaId 自动递增id
     */
    public void setUaId(Integer uaId) {
        this.uaId = uaId;
    }

    /**
     * 获取邮编号码
     *
     * @return ua_postalcode - 邮编号码
     */
    public String getUaPostalcode() {
        return uaPostalcode;
    }

    /**
     * 设置邮编号码
     *
     * @param uaPostalcode 邮编号码
     */
    public void setUaPostalcode(String uaPostalcode) {
        this.uaPostalcode = uaPostalcode;
    }

    /**
     * 获取真实姓名
     *
     * @return ua_name - 真实姓名
     */
    public String getUaName() {
        return uaName;
    }

    /**
     * 设置真实姓名
     *
     * @param uaName 真实姓名
     */
    public void setUaName(String uaName) {
        this.uaName = uaName;
    }

    /**
     * @return ua_tel
     */
    public Long getUaTel() {
        return uaTel;
    }

    /**
     * @param uaTel
     */
    public void setUaTel(Long uaTel) {
        this.uaTel = uaTel;
    }

    /**
     * @return ua_province
     */
    public String getUaProvince() {
        return uaProvince;
    }

    /**
     * @param uaProvince
     */
    public void setUaProvince(String uaProvince) {
        this.uaProvince = uaProvince;
    }

    /**
     * @return ua_city
     */
    public String getUaCity() {
        return uaCity;
    }

    /**
     * @param uaCity
     */
    public void setUaCity(String uaCity) {
        this.uaCity = uaCity;
    }

    /**
     * @return ua_country
     */
    public String getUaCountry() {
        return uaCountry;
    }

    /**
     * @param uaCountry
     */
    public void setUaCountry(String uaCountry) {
        this.uaCountry = uaCountry;
    }

    /**
     * @return ua_detail
     */
    public String getUaDetail() {
        return uaDetail;
    }

    /**
     * @param uaDetail
     */
    public void setUaDetail(String uaDetail) {
        this.uaDetail = uaDetail;
    }

    /**
     * 获取0-不是默认地址，1-为默认地址
     *
     * @return ua_default - 0-不是默认地址，1-为默认地址
     */
    public Byte getUaDefault() {
        return uaDefault;
    }

    /**
     * 设置0-不是默认地址，1-为默认地址
     *
     * @param uaDefault 0-不是默认地址，1-为默认地址
     */
    public void setUaDefault(Byte uaDefault) {
        this.uaDefault = uaDefault;
    }

    /**
     * 获取外键
     *
     * @return u_id - 外键
     */
    public String getuId() {
        return uId;
    }

    /**
     * 设置外键
     *
     * @param uId 外键
     */
    public void setuId(String uId) {
        this.uId = uId;
    }
}