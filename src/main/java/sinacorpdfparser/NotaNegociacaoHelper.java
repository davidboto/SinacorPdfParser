package sinacorpdfparser;

import java.util.ArrayList;
import java.util.Iterator;

public class NotaNegociacaoHelper {

	public static ArrayList<NotaNegociacao> getNotas(ArrayList<NotaNegociacao> notas, Class notaClass ){
		
		Iterator<NotaNegociacao> it = notas.iterator();
		while(it.hasNext()) {
			NotaNegociacao nova = it.next();
			if(nova.getClass() != notaClass) {
				it.remove();
			}
		}
		return notas;
		
	}
	
}
