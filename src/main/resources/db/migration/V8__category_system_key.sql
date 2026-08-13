ALTER TABLE categories
    ADD COLUMN system_key VARCHAR(32) NULL;

CREATE UNIQUE INDEX uk_categories_user_system_key
    ON categories (user_id, system_key)
    WHERE system_key IS NOT NULL;

INSERT INTO categories (user_id, name, color, icon, movement_type, created_at, updated_at, system_key)
SELECT u.id,
       'Ahorros',
       '#0EA5E9',
       '💰',
       'EXPENSE',
       NOW(),
       NOW(),
       'SAVINGS'
FROM users u
WHERE NOT EXISTS (
    SELECT 1
    FROM categories c
    WHERE c.user_id = u.id
      AND c.system_key = 'SAVINGS'
);
