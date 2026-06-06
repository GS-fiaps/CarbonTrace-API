# CarbonTrace API

API REST desenvolvida para a Global Solution FIAP 2026/1 — Java Advanced.

## Descrição da Solução

O **CarbonTrace** é uma API REST voltada ao monitoramento ambiental com apoio de dados satelitais. A solução permite cadastrar regiões monitoradas, satélites, imagens satelitais, ocorrências ambientais, análises de áreas desmatadas, alertas ambientais e órgãos responsáveis por receber notificações.

A proposta conecta tecnologia espacial e problemas reais da Terra, utilizando dados orbitais para apoiar o acompanhamento de regiões ambientais e a tomada de decisão diante de possíveis impactos ambientais.

## Integrantes

| Nome         | RM         | Turma         |
| ------------ | ---------- | ------------- |
| Pietro Paranhos Wilhelm  | 561378 | 2TDSPG |
| João Vitor Biribilli Ravelli | 565594 | 2TDSPG |
| Pedro Matos | 564184 | 2TDSPG |
| Gabriel Neris Losano | 564093 | 2TDSPG |
| Felipe Monte | 562019 | 2TDSPG |

## Objetivo do Projeto

O objetivo da API é fornecer uma base backend para uma solução de monitoramento ambiental, permitindo:

* Registrar regiões ambientais monitoradas;
* Cadastrar satélites e imagens capturadas;
* Registrar ocorrências ambientais;
* Realizar análises de áreas desmatadas;
* Emitir alertas com níveis de criticidade;
* Vincular alertas a órgãos ambientais;
* Disponibilizar endpoints documentados para integração com outras aplicações.

## Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* Oracle Database
* Maven
* Lombok
* Spring Validation
* Spring HATEOAS
* Swagger / OpenAPI
* Railway
* Insomnia / Postman

## Arquitetura do Projeto

O projeto foi organizado em camadas, seguindo boas práticas de separação de responsabilidades:

```text
src/main/java/br/com/fiap/carbontrace
├── assembler
├── config
├── controller
├── dto
│   ├── request
│   └── response
├── enums
├── model
├── repositories
├── service
└── validation
```

### Camadas

* **model**: contém as entidades JPA mapeadas para o banco de dados.
* **repositories**: contém as interfaces que estendem `JpaRepository`.
* **service**: contém as regras de negócio e a comunicação entre controller e repository.
* **controller**: contém os endpoints REST da aplicação.
* **dto/request**: contém os objetos de entrada da API.
* **dto/response**: contém os objetos de saída da API.
* **validation**: contém o tratamento de erros de validação.
* **config**: contém configurações da aplicação, como Swagger e CORS.
* **assembler**: contém os assemblers utilizados para adicionar links HATEOAS às respostas.

## Entidades Principais

O projeto possui as seguintes entidades:

* Usuario
* Estado
* Regiao
* Satelite
* ImagemSatelital
* Ocorrencia
* Analise
* Relatorio
* Alerta
* OrgaoAmbiental
* AlertaOrgao

## Relacionamentos

A API possui múltiplos relacionamentos entre entidades, incluindo:

* Um Estado possui várias Regiões;
* Uma Região possui várias Ocorrências;
* Uma Região possui várias Imagens Satelitais;
* Um Satélite possui várias Imagens Satelitais;
* Uma Imagem Satelital possui várias Análises;
* Uma Análise pode gerar vários Alertas;
* Um Usuário pode registrar Ocorrências e Relatórios;
* Um Órgão Ambiental pertence a um Estado;
* Um Alerta pode ser vinculado a vários Órgãos Ambientais através da entidade associativa AlertaOrgao.

A entidade `AlertaOrgao` utiliza chave composta, representada pela classe `AlertaOrgaoId`.

## Modelagem Avançada

O projeto contempla recursos avançados de modelagem com JPA, como:

* Relacionamentos `@OneToMany` e `@ManyToOne`;
* Uso de chave composta com `@EmbeddedId`;
* Uso de `@MapsId` na entidade associativa;
* Enums persistidos com `@Enumerated(EnumType.STRING)`;
* Múltiplas tabelas relacionadas no banco Oracle.

