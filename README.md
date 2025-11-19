# 🧬 Projeto VekRest - VekClient - Módulo 1

Este é o cliente do projeto VekRest, um CRUD de clientes/pessoas. Este projeto utiliza conteinerização em **Docker**, persistência com **MongoDB**, cache com **Redis**, build **Maven**, logs com **OpenSearch** e **Graylog** e **Spring Boot Java**.  

Ele foi desenvolvido como a entrega do 1° módulo do projeto completo. Para o 1° módulo, temos apenas esta aplicação cliente.

---

## ✨ Imagem Docker (DockerHub)

> A imagem desta aplicação é atualizada a cada nova tag ou pull request na [branch main](https://github.com/VekRest/vekrest-vekclient-modulo1/tree/main)

> Link da imagem no DockerHub: [vek03/vekrest-vekclient:latest](https://hub.docker.com/repository/docker/vek03/vekrest-vekclient)

## 🐳 Como rodar o container

1️⃣ Para baixar a imagem do Docker Hub:
```bash
docker pull vek03/vekrest-vekclient:latest
```

2️⃣ Para rodar o container localmente:
```bash
docker run --rm -p 8082:8082 --name vekclient -e SPRING_PROFILES_ACTIVE=local vek03/vekrest-vekclient:latest
```

3️⃣ Alternativamente, você pode adicionar o serviço no seu docker-compose.yml local, descomentando ou adicionando o seguinte trecho:
```bash
services:
  vekclient:
    image: vekrest/vekclient:latest
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
```

4️⃣ Depois de adicionar o serviço em docker-compose.yml, suba os containers:
```bash
docker-compose up -d
```

---

## 📘 Estrutura do Projeto

```

📂 vekrest-vekclient-modulo1/
├── 📁 .commands                            ← Pasta de comandos .bat para automatizar na execução/build
├── 📁 .github                                  ← Pasta de configuração da esteira CI/CD do Github Actions
├── 📁 .run                                 ← Pasta de configurações da IDE para facilitar execução local
├── 📁 domain                               ← Módulo de domínio, construído unicamente com o Java, sem dependências do Spring
    ├── 📁 [...]/java                       ← Pasta princípal do projeto (Domínio)
            ├── 📁 entity/                  ← Entidades próprias do domínio
            ├── 📁 exception/               ← Exceções customizadas
            ├── 📁 repository/              ← Interface da Lógica de persistência de dados
            ├── 📁 service/                 ← Lógica de regra de negócio
├── 📁 spring                               ← Módulo do spring (aplicação), construído com dependências do Spring
    ├── 📁 [...]/java                       ← Pasta princípal do projeto (App)
            ├── 📁 configuration/           ← Arquivos de Injeção de Dependência (@Bean)
            ├── 📁 controller/              ← Controllers Rest HTTP
            ├── 📁 repository/              ← Implementação da Lógica de persistência de dados
            📄 VekclientApplication.java    ← Classe principal do Spring Boot
    ├── 📁 [...]/resources                  ← Variáveis de ambiente
├── 📄 docker-compose.yml                   ← Configuração dos containers utilizados
├── 📄 Dockerfile                           ← Configuração para build e deploy no Docker
├── 📄 LICENCE.txt                          ← Arquivo de Licença GPL-3.0
├── 📄 README.md                            ← Este arquivo de documentação

````

---

## ⚙️ Objetivo

Módulo 1
Crie uma API REST utilizando Spring Boot (versão 3+).
A API deve conter um CRUD de Pessoa (Criar, Ler, Atualizar e Deletar), com os seguintes requisitos:

O retorno do serviço deve ser paginado, mostrando 10 itens por página.

Apenas pessoas com o atributo ativo = true devem ser retornadas.

Utilize o banco de dados da sua escolha e crie uma tabela com o seguinte padrão:

ID NOME DT_NASCIMENTO ATIVO
Os logs da aplicação devem ser enviados ao Graylog.

No seu docker-compose, adicione todas as imagens utilizadas (banco de dados, Graylog, aplicação, etc.).

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

## ✨ Deploy (DockerHub)

> A imagem desta aplicação é atualizada a cada atualização na [branch main](https://github.com/VekRest/vekrest-vekclient-modulo1/tree/main)

> Link da imagem no DockerHub: [vek03/vekrest-vekclient:latest](https://hub.docker.com/repository/docker/vek03/vekrest-vekclient)

---

## ✅ Qualidade (SonarQube)

> Este projeto tem qualidade analisada pelo SonarQube Cloud. Verifique nos badges!

[![SonarQube Cloud](https://sonarcloud.io/images/project_badges/sonarcloud-dark.svg)](https://sonarcloud.io/summary/new_code?id=vekclient)
[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=vekclient&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=alert_status&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=bugs&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=code_smells&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=coverage&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=duplicated_lines_density&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=ncloc&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=reliability_rating&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=security_rating&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=sqale_index&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=sqale_rating&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=vulnerabilities&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)

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

# Agora abra o projeto na sua IDE (IntelliJ, Eclipse, VSCode, etc) e rode a aplicação Spring Boot
# Ou, se preferir, rode via terminal com properties-local:
mvn spring-boot:run -pl spring -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=local"
```

### 3️⃣ (Opcional) Alternativamente, se quiser rodar via container localmente:
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

## 📦 Esteira CI/CD Automatizada com Github Actions

> A esteira CI/CD deste projeto é automatizada via Github Actions. A cada tag criada ou execução manual na branch main, a esteira é disparada.

###  Steps da esteira:

1️⃣ Verificação de **Vulnerabilidades** com o **Trivy** (Security)

2️⃣ Análise do **Sonar Cloud** (Quality)

3️⃣ Deploy da imagem do container no **DockerHub e Github Packages** (Deploy)

4️⃣ Deploy do Maven Artifact no **Github Packages** (Deploy)

5️⃣ Deploy da Release no **Github** (Release)

### Para executar a Esteira pelo trigger:
```bash
# Exemplo: Cria a tag
git tag <version>

# Envia a tag para o repositório remoto
git push origin <version>
```

[![VekClient CI/CD Workflow](https://github.com/VekRest/vekrest-vekclient-modulo1/actions/workflows/main.yml/badge.svg)](https://github.com/VekRest/vekrest-vekclient-modulo1/actions/workflows/main.yml)

---

## 💡 Observações Importantes

* Este projeto cumpre com o **Módulo 1 da Atividade**
* Para este módulo, existe apenas **esta aplicação**

---

## Postman Collection

> Link para download da coleção Postman utilizada nos testes da API: [Postman Collection VekRest](https://web.postman.co/workspace/My-Workspace~e702bcc2-18e9-41e7-86d7-21df963c99df/folder/33703402-f59218e7-8804-436c-8866-2693c75b9eb6?action=share&source=copy-link&creator=33703402&ctx=documentation)

> Alternativamente, você pode utilizar o Swagger UI para testar a API:
[Swagger UI VekRest VekClient Módulo 1](http://localhost:8082/vekrest/vekclient/swagger-ui/index.html) (rodando localmente)

---

## ✍️ Autor

<div align="center">

| [<img src="https://avatars.githubusercontent.com/u/98980071" width=115><br><sub>Victor Cardoso</sub>](https://github.com/vek03)
| :---: |

</div>

---
