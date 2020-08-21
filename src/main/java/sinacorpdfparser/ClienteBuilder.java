package sinacorpdfparser;

import java.io.File;
import java.util.List;

public final class ClienteBuilder {
	
	private Parser parser;
	
	private String caminho;
	
	private boolean fonteArquivo;
	
	private boolean fonteDiretorio;
		
	private String senha;
	
	private NotaNegociacao.Tipos tipo;
	
	private List<OpcaoExportacao> opcoesExportacao;
	
	public Parser getParser() {
		return parser;
	}
	
	public ClienteBuilder setParser(Parser parser) {
		this.parser = parser;
		return this;
	}
	
	public String getCaminho() {
		return caminho;
	}

	public ClienteBuilder setCaminho(String caminho) {
		this.caminho = caminho;
		return this;
	}

	public String getSenha() {
		return senha;
	}

	public ClienteBuilder setSenha(String senha) {
		this.senha = senha;
		return this;
	}
	
	public List<OpcaoExportacao> getOpcaoExportacao() {
		return opcoesExportacao;
	}

	public ClienteBuilder setOpcaoExportacao(List<OpcaoExportacao> opcoesExportacao) {
		this.opcoesExportacao = opcoesExportacao;
		return this;
	}
	
	
	public boolean isFonteArquivo() {
		return fonteArquivo;
	}

	public void setFonteArquivo(boolean fonteArquivo) {
		this.fonteArquivo = fonteArquivo;
	}

	public boolean isFonteDiretorio() {
		return fonteDiretorio;
	}

	public void setFonteDiretorio(boolean fonteDiretorio) {
		this.fonteDiretorio = fonteDiretorio;
	}

	public NotaNegociacao.Tipos getTipo() {
		return tipo;
	}

	public void setTipo(NotaNegociacao.Tipos tipo) {
		this.tipo = tipo;
	}

	public Cliente build() {
		Cliente cliente = new Cliente(caminho, fonteArquivo, fonteDiretorio, senha, parser, opcoesExportacao, tipo);
		
		return cliente;
	}
	
	public void validar(Cliente cliente) {
		
		if(fonteArquivo) {
			String subStrings [] = caminho.split(".");
			if(!subStrings[subStrings.length-1].equals("pdf")) {
				throw new IllegalStateException("O nome do arquivo deve ser seguido de .pdf");	
			}
		}
		
		if(fonteDiretorio) {
			String subStrings [] = caminho.split(".");
			if(!subStrings[subStrings.length-1].equals("pdf")) {
				throw new IllegalStateException("O caminho do diretório deve terminar com " + File.separatorChar);	
			}
		}
		
	}

}
