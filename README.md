# E-commerce

Este projeto simula um serviço básico de e-commerce, onde um cliente seleciona os produtos, quantidade, forma de pagamento e o frete, e realiza o checkout (vê todos os detalhes da  transação) antes de finalizar a compra. A aplicação dá ainda a possibilidade de cadastrar novos clientes, produtos e transportadoras.

# Executando

A imagem da aplicão está disponível no Docker Hub, assim como o banco de dados utilizado (MySQL) e podem ser executdas utilizando o arquivo [docker-compose.yml](https://github.com/andreld/ecommerce/blob/main/docker-compose.yml) presente neste repositório.

Para testar as funcionalidades é necessária alguma ferramenta para consumo de APIs REST como o Postman.

Abaixo segue um resumo das funcionalidades do aplicativo:

![image](https://user-images.githubusercontent.com/22616575/115384231-2d846e80-a1ad-11eb-89fe-1f96ed736e2c.png)

Abaixo a URI de cada recurso, com seu respectivo método HTTP.

  base: http://localhost:8081
  ### Cliente
    - cadastro: POST /cliente
    - atualização: PUT /cliente/:id
    - listagem: GET/cliente
    - busca: GET/cliente/:id
 
  Exemplo de corpo da requisição para POST e PUT:
   
   {
    "nome": "Eurico Fernandes",
    "cpfCnpj": "12345678900",
    "telefone": "98987654321",
    "tipoPessoa": 0,
    "email": "email58@email.com",
    "endereco": {
        "id": 1,
        "cep": "65000000",
        "numero": 10,
        "rua": "Rua E",
        "bairro": "Village Jardins II",
        "complemento": "Bloco C1, apto. 110",
        "cidade": "São Luís",
        "estado": "Maranhão"
    }
   }
   
   No caso do POST não passamos o id do endereço, no PUT passamos o id do cliente na URI.
   
   ### EstoqueProduto
    - cadastro: POST /estoque-produto
    - atualização: PUT /estoque-produto/:id
    - listagem: GET/estoque-produto
    - busca: GET/estoque-produto/:id
   
   Exemplo de corpo da requisição para POST e PUT:
   
   
    {
      "descricao": "Laptop i5 8GB 500GB SSD",
      "codigoBarras": "101010101010",
      "valor": "4899.99",
      "quantidade": "50"
    }
    
   no PUT passamos o id do produto na URI.

   ### Transportadora
    - cadastro: POST /transportadora
    - atualização: PUT /transportadora/:id
    - listagem: GET/transportadora
    - busca: GET/transportadora/:id
   
   Exemplo de corpo da requisição para POST e PUT:
   
    
    {
      "nome": "FreteRapido",
      "cpfCnpj": "04285889000170",
      "telefone": "0800 700 7000",
      "tipoPessoa": 1,
      "email": "atendimento@freterapido.com.br",
      "valorFrete": 15.50
    }
    
   no PUT passamos o id da transportadora na URI.
   
   obs.: tipoPessoa é uma enumeração que aceita 0 ou 1 (pessoa FÍSICA OU JURÍDICA)
    
   Os recursos da venda cuidam do negócio mais sensível da aplicação, e tem três principais funcionalidades, sendo cada uma o passo anterior da outra, na ordem: Colocar itens no    carrinho, fazer checkout e finalizar a compra.
   
   ### Venda
    - adiciona item ao carrinho: POST /cliente/id/venda/carrinho
    
    {
       "itensCarrinho": [
          {
              "estoqueProdutoId": 1,
              "quantidade": 2
          },
          {
              "estoqueProdutoId": 3,
              "quantidade": 5
          }
       ]
    }
    
    - checkout: POST /cliente/id/venda/checkout/:id
      - adiciona informação de frete, forma de pagamento (0, 1 ou 2, respectivamente BOLETO, DEBITO e CRÉDITO) e número de parcelas.

     {
        "formaPagamento": 1,
        "numeroParcelas": 1,
        "transportadoraId": 1,
        "itensCarrinho": [
            {
                "estoqueProdutoId": 1,
                "quantidade": 2
            },
            {
                "estoqueProdutoId": 3,
                "quantidade": 5
            }
        ]
    }
    
    - finaliza venda: POST /cliente/id/venda/finalizar
      - os mesmos dados do chekout, mas, neste caso, persiste os dados no banco e encerra a transação. Além disso subtrai a quantidade do produto no estoque.

    obs.: os recursos da venda tem em sua URI o respectivo cliente que está realizando a compra.
