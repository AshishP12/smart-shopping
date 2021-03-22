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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.spvra.shoppingbackend.dao.CategoryDAO;
import net.spvra.shoppingbackend.dao.ProductDAO;
import net.spvra.shoppingbackend.dto.Category;
import net.spvra.shoppingbackend.dto.Product;
import net.spvra.smartshopping.util.FileUploadUtility;
import net.spvra.smartshopping.validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	private static final Logger logger =LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value= "/product")
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false) String operation) {
		
		

		ModelAndView mv = new ModelAndView("page");	
		mv.addObject("title","Product Management");		
		mv.addObject("userClickManageProduct",true);
		
		Product nProduct = new Product();//new product
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
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult results,
			Model model ,HttpServletRequest request) {
		
		//hangle image validation for new products
		if(mProduct.getId()==0) {
	new	ProductValidator().validate(mProduct,results);
		}
		else {
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new	ProductValidator().validate(mProduct,results); //validate the product
			}
		}
		
		
		//check if there are any errors
		if(results.hasErrors()) {
			
			model.addAttribute("userClickManageProduct",true);
			model.addAttribute("title", "Product Management");
			model.addAttribute("message", "Validation failed for product submission");
			return "page";//return page element
		}
		
		
		logger.info(mProduct.toString());
	  
		if(mProduct.getId()==0) {
			// CREATE A NEW PRODUCT if id is 0
		productDAO.add(mProduct);
		}
		else {
			//update the product if id is not 0
			productDAO.update(mProduct);
		}
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode()); //get the real path
		}
		
		return "redirect:/manage/product?operation=product";//
	}
	
	
	@RequestMapping(value= "/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		//get the product by its id from database
		Product product=productDAO.get(id);
		boolean isActive=product.isActive();
		//activating and deactivating based on the value of active field
		product.setActive(!product.isActive()); //active h toh inactive hojayega
		//updating the product
		
		productDAO.update(product);
		return (isActive)
				? "You have successfully deactivate the product with id " + product.getId()
				:"You have successfully activate the product with id " + product.getId();
	}
	
	
	@RequestMapping(value= "/{id}/product", method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		
	ModelAndView mv = new ModelAndView("page");	
	mv.addObject("title","Product Management");		
	mv.addObject("userClickManageProduct",true);
	//fetch the product from the database
	Product nProduct = productDAO.get(id);
	//set the product that we fetched from database
	mv.addObject("product", nProduct); //adding the new product here
	
	
	return mv;
	}
	// returning list of categories for all the request mapping
	  @ModelAttribute("categories") public List<Category> getCategories() {
	  
		  return categoryDAO.list();
	  }
	 
	  @ModelAttribute("category")
	  public Category getCategory() {
		  return new Category();
	  }
	  
	  
}  
