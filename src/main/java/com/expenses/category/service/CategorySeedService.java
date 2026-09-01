package com.expenses.category.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.expenses.category.entity.CategoryEntity;
import com.expenses.category.repository.CategoryJpaMapper;
import com.expenses.common.MovementType;

import lombok.RequiredArgsConstructor;

/** The Class Category Seed Service. */
@Component
@RequiredArgsConstructor
public class CategorySeedService {

    private static final List<DefaultCategory> DEFAULT_CATEGORIES = List.of(
            new DefaultCategory("Comida", "#EF4444", "🍔", MovementType.EXPENSE),
            new DefaultCategory("Transporte", "#3B82F6", "🚌", MovementType.EXPENSE),
            new DefaultCategory("Ocio", "#8B5CF6", "🎮", MovementType.EXPENSE),
            new DefaultCategory("Hogar", "#22C55E", "🏠", MovementType.EXPENSE),
            new DefaultCategory("Ahorros", "#0EA5E9", "💰", MovementType.EXPENSE),
            new DefaultCategory("Otros", "#6B7280", "📦", MovementType.EXPENSE),
            new DefaultCategory("Salario", "#16A34A", "wallet", MovementType.INCOME),
            new DefaultCategory("Reembolso", "#0D9488", "refresh", MovementType.INCOME),
            new DefaultCategory("Otros ingresos", "#65A30D", "plus", MovementType.INCOME),
            new DefaultCategory("Transferencia", "#64748B", "↗", MovementType.TRANSFER_OUT),
            new DefaultCategory("Transferencia", "#64748B", "↙", MovementType.TRANSFER_IN));

    /** The category jpa mapper. */
    private final CategoryJpaMapper categoryJpaMapper;

    /**
     * Seed default categories for an account.
     *
     * @param userId the user id
     * @param accountId the account id
     */
    public void seedDefaultCategories(final Integer userId, final Integer accountId) {

        for (final var defaultCategory : DEFAULT_CATEGORIES) {
            final var categoryEntity = new CategoryEntity();
            categoryEntity.setUserId(userId);
            categoryEntity.setAccountId(accountId);
            categoryEntity.setName(defaultCategory.name());
            categoryEntity.setColor(defaultCategory.color());
            categoryEntity.setIcon(defaultCategory.icon());
            categoryEntity.setMovementType(defaultCategory.movementType());
            this.categoryJpaMapper.save(categoryEntity);
        }
    }

    private record DefaultCategory(
            String name,
            String color,
            String icon,
            MovementType movementType) {
    }
}
