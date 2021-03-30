package com.xjr.pojo.vo;

import com.xjr.pojo.Comments;
import com.xjr.pojo.Schedules;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class selectCardVO implements Serializable {

    public Schedules schedules;

    public BigDecimal rate;

    public List<Comments> commentsList;

    public Schedules getSchedules() {
        return schedules;
    }

    public void setSchedules(Schedules schedules) {
        this.schedules = schedules;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    @Override
    public String toString() {
        return "selectCardVO{" +
                "schedules=" + schedules +
                ", rate=" + rate +
                ", commentsList=" + commentsList +
                '}';
    }
}
