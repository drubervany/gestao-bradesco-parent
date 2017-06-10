| Data | Autor | Comentários | Versão |
| --- | --- | --- | --- |
| 09/03/2017 | Danilo Rubervany Dias | Versão inicial | 1.0 |  

# Sobre esta aplicação

Este projeto é responsável por ler os arquivos de cadastro, fatura e sinistro da Bradesco.

#Sobre a arquitetura 
Este projeto está modelado com base na arquitetura hexagonal com spring boot.

A arquitetura hexagonal está modelada da seguinte forma:

> gestao-bradesco-parent

>> bradesco-adapter-inbound-parent

>>> bradesco-activemq-adapter-inbound

>> bradesco-adapter-outbound-parent

>>> bradesco-dynamodb-adapter-outbound

>> bradesco-build

>> bradesco-core-parent

>>> bradesco-core-application

>>> bradesco-core-port-commons

>>> bradesco-core-port-inbound

>>> bradesco-core-port-outbound



	* gestao-bradesco-parent
   
   Projeto centralizador do microserviço consultar, ativar e desativar Identificadores (TAG).

	* bradesco-adapter-inbound-parent

   Projeto pai (Inbound/Primary Adapters), com os possíveis projetos filhos de implementação dos adapters. 

	* bradesco-activemq-adapter-inbound

   Adapter de entrada do core-application. Implementação da chamada em que o adapter converte um JSON para o DTO e enviar para o core-application.

	* bradesco-adapter-outbound-parent
   
   Projeto pai (Outbound/Secondary Adapters), com os possíveis projetos filhos de implementação dos adapters.

	* bradesco-dynamodb-adapter-outbound
	
   Implementação do Adapter de port-outbound, aplicando uma lista de regras de validação nos dados recebidos da fila, gravando no Dynamodb, em caso de sucesso na validação.

	* bradesco-build

   Projeto que gera um runnable jar com que foi definido no pom.xml. Adapter de entrada e Adapter de saída podem ser alterados.
   
	* bradesco-core-parent

   Projeto core centralizador das dependências do core e dos modulos (commons, port inboud, port outbound, core application)

	* bradesco-core-application

   Projeto core-application responsável pela centralização das regras de negócio.   

	* bradesco-core-port-commons

   Projeto port-commons responsável pelos objetos comuns entre todos os modulos do core (commons, port inboud, port outbound, core application)

	* bradesco-core-port-inbound

   Projeto port-inbound responsável pelas interfaces primárias do projeto core (Primary Ports)

	* bradesco-core-port-outbound

   Projeto port-outbound responsável pelas interfaces secundárias do projeto core (Secondary ports)

	
	
# run application 

   Para executar esta aplicação basta ir na pasta de target do projeto build e executar o comando: java -jar ${aplicacao}.jar
   

