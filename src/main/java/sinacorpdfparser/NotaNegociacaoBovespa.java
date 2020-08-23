package sinacorpdfparser;

import java.lang.reflect.Field;

public class NotaNegociacaoBovespa extends NotaNegociacao {
	
	private String NrNotaNegociacao;
	private Double debentures;
	private Double vendasAVista;
	private Double comprasAVista;
	private Double opcoesCompras;
	private Double opcoesVendas;
	private Double operacoesATermo;
	private Double valorDasOpTitulosPublicos;
	private Double valorDasOperacaoes;
	private Double totalCBLC;
	private Double valorLiquidoDasOperacoes;
	private Double taxaDeLiquidacao;
	private Double taxaDeRegistro;
	private Double totalBovespa;
	private Double taxaDeTermo;
	private Double taxaANA;
	private Double emolumentos;
	private Double totalCustos;
	private Double taxaOperacional;
	private Double execucao;
	private Double taxaDeCustodia;
	private Double imposto;
	private Double IRRF;
	private Double outros;
	private Double liquido;
	
	public String getNrNotaNegociacao() {
		return NrNotaNegociacao;
	}

	public void setNrNotaNegociacao(String nrNotaNegociacao) {
		NrNotaNegociacao = nrNotaNegociacao;
	}
	
	public Double getDebentures() {
		return debentures;
	}

	public void setDebentures(Double debentures) {
		this.debentures = debentures;
	}

	public Double getVendasAVista() {
		return vendasAVista;
	}

	public void setVendasAVista(Double vendasAVista) {
		this.vendasAVista = vendasAVista;
	}

	public Double getComprasAVista() {
		return comprasAVista;
	}

	public void setComprasAVista(Double comprasAVista) {
		this.comprasAVista = comprasAVista;
	}

	public Double getOpcoesCompras() {
		return opcoesCompras;
	}

	public void setOpcoesCompras(Double opcoesCompras) {
		this.opcoesCompras = opcoesCompras;
	}

	public Double getOpcoesVendas() {
		return opcoesVendas;
	}

	public void setOpcoesVendas(Double opcoesVendas) {
		this.opcoesVendas = opcoesVendas;
	}

	public Double getOperacoesATermo() {
		return operacoesATermo;
	}

	public void setOperacoesATermo(Double operacoesATermo) {
		this.operacoesATermo = operacoesATermo;
	}

	public Double getValorDasOpTitulosPublicos() {
		return valorDasOpTitulosPublicos;
	}

	public void setValorDasOpTitulosPublicos(Double valorDasOpTitulosPublicos) {
		this.valorDasOpTitulosPublicos = valorDasOpTitulosPublicos;
	}

	public Double getValorDasOperacaoes() {
		return valorDasOperacaoes;
	}

	public void setValorDasOperacaoes(Double valorDasOperacaoes) {
		this.valorDasOperacaoes = valorDasOperacaoes;
	}

	public Double getTotalCBLC() {
		return totalCBLC;
	}

	public void setTotalCBLC(Double totalCBLC) {
		this.totalCBLC = totalCBLC;
	}

	public Double getValorLiquidoDasOperacoes() {
		return valorLiquidoDasOperacoes;
	}

	public void setValorLiquidoDasOperacoes(Double valorLiquidoDasOperacoes) {
		this.valorLiquidoDasOperacoes = valorLiquidoDasOperacoes;
	}

	public Double getTaxaDeLiquidacao() {
		return taxaDeLiquidacao;
	}

	public void setTaxaDeLiquidacao(Double taxaDeLiquidacao) {
		this.taxaDeLiquidacao = taxaDeLiquidacao;
	}

	public Double getTaxaDeRegistro() {
		return taxaDeRegistro;
	}

	public void setTaxaDeRegistro(Double taxaDeRegistro) {
		this.taxaDeRegistro = taxaDeRegistro;
	}

	public Double getTotalBovespa() {
		return totalBovespa;
	}

	public void setTotalBovespa(Double totalBovespa) {
		this.totalBovespa = totalBovespa;
	}
	
	public Double getTaxaDeTermo() {
		return taxaDeTermo;
	}

	public void setTaxaDeTermo(Double taxaDeTermo) {
		this.taxaDeTermo = taxaDeTermo;
	}

	public Double getTaxaANA() {
		return taxaANA;
	}

	public void setTaxaANA(Double taxaANA) {
		this.taxaANA = taxaANA;
	}

	public Double getEmolumentos() {
		return emolumentos;
	}

	public void setEmolumentos(Double emolumentos) {
		this.emolumentos = emolumentos;
	}

	public Double getTotalCustos() {
		return totalCustos;
	}

	public void setTotalCustos(Double totalCustos) {
		this.totalCustos = totalCustos;
	}

	public Double getTaxaOperacional() {
		return taxaOperacional;
	}

	public void setTaxaOperacional(Double taxaOperacional) {
		this.taxaOperacional = taxaOperacional;
	}

	public Double getExecucao() {
		return execucao;
	}

	public void setExecucao(Double execucao) {
		this.execucao = execucao;
	}

	public Double getTaxaDeCustodia() {
		return taxaDeCustodia;
	}

	public void setTaxaDeCustodia(Double taxaDeCustodia) {
		this.taxaDeCustodia = taxaDeCustodia;
	}

	public Double getImposto() {
		return imposto;
	}

	public void setImposto(Double imposto) {
		this.imposto = imposto;
	}

	public Double getIRRF() {
		return IRRF;
	}

	public void setIRRF(Double iRRF) {
		IRRF = iRRF;
	}

	public Double getOutros() {
		return outros;
	}

	public void setOutros(Double outros) {
		this.outros = outros;
	}

	public Double getLiquido() {
		return liquido;
	}

	public void setLiquido(Double liquido) {
		this.liquido = liquido;
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
		return liquido;
	}
}
