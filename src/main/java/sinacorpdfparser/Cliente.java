package sinacorpdfparser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cliente {
	
	private final static String separator = File.separator;

	private	PDFToText pdf2Text;
	
	/** Identifica se as notas devem ser buscadas em um diretório. */
	private boolean fonteDiretorio;
	
	/** Identifica se as notas devem ser buscadas em um arquivo. */
	private boolean fonteArquivo;

	/** Identifica BMF ou Bovespa. */
	private NotaNegociacao.Tipos tipo;

	private ArrayList<NotaNegociacao> notasNegociacao;
	
	private List<OpcaoExportacao> opcoesExportacao;
	
	private Parser parser;
	
	private String caminho;
	
	private String senha;
	
	private long timestampArquivo;
	
	private final static String OUTPUT_FOLDER = "output";
	
	Cliente(String caminho, boolean arquivo, boolean diretorio, String senha, Parser parser, List<OpcaoExportacao> opcoesExportacao, NotaNegociacao.Tipos tipo){
		this.caminho = caminho;
		this.fonteArquivo = arquivo;
		this.fonteDiretorio = diretorio;
		this.senha = senha;
		this.parser = parser;
		this.opcoesExportacao = opcoesExportacao;
		this.timestampArquivo = Instant.now().getEpochSecond();
		this.tipo = tipo;
	}
	
	private ArrayList<NotaNegociacao> getNotasNegociacao() throws IOException {
		notasNegociacao = new ArrayList<NotaNegociacao>();
		if(fonteDiretorio) { 
			Stream<Path> walk = Files.walk(
					Paths.get(caminho), 
					FileVisitOption.FOLLOW_LINKS);
			
			List<String> result = walk.map(
					x -> x.toString())
					.filter(f -> f.endsWith(".pdf")).collect(Collectors.toList());

			result.forEach((filePath) -> {
				try {
					pdf2Text = new PDFToText(filePath, senha);
					parser.find(pdf2Text.getText());	
					notasNegociacao.addAll(parser.getNotas());	
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		if(fonteArquivo) {
			pdf2Text = new PDFToText(caminho, senha);
			parser.find(pdf2Text.getText());	
			notasNegociacao.addAll(parser.getNotas());
		}
		
		Collections.sort(notasNegociacao, (n1, n2) -> orderByData(n1, n2));

		return notasNegociacao;
	}
	
	public static int orderByData(NotaNegociacao n1, NotaNegociacao n2) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			return formatter.parse(n1.getDataPregao()).compareTo(
					formatter.parse(n2.getDataPregao()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

    public String executar() throws IOException, IllegalArgumentException, IllegalAccessException {
    	getNotasNegociacao();
    	
    	if(opcoesExportacao == null) {
    		exportarJson(notasNegociacao);
    		exportarCSV(notasNegociacao);
    	} else if (opcoesExportacao.contains(OpcaoExportacao.JSON)) {
    		exportarJson(notasNegociacao);
    	} else if(opcoesExportacao.contains(OpcaoExportacao.CSV) ) {
    		exportarCSV(notasNegociacao);
    	}
    	
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
		System.out.println("Total de nota(s) encontradas: " + notasNegociacao.size() );
		System.out.println("Arquivos gerados: ");
		

		if(opcoesExportacao == null) {
			System.out.println(OUTPUT_FOLDER + separator + "notasNegociacao-" + tipo.toString() + "-" + timestampArquivo + ".json");
			System.out.println(OUTPUT_FOLDER + separator + "notasNegociacao-" + tipo.toString() + "-" + timestampArquivo + ".csv");
		} else if (opcoesExportacao.contains(OpcaoExportacao.JSON)){
			System.out.println(OUTPUT_FOLDER + separator + "notasNegociacao-" + tipo.toString() + "-" + timestampArquivo + ".json");
		} else if (opcoesExportacao.contains(OpcaoExportacao.CSV)){
			System.out.println(OUTPUT_FOLDER + separator + "notasNegociacao-" + tipo.toString() + "-" + timestampArquivo + ".csv");
		}
    	return "";
    }
    
	private void exportarJson(ArrayList<NotaNegociacao> notas) throws IllegalArgumentException, IllegalAccessException, IOException{
		Writer fileWriter = new FileWriter(new File(OUTPUT_FOLDER + separator + "notasNegociacao-" + tipo.toString() + "-" + timestampArquivo + ".json"));
		fileWriter.append(new Exporter().toJson(notas));
		fileWriter.close();
	}
	
	private void exportarCSV(ArrayList<NotaNegociacao> notas) throws IllegalArgumentException, IllegalAccessException, IOException{
		Writer fileWriter = new FileWriter(new File(OUTPUT_FOLDER + separator + "notasNegociacao-" + tipo.toString() + "-" + timestampArquivo + ".csv"));
		fileWriter.append(new Exporter().toCSV(notasNegociacao));
		fileWriter.close();
	}
	
}
