package net.spvra.shoppingbackend.dao;

import java.util.List;

import net.spvra.shoppingbackend.dto.Category;

public interface CategoryDAO {

	
	
	Category get(int id); //single select
	List<Category> list(); //multi select
	boolean add(Category category); //insert
	boolean update(Category category); //update
	boolean delete(Category category); //delete
	
	
}
