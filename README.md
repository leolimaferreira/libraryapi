# 📚 Library API

Bem-vindo ao **Library API**, um projeto desenvolvido com **Spring Boot** para gerenciar uma biblioteca com funcionalidades de autenticação e autorização utilizando **OAuth2** e **JWT**. 🚀  
Este projeto foi desenvolvido com **Java 21**, **PostgreSQL** e inclui um **Dockerfile** para facilitar o deploy na nuvem.

## 🛠️ Tecnologias Utilizadas
- **Java 21** 💻
- **Spring Boot** 🌱
- **Spring Security (OAuth2 & JWT)** 🔒
- **Spring Data JPA** 🗄️
- **PostgreSQL** 🛢️
- **Docker** 🐳
- **Swagger (OpenAPI)** 📖
- **Maven** 📦

## 🚀 Funcionalidades
- ✅ Cadastro de livros  
- ✅ Listagem de livros  
- ✅ Atualização de livros  
- ✅ Exclusão de livros  
- ✅ Autenticação com **OAuth2** e **JWT**  
- ✅ Validações de acesso para usuários e administradores

## 🗄️ Configuração do Banco de Dados
Configure as credenciais no arquivo application.yml:
```yml
   spring:
     datasource:
      url: jdbc:postgresql://localhost:5432/seu_banco_de_dados
      username: seu_usuario
      password: sua_senha
```


## 🔑 Autenticação
A aplicação utiliza OAuth2 e JWT para autenticação e autorização:

Login: Realize o login usando o endpoint /login com suas credenciais.
JWT Token: Após o login, um token JWT será gerado e deve ser incluído no header Authorization para acessar endpoints protegidos.

## 🔧 Como Executar

### Pré-requisitos
- **Java 21** instalado
- **Docker** (caso queira rodar em contêiner)

### **Clone o repositório**  
   ```sh
   git clone https://github.com/leolimaferreira/libraryapi.git
```

### 🐳 Usando o Docker
```sh
docker build -t library-api .
docker run -p 8080:8080 library-api
```

### 📖 Documentação da API

A documentação completa da API está disponível através do **Swagger** na interface interativa, que permite explorar e testar todos os endpoints da aplicação.

### Como acessar:
1. **Inicie a aplicação**: Certifique-se de que a aplicação esteja rodando localmente na porta `8080`.
2. **Acesse o Swagger**: Abra seu navegador e acesse a URL:
```sh
http://localhost:8080/swagger-ui/index.html
```
### O que você encontrará na documentação:
- **Listagem de Endpoints**: Todos os endpoints da API são listados, com descrições e exemplos de uso.
- **Autenticação**: A API utiliza **OAuth2** e **JWT** para autenticação. Para testar os endpoints protegidos, você precisará fornecer um token JWT. Você pode inseri-lo diretamente na interface do Swagger.
- **Teste Interativo**: Com o Swagger, você pode realizar requisições diretamente na interface, sem necessidade de ferramentas externas. Basta preencher os parâmetros necessários e enviar as requisições para visualizar as respostas.
- **Modelos de Dados**: A documentação inclui os modelos de dados usados na API, como livros, usuários, etc., com exemplos de entrada e saída.

