package com.xjr.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Comments {
    @Id
    private String id;

    /**
     * 被短评的行程号
     */
    @Column(name = "skd_id")
    private String skdId;

    /**
     * 进行短评的用户
     */
    @Column(name = "from_user_id")
    private String fromUserId;

    /**
     * 行程代购号
     */
    @Column(name = "to_user_id")
    private String toUserId;

    /**
     * 0-未读，1-已读
     */
    private Byte status;

    @Column(name = "create_time")
    private Date createTime;

    private String comment;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取被短评的行程号
     *
     * @return skd_id - 被短评的行程号
     */
    public String getSkdId() {
        return skdId;
    }

    /**
     * 设置被短评的行程号
     *
     * @param skdId 被短评的行程号
     */
    public void setSkdId(String skdId) {
        this.skdId = skdId;
    }

    /**
     * 获取进行短评的用户
     *
     * @return from_user_id - 进行短评的用户
     */
    public String getFromUserId() {
        return fromUserId;
    }

    /**
     * 设置进行短评的用户
     *
     * @param fromUserId 进行短评的用户
     */
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * 获取行程代购号
     *
     * @return to_user_id - 行程代购号
     */
    public String getToUserId() {
        return toUserId;
    }

    /**
     * 设置行程代购号
     *
     * @param toUserId 行程代购号
     */
    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * 获取0-未读，1-已读
     *
     * @return status - 0-未读，1-已读
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0-未读，1-已读
     *
     * @param status 0-未读，1-已读
     */
    public void setStatus(Byte status) {
        this.status = status;
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

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id='" + id + '\'' +
                ", skdId='" + skdId + '\'' +
                ", fromUserId='" + fromUserId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", comment='" + comment + '\'' +
                '}';
    }
}