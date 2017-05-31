package com.zcy.shop.model;

/**
 * AbstractCategory entity provides the base persistence definition of the
 * Category entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCategory implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private Boolean hot;

	// Constructors

	/** default constructor */
	public AbstractCategory() {
	}

	/** full constructor */
	public AbstractCategory(String type, Boolean hot) {
		this.type = type;
		this.hot = hot;
	}
	
	public AbstractCategory(Integer id, String type, Boolean hot) {
		this.id = id;
		this.type = type;
		this.hot = hot;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getHot() {
		return this.hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}

}