## Validação dos Dados

Os DTOs de entrada utilizam Bean Validation para garantir consistência dos dados enviados para a API.

Exemplos de validações utilizadas:

* `@NotBlank`
* `@NotNull`
* `@Email`
* `@Size`
* `@Positive`
* `@PositiveOrZero`
* `@PastOrPresent`
* `@DecimalMin`
* `@DecimalMax`

## Tratamento de Erros

A API possui tratamento de erros de validação por meio de `@RestControllerAdvice`.

Quando uma requisição possui campos inválidos, a API retorna uma resposta padronizada contendo o campo e a mensagem de erro.

Exemplo:

```json
[
  {
    "field": "email",
    "message": "E-mail inválido"
  }
]
```

## HATEOAS

A API implementa HATEOAS utilizando `EntityModel` e `CollectionModel`.

As respostas dos endpoints incluem links de navegação como:

* `self`
* `atualizar`
* `deletar`
* listagem do recurso

Exemplo de resposta:

```json
{
  "_links": {
    "self": {
      "href": "https://carbontrace-api-production.up.railway.app/usuarios/1"
    },
    "usuarios": {
      "href": "https://carbontrace-api-production.up.railway.app/usuarios"
    },
    "atualizar": {
      "href": "https://carbontrace-api-production.up.railway.app/usuarios/1"
    },
    "deletar": {
      "href": "https://carbontrace-api-production.up.railway.app/usuarios/1"
    }
  },
  "idUsuario": 1,
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "tipoUsuario": "ANALISTA",
  "dataCadastro": "2026-05-28"
}
```

## Documentação Swagger

A API possui documentação interativa com Swagger/OpenAPI.

### Swagger UI

```text
https://carbontrace-api-production.up.railway.app/swagger-ui.html
```

ou:

```text
https://carbontrace-api-production.up.railway.app/swagger-ui/index.html
```

### OpenAPI JSON

```text
https://carbontrace-api-production.up.railway.app/v3/api-docs
```

## Deploy

A API foi publicada no Railway.

## Configuração por Variáveis de Ambiente

As informações sensíveis do banco de dados não foram versionadas no GitHub.

A aplicação utiliza variáveis de ambiente:

```properties
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@//host:porta/service
SPRING_DATASOURCE_USERNAME=seu_usuario
SPRING_DATASOURCE_PASSWORD=sua_senha
```

No `application.properties`, essas variáveis são referenciadas da seguinte forma:

```properties
spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
```

## Como Executar Localmente

### 1. Clonar o repositório

```bash
git clone https://github.com/GS-fiaps/CarbonTrace-API.git
```

### 2. Entrar na pasta do projeto

```bash
cd CarbonTrace-API
```

### 3. Configurar as variáveis de ambiente

Configure as variáveis:

```bash
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
```

### 4. Executar o projeto com Maven Wrapper

No Windows:

```bash
.\mvnw clean install
.\mvnw spring-boot:run
```

No Linux/Mac:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

### 5. Acessar a API localmente

```text
http://localhost:8080
```

### 6. Acessar Swagger localmente

```text
http://localhost:8080/swagger-ui.html
```

## Endpoints Principais

### Usuários

```text
POST   /usuarios
GET    /usuarios
GET    /usuarios/{id}
PUT    /usuarios/{id}
DELETE /usuarios/{id}
```

### Estados

```text
POST   /estados
GET    /estados
GET    /estados/{id}
PUT    /estados/{id}
DELETE /estados/{id}
```

### Satélites

```text
POST   /satelites
GET    /satelites
GET    /satelites/{id}
PUT    /satelites/{id}
DELETE /satelites/{id}
```

### Regiões

```text
POST   /regioes
GET    /regioes
GET    /regioes/{id}
PUT    /regioes/{id}
DELETE /regioes/{id}
```

### Imagens Satelitais

