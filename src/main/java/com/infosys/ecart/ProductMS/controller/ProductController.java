package com.infosys.ecart.ProductMS.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infosys.ecart.ProductMS.dto.ProductDTO;
import com.infosys.ecart.ProductMS.dto.SubscribedProuctDTO;
import com.infosys.ecart.ProductMS.service.ProductService;

@RestController
@RequestMapping
@CrossOrigin
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	Environment environment;
	
	//fetches all products 
	@GetMapping("/viewProducts")
	public List<ProductDTO> viewAllProducts() {
		return productService.viewProducts();
	}
	
	//fetches products based on their category
	@GetMapping("/viewProducts/category/{category}")
	public List<ProductDTO> viewProductsByCategory(@PathVariable String category){
		return productService.searchProductsByCategory(category);
	}
	
	//fetches products based on their productName
	@GetMapping("/viewProducts/productname/{productName}")
	public List<ProductDTO> viewProductsByProductName(@PathVariable String productName){
		return productService.searchProductsByProductName(productName);
	}
	
	
	//adding a product to subscription list
	@PostMapping("/subscription/add/")
	public String addToSubscriptionlist(@RequestBody SubscribedProuctDTO subscribedProductDTO) {
		//this method uses the endpoints of Order to place order
		return productService.addProductToSubscribedlist(subscribedProductDTO);
	}
	
	//removing a product from subscription list
	@DeleteMapping("/subscription/remove/{prodId}")
	public String removeFromSubscriptionlist(@PathVariable Integer prodId) {
		return productService.removeProductFromSubscribedlist(prodId);
	}
	
	
	
	//-------------------seller methods----------------------
	
	//fetches all products under his id
	@GetMapping("/seller/viewProducts/{sellerId}")
	public List<ProductDTO> viewSellerProducts(@PathVariable Integer sellerId) {
		return productService.viewProductsBySellerId(sellerId);
	}
	
	//fecthes a particular product
	@GetMapping("/seller/viewProduct/{prodId}")
	public ProductDTO viewProductByProdId(@PathVariable Integer prodId){
		return productService.viewProductByProdId(prodId);
	}
	
	//adding a new product by seller
	@PostMapping("/seller/addProduct")
	public ResponseEntity<String> addProduct(@Valid @RequestBody ProductDTO productDTO) throws Exception{
		
		try {
			String msg = "Your product is added with product ID : "+productService.addProduct(productDTO);
			return new ResponseEntity<>(msg,HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()));
		}
	}
	
	//removing an existing product by seller
	@DeleteMapping("/seller/removeProduct/{prodId}")
	public String removeProduct(@PathVariable Integer prodId) {
		return productService.removeProduct(prodId);
	}
	
	//updating the stock after accepting a order
	@PutMapping("/seller/updateStock/{quantity}")
	public String updateStockAFterOrder(@RequestBody ProductDTO productDTO,@PathVariable Integer quantity) {
		return productService.updateStockAfterOrder(productDTO,quantity);
	}
	
	//adding more stocks to an existing product
	@PutMapping("/seller/addStock/{quantity}")
	public String addStockToProduct(@RequestBody ProductDTO productDTO,@PathVariable Integer quantity) {
		return productService.addStockToProducts(productDTO, quantity);
	}
	
}
