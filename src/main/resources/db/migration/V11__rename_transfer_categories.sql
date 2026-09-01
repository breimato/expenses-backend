-- Unify transfer category display names
UPDATE categories
SET name = 'Transferencia',
    updated_at = NOW()
WHERE movement_type IN ('TRANSFER_OUT', 'TRANSFER_IN');
