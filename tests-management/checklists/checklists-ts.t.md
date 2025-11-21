---
id: TS-REL-001
title: Checklist – Relatórios e Testes Automáticos (Unitários)
description: Suite de validação para garantir a execução correta de testes unitários, geração de relatórios e integração com pipeline CI.
tags:
  - regressão
  - unit-tests
  - coverage
  - reports
owner: LEYYYY
---

# Checklist – Relatórios e Testes Automáticos (Unitários)

## Objetivo
Garantir que o sistema Battleship possui testes unitários adequados, que são executados
corretamente localmente e no pipeline GitHub Actions, incluindo a criação de relatórios de
cobertura em HTML.

---

## Checklist Principal

###  Preparação
- [ ] Confirmar que o projeto compila sem erros
- [ ] Atualizar dependências do POM caso necessário
- [ ] Verificar que o ambiente TMS está funcional no IntelliJ

---

###  Execução de Testes
- [ ] Executar todos os testes unitários via IntelliJ
- [ ] Confirmar que não existem falhas
- [ ] Validar que o comportamento das classes está alinhado com os requisitos
- [ ] Confirmar que os testes cobrem cenários positivos e negativos

---

###  Cobertura de Código
- [ ] Executar **Run with Coverage**
- [ ] Validar métricas: Class, Methods, Line e Branch
- [ ] Melhorar os testes se o valor estiver abaixo do recomendado
- [ ] Gerar relatório HTML com **Generate Coverage Report**
- [ ] Guardar relatório em `reports/tests/LEYYYY-coverage-report/`

---

###  Integração com CI (GitHub Actions)
- [ ] Realizar _Commit & Push_ do branch
- [ ] Confirmar execução automática do workflow
- [ ] Verificar que todos os testes passaram no CI
- [ ] Validar que o relatório de testes aparece no histórico do pipeline

---

## (Opcional) Testes Adicionais Sugeridos
- [ ] Criar testes de integração simples
- [ ] Criar testes de regressão (repetir cenários corrigidos)
- [ ] Adicionar testes de fronteira (limites do tabuleiro, inputs inválidos, etc.)

---

## Critérios de Aceitação
- Todos os testes unitários passam com sucesso
- Cobertura mínima atingida
- Relatório HTML corretamente criado e armazenado
- Pipeline CI executa sem falhas
- Test Suite aprovada pelo grupo

---

## Notas
- Esta suite será reutilizada em futuras versões do projeto Battleship
- Pode ser expandida para incluir testes de UI ou integração  
