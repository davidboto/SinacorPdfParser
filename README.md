# Sinacor PDF Parser

Objetivo: Extrair os principais campos de uma ou mais notas de corretagem e exibi-los em um dado formato (até o momento, .csv, .json). 

Limitações: Por ora, limita-se as notas de corretagem de operações na BM&F.

Testado com notas emitidas pelas corretoras Clear e Rico.

## Passos necessários

1. Clonar este repostório:
```shell
git clone git@github.com:davidboto/SinacorPDFParser.git
```

2. Build:
```shell
gradle shadowJar
```

3. Executar:
```shell
java -jar build/libs/SinacorPDFParser-all.jar --arquivo <arquivo>
```

3.1 Executar (arquivo protegido com senha):
```shell
java -jar build/libs/SinacorPDFParser-all.jar --arquivo <arquivo> --senha <senha>
```

3.2 Executar contra um diretório (arquivos protegido com senha):
```shell
java -jar build/libs/SinacorPDFParser-all.jar --arquivo <arquivo> --senha <senha>
```
* Assume que todos os arquivos são protegidos com a mesma casa.

4. Outros parâmetros

-json ou -csv para salvar as informações em arquivo .json ou .csv, respectivamente.

## Resultado

Os arquivos gerados após a execução podem ser encontrados em:
```shell
output/
```

<h3 align="center">
  <a href="https://github.com/davidboto/SinacorPdfParser/blob/master/.assets/img01_report.jpg?raw=true">
  <img src="https://github.com/davidboto/SinacorPdfParser/blob/master/.assets/img01_report.jpg?raw=true" alt="fastlane Logo" width="800">
  </a>
</h3>

<h3 align="center">
  <a href="https://github.com/davidboto/SinacorPdfParser/blob/master/.assets/img02_report.jpg?raw=true">
  <img src="https://github.com/davidboto/SinacorPdfParser/blob/master/.assets/img02_report.jpg?raw=true" alt="fastlane Logo" width="800">
  </a>
</h3>


### TODO

- [ ] Saída em .XML
- [x] Plotar grafico em relatório
- [x] operações como sum(), avg(), cumulative_sum()
- [x] Permitie que mais de um arquivo seja lido por vez (e.g., informando caminho para diretório ao invés de somente um único arquivo)
- [ ] exibir demais campos da nota de negociação no relatório geral.
- [x] nota de corretagem de operações na Bovespa
- [x] nota de corretagem de operações na BM&F
