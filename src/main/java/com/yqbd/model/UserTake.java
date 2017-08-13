package com.yqbd.model;

public class UserTake extends UserTakeKey {
    private Integer status;

    private Integer publisherCommentId;

    private Integer receiverCommentId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPublisherCommentId() {
        return publisherCommentId;
    }

    public void setPublisherCommentId(Integer publisherCommentId) {
        this.publisherCommentId = publisherCommentId;
    }

    public Integer getReceiverCommentId() {
        return receiverCommentId;
    }

    public void setReceiverCommentId(Integer receiverCommentId) {
        this.receiverCommentId = receiverCommentId;
    }
}