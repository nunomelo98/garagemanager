
# Garage Management - Webhook API

## Descrição

Projeto Spring Boot que recebe eventos via simulador webhook que esta no Docker para gestão de estacionamento, com três tipos de evento:
- Entrada na garagem (ENTRY)
- Entrada na vaga (PARKED)
- Saída da garagem (EXIT)

Tem uma base de dados rodando via Docker Compose.

---

## Tecnologias

- Java 21
- Spring Boot 3.4.x
- Docker, Docker Compose
- Springdoc OpenAPI (Swagger)
- Maven

---

## Passos para rodar o projeto

### Clonar o repositório:

```bash
git clone https://github.com/nunomelo98/garagemanager.git
cd seu-repo
```

### 1. Subir a base de dados

Execute o Docker Compose para subir o banco de dados:

```bash
docker-compose up -d
```

### 2. Subir o simulador webhook

O simulador envia eventos automaticamente para o webhook e configura dados na base para o dia atual.

No **Linux** (usar network host para garantir comunicação):

```bash
docker run -d --network="host" cfontes0estapar/garage-sim:1.0.0
```

No **Windows** (mapear portas):

```bash
docker run -d -p 8080:3000 cfontes0estapar/garage-sim:1.0.0
```

### 3. Rodar a aplicação principal (garage-management)

Build e execute o projeto Spring Boot normalmente:

```bash
mvn clean spring-boot:run
```

> **IMPORTANTE:**
> Ao iniciar, a aplicação vai buscar a configuração do dia no simulador webhook, fazendo uma requisição para:
>
> ```
> http://localhost:8080/garage
> ```
>
> Caso esteja rodando no Linux usando `--network=host` no simulador, altere essa URL para:
>
> ```
> http://localhost:3000/garage
> ```
>
> Esse ajuste deve ser feito na variável `url` do GarageService no projeto.

---

## Endpoints do Webhook

### POST /webhook

Recebe os eventos de entrada, estacionamento e saída.

#### Exemplos de payloads

##### Entrada na garagem (ENTRY)

```json
{
  "license_plate": "ZUL0001",
  "entry_time": "2025-01-01T12:00:00.000Z",
  "event_type": "ENTRY"
}
```

##### Entrada na vaga (PARKED)

```json
{
  "license_plate": "ZUL0001",
  "lat": -23.561684,
  "lng": -46.655981,
  "event_type": "PARKED"
}
```

##### Saída da garagem (EXIT)

```json
{
  "license_plate": "ZUL0001",
  "exit_time": "2025-01-01T12:00:00.000Z",
  "event_type": "EXIT"
}
```

---

## REST API - Consultas

Além do webhook, o projeto oferece APIs para consulta dos dados.

### Consulta de Placa

**POST** `/plate-status`

Request:

```json
{
  "license_plate": "ZUL0001"
}
```

---

### Consulta de Vaga

**POST** `/spot-status`

Request:

```json
{
  "lat": -23.561684,
  "lng": -46.655981
}
```

---

### Consulta de Faturamento

**GET** `/revenue`

Request (query params ou body JSON):

```json
{
  "date": "2025-01-01",
  "sector": "A"
}
```

---

## Validação e Tratamento de Erros

- A API possui validação e tratamento de erros para entradas inválidas e campos obrigatórios básicos.
- Ainda não há validação específica para obrigatoriedade dos campos conforme o `event_type` (ENTRY, PARKED, EXIT), mas erros como JSON mal formado e campos ausentes são tratados com respostas HTTP apropriadas.
- Possui `GlobalExceptionHandler` para padronizar respostas de erro.

---

## Documentação Swagger

Após rodar a aplicação, acesse:
```
http://localhost:3003/swagger-ui.html
```

---

## Considerações finais

- O simulador deve estar rodando para que a aplicação possa inicializar dados na base corretamente no início do dia.
- No Linux, usar `--network=host` no simulador para garantir comunicação direta.
- No Windows, mapear portas com `-p 8080:3000` no simulador.
- Projeto ainda em desenvolvimento, melhorias na validação por tipo de evento serão implementadas futuramente.

---
