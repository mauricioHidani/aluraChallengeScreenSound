# Desafio Screen Sound
🚩Java e Spring Framework G7 - ONE | Java: persistência de dados e consultas com Spring Data JPA<br>
📅30 de outubro de 2024 - 📍São Paulo, Brasil<br>
🌎[@Alura](https://www.alura.com.br/) | [@One](https://www.oracle.com/br/)<br>

![269329508-4b068d55-5cfc-480c-b94f-7d48b3c91eb3](https://github.com/user-attachments/assets/2ae7b8a1-0a9e-40b3-b0b5-ff7a89d72cb4)


## Versões
- [Java 21](https://docs.oracle.com/en/java/javase/21/)
- [Maven](https://maven.apache.org/what-is-maven.html)
- [Theokanning Openai GPT3 v0.18.2](https://github.com/TheoKanning/openai-java)


## Desafio
Implementar uma aplicação para armazenar dados de nossos artistas e músicas preferidos em um banco de dados relacional, 
podendo buscar informações por artistas e consultar dados sobre os mesmos através de integração com a API do ChatGPT.
- Você precisará de uma classe _Artista_, com os dados para representar o mesmo;
- Será necessário também uma classe específica para representar as _Músicas_;
- Para o artista, pode praticar a implementação de _enum_, para definir o **tipo** do artista, por exemplo: **solo**,
  **dupla** ou **banda**;
- Lembre-se de criar o projeto através do site do [Spring Initializr](https://start.spring.io/), onde já é possível
  adicionar as dependências do **Spring Data JPA** e do **PostgreSQL**;
- Crie uma classe principal com o menu, com as opções desejadas, como: **cadastrar artista**, **cadastrar música**,
  **pesquisar músicar por artistas**, **etc**;
- Lembre-se de estender o **CommandLineRunner** na classe do Spring, sobrescrevendo o método run para chamar o menu
  criado.

### 🔨 Objetivos do projeto
- O objetivo do projeto é praticar a modelagem de classes e relacionamentos utilizando o Spring Data JPA;
- É importante descrever e implementar corretamente a relação entre **Artista** e **Música**, visto que um artista
  pode estar associado a mais de uma música;
- Uma música só deve ser salva no banco de dados, caso o Artista tenha sido previamente cadastrado;
- Praticaremos **Derived Queries** e **JPQL** para verificar se o artista já está cadastrado e buscar músicas
  por um determinado artista;
- Faremos a **integração** com a **API do ChatGPT** para buscar informações sobre um determinado artista.


## Utilização

### OpenAI GPT3 API
Para conseguir utilizar a aplicação onde é gerado a _Descrição do Artista_ é necessário que se crie uma conta na 
[Plataforma](https://platform.openai.com/docs/overview) da OpenAI para consumir APIs, após sua criação deve-se registrar 
uma **Chave de API/API Key** para assim conseguir que a aplicação realize requisições de consulta da _Descrição do 
Artista_.

Depois de registrado e com a chave de acesso da OpenAI, é necessário criar uma _variável de ambiente_ no sistema com o 
nome `OPENAI_API_KEY` e o valor da sua **Chave**, ou trocar no arquivo `application.yml` o valor que indica a variável 
de ambiente `OPENAI_API_KEY` para sua **Chave**.

### Perfil ou Profile da Aplicação
No arquivo `application.yml` está indicado dois possiveis **Profiles**, `prod`=Produção e `test`=Teste, cada um representa 
seu ambiente. A aplicação irá inicializar a configuração respectiva ao seu perfil de ambiente escolhido.

Assim quando rodar a aplicação em `test` a base de dados utilizada será o `H2`, um banco de dados de teste que irá 
armazenar as informações em memória, pesistidas apenas enquanto a aplicação estiver rodando.

Já ao perfil de `prod` a aplicação irá rodar com a base de dados usando o `Postgres`. Sendo necessário mudar ou atribuir 
as variáveis de ambiente para conseguir consumir/requisitar o SGBD `Postgres`.
- `POSTGRES_URI`: URI que representa a conexão com o `Postgres` _(exp: jdbc:postgresql://localhost:port/sua_base_de_dados)_;
- `POSTGRES_USERNAME`: Nome de usuário para conectar com o `Postgres` _(obs: quando não modificado será `postgres`)_;
- `POSTGRES_PASSWORD`: Senha utilizada para acessar o SGBD _(obs: caso necessite resetar a senha do `Postgres` por não
  lembrar mais o [link](https://www.youtube.com/watch?v=wfxrRxzm8jY) irá explicar como fazé-lo no OS Windows)_;

## Descrição
**Inicio da aplicação**<br>
![image](https://github.com/user-attachments/assets/21fcd532-d3e5-4401-9e7a-48fc841429df)

**Menu da aplicação**<br>
![image](https://github.com/user-attachments/assets/661a43aa-afcf-4a38-bae1-bc1b792d0284)

**Adicionando Artista**<br>
![image](https://github.com/user-attachments/assets/28cd6b02-aab1-44e8-9321-04048da86a3e)

**Adicionando Música**<br>
![image](https://github.com/user-attachments/assets/3dbe26ae-6efa-4bbe-a961-c961813d5c30)


**Listagem de Músicas**<br>
![image](https://github.com/user-attachments/assets/227bea00-b409-4723-b13f-1ef32db5290b)

**Consulta de Música por Artista**<br>
![image](https://github.com/user-attachments/assets/133fc242-1012-451e-9e0e-67e5b5c5db39)

**Consultar Descrição/Dados de um Artista**<br>
![image](https://github.com/user-attachments/assets/76bf8a4c-28f1-48a2-ab57-c5ca4670875f)

**Mensagem Sair da Aplicação**<br>
![image](https://github.com/user-attachments/assets/ea3a17d2-5b9f-49f1-8981-c381609ba9b9)
