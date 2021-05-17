package com.infosys.ecart.ProductMS.service;

import java.util.List;

import com.infosys.ecart.ProductMS.dto.ProductDTO;
import com.infosys.ecart.ProductMS.dto.SubscribedProuctDTO;

public interface ProductService {

	List<ProductDTO> viewProducts();
	List<ProductDTO> searchProductsByCategory(String category);
	List<ProductDTO> searchProductsByProductName(String productName);
	String addProductToWishlist(SubscribedProuctDTO subscribedProductDTO);
	String removeProductFromWishlist(String prodId);
	
	//methods handled by seller
	String addProduct(ProductDTO productDTO);
	String removeProduct(String prodId);
	ProductDTO viewProductByProdId(String prodId);
	List<ProductDTO> viewProductsBySellerId(String sellerId);
	String updateStockAfterOrder(ProductDTO productDTO,Integer quantity);
	String addStockToProducts(ProductDTO productDTO,Integer quantity);
}
