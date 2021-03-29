# devChallenge
Desafio Colocado pela 2iBi para Recrutamento


# Como Usar

Esta API estará disponivel em https://devchallenge-backend.herokuapp.com (se Ocorrer modificações irei atualizar esta URL) e usando ferramentas como POSTMAN e outros clientes REST será possivel invocar os diferentes Paths que existem ( Os quais estão listados abaixo).

# Como Está Estruturada a API

O Model usado para o País foi { long id, String nome, String capital, Continent continente, String regiao} , decidi em vez de usar Região e Sub_Região usar Continente que é um Enum e Região que vai conter informação similar a "Africa Austral" ou "Asia Ocidental", que foi a minha interpretação do uso dos parametros Região e Sub_Região

Existem 6 paths :

  # 3 Gets 
    --- > "/api/countries" onde obtem-se todos os Países registrados
    --- > "/api/country/{id}" onde obtem-se os dados de um País através do seu ID
    --- > "/api/sortedCountries" onde obtem-se os Países ordenados de acordo com parametros enviados ( Este GET recebe um array de String que contem os parametros para
     Ordenação  )
     
  # 1 POST
     --- > "/api/createCountry" este recebe um JSON com os parametros do País e cria este se não existir outro País com o mesmo nome (Fui com o principio que não existem 
     dois países com o mesmo nome)
  
  # 1 PUT
    --- > "/api/updateCountry/{id}" este recebe um JSON com os parametros que deseja se actualizar do País com o ID especificado no Path
  
  # 1 DELETE
    --- > "/api/deleteCountry/{id}" este elimina o País com o ID especificado no Path 
