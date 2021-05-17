package com.infosys.ecart.ProductMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infosys.ecart.ProductMS.dto.ProductDTO;
import com.infosys.ecart.ProductMS.dto.SubscribedProuctDTO;
import com.infosys.ecart.ProductMS.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {
	
	@Autowired
	ProductService productService;
	
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
	
	//adding a product to wishlist
	@PostMapping("/wishlist/add/")
	public String addToWishlist(@RequestBody SubscribedProuctDTO subscribedProductDTO) {
		return productService.addProductToWishlist(subscribedProductDTO);
	}
	
	//removing a product from wishlist
	@DeleteMapping("/wishlist/remove/{prodId}")
	public String removeFromWishlist(@PathVariable String prodId) {
		return productService.removeProductFromWishlist(prodId);
	}
	
	
	//-------------------seller methods----------------------
	
	//fetches all products under his id
	@GetMapping("/seller/viewProducts/{sellerId}")
	public List<ProductDTO> viewSellerProducts(@PathVariable String sellerId) {
		return productService.viewProductsBySellerId(sellerId);
	}
	
	//fecthes a particular product
	@GetMapping("/seller/viewProduct/{prodId}")
	public ProductDTO viewProductsByProdId(@PathVariable String prodId){
		return productService.viewProductByProdId(prodId);
	}
	
	//adding a new product by seller
	@PostMapping("/seller/addProduct")
	public String addProduct(@RequestBody ProductDTO productDTO) {
		return "Your product is added with product ID : "+productService.addProduct(productDTO);
	}
	
	//removing an existing product by seller
	@DeleteMapping("/seller/removeProduct/{prodId}")
	public String removeProduct(@PathVariable String prodId) {
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
