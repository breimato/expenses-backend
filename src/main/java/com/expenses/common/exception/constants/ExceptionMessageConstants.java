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

    /** The Constant RECURRING_TEMPLATE_NOT_FOUND. */
    public static final String RECURRING_TEMPLATE_NOT_FOUND = "EXP-RECURRING-001 | No se encontró la plantilla recurrente";

    /** The Constant PROFILE_NOT_FOUND. */
    public static final String PROFILE_NOT_FOUND = "EXP-PROFILE-001 | No se encontró el perfil";

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
    private ExceptionMessageConstants() {
    }
}
