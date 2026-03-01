# feature: criacao de api

Vamos usar mysql. como banco de dados da aplicação, crie a configuração para uso em um servidor local.
Criar uma api que faça as operações de inclusão, exclusão, consulta e atualização de dados de clientes.
Deve seguir o padrão controller > service > domain > repository
Use um arquivo docker com uam imagem do mysql para que o desenvolvedor execeute a apliacação localmente.


## requisitos
- devemos criar a capacidade de salvar, alterar, apagar e consultar os clientes
- não deve existir clientes com o mesmo documento
- retornar 404 para clientes não encontrados, usando ExceptionHandler
- a entrada de dados deve validar campos nulos e em branco

## regras de negócio
- cpf e cnpj devem seguir as regras de validação brasileira

## critérios de aceitação
- parametros devem ser final
- testes unitários para criação de usuários e validação de campos devem ser criados
- testes unitários das controllers
- testes unitários das services
- testes unitários dos repositories
- dockerfile com imagem de mysql para uso local

