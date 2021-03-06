package cn.imethan.security.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.imethan.common.hibernate.BaseEntity;

/**
 * Permission.java
 *
 * @author Ethan Wong
 * @time 2014年3月16日下午4:33:10
 */
@Entity
@Table(name="imethan_security_permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "securityCache")
@JsonIgnoreProperties(value={"menu"})
public class Permission extends BaseEntity {
	
	private static final long serialVersionUID = 3002097053120526602L;
	
	private String name;//权限名称
	private String url;//授权URL
	private String intro;//描述
	private String permission;//备用字段，shiro框架使用的授权，格式：“资源名称:操作”，形如：“channel:new”
	@Transient
	private Long menuId;
	
	public Permission(){}
	
	public Permission(Menu menu,String name,String url){
		this.menu = menu;
		this.name = name;
		this.url = url;
	}
	
	public Long getMenuId() {
		return this.getMenu().getId();
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
	@JoinColumn(name="menuId")
	private Menu menu = new Menu();//资源
	
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}

}