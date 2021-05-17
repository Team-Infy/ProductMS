package com.infosys.ecart.ProductMS.service;

import java.util.List;

import com.infosys.ecart.ProductMS.dto.ProductDTO;
import com.infosys.ecart.ProductMS.dto.SubscribedProuctDTO;

public interface ProductService {

	//common methods for normal buyers and visitors
	List<ProductDTO> viewProducts();
	List<ProductDTO> searchProductsByCategory(String category);
	List<ProductDTO> searchProductsByProductName(String productName);
	
	//extra methods for privileged buyers
	String addProductToSubscribedlist(SubscribedProuctDTO subscribedProductDTO);
	String removeProductFromSubscribedlist(Integer prodId);
	
	//methods handled by seller
	Integer addProduct(ProductDTO productDTO) throws Exception;
	String removeProduct(Integer prodId);
	ProductDTO viewProductByProdId(Integer prodId);
	List<ProductDTO> viewProductsBySellerId(Integer sellerId);
	String updateStockAfterOrder(ProductDTO productDTO,Integer quantity);
	String addStockToProducts(ProductDTO productDTO,Integer quantity);
}
