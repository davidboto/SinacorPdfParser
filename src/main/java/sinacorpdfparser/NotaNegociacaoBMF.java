package sinacorpdfparser;

import java.lang.reflect.Field;

public class NotaNegociacaoBMF extends NotaNegociacao {

//	private String DataPregao;
	private String NrNotaNegociacao;
	private Double VendaDisponivel;
	private Double CompraDisponivel;
	private Double VendaOpcoes;
	private Double CompraOpcoes;
	private Double ValorDosNegocios;
	private Double IRRF;
	private Double IRRFDayTrade;
	private Double TaxaOperacional;
	private Double TaxaRegistroBMF;
	private Double TaxaBMF;
	private Double OutrosCustos;
	private Double Impostos;
	private Double AjusteDePosicao;
	private Double AjusteDayTrade;
	private Double TotalDeCustosOperacionais;
	private Double Outros;
	private Double IRRFOpercaional;
	private Double TotalContaInvestimento;
	private Double TotalContaNormal;
	private Double TotalLiquido;
	private Double TotalLiquidoDaNota;

	public NotaNegociacaoBMF() {
	}

//	public String getDataPregao() {
//		return DataPregao;
//	}
//
//	public void setDataPregao(String dataPregao) {
//		DataPregao = dataPregao;
//	}

	public String getNrNotaNegociacao() {
		return NrNotaNegociacao;
	}

	public void setNrNotaNegociacao(String nrNotaNegociacao) {
		NrNotaNegociacao = nrNotaNegociacao;
	}
	
	public Double getVendaDisponivel() {
		return VendaDisponivel;
	}

	public void setVendaDisponivel(Double vendaDisponivel) {
		VendaDisponivel = vendaDisponivel;
	}

	public Double getCompraDisponivel() {
		return CompraDisponivel;
	}

	public void setCompraDisponivel(Double compraDisponivel) {
		CompraDisponivel = compraDisponivel;
	}

	public Double getVendaOpcoes() {
		return VendaOpcoes;
	}

	public void setVendaOpcoes(Double vendaOpcoes) {
		VendaOpcoes = vendaOpcoes;
	}

	public Double getCompraOpcoes() {
		return CompraOpcoes;
	}

	public void setCompraOpcoes(Double compraOpcoes) {
		CompraOpcoes = compraOpcoes;
	}

	public Double getValorDosNegocios() {
		return ValorDosNegocios;
	}

	public void setValorDosNegocios(Double valorDosNegocios) {
		ValorDosNegocios = valorDosNegocios;
	}

	public Double getIRRF() {
		return IRRF;
	}

	public void setIRRF(Double iRRF) {
		IRRF = iRRF;
	}

	public Double getIRRFDayTrade() {
		return IRRFDayTrade;
	}

	public void setIRRFDayTrade(Double iRRFDayTrade) {
		IRRFDayTrade = iRRFDayTrade;
	}

	public Double getTaxaOperacional() {
		return TaxaOperacional;
	}

	public void setTaxaOperacional(Double taxaOperacional) {
		TaxaOperacional = taxaOperacional;
	}

	public Double getTaxaRegistroBMF() {
		return TaxaRegistroBMF;
	}

	public void setTaxaRegistroBMF(Double taxaRegistroBMF) {
		TaxaRegistroBMF = taxaRegistroBMF;
	}

	public Double getTaxaBMF() {
		return TaxaBMF;
	}

	public void setTaxaBMF(Double taxaBMF) {
		TaxaBMF = taxaBMF;
	}

	public Double getOutrosCustos() {
		return OutrosCustos;
	}

	public void setOutrosCustos(Double outrosCustos) {
		OutrosCustos = outrosCustos;
	}

	public Double getImpostos() {
		return Impostos;
	}

	public void setImpostos(Double impostos) {
		Impostos = impostos;
	}

	public Double getAjusteDePosicao() {
		return AjusteDePosicao;
	}

	public void setAjusteDePosicao(Double ajusteDePosicao) {
		AjusteDePosicao = ajusteDePosicao;
	}

	public Double getAjusteDayTrade() {
		return AjusteDayTrade;
	}

	public void setAjusteDayTrade(Double ajusteDayTrade) {
		AjusteDayTrade = ajusteDayTrade;
	}

	public Double getTotalDeCustosOperacionais() {
		return TotalDeCustosOperacionais;
	}

	public void setTotalDeCustosOperacionais(Double totalDeCustosOperacionais) {
		TotalDeCustosOperacionais = totalDeCustosOperacionais;
	}

	public Double getOutros() {
		return Outros;
	}

	public void setOutros(Double outros) {
		Outros = outros;
	}

	public Double getIRRFOpercaional() {
		return IRRFOpercaional;
	}

	public void setIRRFOpercaional(Double iRRFOpercaional) {
		IRRFOpercaional = iRRFOpercaional;
	}

	public Double getTotalContaInvestimento() {
		return TotalContaInvestimento;
	}

	public void setTotalContaInvestimento(Double totalContaInvestimento) {
		TotalContaInvestimento = totalContaInvestimento;
	}

	public Double getTotalContaNormal() {
		return TotalContaNormal;
	}

	public void setTotalContaNormal(Double totalContaNormal) {
		TotalContaNormal = totalContaNormal;
	}

	public Double getTotalLiquido() {
		return TotalLiquido;
	}

	public void setTotalLiquido(Double totalLiquido) {
		TotalLiquido = totalLiquido;
	}

	public Double getTotalLiquidoDaNota() {
		return TotalLiquidoDaNota;
	}

	public void setTotalLiquidoDaNota(Double totalLiquidoDaNota) {
		TotalLiquidoDaNota = totalLiquidoDaNota;
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

	@Override
	public Double getTotal() {
		return TotalLiquidoDaNota;
	}

}
