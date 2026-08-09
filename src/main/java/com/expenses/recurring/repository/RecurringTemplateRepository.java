package com.expenses.recurring.repository;

import java.util.List;
import java.util.Objects;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.expenses.api.dto.PatchRecurringTemplateV1RequestDto;
import com.expenses.api.dto.PostRecurringTemplateV1RequestDto;
import com.expenses.auth.service.CurrentUserService;
import com.expenses.category.repository.CategoryRepository;
import com.expenses.common.EnumMapper;
import com.expenses.common.RecurringFrequency;
import com.expenses.common.exception.CategoryException;
import com.expenses.common.exception.RecurringTemplateException;
import com.expenses.common.exception.constants.ExceptionMessageConstants;
import com.expenses.recurring.entity.RecurringTemplateEntity;
import com.expenses.recurring.mapper.PatchRecurringTemplateRequestMapper;
import com.expenses.recurring.mapper.PostRecurringTemplateRequestMapper;

import lombok.RequiredArgsConstructor;

/** The Class Recurring Template Repository. */
@Component
@RequiredArgsConstructor
public class RecurringTemplateRepository {

    /** The recurring template jpa mapper. */
    private final RecurringTemplateJpaMapper recurringTemplateJpaMapper;

    /** The category repository. */
    private final CategoryRepository categoryRepository;

    /** The post recurring template request mapper. */
    private final PostRecurringTemplateRequestMapper postRecurringTemplateRequestMapper;

    /** The patch recurring template request mapper. */
    private final PatchRecurringTemplateRequestMapper patchRecurringTemplateRequestMapper;

    /** The enum mapper. */
    private final EnumMapper enumMapper;

    /** The current user service. */
    private final CurrentUserService currentUserService;

    /**
     * Find all.
     *
     * @param categoryId the category id
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<RecurringTemplateEntity> findAll(final Integer categoryId) {

        return this.recurringTemplateJpaMapper.findByCriteria(
                this.currentUserService.getRequiredUserId(),
                categoryId);
    }

    /**
     * Create.
     *
     * @param postRecurringTemplateV1RequestDto the post recurring template v1 request dto
     * @return the recurring template entity
     */
    @Transactional
    public RecurringTemplateEntity create(final PostRecurringTemplateV1RequestDto postRecurringTemplateV1RequestDto) {

        final var userId = this.currentUserService.getRequiredUserId();
        this.ensureCategoryExists(postRecurringTemplateV1RequestDto.getCategoryId(), userId);
        final var recurringTemplateEntity =
                this.postRecurringTemplateRequestMapper.toRecurringTemplateEntity(postRecurringTemplateV1RequestDto);
        recurringTemplateEntity.setUserId(userId);
        this.applyDefaults(recurringTemplateEntity, postRecurringTemplateV1RequestDto);
        return this.recurringTemplateJpaMapper.save(recurringTemplateEntity);
    }

    /**
     * Update.
     *
     * @param id the id
     * @param patchRecurringTemplateV1RequestDto the patch recurring template v1 request dto
     * @return the recurring template entity
     */
    @Transactional
    public RecurringTemplateEntity update(final Integer id, final PatchRecurringTemplateV1RequestDto patchRecurringTemplateV1RequestDto) {

        final var recurringTemplateEntity = this.findById(id);
        if (Objects.nonNull(patchRecurringTemplateV1RequestDto.getCategoryId())) {
            this.ensureCategoryExists(patchRecurringTemplateV1RequestDto.getCategoryId(), recurringTemplateEntity.getUserId());
        }
        this.patchRecurringTemplateRequestMapper.updateRecurringTemplateEntity(
                patchRecurringTemplateV1RequestDto,
                recurringTemplateEntity);
        if (Objects.nonNull(patchRecurringTemplateV1RequestDto.getMovementType())) {
            recurringTemplateEntity.setMovementType(
                    this.enumMapper.toMovementType(patchRecurringTemplateV1RequestDto.getMovementType()));
        }
        if (Objects.nonNull(patchRecurringTemplateV1RequestDto.getFrequency())) {
            recurringTemplateEntity.setFrequency(
                    this.enumMapper.toRecurringFrequency(patchRecurringTemplateV1RequestDto.getFrequency()));
        }
        return this.recurringTemplateJpaMapper.save(recurringTemplateEntity);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @Transactional
    public void delete(final Integer id) {

        final var recurringTemplateEntity = this.findById(id);
        this.recurringTemplateJpaMapper.delete(recurringTemplateEntity);
    }

    /**
     * Find by id for current user.
     *
     * @param id the id
     * @return the recurring template entity
     */
    @Transactional(readOnly = true)
    public RecurringTemplateEntity findById(final Integer id) {

        return this.recurringTemplateJpaMapper
                .findByIdAndUserId(id, this.currentUserService.getRequiredUserId())
                .orElseThrow(() -> new RecurringTemplateException(ExceptionMessageConstants.RECURRING_TEMPLATE_NOT_FOUND));
    }

    /**
     * Mark recurring template as used.
     *
     * @param recurringTemplateEntity the recurring template entity
     */
    @Transactional
    public void markAsUsed(final RecurringTemplateEntity recurringTemplateEntity) {

        recurringTemplateEntity.setLastUsedAt(LocalDateTime.now());
        this.recurringTemplateJpaMapper.save(recurringTemplateEntity);
    }

    private void applyDefaults(
            final RecurringTemplateEntity recurringTemplateEntity,
            final PostRecurringTemplateV1RequestDto postRecurringTemplateV1RequestDto) {

        if (Objects.nonNull(postRecurringTemplateV1RequestDto.getMovementType())) {
            recurringTemplateEntity.setMovementType(
                    this.enumMapper.toMovementType(postRecurringTemplateV1RequestDto.getMovementType()));
        }
        if (Objects.nonNull(postRecurringTemplateV1RequestDto.getFrequency())) {
            recurringTemplateEntity.setFrequency(
                    this.enumMapper.toRecurringFrequency(postRecurringTemplateV1RequestDto.getFrequency()));
        } else {
            recurringTemplateEntity.setFrequency(RecurringFrequency.MANUAL);
        }
        if (Objects.nonNull(postRecurringTemplateV1RequestDto.getOffsetsSpendingAverage())) {
            recurringTemplateEntity.setOffsetsSpendingAverage(postRecurringTemplateV1RequestDto.getOffsetsSpendingAverage());
        }
        if (Objects.nonNull(postRecurringTemplateV1RequestDto.getAutoApply())) {
            recurringTemplateEntity.setAutoApply(postRecurringTemplateV1RequestDto.getAutoApply());
        }
        if (Objects.nonNull(postRecurringTemplateV1RequestDto.getEnabled())) {
            recurringTemplateEntity.setEnabled(postRecurringTemplateV1RequestDto.getEnabled());
        }
        if (Objects.nonNull(postRecurringTemplateV1RequestDto.getDayOfMonth())) {
            recurringTemplateEntity.setDayOfMonth(postRecurringTemplateV1RequestDto.getDayOfMonth());
        }
    }

    private void ensureCategoryExists(final Integer categoryId, final Integer userId) {

        if (!this.categoryRepository.existsByIdForUser(categoryId, userId)) {
            throw new CategoryException(ExceptionMessageConstants.CATEGORY_NOT_FOUND);
        }
    }
}
