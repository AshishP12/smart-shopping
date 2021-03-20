package net.spvra.smartshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.spvra.shoppingbackend.dao.CategoryDAO;
import net.spvra.shoppingbackend.dto.Category;
import net.spvra.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	
	@RequestMapping(value= "/product")
	public ModelAndView showManageProducts() {
		
		

		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Product Management");		
		mv.addObject("userClickManageProduct",true);
		
		Product nProduct = new Product();
		nProduct.setSupplierId(1); //1 is for admin 
		nProduct.setActive(true); //assuming the admin entered the product is active
		
		mv.addObject("product", nProduct); //adding the new product here
		
		
		
		return mv;
	}

	
	
	/*
	 * @ModelAttribute("categories") public List<Category> getCategories() {
	 * 
	 * }
	 */
}
