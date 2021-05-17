package com.infosys.ecart.ProductMS.entity;

import java.io.Serializable;

public class CompositeIDclass implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer buyerId;
	private Integer prodId;
	
	public CompositeIDclass() {
	}
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}	
}
