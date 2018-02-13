package com.demo.repositry;

import com.demo.domain.activity;
import com.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


import java.io.Serializable;
import java.util.List;

public interface ActivityRepositry extends JpaRepository<activity, Serializable>{

   
    activity findByActivityName(String activityName);
    activity findByActivityId(Long id);
    List<activity> findByRoleList(Role role);
    activity findByRoleListAndActivityId(Role role, Long id);
    activity findByRoleListAndActivityName(Role role, String activityName);

}