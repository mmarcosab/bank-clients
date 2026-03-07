# feature: criação de domínio
 
## requisitos
- um cliente deve ter seu nome, nome da mãe, estado civil, identificador de cliente, data de nascimento, documento que seja cpf ou cnpj e endereço completo,
- o nome deve ter até 60 caracteres
- o endereço deve ter rua, número, um campo para complemento, bairro, cidade, estado
- a forma de criar um cliente deve ser usando o padrão factory

## regras de negócio
- cpf e cnpj devem seguir as regras de validação brasileira
- estado civil deve aceitar apenas valores pré-definidos do domínio
- nome da mãe é obrigatório e deve ter no máximo 60 caracteres


## critérios de aceitação
- parametros devem ser final
- criação de cliente deve falhar sem nome da mãe
- criação de cliente deve falhar sem estado civil válido
- testes unitários para criação de usuários e validação de campos devem ser criados