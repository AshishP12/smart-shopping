package net.spvra.smartshopping.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.spvra.shoppingbackend.dao.CategoryDAO;
import net.spvra.shoppingbackend.dao.ProductDAO;
import net.spvra.shoppingbackend.dto.Category;
import net.spvra.shoppingbackend.dto.Product;
import net.spvra.smartshopping.exception.ProductNotFoundException;

@Controller
@ComponentScan("net.spvra.shoppingbackend")
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	
	@Autowired(required=true)
	private CategoryDAO categoryDAO ;
	
	@Autowired
	private ProductDAO productDAO ;
	
	@RequestMapping(value = {"/" , "/home" , "/index"})
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		
		logger.info("Inside PageController index method -INFO");
		logger.debug("Inside PageController index method -DEBUG");
		
		//adding the list of categories here
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickHome", true);
		
		return mv;
	}

	
	@RequestMapping(value = "/about")
	public ModelAndView about()
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/contact")
	public ModelAndView contact()
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		
		return mv;
	}
	
	
	//method to load all the products
	@RequestMapping(value = {"/show/all/products"})
	public ModelAndView showAllProducts()
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		
		//adding the list of categories here
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickAllProducts", true);
		
		return mv;
	}
	
	//method to load the products based on the category
	@RequestMapping(value = {"/show/category/{id}/products"})
	public ModelAndView showCategoryProducts(@PathVariable("id") int id)
	{
		ModelAndView mv = new ModelAndView("page");
		
		//to fetch the title based on the category id, we are using categoryDAO object
		Category category = null;
		
		category = categoryDAO.get(id); //for this we have made a new method in CategoryDAO to fetch the id
		
		mv.addObject("title", category.getName()); //getting the name of the single category
		
		//adding the list of categories here
		mv.addObject("categories", categoryDAO.list());
		
		//passing the single category object
		mv.addObject("category", category); // on the right side it is single category which is mapped into "category object"
		
		mv.addObject("userClickCategoryProducts", true);
		
		return mv;
	}

	// VIEWING A SINGLE PRODUCT
	
	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {
		
		ModelAndView mv = new ModelAndView("page");
		
		Product product = productDAO.get(id);
		
		//if product not found
		if(product == null) throw new ProductNotFoundException();
		
		//update the view count
		product.setViews(product.getViews() + 1);
		productDAO.update(product);
		
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		
		mv.addObject("userClickShowProduct", true);
		
		return mv;
	}
	
	
	
	
	
	
	
	
}
