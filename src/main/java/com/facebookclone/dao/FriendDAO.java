package com.facebookclone.dao;
import com.facebookclone.model.FriendRequest;

import java.util.ArrayList;
import java.util.List;
public class FriendDAO {
	private List<FriendRequest> friendRequests = new ArrayList<>();

    public void sendFriendRequest(FriendRequest request) {
        friendRequests.add(request);
    }

    public List<FriendRequest> getAllFriendRequests() {
        return friendRequests;
    }

}
