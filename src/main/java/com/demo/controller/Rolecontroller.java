package com.demo.controller;

import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.demo.mapping.URLMapping;
import com.demo.service.RoleService;
import com.demo.util.Activity;
import com.demo.util.ResponseHandler;



@RestController

public class Rolecontroller {

private static Logger LOGGER = LoggerFactory.getLogger(Rolecontroller.class);
	@Autowired
	RoleService roleService;
	
	/**
	 * @author anil
	 * @param role
	 * @return role object
	 */

	@RequestMapping(value = URLMapping.ADD_ROLE, method = RequestMethod.POST)
	public ResponseEntity<Object> addRole(@RequestBody String role) {
		Map<String, Object> result = null;
		try {
			result = roleService.addRole(role);
			if(result.get("isSuccess").equals(true)){
			return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
			}
			else
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);		
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}
	
	/**
	 * @author anil
	 * @param data
	 * @return
	 */
	
	@RequestMapping(value = URLMapping.ACTIVATE_OR_DEACTIVATE_ROLE, method = RequestMethod.POST)
	public ResponseEntity<Object> changeStatus(@RequestBody Map<String, String> data) {
		Map<String, Object> result = null;
		try {
			result = roleService.changeStatus(data);
			if(result.get("isSuccess").equals(true)){
			return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
			}
			else
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);		
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}
	
	/**
	 * @author anil
	 * @return list of roles
	 */

	/*@Activity
	@RequestMapping(value = URLMapping.GET_ROLES, method = RequestMethod.GET)
	public ResponseEntity<Object> getRoles() {
		List<role> result = null;
		try {
			result = RoleRepository.findAll();
			
			if(result!=null){

				return ResponseHandler.generateResponse(HttpStatus.OK, true, "Success".toString(), result);
			}
			else
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "failer", result);		
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}*/
	@RequestMapping(value = URLMapping.GET_ROLES, method = RequestMethod.GET)
	public ResponseEntity<Object> getRoles() {
		Map<String, Object> result = null;
		try {
			result = roleService.getRoles();
			LOGGER.info("Get roles"+ result.get("isSuccess"));
			if(result.get("isSuccess").equals(true)){

				return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
			}
			else
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);		
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}

	/**
	 * @author anil
	 * @return list of active roles
	 */

//	@PreAuthorize("hasAuthority('getActiveRoles')")
	@RequestMapping(value = URLMapping.GET_ACTIVE_ROLES, method = RequestMethod.GET)
	public ResponseEntity<Object> getActiveRoles() {
			Map<String, Object> result = null;
			try {
				result = roleService.getActiveRoles();
				if (result.get("isSuccess").equals(true)) {
					return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
				} else
					return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);
			} catch (Exception e) {
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
			}
	}

	/**
	 * @author Anil
	 * @param role
	 * @return role object
	 */

	
	@RequestMapping(value = URLMapping.GET_ROLE_BY_NAME, method = RequestMethod.GET)
	public ResponseEntity<Object> getRoleByRoleName(@RequestParam("role") String role) {
		Map<String, Object> result = null;
		try {
			String roleName = role;
			if (roleName != null && roleName != "") {
				result = roleService.getRolesByRoleName(roleName);

				if (result.get("isSuccess").equals(true)) {
					return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
				}
				else
					return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);
			}else {
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "InputMismatchException", result);
			}

		}
		catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}

	/**
	 * @author Anil
	 * @param roleId
	 * @return role object
	 */

	//	@PreAuthorize("hasAuthority('GET_ROLE_BY_ID')")

	@RequestMapping(value = URLMapping.GET_ROLE_BY_ID, method = RequestMethod.GET)
	public ResponseEntity<Object> getRolebyRoleId(@RequestParam("roleId") String roleId) {

		Map<String, Object> result = null;
		try {
			Long id = Long.parseLong(roleId);
			if (id != null) {
				result = roleService.getRolesById(id);

				if (result.get("isSuccess").equals(true)) {
					return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
				}
				else
					return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);
			}else {
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "InputMismatchException", result);
			}

		}
		catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}

}
