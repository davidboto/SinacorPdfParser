package sinacorpdfparser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.Instant;
import java.util.ArrayList;

import freemarker.template.TemplateException;

public class Cliente {
	
	private final static String separator = File.separator;

	private	PDFToText pdf2Text;
	
	private ArrayList<NotaNegociacao> notasNegociacao;
	
	private Parser parser;
	
	private String caminho;
	
	private String senha;

	private final static String OUTPUT_FOLDER = "output";
	
	Cliente(String caminho, String senha, Parser parser){
		this.caminho = caminho;
		this.senha = senha;
		this.parser = parser;
	}
	
	private ArrayList<NotaNegociacao> getNotasNegociacao() throws IOException {
		notasNegociacao = new ArrayList<NotaNegociacao>();
		pdf2Text = new PDFToText(caminho, senha);
		parser.find(pdf2Text.getText());
		notasNegociacao.addAll(parser.getNotas());
		return notasNegociacao;
	}
	
    public String executar() throws IOException, IllegalArgumentException, IllegalAccessException {
    	getNotasNegociacao();
		exportarJson(notasNegociacao);
		exportarCSV(notasNegociacao);
		if(notasNegociacao == null || notasNegociacao.isEmpty()) {
			 System.out.println(
					 "Não foi possível extrair os campos da(s) nota(s) informada(s). \n"
					+ "Padrões suportados: nota de corretagem de operações na BM&F e Bovespa. \n"
					+ "Testado para as corretoras: Rico e Clear" );
		} else {
			ArrayList<NotaNegociacao> notas = NotaNegociacaoHelper.getNotas(notasNegociacao, NotaNegociacaoBMF.class);
	    	if(!notas.isEmpty()) {
	    		new Relatorio(OUTPUT_FOLDER).gerarRelatorio(NotaNegociacaoHelper.getNotas(notasNegociacao, NotaNegociacaoBMF.class));
	    		
	    	}
		} 
		System.out.println(new Exporter().toCSV(notasNegociacao));
    	return "";
    }
    
	private void exportarJson(ArrayList<NotaNegociacao> notas) throws IllegalArgumentException, IllegalAccessException, IOException{
		Writer fileWriter = new FileWriter(new File(OUTPUT_FOLDER + separator + "notasNegociacao-" + Instant.now().getEpochSecond() + ".json"));
		fileWriter.append(new Exporter().toJson(notas));
		fileWriter.close();
	}
	
	private void exportarCSV(ArrayList<NotaNegociacao> notas) throws IllegalArgumentException, IllegalAccessException, IOException{
		Writer fileWriter = new FileWriter(new File(OUTPUT_FOLDER + separator + "notasNegociacao-" + Instant.now().getEpochSecond() + ".csv"));
		fileWriter.append(new Exporter().toCSV(notasNegociacao));
		fileWriter.close();
	}
	
}
