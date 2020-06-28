package sinacorpdfparser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class NotaNegociacaoHelper {

	public static ArrayList<NotaNegociacao> getNotas(ArrayList<NotaNegociacao> notas, Class notaClass ){
		
		List<NotaNegociacao> listaFiltrada = notas.stream()
				.filter(e -> e.getClass() == notaClass)
				.collect(Collectors.toList());
		
		return (ArrayList<NotaNegociacao>) listaFiltrada;		

	}
	
}
