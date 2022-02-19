package com.example.chat.Model;

import java.util.ArrayList;

public class UserStatus {
    private String profilePic , userName;
    private long lastUpdated;
    private ArrayList<Status> statuses;

    public UserStatus() {

    }

    public UserStatus(String profilePic, String userName, long lastUpdated, ArrayList<Status> statuses) {
        this.profilePic = profilePic;
        this.userName = userName;
        this.lastUpdated = lastUpdated;
        this.statuses = statuses;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public ArrayList<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(ArrayList<Status> statuses) {
        this.statuses = statuses;
    }
}
