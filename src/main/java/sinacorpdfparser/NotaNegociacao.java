package sinacorpdfparser;

import java.util.EnumSet;

public abstract class NotaNegociacao {
	
    public enum Tipos {
        BOVESPA,
        BMF;
        public static final EnumSet<Tipos> NAO_IDENTIFICADO = EnumSet.noneOf(Tipos.class);
    }
	
	protected abstract String toCSV();

	
}
