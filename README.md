## Payment App

App desenvolvido para processar transaçoes de pagamentos.
Segue as respostas dos questionamentos apresentados:

Uma explicação sobre as decisões técnicas e arquiteturais do seu desafio

R - Tinha visto uma apresentacao do pessoal da Nubank falando do uso do Hexagonal, como já uso
no projeto que atuo hoje, tentei as boas práticas que o desenho já tem.

Uma justificativa para o uso de frameworks ou bibliotecas

R - Utilizei o Java 11 porque é minha linguagem que atuo a mais tempo e porque tem os recursos de stream e lambdas.
Utilizei o Lombok para diminuir a quantidade de código escrito, simplificar, ser mais produtivo e facilitar o entendimento.
Utilizei o Jackson para fazer a conversao da string para objeto de forma simplificada e rápida.
Utilizei o pacote Junit e Mockito para realizar os testes de cobertura. Coloquei os testes somente nas classes de negócio porque
garante que qualquer evolucao no código esteja coberto pelo teste.

Instruções sobre como compilar e executar o projeto

R - Instrucoes abaixo

## IDE
- IntelliJ

## Pré requisitos
- Java 11
- Maven
- Docker

## Frameworks
- Lombok
- Jackson
- Junit
- Mockito

## Rodando os testes unitários

(No diretório do projeto):
```shell
$ mvn clean install
```

## Rodando a app no terminal

(No diretório do projeto e considerando que o arquivo "operations" vai esta na mesma pasta do jar):
```shell
$ java -jar target/payment-0.0.1-spring-boot.jar < operations
```

## Rodando a app no docker

(No diretório do projeto):

passo 1
```shell
$ docker build -t docker-payment .
```

passo 2
```shell
$ docker run --rm -i docker-payment < operations
```

─╯
