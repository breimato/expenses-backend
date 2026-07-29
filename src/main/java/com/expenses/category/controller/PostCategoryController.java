package com.expenses.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.PostCategoryV1Api;
import com.expenses.api.dto.CategoryV1ResponseDto;
import com.expenses.api.dto.PostCategoryV1RequestDto;

import com.expenses.category.mapper.CategoryResponseMapper;
import com.expenses.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

/** The Class Post Category Controller. */
@RestController
@RequiredArgsConstructor
public class PostCategoryController implements PostCategoryV1Api {

    /** The category repository. */
    private final CategoryRepository categoryRepository;

    /** The category response mapper. */
    private final CategoryResponseMapper categoryResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<CategoryV1ResponseDto> postCategoryV1(final PostCategoryV1RequestDto postCategoryV1RequestDto) {

        final var categoryEntity = this.categoryRepository.create(postCategoryV1RequestDto);
        final var categoryV1ResponseDto = this.categoryResponseMapper.toCategoryV1Response(categoryEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryV1ResponseDto);
    }
}
