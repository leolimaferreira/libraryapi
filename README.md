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

## 🔧 Como Executar

### Pré-requisitos
- **Java 21** instalado
- **Docker** (caso queira rodar em contêiner)

### 1. **Clone o repositório**  
   ```sh
   git clone https://github.com/leolimaferreira/libraryapi.git

🔑 Autenticação
A aplicação utiliza OAuth2 e JWT para autenticação e autorização:

Login: Realize o login usando o endpoint /login com suas credenciais.
JWT Token: Após o login, um token JWT será gerado e deve ser incluído no header Authorization para acessar endpoints protegidos.
