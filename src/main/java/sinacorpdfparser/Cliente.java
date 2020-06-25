package sinacorpdfparser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class Cliente {
	
	private static Configuration cfg;
	
	private Template template;

	private	PDFToText pdf2Text = new PDFToText();
	
	private ArrayList<NotaNegociacao> notasNegociacao;
	
	private ArrayList<NotaNegociacaoBovespa> notasNegociacaoBovespa;
	
	private Double [] acumulado;
	
	private Parser parser;
	
	private String caminho;
	
	private String senha;
	
	private final static String OUTPUT_FOLDER = "output";
	
	static { 
		cfg = new Configuration();
    	cfg.setClassForTemplateLoading(App.class, "/");
    	cfg.setDefaultEncoding("UTF-8");
    	cfg.setLocale(Locale.US);
    	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}
	
	Cliente(String caminho){
		this.caminho = caminho;
	}
 	
	Cliente(String caminho, String senha){
		this.caminho = caminho;
		this.senha = senha;
	}
	
	private ArrayList<NotaNegociacao> getNotasNegociacao() throws IOException {
		pdf2Text = new PDFToText(caminho, senha);
		parser = new Parser(pdf2Text.getText()).extract();
		notasNegociacao = parser.getNotas();
		notasNegociacaoBovespa = parser.getNotasBovespa();
		return notasNegociacao;
	}
	
	private void gerarRelatorio() throws IOException, TemplateException {
		template = cfg.getTemplate("report.ftl");
        
		Map<String, Object> input = new HashMap<String, Object>();
        input.put("notas", notasNegociacao);
        input.put("sum", acumulado);
        input.put("retorno", getTotal());
        input.put("diahora", Instant.now());
        
        Writer fileWriter = new FileWriter(new File(OUTPUT_FOLDER + "/relatorio-" + Instant.now().getEpochSecond() + ".html"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }
	}
	
	private void getAcumulado() {
		acumulado = new Double[notasNegociacao.size()]; 
		acumulado[0] = notasNegociacao.get(0).getTotalLiquidoDaNota();
		for(int i = 1; i < notasNegociacao.size(); i++)
			acumulado[i] = acumulado[i-1] + (notasNegociacao.get(i)).getTotalLiquidoDaNota();
	}
	
	private void exportarJson() throws IllegalArgumentException, IllegalAccessException, IOException{
		Writer fileWriter = new FileWriter(new File(OUTPUT_FOLDER + "/notasNegociacao-" + Instant.now().getEpochSecond() + ".json"));
		fileWriter.append(new Exporter().toJson(notasNegociacao));
		fileWriter.close();
	}
	
	public Double getTotal() {
		return notasNegociacao.stream().map(e -> e.getTotalLiquidoDaNota()).reduce(0.0, (x, y) -> x + y );
	}

    public String executar() throws IOException, TemplateException, IllegalArgumentException, IllegalAccessException {
    	getNotasNegociacao();
    	
		if(notasNegociacao == null || notasNegociacao.isEmpty()) {
			 System.out.println(
					 "Não foi possível extrair os campos da(s) nota(s) informada(s). \n"
					+ "Padrão suportado: nota de corretagem de operações na BM&F.");
		} else {
	    	getAcumulado();
	    	gerarRelatorio();
	    	exportarJson();
	    	System.out.println(new Exporter().toCSV(notasNegociacao));
		} 
		 	
    	if(notasNegociacaoBovespa != null && !notasNegociacaoBovespa.isEmpty()) { 
    		System.out.println(new Exporter().toCSVBovespa(notasNegociacaoBovespa));
    	}
    	
    	return "";
    }
	
}
