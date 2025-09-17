# Guía de Uso del Framework Playwright (Preview)
Este proyecto utiliza el framework Playwright para pruebas automatizadas. El proyecto está estructurado en TypeScript y utiliza Playwright para la automatización de navegadores.

Se ha rediseñado para mantener el uso de Playwright lo más limpio y natural posible, facilitando la escritura y mantenimiento de las pruebas.

## Estructura del Proyecto

La estructura del proyecto es la siguiente:

- `src/playwright/locators`: Directorio para los localizadores de elementos.
- `src/playwright/pages`: Directorio para las clases de páginas.
- `src/playwright/steps`: Directorio para los pasos de las pruebas.
- `src/resources/features`: Directorio para los archivos de características (features) de Cucumber.
- `src/resources/fixtures`: Directorio de recursos de data json
- `./playwright.config.ts`: Archivo de configuración de Playwright.
- `./playwright.env`: Archivo de configuración de variables de entorno para Playwright.


## Instalación de Dependencias
Instala las dependencias necesarias para el proyecto ejecutando:

```bash
npm install
```

## Instalación de Navegadores
Instala las dependencias de navegadores soportados

```bash
npx playwright install
```

## Configuración de Browser
El archivo de configuración de Playwright `playwright.config.ts` define los proyectos y dispositivos a utilizar en las pruebas. Configure lo necesario para su uso; el proyecto por defecto viene configurado con Chromium.

Aquí hay un ejemplo de configuración:

```ts
import { defineConfig, devices } from '@playwright/test';

export default defineConfig({
  projects: [
    {
      name: 'chromium',
      use: { ...devices['Desktop Chrome'] }
    },
    {
      name: 'firefox',
      use: { ...devices['Desktop Firefox'] },
    },
    {
      name: 'webkit',
      use: { ...devices['Desktop Safari'] },
    },
    {
      name: 'Microsoft Edge',
      use: { ...devices['Desktop Edge'], channel: 'msedge' },
    },
    {
      name: 'Google Chrome',
      use: { ...devices['Desktop Chrome'], channel: 'chrome' },
    }
  ]
```

Esta configuración permite ejecutar pruebas en múltiples navegadores, asegurando que tu aplicación funcione correctamente en diferentes entornos. Puedes ajustar o agregar más navegadores según tus necesidades específicas.

## Configuración de Entorno

El archivo `playwright.env` contiene las variables de entorno necesarias para la ejecución de las pruebas. Asegúrate de configurar las variables adecuadamente antes de ejecutar las pruebas.

Cada nueva variable de entorno debe seguir el formato basado en el entorno correspondiente: `INTE`, `CERT`, `PROD`. 

A continuación, se muestra un ejemplo de cómo definir estas variables en el archivo `playwright.env`:

```env
INTE_BASEURL=https://inte.example.com

CERT_BASEURL=https://certi.example.com

PROD_BASEURL=https://prod.example.com
```

**Nota:** Puedes definir las variables `STAGING` y `CI` sin utilizar el formato mencionado anteriormente para simplificar su uso en la terminal. Esto permite ejecutar las pruebas en diferentes entornos de manera más directa.

## Ejecución del Proyecto
```bash
  # Ejecución de todos los test
  npm run test

  # Ejecución por tags 
  npm run test -- --grep @validatePurchase
  npm run test -- --grep @invalidUser
  
  # Ejecución por entornos
  STAGING=CERT npm run test
```

## Storage State
Para el uso de storage en automatizaciones que requieran guardar la sesión, modifica `playwright.config.ts` y agrega `storageState` con el siguiente valor `./target/state/storage.json`.

Ejemplo de configuración en `playwright.config.ts`:

```ts
import { defineConfig, devices } from '@playwright/test';

export default defineConfig({
  projects: [
    {
      name: 'chromium',
      use: {
        ...devices['Desktop Chrome'],
        storageState: './target/state/storage.json'
      }
    }
  ]
});
```

En la definición de los steps, utiliza la misma ruta para definir la ruta de guardado del estado de la sesión:

```ts
await page.context().storageState({ path: './target/state/storage.json' })
```

Esto asegurará que el estado de la sesión se guarde y se reutilice en las pruebas posteriores, facilitando la automatización de flujos que requieren autenticación persistente.

## Reportes & Trace
Los reportes de las pruebas se generan en el directorio `target/playwright-reports`. Puedes abrir los reportes generados utilizando el siguiente comando:

```bash
npx playwright show-report target/playwright-reports/
```

Los reportes incluyen automáticamente el trace de las pruebas. Si necesitas compartir el trace con un desarrollador, ubica el archivo de trace correspondiente en la carpeta `target/generated-test-sources` y proporciona las instrucciones para levantar el trace en su ordenador.

Para abrir un trace específico, el desarrollador puede utilizar el siguiente comando:

```bash
npx playwright show-trace path/to/trace.zip
```

Asegúrate de reemplazar path/to/trace.zip con la ruta real del archivo de trace que deseas compartir.
