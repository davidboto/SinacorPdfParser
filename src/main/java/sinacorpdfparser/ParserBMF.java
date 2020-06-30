
package sinacorpdfparser;

import java.util.ArrayList;

public class ParserBMF implements Parser {

	private String[] documentLines;

	private ArrayList<NotaNegociacao> notas;

	public ParserBMF(String documentText) {
		this.documentLines = documentText.split("\r\n|\r|\n");
		notas = new ArrayList<NotaNegociacao>();
	}

	public Parser extract() {
		NotaNegociacaoBMF nota = new NotaNegociacaoBMF();
		for (int i = 0; i < documentLines.length; i++) {

			if (documentLines[i].contains("NOTA DE NEGOCIAÇÃO Nr. nota")) {
				nota.setNrNotaNegociacao(documentLines[i + 1].trim());
			}

			if (documentLines[i].contains("Data pregão")) {
				nota.setDataPregao(documentLines[i + 1].trim());
			}

			if (documentLines[i].contains("Valor dos negócios")) {
				if (!documentLines[(i + 1)].trim().isEmpty()) {
					nota.setVendaDisponivel(tokenize(documentLines[(i + 1)], 0));
					nota.setCompraDisponivel(tokenize(documentLines[(i + 1)], 1));
					nota.setVendaOpcoes(tokenize(documentLines[(i + 1)], 2));
					nota.setCompraOpcoes(tokenize(documentLines[(i + 1)], 3));
					nota.setValorDosNegocios(tokenize(documentLines[(i + 1)], 4));
				}
			}

			if (documentLines[i].contains("BM&F Taxas BM&F (emol+f.gar)")) {
				if (!documentLines[(i + 1)].trim().isEmpty()) {
					nota.setIRRF(tokenize(documentLines[(i + 1)], 0));
					nota.setIRRFDayTrade(tokenize(documentLines[(i + 1)], 1));
					nota.setTaxaOperacional(tokenize(documentLines[(i + 1)], 2));
					nota.setTaxaRegistroBMF(tokenize(documentLines[(i + 1)], 3));
					nota.setTaxaBMF(tokenize(documentLines[(i + 1)], 4));
				}
			}

			if (documentLines[i].contains("Total de custos operacionais")) {
				if (!documentLines[(i + 1)].trim().isEmpty()) {
					nota.setOutrosCustos(tokenize(documentLines[(i + 1)], 0));
					nota.setImpostos(tokenize(documentLines[(i + 1)], 1));
					nota.setAjusteDePosicao(tokenize(documentLines[(i + 1)], 2));
					nota.setAjusteDayTrade(tokenize(documentLines[(i + 1)], 3));
					nota.setTotalDeCustosOperacionais(tokenize(documentLines[(i + 1)], 4));
				}
			}

			if (documentLines[i].contains("Conta Normal Total liquido (#) Total líquido da not")) {
				if (!documentLines[(i + 1)].trim().isEmpty()) {
					nota.setOutros(tokenize(documentLines[(i + 1)], 0));
					nota.setIRRFOpercaional(tokenize(documentLines[(i + 1)], 1));
					nota.setTotalContaInvestimento(tokenize(documentLines[(i + 1)], 2));
					nota.setTotalContaNormal(tokenize(documentLines[(i + 1)], 3));
					nota.setTotalLiquido(tokenize(documentLines[(i + 1)], 4));
					nota.setTotalLiquidoDaNota(tokenize(documentLines[(i + 1)], 5));
					notas.add(nota);
					nota = new NotaNegociacaoBMF();
				}
			}
		}
		return this;
	}

	public Double tokenize(String line, int tokenIndex) {
		ArrayList<String> tokens = new ArrayList<String>();
		int begin = 0;
		line = line.trim();
		char priorChar = line.charAt(0);
		for (int i = 0; i < line.length(); i++) {
			char currentChar = line.charAt(i);
			if ((Character.isDigit(currentChar)) && priorChar == ' ') {
				tokens.add((line.substring(begin, i)).trim());
				begin = i;
			}
			if (i + 1 == line.length()) {
				tokens.add((line.substring(begin, i + 1)).trim());
			}
			priorChar = currentChar;
		}
		return extract(tokens.get(tokenIndex));
	}

	public Double extract(String text) {
		String[] textSplitted = text.replace(".", "").replace(",", ".").split("\\|");
		Double amount = Double.parseDouble(textSplitted[0].trim());
		if (textSplitted.length >= 2) {
			amount = (textSplitted[1].trim()).equalsIgnoreCase("D") ? amount * (-1) : amount;
		}
		return amount;
	}

	public ArrayList<NotaNegociacao> getNotas() {
		return notas;
	}

}
