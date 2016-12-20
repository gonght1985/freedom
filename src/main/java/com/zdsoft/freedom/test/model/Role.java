package com.zdsoft.freedom.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.freedom.common.model.BaseEntity;

/**
 * 
 * 角色实体
 * 
 * @author gonght
 *
 */
@Entity
@Table(name = "t_role")
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5646691579509474217L;

	@Column(name = "role_name", length = 64)
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
