package com.demo.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class activity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long activityId;
    
    private String activityName;

    private boolean isActive;
   

   

    @ManyToMany(mappedBy = "activities")
    private List<Role> roleList;


    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

   
    

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

}

