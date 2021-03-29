package com.xjr.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "users_like_schedules")
public class UsersLikeSchedules {
    @Id
    private Integer id;

    /**
     * 点赞/收藏的用户
     */
    @Column(name = "from_user_id")
    private String fromUserId;

    /**
     * 代购者id
     */
    @Column(name = "to_user_id")
    private String toUserId;

    /**
     * 被点赞/收藏的行程
     */
    @Column(name = "skd_id")
    private String skdId;

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
     * 获取点赞/收藏的用户
     *
     * @return from_user_id - 点赞/收藏的用户
     */
    public String getFromUserId() {
        return fromUserId;
    }

    /**
     * 设置点赞/收藏的用户
     *
     * @param fromUserId 点赞/收藏的用户
     */
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * 获取代购者id
     *
     * @return to_user_id - 代购者id
     */
    public String getToUserId() {
        return toUserId;
    }

    /**
     * 设置代购者id
     *
     * @param toUserId 代购者id
     */
    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * 获取被点赞/收藏的行程
     *
     * @return skd_id - 被点赞/收藏的行程
     */
    public String getSkdId() {
        return skdId;
    }

    /**
     * 设置被点赞/收藏的行程
     *
     * @param skdId 被点赞/收藏的行程
     */
    public void setSkdId(String skdId) {
        this.skdId = skdId;
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