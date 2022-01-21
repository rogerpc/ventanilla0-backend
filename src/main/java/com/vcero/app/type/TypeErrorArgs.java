package com.vcero.app.type;

public enum TypeErrorArgs {

	ERR_DESERIALIZATION_DATE_x("Formato de fecha requerido: ISO 8601 UTC"), //
	ERR_DESERIALIZATION_BEAN_x("Valor requerido: %s"), //
	//
	ERR_ENTITY_TIPO_INVALID_x("Tipo no valido: %s"), //
	ERR_FILE_EXT_INVALID_x("Extencion no válida: %s"), //
	//
	ERR_QUERY_TYPE_NOT_FOUND_x("El tipo '%s' no existe"), //
	ERR_QUERY_PROPERTY_NOT_FOUND_x("La propiedad '%s' no existe"), //
	ERR_QUERY_OPERATOR_3x("El operador '%s' utilizado en la propiedad '%s' no esta definido para el tipo '%s'"), //
	ERR_QUERY_TYPE_NUMBER_3x("El tipo '%s' utilizado en la propiedad '%s' requiere un número. Valor no válido: '%s'"), //
	ERR_QUERY_TYPE_DATE_3x("El tipo '%s' utilizado en la propiedad '%s' requiere una fecha (ISO 8601 UTC). Valor no válido: '%s'"), //
	ERR_QUERY_TYPE_BOOLEAN_3x(
			"El tipo '%s' utilizado en la propiedad '%s' requiere un booleano (true, false). Valor no válido: '%s'"), //
	ERR_QUERY_OPERATOR_NULL_3x(
			"El operador '%s' utilizado en la propiedad '%s' requiere un booleano (true, false). Valor no válido: '%s'"), //
	ERR_QUERY_OPERATOR_STR_ARRAY_3x("El operador '%s' de la propiedad '%s' requiere una lista. Lista no valida: %s"), //
	ERR_QUERY_ORDER_BY_2x("El operador de ordenamiento '%s' de la propiedad '%s' no esta definido"), //
	;
	
	private String msg;

	TypeErrorArgs(String msg) {
		this.msg = msg;
	}

	public String msg(String argx) {
		return String.format(this.msg, new Object[] { argx });
	}

	public String msg(String argx, String arg2x) {
		return String.format(this.msg, new Object[] { argx, arg2x });
	}

	public String msg(String argx, String arg2x, String arg3x) {
		return String.format(this.msg, new Object[] { argx, arg2x, arg3x });
	}
}
