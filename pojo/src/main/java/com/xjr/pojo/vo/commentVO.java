package com.xjr.pojo.vo;

public class commentVO {

    public String comment;

    public Integer nums;

    public String skdId;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String getSkdId() {
        return skdId;
    }

    public void setSkdId(String skdId) {
        this.skdId = skdId;
    }

    @Override
    public String toString() {
        return "commentVO{" +
                "comment='" + comment + '\'' +
                ", nums=" + nums +
                ", skdId='" + skdId + '\'' +
                '}';
    }
}
