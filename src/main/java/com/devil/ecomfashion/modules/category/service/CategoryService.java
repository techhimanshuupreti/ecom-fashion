package com.devil.ecomfashion.modules.category.service;

import com.devil.ecomfashion.constant.Message;
import com.devil.ecomfashion.exception.AlreadyExistException;
import com.devil.ecomfashion.exception.ResourceNotFoundException;
import com.devil.ecomfashion.modules.category.dto.request.CategoryDTO;
import com.devil.ecomfashion.modules.category.dto.response.CategoryResponse;
import com.devil.ecomfashion.modules.category.dto.response.PageableCategoryResponse;
import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.repository.CategoryRepository;
import com.devil.ecomfashion.utils.CategoryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public PageableCategoryResponse find(String name, int pageNo, int pageSize) {

        Page<Category> categoryPage = null;
        Pageable pageable = PageRequest.of(pageNo >= 1 ? pageNo - 1 : 0, pageSize, Sort.by("createdAt")
                .descending().and(Sort.by("id").descending()));
        if (StringUtils.isBlank(name)) {
            categoryPage = categoryRepository.findAll(pageable);
        } else {
            categoryPage = categoryRepository.findAllByNameContainingIgnoreCase(name, pageable);
        }

        return CategoryUtils.convert(categoryPage);

    }

    @Transactional
    public CategoryResponse findById(long id) {
        return CategoryUtils.convert(getById(id));

    }

    @Transactional
    public Category getById(long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new ResourceNotFoundException(Message.NO_CATEGORY_FOUND);
        }
        return category.get();
    }

    @Transactional
    public CategoryResponse create(CategoryDTO categoryDTO) {
        log.info("calling creating the category");
        Category category = findOne(categoryDTO.getName());
        if (!ObjectUtils.isEmpty(category)) {
            throw new AlreadyExistException(Message.ALREADY_FOUND.replace("%",categoryDTO.getName()));
        }

        category = new Category();
        category.setCreatedAt(new Date());
        category.setUpdatedAt(new Date());
        category.setName(categoryDTO.getName());

        category = categoryRepository.save(category);

        return CategoryUtils.convert(category);
    }

    @Transactional
    public CategoryResponse update(long id, CategoryDTO categoryDTO) {

        Category updatedCategory = getById(id);
        updatedCategory.setName(categoryDTO.getName());
        updatedCategory.setUpdatedAt(new Date());

        updatedCategory = categoryRepository.save(updatedCategory);
        return CategoryUtils.convert(updatedCategory);
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
