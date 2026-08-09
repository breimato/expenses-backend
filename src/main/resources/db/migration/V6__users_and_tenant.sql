DELETE FROM recurring_applications;
DELETE FROM expenses;
DELETE FROM recurring_templates;
DELETE FROM categories;
DELETE FROM profile;

CREATE TABLE users (
    id            INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email         VARCHAR(255)  NOT NULL,
    password_hash VARCHAR(255)  NOT NULL,
    display_name  VARCHAR(100)  NOT NULL,
    created_at    TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    updated_at    TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    CONSTRAINT uq_users_email UNIQUE (email)
);

DROP TABLE profile;

CREATE TABLE profile (
    id           INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id      INT           NOT NULL,
    display_name VARCHAR(100)  NOT NULL,
    balance      NUMERIC(19, 2) NOT NULL DEFAULT 0,
    created_at   TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    CONSTRAINT uq_profile_user_id UNIQUE (user_id),
    CONSTRAINT fk_profile_user_id
        FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE NO ACTION
);

ALTER TABLE categories
    ADD COLUMN user_id INT NOT NULL;

ALTER TABLE categories
    ADD CONSTRAINT fk_categories_user_id
        FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE NO ACTION;

CREATE INDEX idx_categories_user_id ON categories (user_id);

ALTER TABLE expenses
    ADD COLUMN user_id INT NOT NULL;

ALTER TABLE expenses
    ADD CONSTRAINT fk_expenses_user_id
        FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE NO ACTION;

CREATE INDEX idx_expenses_user_id ON expenses (user_id);

ALTER TABLE recurring_templates
    ADD COLUMN user_id INT NOT NULL;

ALTER TABLE recurring_templates
    ADD CONSTRAINT fk_recurring_templates_user_id
        FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE NO ACTION;

CREATE INDEX idx_recurring_templates_user_id ON recurring_templates (user_id);
