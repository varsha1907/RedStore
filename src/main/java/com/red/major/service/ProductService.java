package com.red.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.red.major.model.Category;
import com.red.major.model.Product;
import com.red.major.repository.CategoryRepository;
import com.red.major.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public void addProduct(Product product){
		productRepository.save(product);
	}
	
	public void removeProductById(Long id)
	{
		productRepository.deleteById(id);
	}
	
	public Optional<Product> getProductById(long id)
	{
		return productRepository.findById(id);		
	}
	
	public List<Product> getAllProductsByCategoryId(int id)
	{
		return productRepository.findAllByCategory_Id(id);
	}
}
