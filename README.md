# 🧬 Projeto VekRest - VekClient

Este é o cliente do projeto VekRest, um CRUD de clientes utilizando MongoDB, Redis, OpenSearch e Graylog. Este projeto utiliza conteinerização em **Docker**, build **Maven** e **Spring Boot Java**.  

Ele foi desenvolvido como a entrega do 1° módulo do projeto completo. Para o 1° módulo, temos apenas esta aplicação cliente.

---

## 📘 Estrutura do Projeto

```

📂 vekrest-vekclient-modulo1/
├── 📁 .commands                            ← Pasta de comandos .bat para automatizar na execução/build
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
- **MongoDB** → Persistência de dados
- **Redis** → Cache
- **OpenSearch e Graylog** → Logs da Aplicação
- **Swagger** → Documentação da API
- **SonarQube** → Qualidade
- **Github Actions** → CI/CD automatizado

---

## ✨ Deploy (DockerHub)

> A imagem desta aplicação é atualizada a cada atualização na [branch main](https://github.com/VekRest/vekrest-vekclient-modulo1/tree/main)

---

## ✅ Qualidade (SonarQube)

> Este projeto tem qualidade analisada pelo SonarQube Cloud. Verifique nos badges!

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=alert_status&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=bugs&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=code_smells&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=coverage&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)

[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=vekclient&metric=duplicated_lines_density&token=a573f3b8fb7f26a26cc71c5bb7b6806634231453)](https://sonarcloud.io/summary/new_code?id=vekclient)

---

## 📦 Instalação e Configuração do Ambiente

### 1️⃣ Clone o projeto na sua máquina e baixe as dependências:
```bash
# Clonar
git clone https://github.com/VekRest/vekrest-vekclient-modulo1.git
````

### 2️⃣ Rode o projeto na sua IDE de preferência

### 3️⃣ (Opcional) Alternativamente, se quiser rodar via container localmente:
```bash
# Dentro da pasta do projeto:
mvn clean package -DskipTests

# Agora faça deploy no Docker local:
docker build -t vek03/vekrest-vekclient:latest .
```

> A API Rest VekClient fica dispoível na porta 8082 do [Localhost](http://localhost:4200) ao rodar localmente via IDE.

### 4️⃣ (Opcional) Caso deseje, pode rodar o SonarQube localmente

```bash
# Após configurar o pom.xml com as informações do Sonar em Properties:
mvn clean install sonar:sonar -Dsonar.token={TOKEN_SONAR}
```

---

## 💡 Observações Importantes

* Este projeto cumpre com o **Módulo 1 da Atividade**
* Para este módulo, existe apenas **esta aplicação**

---

## ✍️ Autores

**Projeto desenvolvido por:**

<div align="center">

| [<img src="https://avatars.githubusercontent.com/u/98980071" width=115><br><sub>Victor Cardoso</sub>](https://github.com/vek03)
| :---: |

</div>

---
