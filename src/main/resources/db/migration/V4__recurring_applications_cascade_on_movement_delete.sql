ALTER TABLE recurring_applications
    DROP CONSTRAINT fk_recurring_applications_movement_id;

ALTER TABLE recurring_applications
    ADD CONSTRAINT fk_recurring_applications_movement_id
        FOREIGN KEY (movement_id) REFERENCES expenses (id)
        ON DELETE CASCADE ON UPDATE NO ACTION;
