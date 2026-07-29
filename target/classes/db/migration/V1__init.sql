CREATE TABLE profile (
    id           INT           PRIMARY KEY,
    display_name VARCHAR(100)  NOT NULL,
    balance      NUMERIC(19, 2) NOT NULL,
    created_at   TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMPTZ   NOT NULL DEFAULT NOW()
);

CREATE TABLE categories (
    id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name       VARCHAR(100)  NOT NULL,
    color      VARCHAR(20)   NOT NULL,
    icon       VARCHAR(20),
    sort_order INT           NOT NULL DEFAULT 0,
    created_at TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ   NOT NULL DEFAULT NOW()
);

CREATE TABLE expenses (
    id           INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    category_id  INT           NOT NULL,
    amount       NUMERIC(19, 2) NOT NULL,
    description  VARCHAR(500)  NOT NULL,
    expense_date DATE          NOT NULL,
    created_at   TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_expenses_category_id
        FOREIGN KEY (category_id) REFERENCES categories (id)
        ON DELETE RESTRICT ON UPDATE NO ACTION
);

CREATE TABLE recurring_templates (
    id           INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    label        VARCHAR(100)  NOT NULL,
    amount       NUMERIC(19, 2) NOT NULL,
    category_id  INT           NOT NULL,
    sort_order   INT           NOT NULL DEFAULT 0,
    last_used_at TIMESTAMPTZ,
    created_at   TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_recurring_templates_category_id
        FOREIGN KEY (category_id) REFERENCES categories (id)
        ON DELETE RESTRICT ON UPDATE NO ACTION
);
