package sinacorpdfparser;

public final class ClienteBuilder {
	
	private Parser parser;
	
	private String caminho;
	
	private String senha;
	
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

	public Cliente build() {
		Cliente cliente = new Cliente(caminho, senha, parser);
		return cliente;
	}

}
