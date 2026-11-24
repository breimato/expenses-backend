package com.expenses.common.exception.constants;


/** The Class Exception Message Constants. */
public final class ExceptionMessageConstants {

    /** The Constant CATEGORY_NOT_FOUND. */
    public static final String CATEGORY_NOT_FOUND = "EXP-CATEGORY-001 | No se encontró la categoría";

    /** The Constant CATEGORY_MOVEMENT_TYPE_MISMATCH. */
    public static final String CATEGORY_MOVEMENT_TYPE_MISMATCH = "EXP-CATEGORY-002 | El tipo de movimiento de la categoría no coincide";

    /** The Constant CATEGORY_IN_USE. */
    public static final String CATEGORY_IN_USE = "EXP-CATEGORY-003 | Esta categoría tiene gastos o plantillas asociadas. Para eliminarla, primero borra o reasigna los gastos y plantillas que la usan.";

    /** The Constant EXPENSE_NOT_FOUND. */
    public static final String EXPENSE_NOT_FOUND = "EXP-EXPENSE-001 | No se encontró el gasto";

    /** The Constant EXPENSE_REIMBURSED_NOT_FOUND. */
    public static final String EXPENSE_REIMBURSED_NOT_FOUND =
            "EXP-EXPENSE-002 | No se encontró el gasto a reembolsar";

    /** The Constant EXPENSE_REIMBURSED_MUST_BE_EXPENSE. */
    public static final String EXPENSE_REIMBURSED_MUST_BE_EXPENSE =
            "EXP-EXPENSE-003 | Solo se puede vincular un reembolso a un movimiento de tipo gasto";

    /** The Constant EXPENSE_TRANSFER_TYPE_IMMUTABLE. */
    public static final String EXPENSE_TRANSFER_TYPE_IMMUTABLE =
            "EXP-EXPENSE-004 | No se puede cambiar el tipo de una transferencia";

    /** The Constant EXPENSE_TRANSFER_CATEGORY_IMMUTABLE. */
    public static final String EXPENSE_TRANSFER_CATEGORY_IMMUTABLE =
            "EXP-EXPENSE-005 | No se puede cambiar la categoría de una transferencia";

    /** The Constant RECURRING_TEMPLATE_NOT_FOUND. */
    public static final String RECURRING_TEMPLATE_NOT_FOUND = "EXP-RECURRING-001 | No se encontró la plantilla recurrente";

    /** The Constant PROFILE_NOT_FOUND. */
    public static final String PROFILE_NOT_FOUND = "EXP-PROFILE-001 | No se encontró el perfil";

    /** The Constant ACCOUNT_NOT_FOUND. */
    public static final String ACCOUNT_NOT_FOUND = "EXP-ACCOUNT-001 | No se encontró la cuenta";

    /** The Constant ACCOUNT_TRANSFER_SAME_ACCOUNT. */
    public static final String ACCOUNT_TRANSFER_SAME_ACCOUNT =
            "EXP-ACCOUNT-002 | La cuenta de origen y destino deben ser distintas";

    /** The Constant ACCOUNT_TRANSFER_INVALID_AMOUNT. */
    public static final String ACCOUNT_TRANSFER_INVALID_AMOUNT =
            "EXP-ACCOUNT-003 | El importe de la transferencia debe ser mayor que cero";

    /** The Constant ACCOUNT_TRANSFER_CATEGORY_MISSING. */
    public static final String ACCOUNT_TRANSFER_CATEGORY_MISSING =
            "EXP-ACCOUNT-004 | No se encontró categoría de transferencia en la cuenta";

    /** The Constant ACCOUNT_FORBIDDEN. */
    public static final String ACCOUNT_FORBIDDEN =
            "EXP-ACCOUNT-005 | No tienes permisos para esta operación en la cuenta";

    /** The Constant ACCOUNT_INVITATION_NOT_FOUND. */
    public static final String ACCOUNT_INVITATION_NOT_FOUND =
            "EXP-ACCOUNT-006 | No se encontró la invitación";

    /** The Constant ACCOUNT_INVITATION_INVALID. */
    public static final String ACCOUNT_INVITATION_INVALID =
            "EXP-ACCOUNT-007 | La invitación no es válida o ya no está disponible";

    /** The Constant ACCOUNT_INVITATION_ALREADY_MEMBER. */
    public static final String ACCOUNT_INVITATION_ALREADY_MEMBER =
            "EXP-ACCOUNT-008 | Ya eres miembro de esta cuenta";

    /** The Constant ACCOUNT_MEMBER_NOT_FOUND. */
    public static final String ACCOUNT_MEMBER_NOT_FOUND =
            "EXP-ACCOUNT-009 | No se encontró el miembro de la cuenta";

    /** The Constant ACCOUNT_CANNOT_REMOVE_OWNER. */
    public static final String ACCOUNT_CANNOT_REMOVE_OWNER =
            "EXP-ACCOUNT-010 | No se puede eliminar al propietario de la cuenta";

    /** The Constant ACCOUNT_OWNER_CANNOT_LEAVE. */
    /** The Constant ACCOUNT_DEFAULT_CANNOT_DELETE. */
    public static final String ACCOUNT_DEFAULT_CANNOT_DELETE =
            "EXP-ACCOUNT-012 | No se puede eliminar la cuenta principal. Cambia la cuenta principal antes de borrarla";

    public static final String ACCOUNT_OWNER_CANNOT_LEAVE =
            "EXP-ACCOUNT-011 | El propietario no puede abandonar la cuenta";

    /** The Constant AUTH_EMAIL_ALREADY_EXISTS. */
    public static final String AUTH_EMAIL_ALREADY_EXISTS = "EXP-AUTH-001 | Ya existe un usuario con ese email";

    /** The Constant AUTH_INVALID_CREDENTIALS. */
    public static final String AUTH_INVALID_CREDENTIALS = "EXP-AUTH-002 | Email o contraseña incorrectos";

    /** The Constant AUTH_UNAUTHORIZED. */
    public static final String AUTH_UNAUTHORIZED = "EXP-AUTH-003 | No autenticado";

    /** The Constant INTERNAL_ERROR. */
    public static final String INTERNAL_ERROR = "EXP-GENERAL-001 | Ha ocurrido un error inesperado";

    /** The Constant VALIDATION_ERROR. */
    public static final String VALIDATION_ERROR = "EXP-GENERAL-002 | Error de validación";

    /** The Constant DATA_INTEGRITY_ERROR. */
    public static final String DATA_INTEGRITY_ERROR = "EXP-GENERAL-003 | La operación viola restricciones de datos";

    /** The Constant ANALYTICS_INVALID_DATE_RANGE. */
    public static final String ANALYTICS_INVALID_DATE_RANGE =
            "EXP-ANALYTICS-001 | La fecha de inicio no puede ser posterior a la fecha de fin";

    private ExceptionMessageConstants() {
    }
}