```text
POST   /imagens-satelitais
GET    /imagens-satelitais
GET    /imagens-satelitais/{id}
PUT    /imagens-satelitais/{id}
DELETE /imagens-satelitais/{id}
```

### Ocorrências

```text
POST   /ocorrencias
GET    /ocorrencias
GET    /ocorrencias/{id}
PUT    /ocorrencias/{id}
DELETE /ocorrencias/{id}
```

### Análises

```text
POST   /analises
GET    /analises
GET    /analises/{id}
PUT    /analises/{id}
DELETE /analises/{id}
```

### Relatórios

```text
POST   /relatorios
GET    /relatorios
GET    /relatorios/{id}
PUT    /relatorios/{id}
DELETE /relatorios/{id}
```

### Alertas

```text
POST   /alertas
GET    /alertas
GET    /alertas/{id}
PUT    /alertas/{id}
DELETE /alertas/{id}
```

### Órgãos Ambientais

```text
POST   /orgaos-ambientais
GET    /orgaos-ambientais
GET    /orgaos-ambientais/{id}
PUT    /orgaos-ambientais/{id}
DELETE /orgaos-ambientais/{id}
```

### Alertas e Órgãos

```text
POST   /alertas-orgaos
GET    /alertas-orgaos
GET    /alertas-orgaos/alerta/{alertaId}/orgao/{orgaoAmbientalId}
PUT    /alertas-orgaos/alerta/{alertaId}/orgao/{orgaoAmbientalId}
DELETE /alertas-orgaos/alerta/{alertaId}/orgao/{orgaoAmbientalId}
```

## Exemplos de Requisição

### Cadastro de Usuário

```http
POST /usuarios
```

```json
{
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "senha": "123456",
  "tipoUsuario": "ANALISTA"
}
```

### Cadastro de Estado

```http
POST /estados
```

```json
{
  "nome": "São Paulo",
  "sigla": "SP"
}
```

### Cadastro de Região

```http
POST /regioes
```

```json
{
  "nome": "Amazônia Legal",
  "latitude": -3.4653,
  "longitude": -62.2159,
  "areaKm2": 5000000.0,
  "estadoId": 1
}
```

### Cadastro de Satélite

```http
POST /satelites
```

```json
{
  "nome": "Landsat 8",
  "agencia": "NASA",
  "altitudeKm": 705.0,
  "anoLancamento": 2013
}
```

### Cadastro de Imagem Satelital

```http
POST /imagens-satelitais
```

```json
{
  "dataCaptura": "2026-05-20",
  "resolucaoMetros": 10.0,
  "urlImagem": "https://exemplo.com/imagens/amazonia-2026-05-20.jpg",
  "regiaoId": 1,
  "sateliteId": 1
}
```

### Cadastro de Análise

```http
POST /analises
```

```json
{
  "dataAnalise": "2026-05-22",
  "areaDesmatadaKm2": 8.7,
  "percentualVariacao": 12.5,
  "statusAlerta": "ATENCAO",
  "imagemSatelitalId": 1
}
```

### Cadastro de Alerta

```http
POST /alertas
```

```json
{
  "dataEmissao": "2026-05-28",
  "nivelCriticidade": "ALTO",
  "descricao": "Alerta emitido por aumento significativo de área desmatada.",
  "analiseId": 1
}
```

### Cadastro de Órgão Ambiental

```http
POST /orgaos-ambientais
```

```json
{
  "nome": "IBAMA",
  "tipo": "FEDERAL",
  "emailContato": "contato@ibama.gov.br",
  "estadoId": 1
}
```

### Cadastro de Vínculo Alerta-Órgão

```http
POST /alertas-orgaos
```

```json
{
  "alertaId": 1,
  "orgaoAmbientalId": 1,
  "dataNotificacao": "2026-05-28",
  "statusNotificacao": "ENVIADO"
}
```

## Vídeo de Apresentação

Link do vídeo técnico:

```text
https://youtu.be/htFP0SzNciI
```

Link do pitch:

```text
INSERIR_LINK_DO_VIDEO_PITCH
```

## Repositório

```text
https://github.com/GS-fiaps/CarbonTrace-API
```
