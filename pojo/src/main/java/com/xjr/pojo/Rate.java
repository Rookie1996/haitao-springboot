package com.xjr.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

public class Rate {
    @Id
    private Integer id;

    /**
     * 评分用户id
     */
    @Column(name = "from_user_id")
    private String fromUserId;

    @Column(name = "to_user_id")
    private String toUserId;

    /**
     * 被评分的行程
     */
    @Column(name = "skd_id")
    private String skdId;

    private BigDecimal stars;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取评分用户id
     *
     * @return from_user_id - 评分用户id
     */
    public String getFromUserId() {
        return fromUserId;
    }

    /**
     * 设置评分用户id
     *
     * @param fromUserId 评分用户id
     */
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * @return to_user_id
     */
    public String getToUserId() {
        return toUserId;
    }

    /**
     * @param toUserId
     */
    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * 获取被评分的行程
     *
     * @return skd_id - 被评分的行程
     */
    public String getSkdId() {
        return skdId;
    }

    /**
     * 设置被评分的行程
     *
     * @param skdId 被评分的行程
     */
    public void setSkdId(String skdId) {
        this.skdId = skdId;
    }

    /**
     * @return stars
     */
    public BigDecimal getStars() {
        return stars;
    }

    /**
     * @param stars
     */
    public void setStars(BigDecimal stars) {
        this.stars = stars;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}