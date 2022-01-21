package com.vcero.app;

public class AppRegExp {

	public static final String BARCODE = "^[-A-Za-z0-9]+$"; // Code 128
	public static final String BARCODE_MSG = "requiere solo de letras y numeros, sin acentos ni espacios";

	public static final String CLAVE = "^[A-Z]+$";
	public static final String CLAVE_MSG = "requiere de letras mayúsculas, sin acentos ni espacios";

	public static final String CLAVE_MESA = "^[A-Z0-9]+$";
	public static final String CLAVE_MESA_MSG = "requiere solo de letras mayúsculas y numeros, sin acentos ni espacios";

	public static final String CURP = "^$|" //
			+ "^[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}" //
			+ "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" //
			+ "[HM]{1}" //
			+ "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" //
			+ "[B-DF-HJ-NP-TV-Z]{3}" //
			+ "[0-9A-Z]{1}[0-9]{1}$";
	public static final String CURP_MSG = "CURP valido requerido. Requiere mayúsculas";

	public static final String EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	public static final String EMAIL_MSG = "email valido requerido";

	public static final String PIN = "^[0-9]{4}$";
	public static final String PIN_MSG = "debe tener 4 caracteres numericos";

	public static final String PWD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%&/=?*+-])([a-zA-Z0-9@!#$%&/=?*+-]{8,})$";
	public static final String PWD_MSG = "debe tener al menos 8 caracteres y contar con una letra mayúscula, minúscula, un número y algún carácter especial @!#$%&/=?*+-";

	public static final String RFC = "^$|" //
			+ "^[A-Z]{3,4}" //
			+ "[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" //
			+ "[A-Z0-9]{3}$";
	public static final String RFC_MSG = "RFC valido requerido. Requiere mayúsculas";

	public static final String USERNAME = "^[a-z0-9]+$";
	public static final String USERNAME_MSG = "requiere solo de letras minúsculas y numeros";

}
