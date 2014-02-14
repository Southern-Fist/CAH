package com.onegirloneguy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "Role" )
public class Role {

	private Integer roleId;
	private String rolename;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@GenericGenerator(name="increment", strategy = "increment")
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(final Integer roleId) {
		this.roleId = roleId;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(final String rolename) {
		this.rolename = rolename;
	}
}
