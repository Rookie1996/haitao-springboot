package com.xjr.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

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

    /**
     * 0-女,1-男
     */
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

    /**
     * 用户类型
     */
    @Column(name = "u_type")
    private String uType;

    /**
     * 微信获取的所在地信息
     */
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

    /**
     * 当前定位地点，根据地点推荐行程
     */
    @Column(name = "u_location")
    private String uLocation;

    /**
     * 设备登录型号
     */
    @Column(name = "u_third_party")
    private String uThirdParty;

    @Column(name = "u_register_time")
    private Date uRegisterTime;

    /**
     * 登出时间
     */
    @Column(name = "u_logout_time")
    private Date uLogoutTime;

    /**
     * 0-收货地址未完善，1-收货地址已完善
     */
    @Column(name = "u_addr_complete")
    private Byte uAddrComplete;

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
     * 获取0-女,1-男
     *
     * @return u_gender - 0-女,1-男
     */
    public Integer getuGender() {
        return uGender;
    }

    /**
     * 设置0-女,1-男
     *
     * @param uGender 0-女,1-男
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
     * 获取用户类型
     *
     * @return u_type - 用户类型
     */
    public String getuType() {
        return uType;
    }

    /**
     * 设置用户类型
     *
     * @param uType 用户类型
     */
    public void setuType(String uType) {
        this.uType = uType;
    }

    /**
     * 获取微信获取的所在地信息
     *
     * @return u_address - 微信获取的所在地信息
     */
    public String getuAddress() {
        return uAddress;
    }

    /**
     * 设置微信获取的所在地信息
     *
     * @param uAddress 微信获取的所在地信息
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
     * 获取当前定位地点，根据地点推荐行程
     *
     * @return u_location - 当前定位地点，根据地点推荐行程
     */
    public String getuLocation() {
        return uLocation;
    }

    /**
     * 设置当前定位地点，根据地点推荐行程
     *
     * @param uLocation 当前定位地点，根据地点推荐行程
     */
    public void setuLocation(String uLocation) {
        this.uLocation = uLocation;
    }

    /**
     * 获取设备登录型号
     *
     * @return u_third_party - 设备登录型号
     */
    public String getuThirdParty() {
        return uThirdParty;
    }

    /**
     * 设置设备登录型号
     *
     * @param uThirdParty 设备登录型号
     */
    public void setuThirdParty(String uThirdParty) {
        this.uThirdParty = uThirdParty;
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
     * 获取登出时间
     *
     * @return u_logout_time - 登出时间
     */
    public Date getuLogoutTime() {
        return uLogoutTime;
    }

    /**
     * 设置登出时间
     *
     * @param uLogoutTime 登出时间
     */
    public void setuLogoutTime(Date uLogoutTime) {
        this.uLogoutTime = uLogoutTime;
    }

    /**
     * 获取0-收货地址未完善，1-收货地址已完善
     *
     * @return u_addr_complete - 0-收货地址未完善，1-收货地址已完善
     */
    public Byte getuAddrComplete() {
        return uAddrComplete;
    }

    /**
     * 设置0-收货地址未完善，1-收货地址已完善
     *
     * @param uAddrComplete 0-收货地址未完善，1-收货地址已完善
     */
    public void setuAddrComplete(Byte uAddrComplete) {
        this.uAddrComplete = uAddrComplete;
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