
# Projeto Desafio Mantis
![](https://i.imgur.com/U21ILCg.png)
  
Este projeto foi criado com o intuito de fornecer um sistema com **Front-end**, uma **API Rest** e um **banco de dados** de forma simples para que QAs possam praticar automação de testes. 

O sistema alvo é o [Mantis BugTracker](https://www.mantisbt.org) e é utilizado o Docker para gestão do ambiente e banco de dados.

- Arquitetura Projeto
	- Linguagem	- [Java](https://www.java.com/pt-BR// "Java")
	- [Java Kit Development versão 8](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
	- Gestão de dependências - [Maven](https://maven.apache.org)
	- Framework de Testes automatizass web - [Selenium.WebDriver 3.141](https://www.seleniumhq.org/download/ "Selenium.WebDriver") 
	- Orquestrador de testes - [TestNG](https://testng.org/doc/ "TestNG")
	- Relatório de testes automatizados - [ExtentReports 4.0.9](http://www.extentreports.com/docs/versions/4/java/index.html "ExtentReports 4.0.9")

## Setup para executar o projeto local

- Versão 1.8 do Java JDK instalada no computador (https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
- Versão community do IntelliJ IDEA instalada no computador (https://www.jetbrains.com/pt-br/idea/download/#section=windows)
- Última versão do Google Chrome instalada no computador (https://www.google.com/chrome/) - neste momento Google Chrome 86
- Baixar o projeto, construi-lo e acessar a pasta tests que estará o teste de exemplo.

## Arquitetura

**Premissas de uma boa arquitetura de automação de testes:**
*  Facilitar o desenvolvimento dos testes automatizados (reuso).
*  Facilitar a manutenção dos testes (refatoração).
*  Tornar o fluxo do teste o mais legível possível (fácil entendimento do que está sendo testado).

**Arquitetura padrão Base2**

Para facilitar o entendimento da arquitetura do projeto de testes automatizados, foi criado um fluxograma baseado nas features principais que envolvam a arquitetura conforme imagem abaixo:

![alt text](https://i.imgur.com/wexOWJF.png)


Abaixo um passo a passo para a instalação.


# 1. Preparação do ambiente Mantis

Serão necessárias as seguinte configurações para iniciar o projeto:

**Docker-compose:**  neste repositório é possível encontrar um arquivo chamado "docker-compose.yml", este arquivo contem um grupo de imagens do Mantis, seu banco de dados e o Selenium Grid com seus nós (Mozilla Firefox e Google Chrome). 

Crie o diretório local "C:\mantis", baixe o arquivo **docker-compose.yml** e cole neste diretório criado.

## **1.1 Setup Docker Mantis**

1.  Instalar [Docker Desktop](https://www.docker.com/products/docker-desktop) e reiniciar a máquina
2.  Caso apresente o erro "WSL 2 installation is incomplete", [baixe e instale o WSL2 Kernel](https://docs.microsoft.com/pt-br/windows/wsl/wsl2-kernel) e clique em Restart

![](https://i.imgur.com/4wHESjW.png)

3.  Abra o aplicativo Docker Desktop

![](https://i.imgur.com/cyAeSa2.png)

4.  Deverá ser apresentado o tutorial, basta dar skip que você terá esta tela

![](https://i.imgur.com/Myxqwmv.png)

5.  Abra um terminal e acesse o diretório recém criado: "C:/mantis"

6.  No diretório haverá o arquivo **docker-compose.yml**

7.  Execute o comando> `docker-compose.exe up -d`

8.  Após o processamento se tudo correr bem, as imagens serão baixadas e novos contêineres criados:

![](https://i.imgur.com/TPbVjVQ.png)

9.  Para validar a criação e execução dos execute o comando `docker ps -a` e os contêineres estarão disponíveis e executando:

![](https://i.imgur.com/4pZ3IEQ.png)

10. No aplicativo do Docker Desktop apresentará os containeres ativos conforme imagem:

![](https://i.imgur.com/tZfGGiZ.png)
  

## **1.2 Configuração inicial Mantis**

Faça o seu primeiro acesso ao Mantis pelo endereço http://127.0.0.1:8989

Após acessar será necessário configurar o banco de dados conforme tabela e valores abaixo:

| Variável | Valor |
|-----|------|
| Type of Database | MySQL Improved |
| Hostname (for Database Server) | mantis_db_1 |
| Username (for Database) | mantisbt |
| Password (for Database) | mantisbt |
| Database name (for Database) | bugtracker |
| Admin Username (to create Database if required) | root |
| Admin Password (to create Database if required) | root |

Após preencher, clicar em **Login/Continue** e aguardar o processamento.

O primeiro acesso deverá ser feito utilizando as credenciais *administrator/root*. Redefinir a senha para o valor *administrator* ou outro valor fácil de lembrar.

  

## **1.3 Acessar banco de dados Mantis/MariaDB**

Para acessar ao banco de dados do Mantis (MariaDB) siga os passos abaixo:

1. Baixe e instale o [software HeidiSQL](https://www.heidisql.com/download.php)

2. Ao abrir o Gerenciador de sessões, preencha com os valores abaixo:

![](https://i.imgur.com/AhKMxvu.png)

3. Abra a conexão e será possível verificar todas as tabelas e registros:

![](https://i.imgur.com/EnYk6Md.png)

## 2. Mantis Bug Tracker REST API

Uma vez com a aplicação sendo executada pelo Docker, é possível também realizar testes manuais ou automatizados de API Rest no Mantis.

Basta acessar a [documentação oficial Mantis Bug Tracker REST API](https://documenter.getpostman.com/view/29959/mantis-bug-tracker-rest-api/7Lt6zkP) para visualizar cada endpoint, parâmetros, headers correspondentes.

![](https://i.imgur.com/rLg6Q54.png)

É possível também importar todos os endpoints diretamente no Postman para testar ou automatizar esta API Rest. Basta clicar no botão indicado:


### O Token é um parâmetro esssencial nas requisições do Mantis Bug Tracker REST API, para gerá-lo:

1. Acesse o sistema Mantis com o usuário administrador - http://127.0.0.1:8989

2. Acesse o menu com nome do usuário/Minha Conta

![](https://i.imgur.com/6OHC06W.png)

3. Clique na aba **Tokens API** 

4. Preencha um novo nome para o token e clique em **Criar Token API**

![](https://i.imgur.com/wp7IIFh.png)

5. Copie o Token gerado e use-o como header em requisições nas suas automações (RestSharp, Postman, SuperTest, RestAssured e demais).

![](https://i.imgur.com/7sybiId.png)

Para a instância local deverá ser usada a url de parâmetro **localhost** com a porta correspondente **8989**. 
Exemplo de execução no Postman:

![](https://i.imgur.com/sSofy8o.png)


## 3. Selenium Grid
Para a execução remota dos testes automatizados, via selenum grid, serão utilizados os seguintes passos:

  

- Configuração dos contêineres hub, node chrome e node mozilla

- Verificação do console

- Automação de testes e Selenium Grid

  

**3.1 Configuração dos contêineres hub, node chrome e node mozilla**

- Abrir o prompt de comando

- Validar que os contêineres estarão disponíveis executando o comando `docker ps -a`:

- selenium/node-firefox

- selenium/node-chrome

- selenium/hub



**3.2 Verificação do console**

  

Após o processamento, os containeres estarão disponíveis e em execução. Em vermelho os referentes ao Selenium:

![enter image description here](https://i.imgur.com/iXBkZkT.png)


Ao executar o comando no navegador `http://127.0.0.1:4444/grid/console` também é possível verificar o console rodando corretamente com seus nós:

![enter image description here](https://i.imgur.com/V3choXC.png)

**3.3 Automação de testes e Selenium Grid**
Basta fazer as devidas configurações de Remote WebDriver no seu projeto de testes automatizados que os testes poderão ser executados remotamente. 
Se necessário suba mais containeres para multiplicar os nós.

[Exemplo de configuração Remote Driver - BrowserStack.](https://www.browserstack.com/guide/difference-between-selenium-remotewebdriver-and-webdriver)

![](https://i.imgur.com/Eu4JiJm.png)

4.0 WEBDAV

5.0 JENKINS 

6## 4. ![hp4](https://user-images.githubusercontent.com/22267601/142078606-90c7363a-d6d1-4874-afcb-0756fa518e6a.png)

:heavy_check_mark: Foram Criados mais de 50 Scripts de Automação Utilizando as API's </br>

![casosdeTeste](https://user-images.githubusercontent.com/22267601/141992815-a8b225e5-e44f-41fa-a79a-e7aa22be118c.png)

:heavy_check_mark: Criação de Projetos e Usuários Utilizando DataDriven </br>

![dataUser](https://user-images.githubusercontent.com/22267601/141995202-ed804bca-bdbe-4688-946b-beb56e380af6.png)</br>


![projeto](https://user-images.githubusercontent.com/22267601/141995279-b8c8cb97-eeed-46ba-ad17-57ef6059ac16.png)

:heavy_check_mark: Nome de Projeto e Usuário utilizando uma String Randomica gerada através do JavaScript(Node.Js) </br>

![classe](https://user-images.githubusercontent.com/22267601/141994542-218f78a2-14f0-4db1-ad34-64e7d4d551f3.png)
 
:heavy_check_mark: Script Utilizado </br>

![funcao](https://user-images.githubusercontent.com/22267601/141994442-75abbfa9-0035-4975-b3f0-73c238d49695.png)

:heavy_check_mark: Criação de Queries para Inserir e Deletar informações necessárias </br>

![queries](https://user-images.githubusercontent.com/22267601/141994040-365db98f-1e5b-4800-beb5-90f9cd6bb4e0.png)

:heavy_check_mark: Utilização do Jenkins como Ambiente de CI para rodar e também mostrar o relatório gerado </br>

![relatorio](https://user-images.githubusercontent.com/22267601/141993615-3380dc4b-a8cd-46ee-9180-2ff245db5669.png)










