# OAuth2 DEMO

## Objetivo

Simular um ambiente com todas as partes envolvidas em um fluxo de autorização com OAuth2.

## Pré-Requisitos

- Docker

## Como usar

Executar o script `run.sh` para construir as aplicações e executá-las com Docker. As aplicações abaixo serão executadas:

- `mysql` (localhost:3306)
  - O banco de dados é iniciado com um cliente e um usuário
- `authortization-server` (http://localhost:8081)
  - Aplicação Java responsável por autenticar e autorizar o acesso
- `resource-server` (http://localhost:8080)
  - Aplicação Java responsável por gerenciar os recursos protegidos
- `client JS` (http://localhost:8000)
  - Aplicação JavaScript responsável por acessar os recursos protegidos

Acesse a URL http://localhost:8000 (`client JS`) via web browser. Será exibida uma página simples com um botão de login, um formulário para cadastro de cidades, um botão para listar as cidades cadastradas e um console para visualizar os detalhes das ações.

## Autenticação

Para realizar o login, utilize as credenciais abaixo:

Clien ID: __client-a__
Client Secret: __123456__

Usuário: __user-a__  
Senha: __123456__

Ao executar uma ação sem fazer login o `resource-server` responderá com status code __401 - Unauthorized__

## Autorização

Ao fazer login será redirecionado para a página de autorização. Para o cliente __client-a__ existem 2 escopos cadastrados: `read` e `write`.

### Escopos

- `read`
  - Necessário para listar cidades
- `write`
  - Necessário para cadastrar cidades

Ao executar uma ação sem aceitar o escopo necessário o `resource-server` responderá com status code __403 - Forbidden__ 