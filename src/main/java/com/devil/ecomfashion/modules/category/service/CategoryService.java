package com.devil.ecomfashion.modules.category.service;

import com.devil.ecomfashion.exception.AlreadyExistException;
import com.devil.ecomfashion.exception.ResourceNotFoundException;
import com.devil.ecomfashion.modules.category.dto.request.CategoryDTO;
import com.devil.ecomfashion.modules.category.dto.response.CategoryResponse;
import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.repository.CategoryRepository;
import com.devil.ecomfashion.modules.product.dto.response.ProductResponse;
import com.devil.ecomfashion.modules.product.service.ProductService;
import com.devil.ecomfashion.utils.CategoryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public List<CategoryResponse> find(String name) {

        List<Category> categories;

        if (StringUtils.isBlank(name)) {
            categories = categoryRepository.findAll();
        } else {
            categories = Collections.singletonList(categoryRepository.findByNameIgnoreCase(name));
        }

        return CategoryUtils.convertCategoryResponse(categories);

    }

    @Transactional
    public CategoryResponse findById(long id) {
        return CategoryUtils.convertCategoryResponse(getById(id));

    }

    @Transactional
    public Category getById(long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new ResourceNotFoundException("no found category");
        }
        return category.get();
    }

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

    @Transactional
    public Category findOne(String name) {

        Category category = categoryRepository.findByNameIgnoreCase(name);

        if (ObjectUtils.isEmpty(category)) {
            return null;
        }

        return category;
    }

}
