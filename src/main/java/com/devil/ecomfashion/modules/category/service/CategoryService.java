package com.devil.ecomfashion.modules.category.service;

import com.devil.ecomfashion.modules.category.entity.Category;
import com.devil.ecomfashion.modules.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> find() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Optional<Category> findOne(long id) {
        return categoryRepository.findById(id);
    }
}
