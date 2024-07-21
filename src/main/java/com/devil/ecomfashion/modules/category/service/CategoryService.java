package com.devil.ecomfashion.modules.category.service;

import com.devil.ecomfashion.exception.ResourceNotFoundException;
import com.devil.ecomfashion.modules.category.dto.CategoryDTO;
import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.repository.CategoryRepository;
import com.devil.ecomfashion.modules.subcategory.entity.SubCategory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> find(String name) {

        List<Category> categories;

        if (StringUtils.isBlank(name)) {
            categories = categoryRepository.findAll();
        } else {
            categories = Collections.singletonList(categoryRepository.findByNameIgnoreCase(name));
        }

        return categories;
    }

    public Category findById(long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new NullPointerException("no found category");
        }
        return category.get();
    }

    @Transactional
    public Category create(CategoryDTO categoryDTO) {

        Category category = findOne(categoryDTO.getName());
        if(!ObjectUtils.isEmpty(category)){
            throw new DuplicateKeyException("name");
        }

        category = new Category();
        category.setCreatedAt(new Date());
        category.setUpdatedAt(new Date());
        category.setName(categoryDTO.getName());

        return categoryRepository.save(category);
    }

    public Category update(long id,CategoryDTO categoryDTO) {

        Category updatedCategory = findById(id);
        updatedCategory.setName(categoryDTO.getName());
        updatedCategory.setUpdatedAt(new Date());

        return categoryRepository.save(updatedCategory);
    }

    public Boolean delete(String name) {

        Category category = findOne(name);

        return categoryRepository.deleteByNameIgnoreCase(name);
    }

    public Category findOne(String name) {

        Category category = categoryRepository.findByNameIgnoreCase(name);

        if (ObjectUtils.isEmpty(category)) {
            throw new ResourceNotFoundException(name + " category is not found");
        }

        return category;
    }
}
