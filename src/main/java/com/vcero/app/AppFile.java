package com.vcero.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.vcero.app.exception.NotFoundException;
import com.vcero.app.type.TypeEntity;
import com.vcero.app.type.TypeErrorArgs;
import com.vcero.entity.Documentacion;
import com.vcero.entity.TramitePersona;
import com.vcero.entity.view.VDocumentacion;
import com.vcero.model.CiudadanoDoc;
import com.vcero.model.ValidarDoc;

public class AppFile {

	// APP
	private static String PATH_SO = (System.getProperty("os.name").toLowerCase().contains("win") ? "C:/" : "/");
	private static String PATH_APP = PATH_SO + "app/vcero/";
	//
	private static String PATH_PERSONA = PATH_APP + "persona/";

	public static TramitePersona saveDocumentacion(TramitePersona tramitePersona, List<CiudadanoDoc> documentos)
			throws IOException {
		String pathTramite = PATH_PERSONA + tramitePersona.getIdpersona() + "/" + tramitePersona.getIdtramite() + "/";
		File directorio = new File(pathTramite);
		if (!directorio.exists()) {
			if (!directorio.mkdirs()) {
				throw new RuntimeException("Error al guardar los archivos (Folder)");
			}
		}
		for (int i = 0; i < documentos.size(); i++) {
			CiudadanoDoc archivo = documentos.get(i);
			if (archivo.getFiledoc() != null) {
				Documentacion documentacion = tramitePersona.getDocumentacion().stream()
						.filter(d -> d.getIddoc() == archivo.getIddoc()).findFirst().orElse(null);	
				try {
					byte[] bytes = archivo.getFiledoc().getBytes();
					String ext = AppFile.getExtencionDocumento(archivo.getFiledoc());
					documentacion.setNombrefile(documentacion.getIddoctcion() + "." + ext);
					Files.write(Paths.get(pathTramite + documentacion.getNombrefile()), bytes);
				} catch (IOException e) {
					throw new RuntimeException("Error al guardar los archivos");
				}
			}
		}
		return tramitePersona;
	}

	public static TramitePersona updateDocumentacion(TramitePersona tramitePersona, List<ValidarDoc> documentos)
			throws IOException {
		String pathTramite = PATH_PERSONA + tramitePersona.getIdpersona() + "/" + tramitePersona.getIdtramite() + "/";
		File directorio = new File(pathTramite);
		if (!directorio.exists()) {
			if (!directorio.mkdirs()) {
				throw new RuntimeException("Error al guardar los archivos (Folder)");
			}
		}
		for (int i = 0; i < documentos.size(); i++) {
			ValidarDoc archivo = documentos.get(i);
			if (archivo.getFiledoc() != null) {
				Documentacion documentacion = tramitePersona.getDocumentacion().stream()
						.filter(d -> d.getIddoc() == archivo.getIddoc()).findFirst().orElse(null);	
				try {
					byte[] bytes = archivo.getFiledoc().getBytes();
					String ext = AppFile.getExtencionDocumento(archivo.getFiledoc());
					documentacion.setNombrefile(documentacion.getIddoctcion() + "." + ext);
					Files.write(Paths.get(pathTramite + documentacion.getNombrefile()), bytes);
				} catch (IOException e) {
					throw new RuntimeException("Error al guardar los archivos");
				}
			}
		}
		return tramitePersona;
	}

	private static String getExtencionDocumento(MultipartFile imagen) {
		String extencion = StringUtils.getFilenameExtension(imagen.getOriginalFilename());
		switch (extencion) {
		case "pdf":
			return extencion;
		default:
			throw new RuntimeException(TypeErrorArgs.ERR_FILE_EXT_INVALID_x.msg(extencion));
		}
	}

	public static byte[] toByteArrayDocumentacion(VDocumentacion doc) {
		if (Objects.isNull(doc.getNombredoc())) {
			throw new NotFoundException(TypeEntity.DOCUMENTO);
		}
		try {
			String pathTramite = PATH_PERSONA + doc.getIdpersona() + "/" + doc.getIdtramite() + "/"
					+ doc.getNombrefile();
			return IOUtils.toByteArray(new FileInputStream(pathTramite));
		} catch (IOException e) {
			throw new NotFoundException(TypeEntity.DOCUMENTO);
		}
	}

}
