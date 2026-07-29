package com.expenses.category.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.PatchCategoryV1Api;
import com.expenses.api.dto.CategoryV1ResponseDto;
import com.expenses.api.dto.PatchCategoryV1RequestDto;

import com.expenses.category.entity.CategoryEntity;
import com.expenses.category.mapper.CategoryResponseMapper;
import com.expenses.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

/** The Class Patch Category Controller. */
@RestController
@RequiredArgsConstructor
public class PatchCategoryController implements PatchCategoryV1Api {

    /** The category repository. */
    private final CategoryRepository categoryRepository;

    /** The category response mapper. */
    private final CategoryResponseMapper categoryResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<CategoryV1ResponseDto> patchCategoryV1(final Integer id, final PatchCategoryV1RequestDto patchCategoryV1RequestDto) {

        final var categoryEntity = this.categoryRepository.update(id, patchCategoryV1RequestDto);

        final var categoryV1ResponseDto = this.categoryResponseMapper.toCategoryV1Response(categoryEntity);

        return ResponseEntity.ok(categoryV1ResponseDto);
    }
}
