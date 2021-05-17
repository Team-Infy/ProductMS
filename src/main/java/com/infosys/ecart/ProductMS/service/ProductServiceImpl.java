package com.infosys.ecart.ProductMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infosys.ecart.ProductMS.dto.ProductDTO;
import com.infosys.ecart.ProductMS.dto.SubscribedProuctDTO;
import com.infosys.ecart.ProductMS.entity.Product;
import com.infosys.ecart.ProductMS.entity.SubscribedProduct;
import com.infosys.ecart.ProductMS.repository.ProductRepository;
import com.infosys.ecart.ProductMS.repository.SubscribedProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	SubscribedProductRepository subscribedProductRepository;
	
	public List<ProductDTO> viewProducts(){
		
		Iterable<Product> iterable = productRepository.findAll();
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		for(Product product : iterable) {
			ProductDTO p = new ProductDTO();
			
			p.setProdId(product.getProdId());
			p.setProductName(product.getProductName());
			p.setPrice(product.getPrice());
			p.setStock(product.getStock());
			p.setDescription(product.getDescription());
			p.setImage(product.getImage());
			p.setSellerId(product.getSellerId());
			p.setCategory(product.getCategory());
			p.setSubCategory(product.getSubCategory());
			p.setProductRating(product.getProductRating());
			
			list.add(p);
		}
		
		return list;
	}
	public List<ProductDTO> searchProductsByCategory(String category){
		
		Iterable<Product> iterable = productRepository.findByCategory(category);
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		for(Product product : iterable) {
			ProductDTO p = new ProductDTO();
			
			p.setProdId(product.getProdId());
			p.setProductName(product.getProductName());
			p.setPrice(product.getPrice());
			p.setStock(product.getStock());
			p.setDescription(product.getDescription());
			p.setImage(product.getImage());
			p.setSellerId(product.getSellerId());
			p.setCategory(product.getCategory());
			p.setSubCategory(product.getSubCategory());
			p.setProductRating(product.getProductRating());
			
			list.add(p);
		}
		
		return list;
	}

	public List<ProductDTO> searchProductsByProductName(String productName){
		
		Iterable<Product> iterable = productRepository.findByProductName(productName);
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		for(Product product : iterable) {
			ProductDTO p = new ProductDTO();
			
			p.setProdId(product.getProdId());
			p.setProductName(product.getProductName());
			p.setPrice(product.getPrice());
			p.setStock(product.getStock());
			p.setDescription(product.getDescription());
			p.setImage(product.getImage());
			p.setSellerId(product.getSellerId());
			p.setCategory(product.getCategory());
			p.setSubCategory(product.getSubCategory());
			p.setProductRating(product.getProductRating());
			
			list.add(p);
		}
		
		return list;
	}
	
	//service methods on subscribedProduct table
	public String addProductToSubscribedlist(SubscribedProuctDTO subscribedProductDTO) {

		SubscribedProduct s = new SubscribedProduct();
		s.setBuyerId(subscribedProductDTO.getBuyerId());
		s.setProdId(subscribedProductDTO.getProdId());
		s.setQuantity(subscribedProductDTO.getQuantity());
		
		SubscribedProduct sp = subscribedProductRepository.save(s);
		if(sp != null) {
			return "Product is successfully added to the Subscriptionlist.";
		}else {
				return "Something went wrong.";
			}
	}
	public String removeProductFromSubscribedlist(Integer prodId) {
		
		long count = subscribedProductRepository.count();
		
		SubscribedProduct s= subscribedProductRepository.findByProdId(prodId);
		subscribedProductRepository.delete(s);
		
		if(subscribedProductRepository.count() == count-1) {
			return "The product is successfully removed from Wishlist.";
		}
		return "Something went wrong.";
	}
	
	
	//methods for seller
	
	public List<ProductDTO> viewProductsBySellerId(Integer sellerId){
		
		Iterable<Product> iterable = productRepository.findBySellerId(sellerId);
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		for(Product product : iterable) {
			ProductDTO p = new ProductDTO();
			
			p.setProdId(product.getProdId());
			p.setProductName(product.getProductName());
			p.setPrice(product.getPrice());
			p.setStock(product.getStock());
			p.setDescription(product.getDescription());
			p.setImage(product.getImage());
			p.setSellerId(product.getSellerId());
			p.setCategory(product.getCategory());
			p.setSubCategory(product.getSubCategory());
			p.setProductRating(product.getProductRating());
			
			list.add(p);
		}
		
		return list;
	}
	
	public Integer addProduct(ProductDTO productDTO) {
		
		Product p = new Product();
		
		p.setProductName(productDTO.getProductName());
		p.setPrice(productDTO.getPrice());
		p.setStock(productDTO.getStock());
		p.setDescription(productDTO.getDescription());
		p.setImage(productDTO.getImage());
		p.setSellerId(productDTO.getSellerId());
		p.setCategory(productDTO.getCategory());
		p.setSubCategory(productDTO.getSubCategory());
		p.setProductRating(productDTO.getProductRating());
		
		productRepository.save(p);
		return p.getProdId();
	}
	
	public String removeProduct(Integer prodId) {
		
		long count = productRepository.count();
		
		Optional<Product> optional = productRepository.findById(prodId);
		Product p = optional.orElseThrow();
		productRepository.delete(p);
		if(productRepository.count() == count-1) {
			return "You made your product unavailable to the customers successfully.";
		}
		return "Something went wrong";
	}
	

	public ProductDTO viewProductByProdId(Integer prodId) {
		
		Optional<Product> optional = productRepository.findById(prodId);
		Product product = optional.orElseThrow();
		
		ProductDTO p = new ProductDTO();
		
		p.setProdId(product.getProdId());
		p.setProductName(product.getProductName());
		p.setPrice(product.getPrice());
		p.setStock(product.getStock());
		p.setDescription(product.getDescription());
		p.setImage(product.getImage());
		p.setSellerId(product.getSellerId());
		p.setCategory(product.getCategory());
		p.setSubCategory(product.getSubCategory());
		p.setProductRating(product.getProductRating());
		
		return p;
	}
	
	public String updateStockAfterOrder(ProductDTO productDTO,Integer quantity) {
		
		Optional<Product> optional = productRepository.findById(productDTO.getProdId());
		Product p = optional.orElseThrow();
		p.setStock(p.getStock()-quantity);
		productRepository.save(p);
		return p.getProdId()+" Updated Successfully";
	}

	public String addStockToProducts(ProductDTO productDTO,Integer quantity) {

		Optional<Product> optional = productRepository.findById(productDTO.getProdId());
		Product p = optional.orElseThrow();
		p.setStock(p.getStock()+quantity);
		productRepository.save(p);
		return p.getProdId()+" Updated Successfully";
	}

}
