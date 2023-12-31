package com.devil.ecomfashion.modules.category.service;

import com.devil.ecomfashion.modules.category.dto.CategoryDTO;
import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryRepository.DisplayCategory> find() {
        return categoryRepository.findDistinctMainCategory();
    }

    public Optional<Category> findById(String id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new NullPointerException("no found category");
        }
        return category;
    }

    @Transactional
    public Category create(CategoryDTO categoryDTO) {

        Category category = new Category();
        category.setCreatedAt(new Date());
        category.setUpdatedAt(new Date());
        category.setType(categoryDTO.getType());
        category.setName(categoryDTO.getName());

        return categoryRepository.save(category);
    }

    public List<CategoryRepository.DisplayCategory> findByName(String name) {
        return categoryRepository.findDistinctByType(name);
    }
}
