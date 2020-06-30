package sinacorpdfparser;

import java.util.ArrayList;

public interface Parser {
	
	ArrayList<NotaNegociacao> getNotas();
	
	Parser extract();
	
}
