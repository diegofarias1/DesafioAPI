# Desafio testes de API
- Arquitetura Projeto
    - Linguagem - Java
    - Orquestrador de testes - [Maven 3.6.3](https://maven.apache.org/download.cgi)
    - Relatório de testes automatizados - [ExtentReports 4.1.3](https://mvnrepository.com/artifact/com.aventstack/extentreports/4.1.3)
    - Framework interação com API - [Rest Assured 4.3.0](https://github.com/rest-assured/rest-assured/wiki/Downloads)
    - Denpendências do projeto (pom.xml) - [Maven Repository](https://mvnrepository.com/)

##  Testes realizados
### Restrições

##### Consultar uma restrição pelo CPF
`GET <host>/api/v1/restricoes/{cpf}`

-[X] Se não possui restrição do HTTP Status 204 é retornado 
-[X] Se possui restrição o HTTP Status 200 é retornado com a mensagem "O CPF 99999999999 possui restrição"
* Realizado consulta em um CPF sem restrição e aguardando status 204
* Realizado consulta utilizando DDT para consultar todos os CPF listados abaixo:


CPFs com restrição
| CPF         |
|-------------|
| 97093236014 |
| 60094146012 |
| 84809766080 |
| 62648716050 |
| 26276298085 |
| 01317496094 |
| 55856777050 |
| 19626829001 |
| 24094592008 |
| 58063164083 |

## Simulações

### Criar uma simulação

`POST <host>/api/v1/simulacoes`

-[X] Uma simulação cadastrada com sucesso retorna o HTTP Status 201 e os dados inseridos como retorno
-[X] Uma simulação com problema em alguma regra retorna o HTTP Status 400 com a lista de erros
-[X] Uma simulação para um mesmo CPF retorna um HTTP Status 409 com a mensagem "CPF já existente"

### Alterar uma simulação
`PUT <host>/api/v1/simulacoes/{cpf}`
Altera uma simulação já existente, onde o CPF deve ser informado para que a alteração possa ser efetuada.

-[X] A alteração pode ser feita em qualquer atributo da simulação
-[X] As mesmas regras se mantém
-[X] Se o CPF não possuir uma simulação o HTTP Status 404 é retornado com a
mensagem "CPF não encontrado"

### Consultar todas a simulações cadastradas

`GET <host>/api/v1/simulacoes`

Lista as simulações cadastradas.

-[X] Retorna a lista de simulações cadastradas e existir uma ou mais
-[X] Retorna HTTP Status 204 se não existir simulações cadastradas

### Consultar uma simulação pelo CPF

`GET <host>/api/v1/simulacoes/{cpf}`

Retorna a simulação previamente cadastrada pelo CPF.

-[X] Retorna a simulação cadastrada
-[X] Se o CPF não possuir uma simulação o HTTP Status 404 é retornado

### Remover uma simulação

`DELETE <host>/api/v1/simulacoes/{id}`

Remove uma simulação previamente cadastrada pelo seu ID.

-[X] Retorna o HTTP Status 204 se simulação for removida com sucesso
-[X] Retorna o HTTP Status 404 com a mensagem "Simulação não encontrada" se não existir a simulação pelo ID informado