package com.demo.controller;


import com.demo.DTO.RoleActivityDTO;
import com.demo.domain.Role;
import com.demo.mapping.URLMapping;
import com.demo.service.ActivityService;

import com.demo.util.ResponseHandler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller

public class Activitycontroller {

    private static Logger LOGGER = LoggerFactory.getLogger(Activitycontroller.class);

    @Autowired
    ActivityService activityService;

    
    
    
    
    
    @RequestMapping(value = URLMapping.GET_ACTIVITY, method = RequestMethod.GET)
	public ResponseEntity<Object> getactivity() {
		Map<String, Object> result = null;
		try {
			result = activityService.getactivity();
			LOGGER.info("Get activity"+ result.get("isSuccess"));
			if(result.get("isSuccess").equals(true)){

				return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
			}
			else
				
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);		
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}

	@RequestMapping(value = URLMapping.ADD_ACTIVITY, method = RequestMethod.POST)
	public ResponseEntity<Object> addactivity(@RequestBody String activ) {
		Map<String, Object> result = null;
		try {
			result = activityService.addactivity(activ);
			if(result.get("isSuccess").equals(true)){
			return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
			}
			else
				return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);		
		} catch (Exception e) {
			return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
		}
	}
	@RequestMapping(value = URLMapping.ASSIGN_ACTIVITIES, method = RequestMethod.PUT)
    public ResponseEntity<Object> assignActivities(@RequestBody RoleActivityDTO dto) {
        Map<String , Object> result = null;
        try {
            result = activityService.assignActivities(dto);
            if(result.get("isSuccess").equals(true)){
                return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
            }
            else
                return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
        }
    }
   /* @RequestMapping(value = URLMapping.ADD_ACTIVITY, method = RequestMethod.POST)
    public ResponseEntity<Object> allActivities() {
        Map<String, Object> result = null;
        try {
            result = activityService.getActivities();
            if(result.get("isSuccess").equals(true)){
                return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
            }
            else
                return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
        }
    }
*/
    

   /* @RequestMapping(value = URLMapping.AUTHORIZED_ACTIVITIES, method = RequestMethod.GET)
    public ResponseEntity<Object> authorizedActivities(@RequestParam("roleId") String roleId) {
        Map<String, Object> result = null;
        try {
            result = activityService.authorizedActivities(roleId);
            if(result.get("isSuccess").equals(true)){
                return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
            }
            else
                return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
        }
    }


    @RequestMapping(value = URLMapping.ASSIGN_ACTIVITIES, method = RequestMethod.PUT)
    public ResponseEntity<Object> assignActivities(@RequestBody RoleActivityDTO dto) {
        Map<String, Object> result = null;
        try {
            result = activityService.assignActivities(dto);
            if(result.get("isSuccess").equals(true)){
                return ResponseHandler.generateResponse(HttpStatus.OK, true, result.get("message").toString(), result);
            }
            else
                return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, result.get("message").toString(), result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), result);
        }
    }
*/

}

