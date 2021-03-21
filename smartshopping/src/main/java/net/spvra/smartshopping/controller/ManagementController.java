package net.spvra.smartshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.spvra.shoppingbackend.dao.CategoryDAO;
import net.spvra.shoppingbackend.dao.ProductDAO;
import net.spvra.shoppingbackend.dto.Category;
import net.spvra.shoppingbackend.dto.Product;
import net.spvra.smartshopping.util.FileUploadUtility;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	
	@RequestMapping(value= "/product", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false) String operation) {
		
		

		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Product Management");		
		mv.addObject("userClickManageProduct",true);
		
		Product nProduct = new Product();
		nProduct.setSupplierId(1); //1 is for admin 
		nProduct.setActive(true); //assuming the admin entered the product is active
		
		mv.addObject("product", nProduct); //adding the new product here
		
		if(operation!=null) {
			
			
			if(operation.equals("product")) {
				mv.addObject("message", "Product Submitted Succesfully !");
				
			}
		}
		
		
		
		
		return mv;
	}

	// HANDLING PRODUCT SUBMISSION
	@RequestMapping(value= "/product", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct ,BindingResult results, Model model , HttpServletRequest request ) {
		
		
		if(results.hasErrors()) {
		
			model.addAttribute("userClickManageProduct",true);
		//	model.addAttribute("title", "Manage product!");
			model.addAttribute("message", "Validation fails for adding the product!");
			return "page";
		}	
		
		
		
		logger.info(mProduct.toString());
		
		
	  // CREATE A NEW PRODUCT
		productDAO.add(mProduct);
		
		if(!mProduct.getFile().getOriginalFilename().equals("") ) {
			
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		
		
		
		
		return "redirect:/manage/product?operation=product";
	}
	
	
	
	
	
	
	// returning categories of all request maping
	  @ModelAttribute("categories") public List<Category> getCategories() {
	  
		  return categoryDAO.list();
	  }
	 
}
