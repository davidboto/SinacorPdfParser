package sinacorpdfparser;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Exporta os dados da notas de negociação na bovespa e bmf.
 */

public final class CsvExporter {

	public static String export(ArrayList<NotaNegociacao> notas) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder content = new StringBuilder();
		int qtdCampos = 0;
		
		/** Conteúdo das notas **/
		for(NotaNegociacao nota : notas) {
			if(nota.getClass().getDeclaredFields().length != qtdCampos) {
				qtdCampos = nota.getClass().getDeclaredFields().length;
				content.append(getHeader(nota));
			}
			content.append(nota.toCSV());
			content.append("\n");
		}
		
		return content.toString();
	}
	
	public static String getHeader(NotaNegociacao nota) {
		StringBuilder header = new StringBuilder();
		Field[] fields = nota.getClass().getDeclaredFields();
		String prefix = "";
		for (Field field : fields) {
			header.append(prefix + field.getName());
			prefix = ";";
		}
		header.append("\n");
		return header.toString();
	}
	
}
