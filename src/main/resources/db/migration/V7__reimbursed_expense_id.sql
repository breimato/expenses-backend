ALTER TABLE expenses
    ADD COLUMN reimbursed_expense_id INT NULL;

ALTER TABLE expenses
    ADD CONSTRAINT fk_expenses_reimbursed_expense_id
        FOREIGN KEY (reimbursed_expense_id) REFERENCES expenses (id)
        ON DELETE SET NULL ON UPDATE NO ACTION;

CREATE INDEX idx_expenses_reimbursed_expense_id ON expenses (reimbursed_expense_id);
