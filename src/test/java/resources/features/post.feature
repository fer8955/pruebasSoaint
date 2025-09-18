@postUsers
Feature: API Users - Post

  Background:
    * url baseUrl
    * path '/usuarios'
    * def body = read('classpath:resources/request/user.body.json')
    * def schema = read('classpath:resources/request/user.schema.json')

  @201
  Scenario: POST Users - Success

    #Generar data
    * def dataGenerator = Java.type('karate.helpers.dataPostGenerator')
    * def nome = dataGenerator.generateUniqueNome()
    * def email = dataGenerator.generateUniqueEmail()
    * def admin = dataGenerator.getRandomBooleanAsString()
    * def pass = dataGenerator.generateUniquePass()
    * body.post.nome = nome
    * body.post.email = email
    * body.post.password = pass
    * body.post.administrador = admin

    Given request body.post
    When method post
    Then status 201
    And match response == schema.post["201"]

  @400
  Scenario: POST Users - Error - Email Used
    #Obtener un email reutilizando el metodo get
    * def email = call read('classpath:resources/features/get.feature@200')
    #Guardar el email en el body
    * body.post.email = email.response.usuarios[0].email
    Given request body.post
    When method post
    Then status 400
    And match response == schema.post["400"]

  @400
  Scenario Outline: POST Users - Error - Empty field
    * print '<case>'
    * body.post.<value> = ""
    Given request body.post
    When method post
    Then status 400
    And match response.<value> == "<message>"
    Examples:
      |read('classpath:karate/util/dataPost400Empty.csv')|

  @400
  Scenario Outline: POST Users - Error - Missing field
    * print '<case>'
    * karate.remove('body', 'post.<value>')
    Given request body.post
    When method post
    Then status 400
    And match response.<value> == "<value> é obrigatório"
    Examples:
      |read('classpath:karate/util/dataPost400Empty.csv')|