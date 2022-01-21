package com.vcero.app.type;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public enum TypeEntity {

	COLONIA("Colonia", "Colonias", false), //
	EDO_TRAMITE("Estado de Tramite", "Estados de Tramite", true), //
	EJERCICIO("Ejercicio", "Ejercicios", true), //
	REGISTRO("Origen del Registro", "Origenes del Registro", true), //
	ORGANIZACION("Organización", "Organizaciones", false), //
	UNIDAD("Unidad", "Unidades", false), //
	//
	ESTADO("Estado", "Estados", true), //
	MUNICIPIO("Municipio", "Municipios", true), //
	CODIGO_POSTAL("Código postal", "Códigos postales", true), //
	USUARIO("Usuario", "Usuarios", true), //
	PERSONA("Persona", "Personas", false), //
	TRAMITE_PERSPONA("Tramite Ciudadano", "Tramites", true), //
	DOCUMENTO("Documento", "Documentos", true), //
	REQUISITOS("Requisito", "Requisitos", true), //
	DOCUMENTACION("Documentación", "Documentación", false), //
	TRAMITE("Tramite", "Tramites", true), //
	VALIDAR_EMAIL("Código", "Códigos", false), //
	
	;

	private String singular;
	private String plural;
	private Boolean genero;

	TypeEntity(String singular, String plural, Boolean genero) {
		this.singular = singular;
		this.plural = plural;
		this.genero = genero;
	}

	public String singular() {
		return this.singular;
	}

	public String plural() {
		return this.plural;
	}

	private Boolean genero() {
		return this.genero;
	}

	private String articulo() {
		return genero() ? "El" : "La";
	}

	/*
	 * EXISTE
	 */

	public String exists(String id) {
		return singular() + ". La clave ya existe: " + id;
	}

	public String exists(Integer id) {
		return exists(id + "");
	}

	/*
	 * REPETIDO
	 */

	public String repeated(String id) {
		return singular() + ". Clave duplicada: " + id;
	}

	public String repeatedInt(List<Integer> id) {
		Integer size = id.size();
		if (size == 1) {
			return singular() + ". Clave duplicada: " + StringUtils.join(id, ", ");
		} else {
			return plural() + ". Claves duplicadas: " + StringUtils.join(id, ", ");
		}
	}

	public String repeatedString(List<String> id) {
		Integer size = id.size();
		if (size == 1) {
			return singular() + ". Clave duplicada: " + StringUtils.join(id, ", ");
		} else {
			return plural() + ". Claves duplicadas: " + StringUtils.join(id, ", ");
		}
	}

	public String repeated() {
		return singular() + " duplicad" + (genero() ? "o" : "a");
	}

	/*
	 * REQUERIDO
	 */

	public String requiered() {
		return singular() + " requerid" + (genero() ? "o" : "a");
	}

	public String requiered(Enum<?>... id) {
		return singular() + ". Clave requerida: " + StringUtils.join(id, ", ");
	}

	public String requiered(List<Integer> id) {
		Integer size = id.size();
		if (size == 1) {
			return singular() + ". Clave requerida: " + StringUtils.join(id, ", ");
		} else {
			return plural() + ". Claves requeridas: " + StringUtils.join(id, ", ");
		}
	}

	public String requieredStatusTrue(String id) {
		return singular() + ". Clave activa requerida: " + id;
	}

	public String notRequiered() {
		return singular() + " no requerid" + (genero() ? "o" : "a");
	}

	/*
	 * NO ENCONTRADO
	 */

	public String notFound() {
		return singular() + " no encontrad" + (genero() ? "o" : "a");
	}

	public String notFound(String id) {
		return singular() + ". Clave no encontrada: " + id;
	}

	public String notFound(Integer id) {
		return notFound(id + "");
	}

	public String notFound(List<Integer> id) {
		return plural() + ". Clave no encontrada: " + StringUtils.join(id, ", ");
	}

	/*
	 * STATUS
	 */

	public String status(Boolean status, String id) {
		return singular() + ". Clave " + (status ? "ativa" : "dada de baja") + ": " + id;
	}

	public String status(Boolean status, Integer id) {
		return status(status, id + "");
	}

	/*
	 * NO PUEDE CAMBIAR DE STATUS
	 */

	public String cantStatus(Boolean status) {
		return singular() + "  " + (status ? "ser ativada" : "darse de baja");
	}

	/*
	 * Have. "A" tiene x veces a "B"
	 */

	public String have(TypeEntity entity, Long numero) {
		String msg = articulo() + " " + singular() + " cuenta con " + numero + " ";
		return msg + (numero == 1 ? entity.singular() : entity.plural());
	}

	public String have(TypeEntity entity, Long numero, Boolean status) {
		String msg = articulo() + " " + singular() + " cuenta con " + numero + " ";
		if (numero == 1) {
			msg += entity.singular() + " ";
			msg += status ? ("activ" + (genero() ? "o" : "a")) : "en baja";
		} else {
			msg += entity.plural() + " ";
			msg += status ? ("activ" + (genero() ? "os" : "as")) : "en baja";
		}
		return msg;
	}

	public String have(TypeEntity entity, List<Integer> id, Boolean status) {
		String msg = articulo() + " " + singular() + " cuenta con " + id.size() + " ";
		if (id.size() == 1) {
			msg += entity.singular() + " ";
			msg += status ? ("activ" + (genero() ? "o" : "a")) : "en baja";
		} else {
			msg += entity.plural() + " ";
			msg += status ? ("activ" + (genero() ? "os" : "as")) : "en baja";
		}
		return msg + ": " + StringUtils.join(id, ", ");
	}

	/*
	 * Be. "A" esta x veces en "B"
	 */

	public String be(TypeEntity entity, Long numero) {
		String msg = articulo() + " " + singular() + " se encuentra asociado a " + numero + " ";
		return msg + (numero == 1 ? entity.singular() : entity.plural());
	}

	public String be(TypeEntity entity, Long numero, Boolean status) {
		String msg = articulo() + " " + singular() + " se encuentra asociado a " + numero + " ";
		if (numero == 1) {
			msg += entity.singular() + " ";
			msg += status ? ("activ" + (genero() ? "o" : "a")) : "en baja";
		} else {
			msg += entity.plural() + " ";
			msg += status ? ("activ" + (genero() ? "os" : "as")) : "en baja";
		}
		return msg;
	}

	public String be(TypeEntity entity, List<Integer> id, Boolean status) {
		String msg = articulo() + " " + singular() + " se encuentra asociado a " + id.size() + " ";
		if (id.size() == 1) {
			msg += entity.singular() + " ";
			msg += status ? ("activ" + (genero() ? "o" : "a")) : "en baja";
		} else {
			msg += entity.plural() + " ";
			msg += status ? ("activ" + (genero() ? "os" : "as")) : "en baja";
		}
		return msg + ": " + StringUtils.join(id, ", ");
	}

	/*
	 * Not Change. A -> B
	 */

	public String cantChangedOf(TypeEntity e) {
		return articulo() + " " + singular() + " no puede cambiarse de " + e.singular();
	}

	/*
	 * "A" debe ser "B"
	 */
	public String mustBe(Enum<?>... id) {
		return articulo() + " " + singular() + " debe ser: " + StringUtils.join(id, ", ");
	}

	/*
	 * "A" es "B"
	 */
	public String is(Enum<?> id) {
		return articulo() + " " + singular() + " es: " + id;
	}
}
