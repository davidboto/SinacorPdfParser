package sinacorpdfparser;

import java.lang.reflect.Field;

public class NotaNegociacaoBovespa {
	
	private Double vendaAVista;
	private Double valorDasOperacoes;
	
	public Double getVendaAVista() {
		return vendaAVista;
	}
	public void setVendaAVista(Double vendaAVista) {
		this.vendaAVista = vendaAVista;
	}
	public Double getValorDasOperacoes() {
		return valorDasOperacoes;
	}
	public void setValorDasOperacoes(Double valorDasOperacoes) {
		this.valorDasOperacoes = valorDasOperacoes;
	}

	public String toCSV() {
		StringBuilder objectContent = new StringBuilder();
		Field[] fields = this.getClass().getDeclaredFields();
		String prefix = "";
		for (Field f : fields) {
			try {
				objectContent.append(prefix + f.get(this));
				prefix = ";";
			} catch (IllegalAccessException ex) {
				System.err.println(ex);
			}
		}
		return objectContent.toString();
	}
}
