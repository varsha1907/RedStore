package com.red.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.red.major.global.GlobalData;
import com.red.major.service.ProductService;

@Controller
public class CartController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) {
		
		GlobalData.cart.add(productService.getProductById(id).get());
		
		return "redirect:/cart";
	}
	
	/*
	@GetMapping("/cart")
	public String cartGet(Model model)
	{
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
	
		model.addAttribute("cart", GlobalData.cart);
		
		return "cart";
	}*/

	
	
	
	
}
