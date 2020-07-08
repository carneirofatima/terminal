


**Projeto: Terminal de Caminhões**
-
Plataforma para controlar motoristas que passam por um terminal, desde o cadastro de um caminhoneiro, até buscas de fluxo de veículos em um determinado período, por exemplo.
_________

## *Sobre o projeto*

***Principais funcionalidades***  🚚

 - Cadastra um motorista, sendo obrigatório informar o email e telefone do mesmo e não é possível adicionar essas informações para um segundo caminhoneiro;
 - Lista caminhoneiros que possuem veículo próprio;
 - Lista caminhoneiros que não possuem carga para voltar ao seu destino;
 - Lista todos os caminhoneiros que passaram pelo terminal durante um período;
 - Adiciona visita de um motorista no terminal;
 - Lista destinos;
 - Lista origens;
 
 **Importante:** para cadastrar um motorista, é necessário informar nome, gênero, se possui veículo próprio, telefone, email,  se o caminhão já possui carga no veículo, idade, tipo do veículo e tipo da CNH;

 🚗 *Tipo veículo:*
|Tipo de caminhões | Código  |
|--|--|
| Caminhão 3/4 | 1  |
|Caminhão Toco|2|
|Caminhão Truck|3|
|Caminhão Simples|4|
|Carreta Eixo Extendido|5|



📄  *Tipo CNH:*

 
|Categoria| Código   |
|--|--|
|🏍️  A| 1 |
|🚗 B  | 2 |
|🚛 C  |  3|
|🚌 D  |  4|
|🚚 E  |  5|


 

 ***Tecnologias usadas*** 💻
 

 - Java;
 - JUnit;
 -  Maven;
 -  Lombok;
 -  Mockito;
 - Spring JPA;
 - Spring Boot;
 
 ---
 

## *Como instalar e rodar* 👩‍💻
***Pré-requisitos:***

✔️ Possuir docker instalado na máquina;
✔️ Possuir uma JDK baixada e configurada na máquina;
✔️ Possuir uma IDE de tua preferência para rodar  e analisar o projeto.

 ---
***Instruções:***

 1. Clonar este repositório em sua máquina, colando o comando abaixo em seu terminal:
  `git clone https://github.com/carneirofatima/terminal.git`
 
 2. Importar o projeto para IDE que estiver utilizando;
 3. Subir o banco MySQL com o seguinte comando: 
 

    `docker run --name terminal -mysql -p3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MY_SQL_DATABASE=terminal -d mysql:5.6`
    

 4. Subir a aplicação através do terminal.
