package com.zdsoft.freedom.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zdsoft.freedom.common.model.BaseEntity;

/**
 * 
 * 用户登录账号
 * 
 * @author gonght
 *
 */
@Entity
@Table(name = "t_account")
public class Account extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9056302805828851861L;

	@Column(name = "account_no", length = 32)
	private String accountNo;

	@Column(name = "account_password", length = 64)
	private String accountPwd;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id_")
	private User user;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountPwd() {
		return accountPwd;
	}

	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}