-- Remove empty SAVINGS system categories created by V8 (no movements / templates).
DELETE FROM categories c
WHERE c.system_key = 'SAVINGS'
  AND NOT EXISTS (SELECT 1 FROM expenses e WHERE e.category_id = c.id)
  AND NOT EXISTS (SELECT 1 FROM recurring_templates r WHERE r.category_id = c.id);

DROP INDEX IF EXISTS uk_categories_user_system_key;

ALTER TABLE categories
    DROP COLUMN system_key;
