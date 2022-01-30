
# Projeto Desafio Mantis
![](https://i.imgur.com/U21ILCg.png)
  
Este projeto foi criado com o intuito de fornecer um sistema com **Front-end** e um **banco de dados** de forma simples para que QAs possam praticar automação de testes. 

O sistema alvo é o [Mantis BugTracker](https://www.mantisbt.org) e é utilizado o Docker para gestão do ambiente e banco de dados.

:point_right: Setup inicial de projeto com Docker (MariaDB, Jenkins e  Mantis BT) </br>
:point_right: Criação de testes DataDriven para Usuários e Projetos lendo de um arquivo Excel </br>
:point_right: Criação de modelo de dados para o mapeamento de entidades em bancos de dados </br>
:point_right: Desenvolvimento de operações para: Criar,Remover, Atualizar e Buscar (Issues,Projetos,Usuários, Filtros e Configurações) </br>
:point_right: Desenvolvimento de testes unitários para validação de funcionalides básicas: Criação, listagem, remoção, atualização de (Usuários,Projetos,Issues,Filtros e Configurações) </br>
:point_right:  Criação de  Script para criar uma string randomica em Java Script </br>
:point_right: Criação de testes DataDriven para Usuários e Projetos lendo de um arquivo Excel  Ambiente de CI utilizando o Jenkins
:point_right: Execução Parelela de Teste


## Arquitetura 

- Arquitetura Projeto
	- Linguagem	- [Java](https://www.java.com/pt-BR// "Java")
	- [Java Kit Development versão 8](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
	- Gestão de dependências - [Maven](https://maven.apache.org)
	- Framework de Testes automatizass web - [Selenium.WebDriver 3.141](https://www.seleniumhq.org/download/ "Selenium.WebDriver") 
	- Orquestrador de testes - [TestNG](https://testng.org/doc/ "TestNG")
	- Relatório de testes automatizados - [ExtentReports 4.0.9](http://www.extentreports.com/docs/versions/4/java/index.html "ExtentReports 4.0.9")

## ![1imagem](https://user-images.githubusercontent.com/22267601/151709066-48e2ea2c-182d-460f-b372-b1304e0b9764.png)


- Versão 1.8 do Java JDK instalada no computador (https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)
- Versão community do IntelliJ IDEA instalada no computador (https://www.jetbrains.com/pt-br/idea/download/#section=windows)
- Última versão do Google Chrome instalada no computador (https://www.google.com/chrome/) - neste momento Google Chrome 86
- Baixar o projeto, construi-lo e acessar a pasta tests que estará o teste de exemplo.

**Premissas de uma boa arquitetura de automação de testes:**
*  Facilitar o desenvolvimento dos testes automatizados (reuso).
*  Facilitar a manutenção dos testes (refatoração).
*  Tornar o fluxo do teste o mais legível possível (fácil entendimento do que está sendo testado).

**Arquitetura padrão Base2**

Para facilitar o entendimento da arquitetura do projeto de testes automatizados, foi criado um fluxograma baseado nas features principais que envolvam a arquitetura conforme imagem abaixo:

![alt text](https://i.imgur.com/wexOWJF.png)


# ![2imagem](https://user-images.githubusercontent.com/22267601/151716141-1b2a5f35-5ba6-498c-ae1f-720b38310b6e.png)

Serão necessárias as seguinte configurações para iniciar o projeto:

**Docker-compose:**  neste repositório é possível encontrar um arquivo chamado "docker-compose.yml", este arquivo contem um grupo de imagens do Mantis, seu banco de dados e o Selenium Grid com seus nós (Mozilla Firefox e Google Chrome). 

Crie o diretório local "C:\mantis", baixe o arquivo **docker-compose.yml** e cole neste diretório criado.

##  Setup Docker Mantis

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
  

## Configuração inicial Mantis

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

  

## Acessar banco de dados Mantis/MariaDB

Para acessar ao banco de dados do Mantis (MariaDB) siga os passos abaixo:

1. Baixe e instale o [software HeidiSQL](https://www.heidisql.com/download.php)

2. Ao abrir o Gerenciador de sessões, preencha com os valores abaixo:

![](https://i.imgur.com/AhKMxvu.png)

3. Abra a conexão e será possível verificar todas as tabelas e registros:

![](https://i.imgur.com/EnYk6Md.png)



## Selenium Grid
Para a execução remota dos testes automatizados, via selenum grid, serão utilizados os seguintes passos:

  

- Configuração dos contêineres hub, node chrome e node mozilla

- Verificação do console

- Automação de testes e Selenium Grid

  

**Configuração dos contêineres hub, node chrome, node mozilla e node edge**

- Abrir o prompt de comando

- Validar que os contêineres estarão disponíveis executando o comando `docker ps -a`:

- selenium/node-firefox

- selenium/node-chrome

- selenium/hub



** Verificação do console**

  

Após o processamento, os containeres estarão disponíveis e em execução. Em vermelho os referentes ao Selenium:

![enter image description here](https://i.imgur.com/iXBkZkT.png)


Ao executar o comando no navegador `http://127.0.0.1:4444/grid/console` também é possível verificar o console rodando corretamente com seus nós:

![enter image description here](https://i.imgur.com/V3choXC.png)

**2.3 Automação de testes e Selenium Grid**
Basta fazer as devidas configurações de Remote WebDriver no seu projeto de testes automatizados que os testes poderão ser executados remotamente. 
Se necessário suba mais containeres para multiplicar os nós.

[Exemplo de configuração Remote Driver - BrowserStack.](https://www.browserstack.com/guide/difference-between-selenium-remotewebdriver-and-webdriver)

![](https://i.imgur.com/Eu4JiJm.png)

## ![3imagem](https://user-images.githubusercontent.com/22267601/151716237-d017dc23-6f8d-4964-89d0-0a985d21f78b.png)


Para Acessar o WebDav entre no endereço 127.0.0.1:8090/data/


* É necessário a Criação de 2 pastas no conteiner na pasta /var/lib/dav/data/ do container do WebDav: Downloads e Upload(dentro dessa pasta é necessário criar um arquivo de teste chamado teste.txt) 
* Dar permissão para essas pastas chmod777

![2022-01-30 16_51_48-Index of _data](https://user-images.githubusercontent.com/22267601/151715861-ce677a03-0212-4469-a54d-163f93cc4fc1.png)</br>
![2022-01-30 16_50_49-ProjetoWebNovo – WebdavUtils java](https://user-images.githubusercontent.com/22267601/151715873-9b115334-37d3-40b1-842e-55d79d70f979.png)

## ![4imagem](https://user-images.githubusercontent.com/22267601/151716342-f9c3b29c-7ff8-4881-8944-6796c7586c92.png)

## ![5imagem](https://user-images.githubusercontent.com/22267601/151716398-8ffaefcb-ee8a-4475-9f6b-6f213358d181.png)

:heavy_check_mark: Foram Criados mais de 50 Scripts de Automação </br>

![2022-01-30 14_08_02-ProjetoWebNovo – GerenciarProjetosTests java](https://user-images.githubusercontent.com/22267601/151715336-288c8fcb-2dad-4bee-bbe4-0669462f62a2.png)

:heavy_check_mark: Criação de Projetos e Usuários Utilizando DataDriven </br>

![2022-01-30 16_47_26-Window](https://user-images.githubusercontent.com/22267601/151715412-67634f48-b7e9-4212-bab6-4c45c0c750cc.png) )</br>
![2022-01-30 16_48_41-TestData xls](https://user-images.githubusercontent.com/22267601/151715421-79e3ae8c-41d1-469a-aef3-108428396924.png)


:heavy_check_mark: Nome de Projeto e Usuário utilizando uma String Randomica gerada através do JavaScript(Node.Js) </br>

![2022-01-30 17_06_26-ProjetoWebNovo – ExecutarJavaScriptNode java](https://user-images.githubusercontent.com/22267601/151715756-3b1d0e1f-01ae-4717-b05a-12d861e7f084.png)
 
:heavy_check_mark: Script Utilizado </br>

![2022-01-30 16_46_04-ProjetoWebNovo – example js](https://user-images.githubusercontent.com/22267601/151715685-a662bf8b-ae08-4b74-8057-1c40166dbdba.png)

:heavy_check_mark: Criação de Queries para Inserir e Deletar informações necessárias </br>

![2022-01-30 16_44_54-ProjetoWebNovo – deletarCategoria sql](https://user-images.githubusercontent.com/22267601/151715808-8cbba661-de0a-4da0-b395-4c7e8adce189.png)


:heavy_check_mark: Utilização do Jenkins como Ambiente de CI para rodar e também mostrar o relatório gerado </br>

![relatorio](https://user-images.githubusercontent.com/22267601/141993615-3380dc4b-a8cd-46ee-9180-2ff245db5669.png)



:heavy_check_mark: Execução Paralela de Testes </br>

![2022-01-30 16_50_12-ProjetoWebNovo – testngsuite xml](https://user-images.githubusercontent.com/22267601/151716054-9c8b9a4d-305e-4acf-9de1-a1cf3fbbd98a.png)






