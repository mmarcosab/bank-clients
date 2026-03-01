# feature: criação de domínio
 
## requisitos
- um cliente deve ter seu nome, identificador de cliente, data de nascimento, documento que seja  cpf ou cnpj e endereço completo,
- o nome deve ter até 60 caracteres
- o endereço deve ter rua, número, um campo para complemento, bairro, cidade, estado
- a forma de criar um cliente deve ser usando o padrão factory

## regras de negócio
- cpf e cnpj devem seguir as regras de validação brasileira


## critérios de aceitação
- parametros devem ser final
- testes unitários para criação de usuários e validação de campos devem ser criados