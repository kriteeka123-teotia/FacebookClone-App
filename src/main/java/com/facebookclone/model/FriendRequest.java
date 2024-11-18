package com.facebookclone.model;

public class FriendRequest {
	private int requestId;
    private int senderId;
    private int receiverId;

    // Constructors, Getters, and Setters
    public FriendRequest() {}

    public FriendRequest(int requestId, int senderId, int receiverId) {
        this.requestId = requestId;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public int getRequestId() { return requestId; }
    public void setRequestId(int requestId) { this.requestId = requestId; }

    public int getSenderId() { return senderId; }
    public void setSenderId(int senderId) { this.senderId = senderId; }

    public int getReceiverId() { return receiverId; }
    public void setReceiverId(int receiverId) { this.receiverId = receiverId; }

}
