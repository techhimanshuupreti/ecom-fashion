package com.devil.ecomfashion.modules.category.service;

import com.devil.ecomfashion.modules.category.dto.CategoryDTO;
import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> find() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Optional<Category> findOne(String id) {
        return categoryRepository.findById(id);
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
}
