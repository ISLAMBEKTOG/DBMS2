package org.example.service.category;

import org.example.dao.category.CategoryDAOImpl;
import org.example.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAOImpl categoryDAOimpl;

    @Autowired
    public CategoryServiceImpl(CategoryDAOImpl categoryDAOimpl) {
        this.categoryDAOimpl = categoryDAOimpl;
    }

    @Override
    public List<Category> getAllCategories() {
        return (List<Category>) categoryDAOimpl.findAll();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryDAOimpl.findByName(name).isPresent()
                ? categoryDAOimpl.findByName(name).get() : null;
    }

    @Override
    public List<Category> getCategoriesByIds(Long first, Long second) {
        return categoryDAOimpl.getCategoriesByIds(first, second);
    }
}
