package com.expenses.category.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.DeleteCategoryV1Api;

import com.expenses.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

/** The Class Delete Category Controller. */
@RestController
@RequiredArgsConstructor
public class DeleteCategoryController implements DeleteCategoryV1Api {

    /** The category repository. */
    private final CategoryRepository categoryRepository;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<Void> deleteCategoryV1(final Integer id) {

        this.categoryRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
