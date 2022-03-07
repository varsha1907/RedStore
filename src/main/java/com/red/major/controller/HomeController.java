package com.red.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;


import com.red.major.global.GlobalData;
import com.red.major.model.Product;
import com.red.major.model.User;
import com.red.major.repository.UserRepository;
import com.red.major.service.CategoryService;
import com.red.major.service.ProductService;

@Controller
public class HomeController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserRepository urepo;
	
	@GetMapping({"/", "/home"})
	public String home(Model model)
	{
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "index";
	}
	
	@GetMapping("/shop")
	public String shop(Model model)
	{
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("products",productService.getAllProduct());
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "shop";
	}
	
	/*
	 * @RequestMapping("/signup") public String getSignup() { return "register"; }
	 */
	
	@GetMapping("/login")
	public String getLogin()
	{
		return "login";
	}
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model,@PathVariable int id)
	{
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("products",productService.getAllProductsByCategoryId(id));
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model,@PathVariable int id)
	{
		model.addAttribute("product",productService.getProductById(id).get());
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "viewProduct";
	}
	
	@GetMapping("/cart")
	public String cartGet(Model model)
	{
		System.out.println("This is cart");
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
	
		model.addAttribute("cart", GlobalData.cart);
		
		return "cart";
	}
	
	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index) {
		
		GlobalData.cart.remove(index);
		
		return "redirect:/cart";
		
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		return "checkout";
	}
	
	@PostMapping("/login")
	public String login_user(@RequestParam("username") String username,@RequestParam("password") String password,
			HttpSession session,ModelMap modelMap)
	{
		
	User auser=urepo.findByUsernamePassword(username, password);
	
	if(auser!=null)
	{
		String uname=auser.getUser_email();
		String upass=auser.getUser_pass();
	
		if(username.equalsIgnoreCase(uname) && password.equalsIgnoreCase(upass)) 
		{
			session.setAttribute("username",username);
			return "shop";
		}
		else 
		{
			modelMap.put("error", "Invalid Account");
			return "login";
		}
	}
	else
	{
		modelMap.put("error", "Invalid Account");
		return "login";
	}
	
	}
	
	  @PostMapping("/addUser") public ModelAndView
	  addUser(@RequestParam("user_email") String user_email, User user) {
	  ModelAndView mv=new ModelAndView("adminHome"); 
	  List<User> list=urepo.findByEMAIL(user_email);
	  
	  if(list.size()!=0) { mv.addObject("message",
	  "Oops!  There is already a user registered with the email provided.");
	  
	  } else { urepo.save(user); mv.addObject("message","Logged In"); }
	  
	  return mv; }
	 
	/*
	 * @PostMapping("/login") public String login_User() {
	 * 
	 * return "shop"; }
	 */
	  
	  @GetMapping("/logout")
	  public String get_logout()
	  {
		  return "index";
	  }
	
	 
	
}
