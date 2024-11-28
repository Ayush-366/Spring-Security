package com.cg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.model.Product;
import com.cg.model.UserInfo;
import com.cg.repository.ProductRepository;
import com.cg.repository.UserInfoRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	ProductRepository productrepository;
	
	@Autowired
    private UserInfoRepository repository;
 
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public List<Product> findAllProducts() {
		
		return productrepository.findAll();
		
		
//		ArrayList<Product> products = new ArrayList<Product>();
//		products.add(new Product(100, "Laptop", 9000.0));  
//		products.add(new Product(101, "Smart TV", 60000.00));  
//		products.add(new Product(102, "Mobile",  9000.00));  
//		return products;
		
		
	}
	
	 
	
	

	@Override
	public Optional<Product> findProductById(int id) {
		// TODO Auto-generated method stub
		return productrepository.findById(id);
	}



	@Override
	public Product createProduct(Product p) {
		// TODO Auto-generated method stub
		return productrepository.save(p);
	}



	@Override
	public String deleteProduct(int id) {
		// TODO Auto-generated method stub
		productrepository.deleteById(id);
		String i = Integer.toString(id);
		return i + " Product Has Been Deleted";
	}
	
	@Override
	public List<Product> getProductByName(String name)
	{
		return productrepository.findAllBypName(name);
	}
	
	@Override
	public String update( Product p, int id) {
	    if (productrepository.existsById(id)) {
	        Product product = new Product();
	        product.setId(id);
	        product.setpName(p.getpName());
	        product.setPrice(p.getPrice());
	        productrepository.save(product);
	        return "Product updated successfully";
	    } else {
	        return "Product ID not found";
	    }
	}
	
	@Override
	public int getCountpName(String pName)
	{
		return productrepository.findCountpName(pName);
	}
	
	    List<Product> productList = null;
	 
//	    @PostConstruct
//	    public void loadProductsFromDB() {
//	        productList = IntStream.rangeClosed(1, 100)
//	                .mapToObj(i -> Product.builder()
//	                        .productId(i)
//	                        .name("product " + i)
//	                        .qty(new Random().nextInt(10))
//	                        .price(new Random().nextInt(5000)).build()
//	                ).collect(Collectors.toList());
//	    }
	 
	 
	    public List<Product> getProducts() {
	        return productList;
	    }
	 
	    public Product getProduct(int id) {
	        return productList.stream()
	                .filter(product -> product.getId() == id)
	                .findAny()
	                .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
	    }
	 
	 
	    public String addUser(UserInfo userInfo) {
	        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
	        repository.save(userInfo);
	        return "user added to system ";
	    }
	
}
