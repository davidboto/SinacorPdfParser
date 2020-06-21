# Sinacor PDF Parser

Objetivo: Extrair os principais campos de uma ou mais notas de corretagem e exibi-los em um dado formato (até o momento, .csv).

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
java -jar build/libs/SinacorPDFParser-all.jar <caminho_pdf> 
```

3.1 Executar (arquivo protegido com senha):
```shell
java -jar build/libs/SinacorPDFParser-all.jar <caminho_pdf> <senha>
```

### TODO

- [ ] saída em outros formatos (json, csv, xml, ...)
- [ ] plotar grafico com so resultados
- [ ] operações como sum(), avg(), cumulative_sum()
- [ ] permitir que mais de um arquivo seja lido por vez (e.g., informando caminho para diretório ao invés de somente um único arquivo)
- [ ] exibir demais campos da nota de negociação no relatório geral.