package com.infosys.ecart.ProductMS.service;

import java.util.List;

import com.infosys.ecart.ProductMS.dto.ProductDTO;
import com.infosys.ecart.ProductMS.dto.SubscribedProuctDTO;

public interface ProductService {

	List<ProductDTO> viewProducts();
	List<ProductDTO> searchProductsByCategory(String category);
	List<ProductDTO> searchProductsByProductName(String productName);
	String addProductToSubscribedlist(SubscribedProuctDTO subscribedProductDTO);
	String removeProductFromSubscribedlist(Integer prodId);
	
	//methods handled by seller
	Integer addProduct(ProductDTO productDTO);
	String removeProduct(Integer prodId);
	ProductDTO viewProductByProdId(Integer prodId);
	List<ProductDTO> viewProductsBySellerId(Integer sellerId);
	String updateStockAfterOrder(ProductDTO productDTO,Integer quantity);
	String addStockToProducts(ProductDTO productDTO,Integer quantity);
}
