package com.demo.mapping;


public class URLMapping {

	public static final String ACCOUNTS_API_V1 = "/v1";

	public static final String REGISTER = ACCOUNTS_API_V1 + "/register";

	// Roles
	public static final String ADD_ROLE = ACCOUNTS_API_V1 + "/add_role";
	public static final String ACTIVATE_OR_DEACTIVATE_ROLE = ACCOUNTS_API_V1 + "/update_role";
	public static final String GET_ROLES = ACCOUNTS_API_V1 + "/roles";
	public static final String GET_ACTIVE_ROLES = ACCOUNTS_API_V1 + "/active_roles";
	public static final String GET_ROLE_BY_ID = ACCOUNTS_API_V1 + "/role/id";
	public static final String GET_ROLE_BY_NAME = ACCOUNTS_API_V1 + "/role/name";

	// Activities
		public static final String ADD_ACTIVITY = ACCOUNTS_API_V1 + "/add_activity";
		public static final String GET_ACTIVITY = ACCOUNTS_API_V1 + "/get_activity";
		public static final String ASSIGN_ACTIVITIES = ACCOUNTS_API_V1 + "/assign_activities";
}
