package sinacorpdfparser;

public abstract class NotaNegociacao {

	private String DataPregao;
	
	private String NrNotaNegociacao;
	
	protected abstract String toCSV();
	
	public String getDataPregao() {
		return DataPregao;
	}

	public void setDataPregao(String dataPregao) {
		DataPregao = dataPregao;
	}

	public String getNrNotaNegociacao() {
		return NrNotaNegociacao;
	}

	public void setNrNotaNegociacao(String nrNotaNegociacao) {
		NrNotaNegociacao = nrNotaNegociacao;
	}
	
}
