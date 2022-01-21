package com.vcero.app.type;

public enum TypeError {
	
	ERR_INSTANCE_OF_KEY("La instancia de KEY no es valida. Instancia requerida String o Integer"),
	ERR_HTTP_403("Acceso denegado"), //
	ERR_HTTP_401("Autenticación requerida"), //
	ERR_BEAN_VALIDATION("El formulario presenta valores no validos"), //
	ERR_REQUEST_BODY_NULL ("Solicitud JSON requerida"), //
	ERR_ENTITY_STATUS_ARE_EQUALS("El status debe ser distinto al actual"), //
	ERR_DOCUMENTACION("La cantidad de documentos enviados es difiere de la documentación requerida")
	;

	private String msg;

	TypeError(String msg) {
		this.msg = msg;
	}

	public String msg() {
		return msg;
	}
}
