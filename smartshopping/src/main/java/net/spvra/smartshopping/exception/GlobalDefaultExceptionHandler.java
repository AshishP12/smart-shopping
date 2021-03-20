package net.spvra.smartshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException() {
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Required page not found!" );
		mv.addObject("errorDescription", "The page you are looking for is not available now!");
		mv.addObject("title", "404 Error Page");
		
		return mv;
		
	}
	
	//adding custom exception
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException() {
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Product is not available. Please take a look at what we have to offer!" );
		mv.addObject("errorDescription", "The product you are looking for is not available!");
		mv.addObject("title", "Product Unavailable");
		
		return mv;
		
	}
	
	//handling generalized exception
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex) {
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Contact your adminstrator" );
		
		//this part only for debugging purpose- printing the whole stack
		//StringWriter sw = new StringWriter();
		//PrintWriter pw = new PrintWriter(sw);
		
		//ex.printStackTrace(pw);
		
		
		mv.addObject("errorDescription", ex.toString() );
		//mv.addObject("errorDescription", sw.toString() );
		mv.addObject("title", "Error");
		
		return mv;
		
	}
}
