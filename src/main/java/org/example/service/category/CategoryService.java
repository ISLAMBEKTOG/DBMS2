package org.example.service.category;

import org.example.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryByName(String name);

    List<Category> getCategoriesByIds(Long first, Long second);
}
