-- 1. Create accounts table
CREATE TABLE accounts (
    id           INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id      INT           NOT NULL,
    name         VARCHAR(100)  NOT NULL,
    is_default   BOOLEAN       NOT NULL DEFAULT FALSE,
    created_at   TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_accounts_user_id
        FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE INDEX idx_accounts_user_id ON accounts (user_id);
CREATE UNIQUE INDEX uq_accounts_user_default ON accounts (user_id) WHERE is_default = TRUE;

-- 2. Migrate profile → default account per user
INSERT INTO accounts (user_id, name, is_default, created_at, updated_at)
SELECT user_id, display_name, TRUE, created_at, updated_at
FROM profile;

-- 3. Add nullable account_id columns
ALTER TABLE expenses ADD COLUMN account_id INT;
ALTER TABLE categories ADD COLUMN account_id INT;
ALTER TABLE recurring_templates ADD COLUMN account_id INT;

-- 4. Backfill account_id from default account
UPDATE expenses e
SET account_id = a.id
FROM accounts a
WHERE e.user_id = a.user_id
  AND a.is_default = TRUE;

UPDATE categories c
SET account_id = a.id
FROM accounts a
WHERE c.user_id = a.user_id
  AND a.is_default = TRUE;

UPDATE recurring_templates rt
SET account_id = a.id
FROM accounts a
WHERE rt.user_id = a.user_id
  AND a.is_default = TRUE;

-- 5. Widen movement_type for TRANSFER_OUT / TRANSFER_IN
ALTER TABLE expenses ALTER COLUMN movement_type TYPE VARCHAR(20);
ALTER TABLE categories ALTER COLUMN movement_type TYPE VARCHAR(20);
ALTER TABLE recurring_templates ALTER COLUMN movement_type TYPE VARCHAR(20);

-- 6. Account transfers table
CREATE TABLE account_transfers (
    id               INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id          INT           NOT NULL,
    from_account_id  INT           NOT NULL,
    to_account_id    INT           NOT NULL,
    amount           NUMERIC(19, 2) NOT NULL,
    transfer_date    DATE          NOT NULL,
    description      VARCHAR(500)  NOT NULL,
    created_at       TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_account_transfers_user_id
        FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_account_transfers_from_account_id
        FOREIGN KEY (from_account_id) REFERENCES accounts (id)
        ON DELETE RESTRICT ON UPDATE NO ACTION,
    CONSTRAINT fk_account_transfers_to_account_id
        FOREIGN KEY (to_account_id) REFERENCES accounts (id)
        ON DELETE RESTRICT ON UPDATE NO ACTION,
    CONSTRAINT chk_account_transfers_different_accounts
        CHECK (from_account_id <> to_account_id)
);

CREATE INDEX idx_account_transfers_user_id ON account_transfers (user_id);

ALTER TABLE expenses ADD COLUMN transfer_id INT;

ALTER TABLE expenses
    ADD CONSTRAINT fk_expenses_transfer_id
        FOREIGN KEY (transfer_id) REFERENCES account_transfers (id)
        ON DELETE SET NULL ON UPDATE NO ACTION;

-- 7. Enforce NOT NULL and FKs
ALTER TABLE expenses ALTER COLUMN account_id SET NOT NULL;
ALTER TABLE categories ALTER COLUMN account_id SET NOT NULL;
ALTER TABLE recurring_templates ALTER COLUMN account_id SET NOT NULL;

ALTER TABLE expenses
    ADD CONSTRAINT fk_expenses_account_id
        FOREIGN KEY (account_id) REFERENCES accounts (id)
        ON DELETE RESTRICT ON UPDATE NO ACTION;

ALTER TABLE categories
    ADD CONSTRAINT fk_categories_account_id
        FOREIGN KEY (account_id) REFERENCES accounts (id)
        ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE recurring_templates
    ADD CONSTRAINT fk_recurring_templates_account_id
        FOREIGN KEY (account_id) REFERENCES accounts (id)
        ON DELETE CASCADE ON UPDATE NO ACTION;

CREATE INDEX idx_expenses_account_id ON expenses (account_id);
CREATE INDEX idx_categories_account_id ON categories (account_id);
CREATE INDEX idx_recurring_templates_account_id ON recurring_templates (account_id);

-- 9. Seed transfer categories for existing accounts
INSERT INTO categories (user_id, account_id, name, color, icon, movement_type, created_at, updated_at)
SELECT a.user_id, a.id, 'Transferencia (salida)', '#64748B', '↗', 'TRANSFER_OUT', NOW(), NOW()
FROM accounts a;

INSERT INTO categories (user_id, account_id, name, color, icon, movement_type, created_at, updated_at)
SELECT a.user_id, a.id, 'Transferencia (entrada)', '#64748B', '↙', 'TRANSFER_IN', NOW(), NOW()
FROM accounts a;

-- 10. Drop legacy profile table
DROP TABLE profile;
