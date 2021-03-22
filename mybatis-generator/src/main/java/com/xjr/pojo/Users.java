package com.xjr.pojo;

import java.util.Date;
import javax.persistence.*;

public class Users {
    /**
     * 用户编号
     */
    @Id
    @Column(name = "u_id")
    private String uId;

    private String openid;

    /**
     * 用户名，用于登录
     */
    @Column(name = "u_name")
    private String uName;

    @Column(name = "u_gender")
    private Integer uGender;

    @Column(name = "u_age")
    private Integer uAge;

    @Column(name = "u_email")
    private String uEmail;

    /**
     * 个人简介
     */
    @Column(name = "u_intro")
    private String uIntro;

    @Column(name = "u_type")
    private String uType;

    @Column(name = "u_address")
    private String uAddress;

    @Column(name = "u_face_image")
    private String uFaceImage;

    @Column(name = "u_nickname")
    private String uNickname;

    @Column(name = "u_like_counts")
    private Integer uLikeCounts;

    @Column(name = "u_level")
    private Integer uLevel;

    @Column(name = "u_register_time")
    private Date uRegisterTime;

    @Column(name = "u_third_party_id")
    private Integer uThirdPartyId;

    @Column(name = "u_logout_time")
    private Date uLogoutTime;

    /**
     * 0-活跃，1-已注销
     */
    @Column(name = "u_is_del")
    private Byte uIsDel;

    /**
     * 获取用户编号
     *
     * @return u_id - 用户编号
     */
    public String getuId() {
        return uId;
    }

    /**
     * 设置用户编号
     *
     * @param uId 用户编号
     */
    public void setuId(String uId) {
        this.uId = uId;
    }

    /**
     * @return openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * @param openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取用户名，用于登录
     *
     * @return u_name - 用户名，用于登录
     */
    public String getuName() {
        return uName;
    }

    /**
     * 设置用户名，用于登录
     *
     * @param uName 用户名，用于登录
     */
    public void setuName(String uName) {
        this.uName = uName;
    }

    /**
     * @return u_gender
     */
    public Integer getuGender() {
        return uGender;
    }

    /**
     * @param uGender
     */
    public void setuGender(Integer uGender) {
        this.uGender = uGender;
    }

    /**
     * @return u_age
     */
    public Integer getuAge() {
        return uAge;
    }

    /**
     * @param uAge
     */
    public void setuAge(Integer uAge) {
        this.uAge = uAge;
    }

    /**
     * @return u_email
     */
    public String getuEmail() {
        return uEmail;
    }

    /**
     * @param uEmail
     */
    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    /**
     * 获取个人简介
     *
     * @return u_intro - 个人简介
     */
    public String getuIntro() {
        return uIntro;
    }

    /**
     * 设置个人简介
     *
     * @param uIntro 个人简介
     */
    public void setuIntro(String uIntro) {
        this.uIntro = uIntro;
    }

    /**
     * @return u_type
     */
    public String getuType() {
        return uType;
    }

    /**
     * @param uType
     */
    public void setuType(String uType) {
        this.uType = uType;
    }

    /**
     * @return u_address
     */
    public String getuAddress() {
        return uAddress;
    }

    /**
     * @param uAddress
     */
    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    /**
     * @return u_face_image
     */
    public String getuFaceImage() {
        return uFaceImage;
    }

    /**
     * @param uFaceImage
     */
    public void setuFaceImage(String uFaceImage) {
        this.uFaceImage = uFaceImage;
    }

    /**
     * @return u_nickname
     */
    public String getuNickname() {
        return uNickname;
    }

    /**
     * @param uNickname
     */
    public void setuNickname(String uNickname) {
        this.uNickname = uNickname;
    }

    /**
     * @return u_like_counts
     */
    public Integer getuLikeCounts() {
        return uLikeCounts;
    }

    /**
     * @param uLikeCounts
     */
    public void setuLikeCounts(Integer uLikeCounts) {
        this.uLikeCounts = uLikeCounts;
    }

    /**
     * @return u_level
     */
    public Integer getuLevel() {
        return uLevel;
    }

    /**
     * @param uLevel
     */
    public void setuLevel(Integer uLevel) {
        this.uLevel = uLevel;
    }

    /**
     * @return u_register_time
     */
    public Date getuRegisterTime() {
        return uRegisterTime;
    }

    /**
     * @param uRegisterTime
     */
    public void setuRegisterTime(Date uRegisterTime) {
        this.uRegisterTime = uRegisterTime;
    }

    /**
     * @return u_third_party_id
     */
    public Integer getuThirdPartyId() {
        return uThirdPartyId;
    }

    /**
     * @param uThirdPartyId
     */
    public void setuThirdPartyId(Integer uThirdPartyId) {
        this.uThirdPartyId = uThirdPartyId;
    }

    /**
     * @return u_logout_time
     */
    public Date getuLogoutTime() {
        return uLogoutTime;
    }

    /**
     * @param uLogoutTime
     */
    public void setuLogoutTime(Date uLogoutTime) {
        this.uLogoutTime = uLogoutTime;
    }

    /**
     * 获取0-活跃，1-已注销
     *
     * @return u_is_del - 0-活跃，1-已注销
     */
    public Byte getuIsDel() {
        return uIsDel;
    }

    /**
     * 设置0-活跃，1-已注销
     *
     * @param uIsDel 0-活跃，1-已注销
     */
    public void setuIsDel(Byte uIsDel) {
        this.uIsDel = uIsDel;
    }
}