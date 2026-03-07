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
