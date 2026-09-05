-- Account membership (OWNER / MEMBER)
CREATE TABLE account_members (
    id           INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    account_id   INT           NOT NULL,
    user_id      INT           NOT NULL,
    role         VARCHAR(20)   NOT NULL,
    created_at   TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_account_members_account_id
        FOREIGN KEY (account_id) REFERENCES accounts (id)
        ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_account_members_user_id
        FOREIGN KEY (user_id) REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT chk_account_members_role
        CHECK (role IN ('OWNER', 'MEMBER')),
    CONSTRAINT uq_account_members_account_user
        UNIQUE (account_id, user_id)
);

CREATE INDEX idx_account_members_user_id ON account_members (user_id);
CREATE INDEX idx_account_members_account_id ON account_members (account_id);

-- Backfill OWNER membership from current account owners
INSERT INTO account_members (account_id, user_id, role, created_at)
SELECT a.id, a.user_id, 'OWNER', NOW()
FROM accounts a;

-- Invitations by token
CREATE TABLE account_invitations (
    id                   INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    account_id           INT           NOT NULL,
    token                VARCHAR(36)   NOT NULL,
    created_by_user_id   INT           NOT NULL,
    expires_at           TIMESTAMPTZ   NOT NULL,
    accepted_at          TIMESTAMPTZ,
    accepted_by_user_id  INT,
    revoked_at           TIMESTAMPTZ,
    created_at           TIMESTAMPTZ   NOT NULL DEFAULT NOW(),
    CONSTRAINT fk_account_invitations_account_id
        FOREIGN KEY (account_id) REFERENCES accounts (id)
        ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_account_invitations_created_by
        FOREIGN KEY (created_by_user_id) REFERENCES users (id)
        ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_account_invitations_accepted_by
        FOREIGN KEY (accepted_by_user_id) REFERENCES users (id)
        ON DELETE SET NULL ON UPDATE NO ACTION,
    CONSTRAINT uq_account_invitations_token
        UNIQUE (token)
);

CREATE INDEX idx_account_invitations_account_id ON account_invitations (account_id);
