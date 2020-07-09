package sinacorpdfparser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class Cliente {
	
	private static Configuration config;
	
	private final static String separator = File.separator;
	
	private Template template;

	private	PDFToText pdf2Text;
	
	private ArrayList<NotaNegociacao> notasNegociacao;
	
	private Double [] acumulado;
	
	private Parser parser;
	
	private String caminho;
	
	private String senha;
	
	private final static String OUTPUT_FOLDER = "output";
	
	private final static String TEMPLATE_FREEMARKER = "report.ftl";
	
	static { 
		config = new Configuration();
		config.setClassForTemplateLoading(App.class, separator);
		config.setDefaultEncoding("UTF-8");
		config.setLocale(Locale.US);
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

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
	
	private void gerarRelatorio(ArrayList<NotaNegociacao> notas) throws IOException, TemplateException {
		template = config.getTemplate(TEMPLATE_FREEMARKER);
		Map<String, Object> input = new HashMap<String, Object>();
		getAcumulado(NotaNegociacaoHelper.getNotas(notasNegociacao, NotaNegociacaoBMF.class));
        input.put("notas", notas);
        input.put("sum", acumulado);
        input.put("retorno", getTotal(notas));
        input.put("diahora", Instant.now());
        
        Writer fileWriter = new FileWriter(new File(OUTPUT_FOLDER + separator + "relatorio-" + Instant.now().getEpochSecond() + ".html"));
        try {
            template.process(input, fileWriter);
        } finally {
            fileWriter.close();
        }
	}
	
	private void getAcumulado(ArrayList<NotaNegociacao> notas) {
		acumulado = new Double[notas.size()]; 
		acumulado[0] = ((NotaNegociacaoBMF) notas.get(0)).getTotalLiquidoDaNota();
		for(int i = 1; i < notas.size(); i++)
			acumulado[i] = acumulado[i-1] + ((NotaNegociacaoBMF) notas.get(i)).getTotalLiquidoDaNota();
	}
	
	public Double getTotal(List<NotaNegociacao> notas) {
		return notas.stream().map(e -> ((NotaNegociacaoBMF) e).getTotalLiquidoDaNota()).reduce(0.0, (x, y) -> x + y );
	}

    public String executar() throws IOException, TemplateException, IllegalArgumentException, IllegalAccessException {
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
	    		gerarRelatorio(NotaNegociacaoHelper.getNotas(notasNegociacao, NotaNegociacaoBMF.class));
	    		
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
