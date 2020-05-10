# Correios San Andreas

## Definição do Problema  
  
O problema foi modelado utilizando a abordagem de *grafos*, onde cada cidade é equivalente a um vértice e a lista de trechos entre as cidades são equivalentes às arestas do grafo.  
  
O problema de menor caminho em um grafo é um problema clássico da computação que consiste em determinar o menor caminho entre um vértice inicial `s ∈ V` e todos os demais vértices de V. Para resolvê-lo, foi escolhido o **algoritmo de Dijkstra**, concebido pelo cientista da computação holandês Edsger Dijkstra em 1956, soluciona o problema do caminho mais curto num grafo dirigido ou não dirigido com arestas de peso não negativo*, em tempo computacional `O(m + n log n)` onde m é o número de arestas e n é o número de vértices.  
  
**Por se tratar de um problema de distâncias entre cidades, foi assumido que não existem arestas de peso negativo no grafo resultante da modelagem do problema.*  
  
## Padrão de Projeto  
  
Foi utilizado o modelo arquitetural **Domain-driven Design (DDD)**, mas pelo fato do escopo do projeto ser pequeno, foi implementada a classe `ControllerDeEncomendas` dentro do pacote `controller`, onde o mesmo é o responsável por realizar a ação do problema.  
Os dtos `cidade` e `encomenda` refletem o domínio do problema.  
O pacote `utils` contém o arquivo utilitário para manejo dos arquivos de entrada e do arquivo de saída.  
  
## Instruções de Execução  
  
1. Importe o projeto para sua IDE de preferência (IntelliJ IDEA 2018.3.5 (Community Edition) foi utilizada no desenvolvimento);  
  
2. Os arquivos **encomendas.txt** e **trechos.txt** encontram-se dentro da pasta **src/main/resources** e o arquivo **rotas.txt** também é gerado neste diretório;  
  
3. Execute o método `main` da classe **Main**.

## Testes  
  
Foram criados testes de unidade utilizando ***JUnit 4.12*** para as classes `ControllerDeEncomendas` e `FileUtils` para cobrir os casos de sucesso e possíveis casos de erro pensados. As classes de teste se encontram no diretório **src/test/java**.
