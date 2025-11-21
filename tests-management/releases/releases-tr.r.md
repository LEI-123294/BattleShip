# releases-tr

## Checklist – Relatórios e Testes Automáticos (Unitários)

### (Opcional) Testes Adicionais Sugeridos
* [unknown] Criar testes de integração simples

* [unknown] Criar testes de regressão (repetir cenários corrigidos)

* [unknown] Adicionar testes de fronteira (limites do tabuleiro, inputs inválidos, etc.)


### Checklist Principal

#### Cobertura de Código
* [unknown] Executar **Run with Coverage**

* [unknown] Validar métricas: Class, Methods, Line e Branch

* [unknown] Melhorar os testes se o valor estiver abaixo do recomendado

* [unknown] Gerar relatório HTML com **Generate Coverage Report**

* [unknown] Guardar relatório em `reports/tests/LEYYYY-coverage-report/`


#### Execução de Testes
* [unknown] Executar todos os testes unitários via IntelliJ

* [unknown] Confirmar que não existem falhas

* [unknown] Validar que o comportamento das classes está alinhado com os requisitos

* [unknown] Confirmar que os testes cobrem cenários positivos e negativos


#### Integração com CI (GitHub Actions)
* [unknown] Realizar _Commit & Push_ do branch

* [unknown] Confirmar execução automática do workflow

* [unknown] Verificar que todos os testes passaram no CI

* [unknown] Validar que o relatório de testes aparece no histórico do pipeline


#### Preparação
* [unknown] Confirmar que o projeto compila sem erros

* [unknown] Atualizar dependências do POM caso necessário

* [unknown] Verificar que o ambiente TMS está funcional no IntelliJ


### Critérios de Aceitação
* [unknown] Todos os testes unitários passam com sucesso

* [unknown] Cobertura mínima atingida

* [unknown] Relatório HTML corretamente criado e armazenado

* [unknown] Pipeline CI executa sem falhas

* [unknown] Test Suite aprovada pelo grupo


### Notas
* [unknown] Esta suite será reutilizada em futuras versões do projeto Battleship

* [unknown] Pode ser expandida para incluir testes de UI ou integração  


## Lista de Casos de Teste Unitários

### Testes da aplicação Battleship
* [unknown] Teste da criação do tabuleiro

* [unknown] Teste de posicionamento válido de navios

* [unknown] Teste de posicionamento inválido (sobreposição, fora do tabuleiro)

* [unknown] Teste de registo de tiro num local vazio

* [unknown] Teste de registo de tiro que atinge um navio

* [unknown] Teste de repetição de tiro no mesmo local

* [unknown] Teste da verificação de fim do jogo

* [unknown] Teste da contagem de navios ainda ativos

* [unknown] Teste da leitura de coordenadas inválidas

* [unknown] Teste de inicialização do jogo com dados incorretos


### Testes de Classes Auxiliares
* [unknown] Teste da classe Ship – estados e integridade

* [unknown] Teste da classe Fleet – organização e integridade da frota

* [unknown] Teste da classe Point – coordenadas e igualdade

* [unknown] Teste da classe Game – lógica principal


### Testes de Regressão (opcional)
* [unknown] Teste de validação após correção de posicionamento inválido

* [unknown] Teste de regressão da lógica de tiro duplicado


### Testes relacionados com tarefas específicas
* [unknown] Teste da criação de uma tarefa válida

* [unknown] Teste da tentativa de criar tarefa com parâmetros inválidos

* [unknown] Teste da atualização de estado de uma tarefa

* [unknown] Teste da remoção de uma tarefa existente

* [unknown] Teste da remoção de tarefa inexistente


