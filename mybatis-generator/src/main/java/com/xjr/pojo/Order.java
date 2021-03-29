package com.xjr.pojo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Order {
    /**
     * 自增id，主键索引容易建立
     */
    @Id
    private Long id;

    /**
     * 订单号
     */
    @Id
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 下单用户id
     */
    @Id
    @Column(name = "u_id")
    private String uId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 支付时间
     */
    @Column(name = "payment_time")
    private Date paymentTime;

    /**
     * 发货时间
     */
    @Column(name = "delivery_time")
    private Date deliveryTime;

    /**
     * 确认收货时间
     */
    @Column(name = "receive_time")
    private Date receiveTime;

    /**
     * 收货人姓名
     */
    @Column(name = "receiver_name")
    private String receiverName;

    /**
     * 收货人电话
     */
    @Column(name = "receiver_phone")
    private String receiverPhone;

    /**
     * 收货人邮编
     */
    @Column(name = "receiver_post_code")
    private String receiverPostCode;

    /**
     * 收货人省份
     */
    @Column(name = "receiver_province")
    private String receiverProvince;

    /**
     * 收货人城市
     */
    @Column(name = "receiver_city")
    private String receiverCity;

    /**
     * 收货人街道
     */
    @Column(name = "receiver_region")
    private String receiverRegion;

    /**
     * 收货人更加细节的地址
     */
    @Column(name = "receiver_detail_addr")
    private String receiverDetailAddr;

    /**
     * 订单备注
     */
    private String note;

    /**
     * 订单总金额
     */
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    /**
     * 订单状态 1:未支付（已创建但未支付）， 2：已支付（已创建并已支付），3：已取消，4：已完成（后续加入货运后的订单状态）
     */
    private Byte status;

    /**
     * 物流公司（配送方式）
     */
    @Column(name = "delivery_company")
    private String deliveryCompany;

    /**
     * 物流单号
     */
    @Column(name = "delivery_sn")
    private String deliverySn;

    /**
     * 订单商品件数
     */
    @Column(name = "total_count")
    private Integer totalCount;

    /**
     * 0-默认，1-已删除订单
     */
    @Column(name = "is_del")
    private Byte isDel;

    /**
     * 获取自增id，主键索引容易建立
     *
     * @return id - 自增id，主键索引容易建立
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增id，主键索引容易建立
     *
     * @param id 自增id，主键索引容易建立
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单号
     *
     * @return order_no - 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单号
     *
     * @param orderNo 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取下单用户id
     *
     * @return u_id - 下单用户id
     */
    public String getuId() {
        return uId;
    }

    /**
     * 设置下单用户id
     *
     * @param uId 下单用户id
     */
    public void setuId(String uId) {
        this.uId = uId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取支付时间
     *
     * @return payment_time - 支付时间
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * 设置支付时间
     *
     * @param paymentTime 支付时间
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 获取发货时间
     *
     * @return delivery_time - 发货时间
     */
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    /**
     * 设置发货时间
     *
     * @param deliveryTime 发货时间
     */
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * 获取确认收货时间
     *
     * @return receive_time - 确认收货时间
     */
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     * 设置确认收货时间
     *
     * @param receiveTime 确认收货时间
     */
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * 获取收货人姓名
     *
     * @return receiver_name - 收货人姓名
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 设置收货人姓名
     *
     * @param receiverName 收货人姓名
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * 获取收货人电话
     *
     * @return receiver_phone - 收货人电话
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * 设置收货人电话
     *
     * @param receiverPhone 收货人电话
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    /**
     * 获取收货人邮编
     *
     * @return receiver_post_code - 收货人邮编
     */
    public String getReceiverPostCode() {
        return receiverPostCode;
    }

    /**
     * 设置收货人邮编
     *
     * @param receiverPostCode 收货人邮编
     */
    public void setReceiverPostCode(String receiverPostCode) {
        this.receiverPostCode = receiverPostCode;
    }

    /**
     * 获取收货人省份
     *
     * @return receiver_province - 收货人省份
     */
    public String getReceiverProvince() {
        return receiverProvince;
    }

    /**
     * 设置收货人省份
     *
     * @param receiverProvince 收货人省份
     */
    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    /**
     * 获取收货人城市
     *
     * @return receiver_city - 收货人城市
     */
    public String getReceiverCity() {
        return receiverCity;
    }

    /**
     * 设置收货人城市
     *
     * @param receiverCity 收货人城市
     */
    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    /**
     * 获取收货人街道
     *
     * @return receiver_region - 收货人街道
     */
    public String getReceiverRegion() {
        return receiverRegion;
    }

    /**
     * 设置收货人街道
     *
     * @param receiverRegion 收货人街道
     */
    public void setReceiverRegion(String receiverRegion) {
        this.receiverRegion = receiverRegion;
    }

    /**
     * 获取收货人更加细节的地址
     *
     * @return receiver_detail_addr - 收货人更加细节的地址
     */
    public String getReceiverDetailAddr() {
        return receiverDetailAddr;
    }

    /**
     * 设置收货人更加细节的地址
     *
     * @param receiverDetailAddr 收货人更加细节的地址
     */
    public void setReceiverDetailAddr(String receiverDetailAddr) {
        this.receiverDetailAddr = receiverDetailAddr;
    }

    /**
     * 获取订单备注
     *
     * @return note - 订单备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置订单备注
     *
     * @param note 订单备注
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 获取订单总金额
     *
     * @return total_price - 订单总金额
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * 设置订单总金额
     *
     * @param totalPrice 订单总金额
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * 获取订单状态 1:未支付（已创建但未支付）， 2：已支付（已创建并已支付），3：已取消，4：已完成（后续加入货运后的订单状态）
     *
     * @return status - 订单状态 1:未支付（已创建但未支付）， 2：已支付（已创建并已支付），3：已取消，4：已完成（后续加入货运后的订单状态）
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置订单状态 1:未支付（已创建但未支付）， 2：已支付（已创建并已支付），3：已取消，4：已完成（后续加入货运后的订单状态）
     *
     * @param status 订单状态 1:未支付（已创建但未支付）， 2：已支付（已创建并已支付），3：已取消，4：已完成（后续加入货运后的订单状态）
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取物流公司（配送方式）
     *
     * @return delivery_company - 物流公司（配送方式）
     */
    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    /**
     * 设置物流公司（配送方式）
     *
     * @param deliveryCompany 物流公司（配送方式）
     */
    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    /**
     * 获取物流单号
     *
     * @return delivery_sn - 物流单号
     */
    public String getDeliverySn() {
        return deliverySn;
    }

    /**
     * 设置物流单号
     *
     * @param deliverySn 物流单号
     */
    public void setDeliverySn(String deliverySn) {
        this.deliverySn = deliverySn;
    }

    /**
     * 获取订单商品件数
     *
     * @return total_count - 订单商品件数
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * 设置订单商品件数
     *
     * @param totalCount 订单商品件数
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 获取0-默认，1-已删除订单
     *
     * @return is_del - 0-默认，1-已删除订单
     */
    public Byte getIsDel() {
        return isDel;
    }

    /**
     * 设置0-默认，1-已删除订单
     *
     * @param isDel 0-默认，1-已删除订单
     */
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }
}