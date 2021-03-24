package net.spvra.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.spvra.shoppingbackend.dao.CategoryDAO;
import net.spvra.shoppingbackend.dto.Category;
@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories=new ArrayList<>();
	@Autowired
	private SessionFactory sessionFactory;
	
	/*
	 * static { Category category =new Category();
	 * 
	 * category.setId(1); category.setName("Television");
	 * category.setDescription("This is television"); category.setImageURL("1.jpg");
	 * categories.add(category);
	 * 
	 * category =new Category(); category.setId(2); category.setName("Mobile");
	 * category.setDescription("This is Mobile"); category.setImageURL("2.jpg");
	 * categories.add(category);
	 * 
	 * category =new Category(); category.setId(3); category.setName("Laptop");
	 * category.setDescription("This is Laptop"); category.setImageURL("3.jpg");
	 * categories.add(category);
	 * 
	 * }
	 */
	
	
	
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}




	@Override
	public Category get(int id) {
		// TODO Auto-generated method stub
		
		
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}
	
	
	@Override
	
	public boolean add(Category category) {

		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}




	@Override
	public boolean update(Category category) {
		// TODO Auto-generated method stub
		return false;
	}




	@Override
	public boolean delete(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

}
