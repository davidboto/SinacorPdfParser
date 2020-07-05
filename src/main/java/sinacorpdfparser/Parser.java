package sinacorpdfparser;

import java.util.ArrayList;

/**
 * Interface que deve ser implementada pelo parser.
 */

public interface Parser {
	
	ArrayList<NotaNegociacao> getNotas();
	
	Parser find(String text);
	
	
}
