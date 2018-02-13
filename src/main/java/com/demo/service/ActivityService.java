package com.demo.service;

import com.demo.domain.activity;
import com.demo.DTO.RoleActivityDTO;
import com.demo.configuration.Envconfig;
import com.demo.constants.Message;
import com.demo.domain.Role;
import com.demo.repositry.ActivityRepositry;
import com.demo.domain.activity;
import com.demo.repositry.RoleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;


@Service
public class ActivityService {

    private static Logger LOGGER = LoggerFactory.getLogger(ActivityService.class);


   /* @Autowired*/
    /*private ActivityRepositry activityRepository;*/
    @Autowired
	RoleRepository roleRepository;

    /*@Autowired
    private Envconfig configuration;

    @Autowiredhttps://www.youtube.com/watch?v=kR5qRGrWYMk
    private RoleService roleService;
*/
    @Autowired
    private MessageService messageService;

    @Autowired
    private ActivityRepositry ActivityRepository;

//    @Autowired
//    private RoleActivityService roleActivityService;

   /* public ActivityService(){}*/
    
    
    public Map<String, Object> getactivity() {

		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> allactivity = new ArrayList<Map<String, Object>>();

		Boolean isSuccess = false;
		try{
			List<activity> ro =  ActivityRepository.findAll();
			for (activity r : ro){
				List<Map<String, Object>> allActivities = new ArrayList<Map<String, Object>>();
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("activity id", r.getActivityId());
				data.put("activity name", r.getActivityName());
				/*List<Role> rol =  r.getRoleList();
				for(Role rol1 : rol){
					Map<String, Object> data1 = new HashMap<String, Object>();
					data1.put("role Id", rol1.getId());
					data1.put("role Name", rol1.getRole());
					allActivities.add(data1);
				}*/
				LOGGER.info("activity list fetched successfully");
				data.put("activities", allActivities);
				allactivity.add(data);
			}
			isSuccess = true;
			result.put("ActivityList", allactivity);
			result.put("message", "success");
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			result.put("message", "internal error");
		}
		result.put("isSuccess", isSuccess);
		return result;
	}
    
    
    public Map<String, Object> addactivity(String activityName) {

		Map<String, Object> res = new HashMap<String, Object>();
		Boolean isSuccess = false;
		try{
		if (activityName != null || activityName != ""){
			activity activity=  ActivityRepository.findByActivityName(activityName);
			if(activity == null){
				activity a = new activity();
				a.setActivityName(activityName);
				ActivityRepository.save(a);
				isSuccess = true;
				LOGGER.info("Activity saved successfully {}", activityName);
				res.put("message", "success");
			}else {
				res.put("message", "already exist");
			}
		}
		} catch(Exception e){
			LOGGER.warn(e.getMessage());
			res.put("message","internal server error");
		}
		res.put("isSuccess", isSuccess);
		return res;
	}
    
    
    //---------------------------assign activities----------------------------------------------
                public Map<String, Object> assignActivities(RoleActivityDTO dto) {

        Map<String, Object> result = new HashMap<String, Object>();
        List<activity> activities = new ArrayList<>();
        Boolean isSuccess = false;
        Long roleId = dto.getRoleId();
        Long[] dtoActivities = dto.getActivities();
        if (roleId != null && dtoActivities != null){
            Role role = roleRepository.findById(roleId);

            try{
                for(Long activityId : dtoActivities)
                {
                    activity activity = ActivityRepository.findByActivityId(activityId);

                    if(activity == null){
                        LOGGER.info("No such activity found");
                        result.put("error",activityId);
                        result.put("message", messageService.getMessage(Message.ERROR));
                        result.put("isSuccess", isSuccess);
                        return result;
                    }
                    activities.add(activity);
                }
                role.setActivities(activities);
                roleRepository.save(role);
                LOGGER.info("--------------------role-----------------------"+role.toString());
                LOGGER.info("activities assigned successfully");
                isSuccess = true;
                result.put("message", messageService.getMessage(Message.SUCCESS));
            } catch(Exception e){
                LOGGER.warn(e.getMessage());
                result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
            }
        }else{
            LOGGER.info("Invalid data");
            result.put("message", messageService.getMessage(Message.INVALID_INPUTS));
        }
        result.put("isSuccess", isSuccess);
        return result;
    }
    /**
     *  This method is called after bean
     *  properties initialization to insert
     *  activities entries in database
     */

//    @PostConstruct
//    public void init() {
//        LOGGER.info("initilizing activities*********************");
//        intializeActivities(configuration.getControllerPackages());
//    }

//    public void intializeActivities(String[] packageNames){
//        List<activity> activities = AccountsUtilities.getAllActivityObject(packageNames);
//        LOGGER.info((activities != null ? activities.size() : activities)+" activites read from  @Activty");
//        List<activity> dbActivityArray = getAllActivities();
//        //TO DO null pointer
//        LOGGER.info((dbActivityArray != null  ? dbActivityArray.size() : dbActivityArray)+" activites read from  Database");
//
//        if(dbActivityArray == null)
//            return;
//
//        List<activity> uniqueActivtyArray =  AccountsUtilities.getListA_Minus_ListBObjects(activities, dbActivityArray, "handlerMethodName");
//        LOGGER.info(uniqueActivtyArray.size()+" new activites are created for insert into database.");
//        for(activity activity : uniqueActivtyArray){
//            persistActivity(activity);
//        }
//        LOGGER.info("activites inserted into database Sucessfullly");
//    }

