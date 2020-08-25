package sinacorpdfparser;

import java.util.ArrayList;

import com.google.gson.Gson;

/**
 * Exporta os dados da notas de negociação na bovespa e bmf.
 */

public final class JsonExporter {

	public static String export(ArrayList<NotaNegociacao> notasNegociacao) {
		StringBuilder content = new StringBuilder();
		Gson gson = new Gson();
		content.append(gson.toJson(notasNegociacao));
		return content.toString();
	}
	
}
