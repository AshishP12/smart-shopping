package net.spvra.shoppingbackend.dao;

import java.util.List;

import net.spvra.shoppingbackend.dto.Category;

public interface CategoryDAO {
	List<Category> list();
	Category get(int id);
}
