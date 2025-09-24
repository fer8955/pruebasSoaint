# 🧪 Automatización de API - Sección Usuários | [Serverest.dev](https://serverest.dev).

Este proyecto automatiza la validación de los endpoints relacionados con **usuarios** de la API pública [Serverest](https://serverest.dev), utilizando el framework [Karate](https://github.com/karatelabs/karate) para pruebas de servicios REST.


## Src

En la carpeta ubicada en la raíz del proyecto, se encuentra el proyecto realizado a la api de usuarios de ServeRest https://serverest.dev/

# Guía de Uso del Framework Karate

Este proyecto utiliza el framework Karate para pruebas de API. El proyecto está estructurado en Java y Maven.

## Estructura del Proyecto

La estructura del proyecto es la siguiente:

- `src/test/java/karate-config.js`: Este archivo contiene la configuración para las pruebas de Karate.
- `src/test/java/karate/runner`: Este directorio contiene los archivos de ejecución de las pruebas.
- `src/test/java/karate/utils`: Este directorio contiene los archivos de utilidades para las pruebas.
- `src/test/java/resources/features`: Este directorio contiene los archivos de características que definen los escenarios de prueba.
- `src/test/java/resources/request`: Este directorio contiene los archivos de archivos json para realizar mock de data.

## Configuración de entornos

En el archivo `karate-config.js` se encuentran las configuraciones de los entornos de integración, certificación y producción.

  ```javascript
  function fn() {
      var env = karate.env; 
      if (!env) {
          env = 'integracion'; 
      }
  
      var config = { 

      };

      if (env == 'integracion') {
     
      }

      if (env == 'certificacion') {
     
      }
      
      if (env == 'produccion') {

      }
      
      return config;
  }
  ```
## Ejecución de Pruebas

Los escenarios de prueba se definen en los archivos `.feature` en el directorio `src/test/java/resources/features`. Cada escenario está etiquetado con una etiqueta para su fácil identificación y ejecución.

Por ejemplo, para ejecutar el escenario de prueba etiquetado con `@postUsers`, use el siguiente comando:

## Previamente - Instalar Programas y Dependecias

- Tener instalado
  . Java, JDK
  . Karate
  . Maven

- Para la instalación de dependecias abrir la terminar, dirigirse a la ruta del proyecto y ejecutar el siguiente comando

 	mvn test mvn clean install


Para Windows/Linux/Mac:
```bash
# Ejecución de pruebas de todos los endpoints
mvn test -Dkarate.env="certificacion"
```
# Ejecucion de pruebas
1- Dirigirse al archivo: src/test/java/karate/runner/TestRunner.java
2- Ejecutar la linea 11 o 14

# Ejecución de pruebas por Tags

1- Dirigirse al archivo: src/test/java/karate/runner/TestRunner.java
2- En la linea 15 del archivo, completar la ruta del feature a probar
3- En la linea 16, descomentarla y colocar despues del "@" el nombre del tags a ejecutar
4- Ejecutar la linea 11 o 14

# Ejecución paralela

Para ejecutar de manera paralela:
1- Dirigirse al archivo: src/test/java/karate/runner/TestRunner.java
2- En la linea 18 colocar de parametro el número de hilos a probar.
3-
```bash
mvn test -Dkarate.env="certificacion"
```
