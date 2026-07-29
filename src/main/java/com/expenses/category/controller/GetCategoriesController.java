package com.expenses.category.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.expenses.api.GetCategoriesV1Api;
import com.expenses.api.dto.GetCategoriesV1ResponseDto;
import com.expenses.api.dto.MovementTypeV1;
import com.expenses.category.mapper.CategoryResponseMapper;
import com.expenses.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

/** The Class Get Categories Controller. */
@RestController
@RequiredArgsConstructor
public class GetCategoriesController implements GetCategoriesV1Api {

    /** The category repository. */
    private final CategoryRepository categoryRepository;

    /** The category response mapper. */
    private final CategoryResponseMapper categoryResponseMapper;

    /** {@inheritDoc} */
    @Override
    public ResponseEntity<GetCategoriesV1ResponseDto> getCategoriesV1(
            final Integer id, final String name, final MovementTypeV1 movementType) {

        final var categoryEntityList = this.categoryRepository.findAll(id, name, movementType);

        final var getCategoriesV1ResponseDto = this.categoryResponseMapper.toGetCategoriesV1Response(categoryEntityList);

        return ResponseEntity.ok(getCategoriesV1ResponseDto);
    }
}
