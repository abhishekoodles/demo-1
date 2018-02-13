package com.demo.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String role;
	private Boolean isActive = true;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<activity> activities;

	public List<activity> getActivities() {
		return activities;
	}

	public void setActivities(List<activity> activities) {
		this.activities = activities;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}
	
}
