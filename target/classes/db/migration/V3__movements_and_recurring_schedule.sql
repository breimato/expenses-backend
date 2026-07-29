ALTER TABLE expenses
    ADD COLUMN movement_type VARCHAR(10) NOT NULL DEFAULT 'EXPENSE',
    ADD COLUMN offsets_spending_average BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE categories
    ADD COLUMN movement_type VARCHAR(10) NOT NULL DEFAULT 'EXPENSE';

ALTER TABLE recurring_templates
    ADD COLUMN movement_type VARCHAR(10) NOT NULL DEFAULT 'EXPENSE',
    ADD COLUMN offsets_spending_average BOOLEAN NOT NULL DEFAULT FALSE,
    ADD COLUMN frequency VARCHAR(10) NOT NULL DEFAULT 'MANUAL',
    ADD COLUMN day_of_month INT,
    ADD COLUMN auto_apply BOOLEAN NOT NULL DEFAULT FALSE,
    ADD COLUMN enabled BOOLEAN NOT NULL DEFAULT TRUE;

CREATE TABLE recurring_applications (
    id           INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    template_id  INT           NOT NULL,
    period_key   VARCHAR(7)    NOT NULL,
    movement_id  INT           NOT NULL,
    applied_at   TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_recurring_applications_template_id
        FOREIGN KEY (template_id) REFERENCES recurring_templates (id)
        ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_recurring_applications_movement_id
        FOREIGN KEY (movement_id) REFERENCES expenses (id)
        ON DELETE RESTRICT ON UPDATE NO ACTION,
    CONSTRAINT uq_recurring_applications_template_period
        UNIQUE (template_id, period_key)
);

INSERT INTO categories (name, color, icon, sort_order, movement_type)
VALUES
    ('Salario', '#16A34A', 'wallet', 10, 'INCOME'),
    ('Reembolso', '#0D9488', 'refresh', 11, 'INCOME'),
    ('Otros ingresos', '#65A30D', 'plus', 12, 'INCOME');
