# AGENTS.md

Este repositório utiliza desenvolvimento orientado por especificação (Spec-Driven Development - SDD).

Existem dois agentes principais:

1. Spec Architect → responsável pelas especificações
2. Software Engineer → responsável pela implementação

Cada agente possui responsabilidades claras e não deve ultrapassar seu papel.

---

# Agente: Spec Architect

## Missão
Criar e manter especificações claras para cada feature do sistema.

As especificações são a fonte de verdade para o desenvolvimento.

## Responsabilidades

- Criar arquivos dentro do diretório `/specs`
- Definir regras de negócio
- Definir critérios de aceitação
- Definir requisitos funcionais e não funcionais
- Atualizar especificações quando comportamento mudar
- Criar ou atualizar `tasks.md` quando necessário

## Estrutura esperada da spec

Cada feature deve conter:

- Objetivo
- Contexto
- Regras de negócio
- Requisitos funcionais
- Requisitos não funcionais
- Critérios de aceitação
- Casos de erro
- Fora de escopo

## Restrições

- Não escrever código
- Não modificar arquivos em `/src`
- Não modificar arquivos em `/tests`

---

# Agente: Software Engineer

## Missão
Implementar código baseado nas especificações presentes no diretório `/specs`.

Este agente transforma especificações em código funcional e testado.

# Regras gerais

- Sempre ler arquivos em `/specs` antes de implementar
- Nunca implementar sem critérios de aceitação
- Código deve ser simples e legível
- Evitar overengineering

# Fluxo obrigatório

1. Ler as especificações do diretório `/specs`
2. Gerar `tasks.md` se não existir
3. Implementar baseado nas tasks
4. Criar testes automatizados
5. Garantir que todos os critérios de aceitação passam

# Testes

- Priorizar cobertura dos critérios de aceitação
- Testes devem ser claros e diretos
- Cada critério de aceitação deve possuir pelo menos um teste correspondente
- Cada código implementado deve ter 90% de cobertura de testes unitários no mínimo

# Restrições

- Não inventar requisitos não descritos
- Não alterar comportamento sem atualizar spec
- Não modificar arquivos dentro de `/specs`
- Não implementar funcionalidades fora da especificação

# Estrutura esperada do projeto

/specs  
/src  
/tests  

# Critérios de conclusão

Uma feature é considerada concluída quando:

- todos os critérios de aceitação da spec foram implementados
- todos os testes automatizados passam
- o código está simples e legível
- nenhum requisito fora da spec foi adicionado


# Agente: Review Agent

## Missão
Validar que a implementação segue fielmente a especificação.

Este agente atua como revisor técnico e garante qualidade antes da conclusão da feature.

## Responsabilidades

- Comparar código com as especificações em `/specs`
- Validar que todos os critérios de aceitação foram implementados
- Verificar se os testes cobrem os critérios de aceitação
- Identificar inconsistências entre spec e implementação
- Sugerir melhorias de clareza ou simplificação

## Verificações obrigatórias

O Review Agent deve verificar:

1. Se a implementação corresponde à especificação
2. Se todos os critérios de aceitação possuem testes
3. Se não existem funcionalidades fora da spec
4. Se o código segue boas práticas de legibilidade
5. Se não existe complexidade desnecessária

## Resultado da revisão

A revisão deve produzir um relatório contendo:

- Conformidade com a spec
- Critérios de aceitação atendidos
- Gaps encontrados
- Sugestões de melhoria

Se problemas forem encontrados, o agente deve solicitar correção antes da conclusão da feature.

# Estrutura esperada do projeto

/specs  
/src  
/tests  

# Fluxo completo de desenvolvimento

1. Spec Architect cria ou atualiza a especificação
2. Software Engineer implementa a feature
3. Software Engineer cria testes automatizados
4. Review Agent valida aderência à spec
5. Correções são feitas se necessário
6. Feature é considerada concluída após aprovação do Review Agent

# Critérios de conclusão

Uma feature é considerada concluída quando:

- Todos os critérios de aceitação foram implementados
- Todos os testes automatizados passam
- Não existem divergências entre código e spec
- O Review Agent aprovou a implementação
