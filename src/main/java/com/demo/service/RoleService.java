package com.demo.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.domain.activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.domain.Role;
import com.demo.repositry.RoleRepository;
import com.demo.constants.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RoleService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(RoleService.class); 
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	MessageService messageService;

	public Map<String, Object> addRole(String roleName) {

		Map<String, Object> result = new HashMap<String, Object>();
		Boolean isSuccess = false;
		try{
		if (roleName !=null || roleName != ""){
			Role role =  roleRepository.findByRole(roleName);
			if(role == null){
				Role r = new Role();
				r.setRole(roleName);
				roleRepository.save(r);
				isSuccess = true;
				LOGGER.info("Role saved successfully {}", roleName);
				result.put("message", "success");
			}else {
				result.put("message", "already exist");
			}
		}
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			result.put("message","internal server error");
		}
		result.put("isSuccess", isSuccess);
		return result;
	}

	public Map<String, Object> changeStatus(Map<String, String> data) {

		Map<String, Object> result = new HashMap<String, Object>();
		Boolean isSuccess = false;
		try{
		Long id = Long.parseLong(data.get("id"));
		Boolean isActive = Boolean.parseBoolean(data.get("isActive"));
		LOGGER.info("try to update role"+ isActive);
		if (id !=null){
			Role role =  roleRepository.findById(id);
			Boolean active = role.getActive();
			if(role != null && role.getActive() != isActive){
				role.setActive(!active);
				roleRepository.save(role);
				LOGGER.info("Role updated successfully");
				isSuccess = true;
				result.put("message", "success");
			}else {
				result.put("message", "already exist");
			}
		}
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			result.put("message", "internal server error");
		}
		result.put("isSuccess", isSuccess);
		return result;
	}

	public Map<String, Object> getRoles() {

		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> allRoles = new ArrayList<Map<String, Object>>();

		Boolean isSuccess = false;
		try{
			List<Role> role =  roleRepository.findAll();
			for (Role r : role){
				List<Map<String, Object>> allActivities = new ArrayList<Map<String, Object>>();
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("roleId", r.getId());
				data.put("role", r.getRole());
				List<activity> activities =  r.getActivities();
				for(activity activity : activities){
					Map<String, Object> data1 = new HashMap<String, Object>();
					data1.put("activityId", activity.getActivityId());
					data1.put("activityName", activity.getActivityName());
					allActivities.add(data1);
				}
				LOGGER.info("Roles list fetched successfully");
				data.put("activities", allActivities);
				allRoles.add(data);
			}
			isSuccess = true;
			result.put("roleList", allRoles);
			result.put("message", messageService.getMessage(Message.SUCCESS));
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
		}
		result.put("isSuccess", isSuccess);
		return result;
	}

	public Map<String, Object> getActiveRoles() {

		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> allRoles = new ArrayList<Map<String, Object>>();

		Boolean isSuccess = false;
		try{
			List<Role> role =  roleRepository.findByIsActiveTrue();
			for (Role r : role){
				List<Map<String, Object>> allActivities = new ArrayList<Map<String, Object>>();
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("roleId", r.getId());
				data.put("role", r.getRole());
				List<activity> activities =  r.getActivities();
				for(activity activity : activities){
					Map<String, Object> data1 = new HashMap<String, Object>();
					data1.put("activityId", activity.getActivityId());
					data1.put("activityName", activity.getActivityName());
					allActivities.add(data1);
				}
				LOGGER.info("Active roles list fetched successfully");
				data.put("activities", allActivities);
				allRoles.add(data);
			}
			isSuccess = true;
			result.put("roleList", allRoles);
			result.put("message", messageService.getMessage(Message.SUCCESS));
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
		}
		result.put("isSuccess", isSuccess);
		return result;
	}

	public Map<String, Object> getRolesByRoleName(String RoleName) {

		Map<String, Object> result = new HashMap<String, Object>();
		Boolean isSuccess = false;
		try{
			Role role =  roleRepository.findByRole(RoleName);
			LOGGER.info("Role fetched successfully");
			isSuccess = true;
			result.put("data", role);
			result.put("message", messageService.getMessage(Message.SUCCESS));
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
		}
		result.put("isSuccess", isSuccess);
		return result;
	}

	public Map<String, Object> getRolesById(Long id) {

		Map<String, Object> result = new HashMap<String, Object>();
		Boolean isSuccess = false;
		try{
			Role role =  roleRepository.findById(id);
			LOGGER.info("Role fetched successfully");
			isSuccess = true;
			result.put("data", role);
			result.put("message", messageService.getMessage(Message.SUCCESS));
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
		}
		result.put("isSuccess", isSuccess);
		return result;
	}
}
