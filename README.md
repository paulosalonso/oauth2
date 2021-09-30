# OAuth2 DEMO

## Objetivo

Simular um ambiente com todas as partes envolvidas em um fluxo de autorização com OAuth2.

## Como usar

Executar o script `run.sh` para construir as aplicações e executá-las com Docker. As aplicações abaixo serão executadas:

- `authortization-server` (http://localhost:8081)
  - Aplicação Java responsável por autenticar e autorizar o acesso
- `resource-server` (http://localhost:8080)
  - Aplicação Java responsável por gerenciar os recursos protegidos
- `client JS` (http://localhost:8000)
  - Aplicação JavaScript responsável por acessar os recursos protegidos

Acesse a URL http://localhost:8000 (`client JS`) via web browser. Será exibida uma página simples com um botão de login, um formulário para cadastro de cidades, um botão para listar as cidades cadastradas e um console para visualizar os detalhes das ações.

## Autenticação

Para realizar o login, utilize as credenciais abaixo:

usuário: __user-a__  
senha: __123456__

Ao executar uma ação sem fazer login o `resource-server` responderá com status code __401 - Unauthorized__

## Autorização

Ao fazer login será redirecionado para a página de autorização, onde há 3 escopos para aceite/recusa. Os escopos `read` e `write` tem ação direta no cadastro/listagem de cidades. O escopo `other` é apenas um "workaround", pois ao aceitar todos os escopos a aplicação não é mais redirecionada para a tela de autorização. Dado isso, sempre deixe esse escopo com a opção `Deny`.

### Escopos

- `read`
  - Necessário para listar cidades
- `write`
  - Necessário para cadastrar cidades

Ao executar uma ação sem aceitar o escopo necessário o `resource-server` responderá com status code __403 - Forbidden__ 