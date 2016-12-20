package com.zdsoft.freedom.test.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.zdsoft.freedom.common.model.BaseEntity;

/**
 * 
 * 组织机构实体
 * 
 * @author gonght
 *
 */
@Entity
@Table(name = "t_organization")
public class Organization extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5553896140506688084L;

	/**
	 * 组织机构名称
	 */
	@Column(nullable = false, length = 64)
	private String orgNm;

	/**
	 * 部门员工
	 */
	@OneToMany(mappedBy = "org", fetch = FetchType.LAZY)
	private List<User> employees = new ArrayList<User>();

	/**
	 * 上级机构
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Organization superOrg;

	/**
	 * 下级机构
	 */
	@OneToMany(mappedBy = "superOrg", cascade = CascadeType.ALL)
	@OrderBy("dispOrder asc")
	@Where(clause = "isDeleted=0")
	private List<Organization> subOrgs = new ArrayList<Organization>();

	/**
	 * 同级下的顺序
	 */
	@Column(columnDefinition = "99") // 注解字段默认值为99
	@OrderBy("asc") // 默认为小到大排序
	private Integer dispOrder;

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public List<User> getEmployees() {
		return employees;
	}

	public void setEmployees(List<User> employees) {
		this.employees = employees;
	}

	public Organization getSuperOrg() {
		return superOrg;
	}

	public void setSuperOrg(Organization superOrg) {
		this.superOrg = superOrg;
	}

	public List<Organization> getSubOrgs() {
		return subOrgs;
	}

	public void setSubOrgs(List<Organization> subOrgs) {
		this.subOrgs = subOrgs;
	}

	public Integer getDispOrder() {
		return dispOrder;
	}

	public void setDispOrder(Integer dispOrder) {
		this.dispOrder = dispOrder;
	}
}