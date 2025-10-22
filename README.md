# atividade-xpto

#

<h3>Depedências utilizadas no projeto</h3>
<ul>
<li>Spring JPA -> Conexão com banco de dados, mapeamento de tabelas e entidades</li>
<li>Lombok -> Anotações para facilitar declaração de métodos</li>
<li>Mapstruct -> Conversão de entidades concretas para entidades dto</li>
<li>Spring Web -> Rest apis</li>
<li>H2 Database -> Banco de dados em memória</li>
<li>Postgre sql -> Banco de dados</li>
<li>Spring boot validation -> Validar inserções com anotações</li>
<li>Springdoc openapi -> documentação interativa da API</li>
</ul>

<hr>

<h3>Acesse a documentação e teste os Endpoints</h3>
<p>http://localhost:8080/swagger-ui/index.html</p>

<h3>Diagrama de Classes<h3>

```mermaid
classDiagram
    class Cliente {
        +String cliente_id
        +String nome
        +String email
        +String telefone
        +datetime data_cadastro
        +List~Endereco~ enderecos
        +List~Conta~ contas
    }

    class ClientePF {
        +String cpf
        +String rg
    }

    class ClientePJ {
        +String cnpj
    }

    class Endereco {
        +Integer id
        +String rua
        +String numero
        +String bairro
        +String cidade
        +String estado
        +String cep
        +Cliente cliente
    }

    class Conta {
        +Integer id
        +String agencia
        +String numero_conta
        +String tipo_conta
        +Decimal saldo
        +datetime data_criacao
        +Cliente cliente
        +List~Movimentacao~ movimentacoes
    }

    class Movimentacao {
        +Integer id
        +datetime data_movimentacao
        +String tipo_movimentacao
        +Decimal valor
        +String descricao
        +Conta conta
    }

%% Relacionamentos
    Cliente "1" -- "0..*" Endereco : possui
    Cliente "1" -- "0..*" Conta : possui
    Conta "1" -- "0..*" Movimentacao : registra

%% Herança (Especialização de Cliente)
    ClientePF --|> Cliente : é um
    ClientePJ --|> Cliente : é um
```



