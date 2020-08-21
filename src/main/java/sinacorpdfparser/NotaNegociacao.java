package sinacorpdfparser;

import java.util.EnumSet;

public abstract class NotaNegociacao implements Operacoes {
	
	private String DataPregao;
	
    public enum Tipos {
        BOVESPA,
        BMF;
        public static final EnumSet<Tipos> NAO_IDENTIFICADO = EnumSet.noneOf(Tipos.class);
    }
	
	protected abstract String toCSV();

	public String getDataPregao() {
		return DataPregao;
	}

	public void setDataPregao(String dataPregao) {
		DataPregao = dataPregao;
	}
	
}
