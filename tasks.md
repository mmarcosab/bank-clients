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
