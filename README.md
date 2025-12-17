# 🧬 Projeto VekRest - VekClient - Módulo 1

Cliente VekRest: CRUD em Spring Boot com Docker, MongoDB, Redis, OpenSearch e Graylog. **Módulo 1 / CRUD de Pessoas**

## 🧩 PARTES DO MÓDULO 1
| Aplicação      | Descrição                                      | Link             |
|----------------|------------------------------------------------|------------------|
| VekClient      | Aplicação de CRUD de Pessoa                    | Este Repositório |

> Este projeto não depende de nenhuma outra aplicação para funcionar corretamente

---

# 1.✨ Imagem Docker (DockerHub)

> A imagem desta aplicação é atualizada a cada nova tag ou pull request na [branch main](https://github.com/VekRest/vekrest-vekclient-modulo1/tree/main)

> Link da imagem no DockerHub: [vek03/vekrest-vekclient:latest](https://hub.docker.com/repository/docker/vek03/vekrest-vekclient)

---

## 1.1 🧩 Containers necessários para rodar a aplicação:

| Container | Imagem | Link                                                                                                                                           | 
|---|---|------------------------------------------------------------------------------------------------------------------------------------------------|
| MongoDB | `mongo:latest` | https://hub.docker.com/_/mongo                                                                                                                 |
| Redis | `redis:latest` | https://hub.docker.com/_/redis                                                                                                                 |
| OpenSearch | `opensearchproject/opensearch:2.4.0` | https://hub.docker.com/layers/opensearchproject/opensearch/2.4.0/images/sha256-c8681472b70d46e7de61fe770d288a972f84b3f122f3c74ca06ea525264b6fd5 |
| Graylog | `graylog/graylog:5.1.5` | https://hub.docker.com/layers/graylog/graylog/5.1.5/images/sha256-3b6967572e88731eacfa661e6d7ca41da3e259bc5eb041e58fb10e4deb823dcb             |

---

## 1.2 ⚙ Variáveis de ambiente necessárias para rodar o container:

| Variável          | Descrição                       | Exemplo                                                                                |
|-------------------|---------------------------------|----------------------------------------------------------------------------------------|
| `SERVER_PORT`     | Porta onde a aplicação irá rodar | `8082`                                                                                 |
| `REDIS_HOST`      | Host do Redis                   | `redis`                                                                                |
| `REDIS_PORT`      | Porta do Redis                  | `6379`                                                                                 |
| `MONGODB_URI`     | URI de conexão do MongoDB       | `mongodb://mongodb:27017/vekrest?serverSelectionTimeoutMs=15000&connectTimeoutMS=15000` |
| `GRAYLOG_HOST`    | Endereço do Graylog             | `graylog`                                                                              |
| `GRAYLOG_PORT`    | Porta do Graylog                | `12201`                                                                                |

---

## 1.3 🐳 Como rodar o container

1️⃣ Para baixar a imagem do Docker Hub:
```bash
docker pull vek03/vekrest-vekclient:latest
```

2️⃣ Para rodar o container localmente:
```bash
docker run -d \
    --name vekclient \ 
    -e SERVER_PORT=8082 \
    -e REDIS_HOST=redis \
    -e REDIS_PORT=6379 \
    -e MONGODB_URI="mongodb://mongodb:27017/vekrest?serverSelectionTimeoutMs=15000&connectTimeoutMS=15000" \
    -e GRAYLOG_HOST=graylog \
    -e GRAYLOG_PORT=12201 \
    -p 8082:8082 \
    vek03/vekrest-vekclient:latest
```

3️⃣ Alternativamente, você pode adicionar o serviço no seu docker-compose.yml local, descomentando ou adicionando o seguinte trecho:
```bash
services:
  vekclient:
    image: vek03/vekrest-vekclient:latest
    hostname: vekclient
    container_name: vekclient
    environment:
      SERVER_PORT: 8082
      REDIS_HOST: redis
      REDIS_PORT: 6379
      MONGODB_URI: mongodb://mongodb:27017/vekrest?serverSelectionTimeoutMs=15000&connectTimeoutMS=15000
    ports:
        - "8082:8082"
    depends_on:
      mongodb:
        condition: service_healthy
      opensearch:
        condition: service_healthy
      graylog:
        condition: service_started
      redis:
        condition: service_healthy
    healthcheck:
      test: ["CMD-SHELL", "curl -f http://localhost:8082/vekrest/vekclient/actuator/health || exit 1"]
      interval: 5s
      timeout: 15s
      retries: 10
      start_period: 30s
```

4️⃣ Depois de adicionar o serviço em docker-compose.yml, suba os containers:
```bash
docker-compose up -d
```

---

## 📦 Instalação e Configuração do Ambiente

### 1️⃣ Clone o projeto na sua máquina e baixe as dependências:
```bash
# Clonar
git clone https://github.com/VekRest/vekrest-vekclient-modulo1.git

# Acesse a pasta do projeto
cd vekrest-vekclient-modulo1
````

### 2️⃣ Suba os containers necessários e Rode o projeto na sua IDE de preferência (ou via comando Maven)
```bash
# Suba os containers necessários (MongoDB, Redis, OpenSearch, Graylog)
docker-compose up -d

# Rode o projeto via Maven
```

### 3️⃣ (Opcional) Alternativamente, se quiser rodar via Docker localmente:
```bash
# Dentro da pasta do projeto:
mvn clean package -DskipTests

# Agora faça deploy no Docker local:
docker build -t vekrest/vekclient:latest .

# Descomente as últimas linhas do docker-compose.yml (relacionadas ao vekclient) e rode:
docker-compose up -d
```

> Ou execute o script .bat (executar_tudo.bat) na pasta .commands para automatizar o processo.


> A API Rest VekClient fica disponível na porta 8082 do [Localhost](http://localhost:8082) ao rodar localmente via IDE.

### 4️⃣ (Opcional) Caso deseje, pode rodar o SonarQube localmente

```bash
# Após configurar o pom.xml com as informações do Sonar em Properties:
mvn clean install sonar:sonar -Dsonar.token={TOKEN_SONAR}
```

---

## 🧩 Tecnologias Utilizadas

- **Spring Boot** → Framework Back-End
- **Java** → Linguagem de programação
- **Maven** → Build
- **Docker** → Containers e virtualização
- **Docker Hub** → Repositório de imagens Docker
- **MongoDB** → Persistência de dados
- **Redis** → Cache
- **OpenSearch e Graylog** → Logs da Aplicação
- **Swagger** → Documentação da API
- **SonarQube** → Qualidade
- **Github Actions** → CI/CD automatizado
- **.bat** → Scripts para automatizar processos no Windows

---

## 📦 Esteira CI/CD Automatizada com Github Actions

> A esteira CI/CD deste projeto é automatizada via Github Actions. A cada tag criada a esteira é disparada.

### Para executar a Esteira pelo trigger:
```bash
# Exemplo: Cria a tag
git tag <version>

# Envia a tag para o repositório remoto
git push origin <version>
```

[![VekClient CI/CD Workflow](https://github.com/VekRest/vekrest-vekclient-modulo1/actions/workflows/main.yml/badge.svg)](https://github.com/VekRest/vekrest-vekclient-modulo1/actions/workflows/main.yml)

---

## Postman Collection

> Link para download da coleção Postman utilizada nos testes da API: [Postman Collection VekRest](https://web.postman.co/workspace/My-Workspace~e702bcc2-18e9-41e7-86d7-21df963c99df/folder/33703402-f59218e7-8804-436c-8866-2693c75b9eb6?action=share&source=copy-link&creator=33703402&ctx=documentation)

> Alternativamente, você pode utilizar o Swagger UI para testar a API:
[Swagger UI VekRest VekClient Módulo 1](http://localhost:8082/vekrest/vekclient/swagger-ui/index.html) (rodando localmente)

---
