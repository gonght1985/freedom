package com.zdsoft.freedom.test.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	@Column(name = "user_name", length = 64)
	private String userName;

	/**
	 * 所属机构
	 */
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	private Organization org;

	/**
	 * 用户登录账号，一个用户可以用多个登录账号
	 */
	@OneToMany(mappedBy = "user", orphanRemoval = true)
	@Cascade({ CascadeType.ALL })
	private Set<Account> accounts = new HashSet<Account>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * 判断账号是否已存在
	 * 
	 * @param accountNo
	 * @return
	 */
	public Boolean isOwner(String accountNo) {
		for (Account cco : this.getAccounts()) {
			if (cco.getAccountNo().equals(accountNo)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 添加用户对应的账号记录
	 * 
	 * @param accountNo
	 * @param accountPwd
	 */
	public void addAccount(String accountNo, String accountPwd) {
		if (!this.isOwner(accountNo)) {
			Account account = new Account();
			account.setUser(this);
			account.setAccountNo(accountNo);
			account.setAccountPwd(accountPwd);
			this.getAccounts().add(account);
		}
	}
}
