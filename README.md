<<<<<<< HEAD
# 🛰️ GS - API de Soluções da Economia Espacial

## Integrantes do Grupo
> Alexandre Delfino - RM560059
  Enzo Luciano      - RM559557
  Pedro Claudino    - RM561023
  Luigi Thiengo     - RM560755

---

## 📌 Sobre o Projeto

**Nome da solução:** SentinelaVerde  
**Problema escolhido:** Monitoramento de Desmatamento por Satélite  
**ODS relacionado:** ODS 15 – Vida Terrestre

A exploração espacial oferece ferramentas poderosas para monitorar e preservar os ecossistemas do planeta. Esta API simula uma plataforma de gestão de soluções tecnológicas baseadas em dados espaciais, permitindo cadastrar, gerenciar e acompanhar iniciativas ligadas à economia espacial.

---

## 🏗️ Arquitetura

```
src/main/java/br/com/fiap/gs/
├── controller/       → Camada REST (SolucaoController)
├── service/          → Regras de negócio (SolucaoService)
├── repository/       → Acesso a dados (SolucaoRepository - Spring Data JPA)
├── model/            → Entidades JPA + Enums
├── dto/              → DTOs de entrada e saída
├── exception/        → Exceções customizadas + GlobalExceptionHandler
└── config/           → Swagger + DataLoader
```

---

## 🚀 Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.8+

### Passos

```bash
# 1. Clone o repositório
git clone <url-do-repositorio>
cd gs-api

# 2. Execute com Maven
mvn spring-boot:run

# 3. Acesse a documentação Swagger
http://localhost:8080/swagger-ui.html

# 4. Console H2 (banco de dados em memória)
http://localhost:8080/h2-console
# JDBC URL: jdbc:h2:mem:gsdb
# User: sa / Password: (vazio)
```

---

## 📋 Endpoints da API

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/solucoes` | Cadastrar nova solução |
| `GET` | `/api/solucoes` | Listar todas as soluções |
| `GET` | `/api/solucoes/{id}` | Buscar solução por ID |
| `GET` | `/api/solucoes/area/{areaImpacto}` | Buscar por área de impacto |
| `GET` | `/api/solucoes/ods/{ods}` | Listar por ODS relacionado |
| `PUT` | `/api/solucoes/{id}` | Atualizar solução completa |
| `PATCH` | `/api/solucoes/{id}/status` | Alterar apenas o status |
| `DELETE` | `/api/solucoes/{id}` | Excluir solução |
| `GET` | `/api/solucoes/resumo` | Resumo geral das soluções |

---

## 📝 Exemplos de JSON

### POST /api/solucoes
```json
{
  "nome": "SentinelaVerde - Monitoramento Amazônico",
  "descricao": "Sistema de monitoramento de desmatamento em tempo real usando satélites Sentinel-2.",
  "areaImpacto": "MEIO_AMBIENTE",
  "ods": "ODS_15_VIDA_TERRESTRE",
  "urgencia": 5,
  "impacto": 5
}
```

### Resposta 201 Created
```json
{
  "id": 1,
  "nome": "SentinelaVerde - Monitoramento Amazônico",
  "descricao": "Sistema de monitoramento de desmatamento em tempo real usando satélites Sentinel-2.",
  "areaImpacto": "MEIO_AMBIENTE",
  "ods": "ODS_15_VIDA_TERRESTRE",
  "status": "PROPOSTA",
  "urgencia": 5,
  "impacto": 5,
  "prioridade": 25,
  "nivelPrioridade": "CRITICA",
  "dataCadastro": "2025-06-08T10:00:00",
  "dataAtualizacao": "2025-06-08T10:00:00"
}
```

### PATCH /api/solucoes/1/status
```json
{
  "status": "EM_DESENVOLVIMENTO"
}
```

### GET /api/solucoes/resumo
```json
{
  "totalSolucoes": 5,
  "quantidadePorStatus": {
    "PROPOSTA": 2,
    "EM_DESENVOLVIMENTO": 1,
    "VALIDADA": 1,
    "ATIVA": 1
  },
  "quantidadePorAreaImpacto": {
    "MEIO_AMBIENTE": 2,
    "AGRONEGOCIO": 1,
    "GESTAO_DE_RISCOS": 1,
    "CONECTIVIDADE": 1
  },
  "solucoesPrioridadeAlta": [...]
}
```

---

## ⚙️ Regras de Negócio

| Regra | Descrição |
|-------|-----------|
| ✅ Prioridade automática | `prioridade = urgência × impacto` (máx. 25) |
| ✅ Nível de prioridade | CRITICA (≥20), ALTA (≥12), MEDIA (≥6), BAIXA (<6) |
| ❌ Alteração bloqueada | Não é possível alterar soluções com status `CANCELADA` |
| ❌ Exclusão bloqueada | Não é possível excluir soluções com status `VALIDADA` |
| ❌ Status bloqueado | Não é possível alterar status de soluções `CANCELADAS` |
| ⚠️ 404 Not Found | Retornado quando a solução não existe |
| ⚠️ 400 Bad Request | Retornado quando os dados enviados são inválidos |
| ⚠️ 422 Unprocessable | Retornado quando uma regra de negócio é violada |

---

## 📊 Valores dos Enums

### AreaImpacto
`AGRONEGOCIO`, `MINERACAO`, `LOGISTICA`, `SUSTENTABILIDADE`, `SAUDE`, `CONECTIVIDADE`, `GESTAO_DE_RISCOS`, `EDUCACAO`, `MEIO_AMBIENTE`

### StatusSolucao
`PROPOSTA`, `EM_DESENVOLVIMENTO`, `VALIDADA`, `ATIVA`, `CANCELADA`

### ODS (exemplos)
`ODS_15_VIDA_TERRESTRE`, `ODS_13_ACAO_CONTRA_CLIMA`, `ODS_2_FOME_ZERO`, `ODS_3_SAUDE_E_BEM_ESTAR`, ... (ODS_1 a ODS_17)

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- Spring Web (REST)
- Bean Validation
- H2 Database (dev) / Oracle (produção)
- Springdoc OpenAPI (Swagger UI)
- Maven
- JUnit 5 + Mockito

---

## 🎥 Vídeo Explicativo
>(https://youtu.be/Ni6YWguU7Yg)
=======
# DomainDrivenDesign-Java-GlobalSolution-
>>>>>>> 42e392520d429ae05efb2b00359ccb39b7b9d162
