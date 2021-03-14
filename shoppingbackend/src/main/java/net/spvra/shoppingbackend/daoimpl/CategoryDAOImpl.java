package net.spvra.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.spvra.shoppingbackend.dao.CategoryDAO;
import net.spvra.shoppingbackend.dto.Category;


@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	
	

	public static List<Category> categories = new ArrayList<>();
	static {
		Category category = new Category();
		category.setId(1);
		category.setName("TELIVISION");
		category.setDescription("This is TV");
		category.setImageURL("cat_1.png");

		categories.add(category);

		category = new Category();
		category.setId(2);
		category.setName("LAPTOP");
		category.setDescription("This is LAPY");
		category.setImageURL("cat_2.png");

		categories.add(category);

		category= new Category();
		category.setId(3);
		category.setName("MOBILE");
		category.setDescription("This is MOBILE");
		category.setImageURL("cat_3.png");
		
	categories.add(category);
		
		
		
		
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category get(int id) {
		//enhanced for loop
		for(Category category : categories) {
			if(category.getId() == id) return category;
		}
		return null;
	}
}
