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

public class Relatorio {
	
	private final static String separator = File.separator;
	
	private static Configuration config;
	
	private Template template;
	
	private String outputFolder;

	static { 
		config = new Configuration();
		config.setClassForTemplateLoading(App.class, separator);
		config.setDefaultEncoding("UTF-8");
		config.setLocale(Locale.US);
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}
	
	private final static String TEMPLATE_FREEMARKER = "report.ftl";
	
	public Relatorio(String outputFolder) {
		this.outputFolder = outputFolder;
	}
	
	public void gerarRelatorio(ArrayList<NotaNegociacao> notas) throws IOException {
		template = config.getTemplate(TEMPLATE_FREEMARKER);
		Map<String, Object> input = new HashMap<String, Object>();
        input.put("notas", notas);
        input.put("sum", getAcumulado(notas));
        input.put("retorno", getTotal(notas));
        input.put("diahora", Instant.now());
        
        Writer fileWriter = new FileWriter(new File(outputFolder + separator + "relatorio-" + Instant.now().getEpochSecond() + ".html"));
        
		try {
			template.process(input, fileWriter);
		} catch (TemplateException e) {
			e.printStackTrace();
			System.err.println("[Erro]: Não foi possível encontrar o template." + e.getMessage());
        } finally {
            fileWriter.close();
        }
	}
	
	public Double getTotal(List<NotaNegociacao> notas) {
		return notas.stream().map(e -> ((NotaNegociacaoBMF) e).getTotalLiquidoDaNota()).reduce(0.0, (x, y) -> x + y );
	}

	private Double [] getAcumulado(ArrayList<NotaNegociacao> notas) {
		Double [] acumulado = new Double[notas.size()]; 
		acumulado[0] = ((NotaNegociacaoBMF) notas.get(0)).getTotalLiquidoDaNota();
		for(int i = 1; i < notas.size(); i++)
			acumulado[i] = acumulado[i-1] + ((NotaNegociacaoBMF) notas.get(i)).getTotalLiquidoDaNota();
		return acumulado;
	}
	
}
