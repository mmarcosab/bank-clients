# tasks

## feature-dominio-v1
- [x] Criar modelo de domínio para Cliente com nome, identificador, data de nascimento, documento e endereço completo.
- [x] Implementar padrão Factory para criação de Cliente.
- [x] Validar regra de nome com no máximo 60 caracteres.
- [x] Validar documento brasileiro (CPF/CNPJ).
- [x] Criar testes unitários para criação de cliente e validações de campos.

## feature-crud-clientes-v1
- [x] Configurar aplicação para uso de MySQL local.
- [x] Implementar CRUD de clientes no padrão controller > service > domain > repository.
- [x] Garantir unicidade de documento de cliente.
- [x] Retornar 404 para cliente não encontrado via ExceptionHandler.
- [x] Validar entrada com campos nulos e em branco.
- [x] Criar testes unitários para controller, service e repository.
- [x] Disponibilizar arquivo docker para subir MySQL localmente.

## feature-estado-civil-cliente-v1
- [x] Adicionar estado civil ao domínio de Cliente com validação de obrigatoriedade e valores permitidos.
- [x] Persistir estado civil na entidade JPA de cliente.
- [x] Expor estado civil nos contratos da API (request/response).
- [x] Atualizar service/factory para mapear estado civil na criação e atualização.
- [x] Criar testes automatizados cobrindo estado civil válido e inválido.

## feature-nome-mae-cliente-v1
- [x] Adicionar nome da mãe ao domínio de Cliente com validação de obrigatoriedade e limite de 60 caracteres.
- [x] Persistir nome da mãe na entidade JPA de cliente.
- [x] Expor nome da mãe nos contratos da API (request/response).
- [x] Atualizar service/factory para mapear nome da mãe na criação e atualização.
- [x] Criar testes automatizados cobrindo cenário válido e inválido para nome da mãe.
