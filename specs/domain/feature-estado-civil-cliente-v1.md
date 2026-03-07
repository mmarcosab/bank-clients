# Feature: adicionar estado civil à entidade cliente

## Objetivo
Adicionar o atributo **estado civil** à entidade de cliente para permitir o registro, persistência e consulta dessa informação de forma padronizada e validada.

## Contexto
Atualmente a entidade cliente não contempla o estado civil. A feature deve evoluir o domínio e os fluxos de cadastro/atualização para suportar esse novo dado sem quebrar comportamentos existentes. Também deve garantir que nome da mãe siga como requisito obrigatório já padronizado no domínio.

## Regras de negócio
- Estado civil é um campo obrigatório para cadastro de cliente.
- Estado civil deve ser representado por valores pré-definidos do domínio (sem texto livre).
- Valores aceitos para estado civil:
  - `SOLTEIRO`
  - `CASADO`
  - `DIVORCIADO`
  - `VIUVO`
  - `UNIAO_ESTAVEL`
- Atualização de cliente deve permitir alteração do estado civil para qualquer valor válido.
- Operações de consulta devem retornar o estado civil armazenado.

## Requisitos funcionais
- A entidade cliente deve possuir o atributo `estadoCivil`.
- A criação de cliente deve exigir o preenchimento de `estadoCivil` e `nomeMae`.
- A atualização de cliente deve aceitar `estadoCivil`.
- A API de consulta (individual e lista) deve expor `estadoCivil` no payload de resposta.
- O repositório deve persistir `estadoCivil` no banco de dados.

## Requisitos não funcionais
- Manter compatibilidade com o padrão arquitetural atual (`controller > service > domain > repository`).
- Validações devem gerar mensagens claras e consistentes.
- Implementação deve preservar legibilidade e simplicidade.
- A feature deve possuir cobertura de testes unitários mínima de 90% para os trechos alterados.

## Critérios de aceitação
- Dado um payload de criação com estado civil válido, o cliente é criado com sucesso e o valor é persistido.
- Dado um payload de criação sem `estadoCivil`, a API retorna erro de validação.
- Dado um payload de criação com `estadoCivil` fora da lista permitida, a API retorna erro de validação.
- Dada a atualização de um cliente existente com `estadoCivil` válido, o novo valor é persistido.
- Dada a consulta de cliente(s), o campo `estadoCivil` é retornado corretamente.
- Devem existir testes unitários cobrindo todos os critérios acima.

## Casos de erro
- `estadoCivil` nulo.
- `estadoCivil` em branco.
- `estadoCivil` com valor inválido (não mapeado no domínio).
- Tentativa de atualização de `estadoCivil` para valor inválido.

## Fora de escopo
- Alterar regras de validação de CPF/CNPJ.
- Criar novos estados civis além dos definidos nesta especificação.
- Implementar histórico de alterações de estado civil.
- Migrar ou normalizar dados legados fora do atributo adicionado.
