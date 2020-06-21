package sinacorpdfparser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
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
	
	private Double [] acumulado;
	
	private Parser parser;
	
	private String caminho;
	
	private String senha;
	
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
		pdf2Text = new PDFToText(caminho);
		parser = new Parser(pdf2Text.getText()).extract();
		notasNegociacao = parser.getNotas();	
		return notasNegociacao;
	}
	
	private void gerarRelatorio() throws IOException, TemplateException {
		template = cfg.getTemplate("report.ftl");
        
		Map<String, Object> input = new HashMap<String, Object>();
        input.put("notas", notasNegociacao);
        input.put("sum", acumulado);
        input.put("diahora", Instant.now());
        
        Writer fileWriter = new FileWriter(new File("report.html"));
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

    public String executar() throws IOException, TemplateException {
    	getNotasNegociacao();
    	getAcumulado();
    	gerarRelatorio();
    	StringBuilder resultado = new StringBuilder(NotaNegociacao.getHeader() + "\n");
		for(NotaNegociacao nota : notasNegociacao) {
			resultado.append(nota.toCSV() + "\n");
		}
		return resultado.toString();
    }
	
}
