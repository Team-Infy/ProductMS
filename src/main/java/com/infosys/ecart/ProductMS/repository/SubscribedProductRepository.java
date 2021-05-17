package com.infosys.ecart.ProductMS.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infosys.ecart.ProductMS.entity.CompositePK;
import com.infosys.ecart.ProductMS.entity.SubscribedProduct;

public interface SubscribedProductRepository extends CrudRepository<SubscribedProduct,CompositePK>{
	
	@Query("select S from SubscribedProduct S where S.prodId = :prodId")
	public SubscribedProduct findByProdId(String prodId);
}
