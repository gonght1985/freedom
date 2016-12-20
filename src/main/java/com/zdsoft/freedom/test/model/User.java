package com.zdsoft.freedom.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.zdsoft.freedom.common.model.BaseEntity;

/**
 * 
 * 测试用户实体
 * 
 * @author gonght
 *
 */
@Entity
@Table(name = "t_user")
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8454126254703425038L;

	/**
	 * 用户名称
	 */
	@Column(name="user_name",length=64)
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
