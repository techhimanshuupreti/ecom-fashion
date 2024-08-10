package com.devil.ecomfashion.modules.category.service;

import com.devil.ecomfashion.exception.AlreadyExistException;
import com.devil.ecomfashion.modules.category.dto.request.CategoryDTO;
import com.devil.ecomfashion.modules.category.dto.response.CategoryResponse;
import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.repository.CategoryRepository;
import com.devil.ecomfashion.utils.CategoryUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> find(String name) {

        List<Category> categories;

        if (StringUtils.isBlank(name)) {
            categories = categoryRepository.findAll();
        } else {
            categories = Collections.singletonList(categoryRepository.findByNameIgnoreCase(name));
        }

        return CategoryUtils.convertCategoryResponse(categories);

    }

    public CategoryResponse findById(long id) {
        return CategoryUtils.convertCategoryResponse(getById(id));

    }

    public Category getById(long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new NullPointerException("no found category");
        }
        return category.get();
    }

    @Transactional
    public CategoryResponse create(CategoryDTO categoryDTO) {
        log.info("calling creating the category");
        Category category = findOne(categoryDTO.getName());
        if (!ObjectUtils.isEmpty(category)) {
            throw new AlreadyExistException(categoryDTO.getName() + " is already exist.");
        }

        category = new Category();
        category.setCreatedAt(new Date());
        category.setUpdatedAt(new Date());
        category.setName(categoryDTO.getName());

        category = categoryRepository.save(category);

        return CategoryUtils.convertCategoryResponse(category);
    }

    public CategoryResponse update(long id, CategoryDTO categoryDTO) {

        Category updatedCategory = getById(id);
        updatedCategory.setName(categoryDTO.getName());
        updatedCategory.setUpdatedAt(new Date());

        updatedCategory = categoryRepository.save(updatedCategory);
        return CategoryUtils.convertCategoryResponse(updatedCategory);
    }

    public Boolean delete(long id) {

        Category category = getById(id);
        if (category == null) {
            return false;
        }
        categoryRepository.deleteById(id);
        return true;
    }

    public Category findOne(String name) {

        Category category = categoryRepository.findByNameIgnoreCase(name);

        if (ObjectUtils.isEmpty(category)) {
            return null;
        }

        return category;
    }

}
