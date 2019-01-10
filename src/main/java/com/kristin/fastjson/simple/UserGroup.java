package com.kristin.fastjson.simple;


import java.util.ArrayList;
import java.util.List;

public class UserGroup {
    private String groupname;

    private List<User> userList = new ArrayList<>();

    public UserGroup() {

    }

    public UserGroup(String groupname, List<User> userList) {
        this.groupname = groupname;
        this.userList = userList;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "groupname='" + groupname + '\'' +
                ", userList=" + userList +
                '}';
    }
}