   /* @Transactional
    public activity persistActivity(activity activity){
        try{
            activity =  activityRepository.save(activity);
        }catch(Exception e){
            LOGGER.warn("Activity not Saved "+e);
            return null;
        }
        return activity;
    }

    

    public activity findByActivityName(String activityName){
        return this.activityRepository.findByActivityName(activityName);
    }

    public activity findByActivityId(Long activityId){
        return this.activityRepository.findByActivityId(activityId);
    }

//    @Transactional
//    public boolean deleteActivityByActivityId(Long activityId){
//        boolean isSucces = true;
//        try{
//            activityRepository.deleteActivityByActivityId(activityId);
//        }catch(Exception e){
//            isSucces = false;
//            log.error("Activity not Deleted "+e.getMessage());
//        }
//        return isSucces;
//    }

    public List<activity> getAllActivities(){
        LOGGER.info("returing all activities list");
        try{
            return activityRepository.findAll();
        }catch(Exception e){
            LOGGER.warn("Exception occure while fetch all activities from database : "+e);
            return null;
        }
    }

    public List<activity> findAll(Role role){
        List<activity> activityList = new ArrayList<activity>();
        if(role.getRole() == "SUPER_ADMIN")
            activityList=new ArrayList<activity>(this.activityRepository.findAll());
        else{
            activityList = activityRepository.findByRoleList(role);
        }
        return activityList;
    }

//    public List<Activity> findAll(Role role){
//        List<com.accounts.domain.Activity> activityList = new ArrayList<com.accounts.domain.Activity>();
//        if(KYCRoleUtil.level(role.getRoleName())==1)
//            activityList=new ArrayList<com.oodles.domain.role.Activity>(this.activityRepository.findAll());
//        else{
//            for(RoleActivity roleActivity : role.getActivities())
//            {
//                activityList.add(roleActivity.getActivity());
//            }
//        }
//        return activityList;
//    }

    public List<activity> findAll(){
        return activityRepository.findAll();
    }

	public Map<String,Object> assginActivitybySuperUser(Long ids[]){
		   long count=0;
		   Set<activity> activities = new LinkedHashSet<>();
			Map<String,Object> map=null;
		   for(Long activityId : ids){
			 activity activity=activityRepository.findByActivityId(activityId);
			 if(activity==null){
					map=new HashMap<String,Object>();
					map.put("error",activityId);
				 return map;
			 }
			 activities.add(activity);
			 count++;
			}
			map=new HashMap<String,Object>();
			map.put("count",count);
			map.put("activities",activities);
			return map;
	}

//	public Map<String,Object> assignActivty(Long ids[],Long roleId){
//		long count=0;
//		Set<Activity> activities = new LinkedHashSet<>();
//		Map<String,Object> map=null;
//		for(Long activityId : ids){
//		//	List<Object[]> role_Activites=roleService.getRoleActivites(roleid,activityId);
//           Activity activityOfRole = roleActivityService.getActivityByRole(roleId, activityId);
//			if(activityOfRole == null){
//      			map=new HashMap<String,Object>();
//				map.put("error",activityId);
//			 return map;
//              }
//		//	com.oodles.domain.role.Activity activity = findByActivityId(activityId);
//			count++;
//			activities.add(activityOfRole);
//		}
//		map=new HashMap<String,Object>();
//		map.put("count",count);
//		map.put("activities",activities);
//		return map;
//	}





    
     new changes
     

    public Map<String, Object> getActivities() {

        Map<String, Object> result = new HashMap<String, Object>();
        Boolean isSuccess = false;
        try{
            List<activity> activities =  activityRepository.findAll();
            LOGGER.info("List of activities fetched successfully");
            isSuccess = true;
            result.put("data", activities);
            result.put("message", messageService.getMessage(Message.SUCCESS));
        } catch(Exception e){
            LOGGER.warn(e.getMessage());
            result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
        }
        result.put("isSuccess", isSuccess);
        return result;
    }

    public Map<String, Object> authorizedActivities(String roleId) {

        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> allActivities = new ArrayList<Map<String, Object>>();
        Boolean isSuccess = false;
        Long id = Long.parseLong(roleId);
        if (roleId != null){
            Role role = roleRepository.findById(id);
            if(role != null) {
                try {
                    List<activity> activities = activityRepository.findByRoleList(role);
                    for (activity activity : activities) {
                        Map<String, Object> data = new HashMap<String, Object>();
                        data.put("activityId", activity.getActivityId());
                        data.put("activityName", activity.getActivityName());
                        allActivities.add(data);
                    }
                    LOGGER.info("List of authorized activities fetched successfully");
                    isSuccess = true;
                    result.put("roleId",roleId);
                    result.put("roleName", role.getRole());
                    result.put("activities", allActivities);
                    result.put("message", messageService.getMessage(Message.SUCCESS));
                } catch (Exception e) {
                    LOGGER.warn(e.getMessage());
                    result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
                }
            }else{
                LOGGER.info("Invalid data");
                result.put("message", messageService.getMessage(Message.INVALID_INPUTS));
            }
        }else{
            LOGGER.info("Invalid data");
            result.put("message", messageService.getMessage(Message.INVALID_INPUTS));
        }
        result.put("isSuccess", isSuccess);
        return result;
    }

    public Map<String, Object> assignActivities(RoleActivityDTO dto) {

        Map<String, Object> result = new HashMap<String, Object>();
        List<activity> activities = new ArrayList<>();
        Boolean isSuccess = false;
        Long roleId = dto.getRoleId();
        Long [] dtoActivities = dto.getActivities();
        if (roleId != null && dtoActivities != null){
            Role role = roleRepository.findById(roleId);

            try{
                for(Long activityId : dtoActivities){
//                    Activity activity =  activityRepository.findByRoleListAndActivityId(role, acId);
                    activity activity = activityRepository.findByActivityId(activityId);

                    if(activity == null){
                        LOGGER.info("No such activity found");
                        result.put("error",activityId);
                        result.put("message", messageService.getMessage(Message.ERROR));
                        result.put("isSuccess", isSuccess);
                        return result;
                    }
                    activities.add(activity);
                }
                role.setActivities(activities);
                roleRepository.save(role);
                LOGGER.info("activities assigned successfully");
                isSuccess = true;
                result.put("message", messageService.getMessage(Message.SUCCESS));
            } catch(Exception e){
                LOGGER.warn(e.getMessage());
                result.put("message", messageService.getMessage(Message.INTERNAL_SERVER_ERROR));
            }
        }else{
            LOGGER.info("Invalid data");
            result.put("message", messageService.getMessage(Message.INVALID_INPUTS));
        }
        result.put("isSuccess", isSuccess);
        return result;
    }

//    	public Map<String,Object> assignActivty(Long ids[],Long roleId){
//		long count=0;
//		Set<Activity> activities = new LinkedHashSet<>();
//		Map<String,Object> map=null;
//		for(Long activityId : ids){
//		//	List<Object[]> role_Activites=roleService.getRoleActivites(roleid,activityId);
//           Activity activityOfRole = roleActivityService.getActivityByRole(roleId, activityId);
//			if(activityOfRole == null){
//      			map=new HashMap<String,Object>();
//				map.put("error",activityId);
//			 return map;
//              }
//		//	com.oodles.domain.role.Activity activity = findByActivityId(activityId);
//			count++;
//			activities.add(activityOfRole);
//		}
//		map=new HashMap<String,Object>();
//		map.put("count",count);
//		map.put("activities",activities);
//		return map;
//	}
*/}

