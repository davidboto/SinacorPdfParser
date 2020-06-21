package sinacorpdfparser;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.google.gson.Gson;

public class Exporter {

	public String toJson(ArrayList<NotaNegociacao> notasNegociacao) {
		StringBuilder content = new StringBuilder();
		Gson gson = new Gson();
		content.append(gson.toJson(notasNegociacao));
		return content.toString();
	}
	
	public String toCSV(ArrayList<NotaNegociacao> notas) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder content = new StringBuilder();
		Field[] fields = NotaNegociacao.class.getDeclaredFields();
	
		// Cabeçalho: nome do campos
		String prefix = "";
		for (Field field : fields) {
			content.append(prefix + field.getName());
			prefix = ";";
		}
		content.append("\n");
		
		// Conteúdo
		for(NotaNegociacao nota : notas) {
			content.append(nota.toCSV());
			content.append("\n");
		}
		
		return content.toString();
	}
	
}
