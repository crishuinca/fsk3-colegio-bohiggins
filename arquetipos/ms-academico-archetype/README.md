# ms-academico-archetype

Arquetipo Maven generado desde el microservicio `ms-academico`.

## Objetivo

Este arquetipo sirve como base para crear nuevos microservicios Spring Boot con una estructura similar a la usada en el proyecto de libro de clases digital.

Incluye una estructura base con:

- Spring Boot.
- Maven.
- Capas `controller`, `service`, `repository`, `dto`, `entity` y `config`.
- Persistencia con JPA.
- Configuracion H2.
- Swagger/OpenAPI.
- Pruebas unitarias base.

## Contenido

```text
ms-academico-archetype/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── resources/
    │       ├── META-INF/maven/archetype-metadata.xml
    │       └── archetype-resources/
    └── test/
```

## Instalar El Arquetipo Localmente

Desde esta carpeta:

```powershell
cd "C:\Users\tobal\Desktop\Fullstack 3\colegio-bo\arquetipos\ms-academico-archetype"
mvn install
```

Tambien se puede usar el Maven Wrapper del microservicio academico:

```powershell
cd "C:\Users\tobal\Desktop\Fullstack 3\colegio-bo\arquetipos\ms-academico-archetype"
..\..\..\ms-academico\mvnw.cmd install
```

## Generar Un Nuevo Proyecto

Luego de instalar el arquetipo, se puede crear un nuevo microservicio con:

```powershell
mvn archetype:generate `
  "-DarchetypeCatalog=local" `
  "-DarchetypeGroupId=cl.bohiggins" `
  "-DarchetypeArtifactId=ms-academico-archetype" `
  "-DarchetypeVersion=0.0.1-SNAPSHOT" `
  "-DgroupId=cl.bohiggins" `
  "-DartifactId=ms-nuevo" `
  "-Dpackage=cl.bohiggins.ms_nuevo" `
  "-DinteractiveMode=false"
```

## Verificar El Proyecto Generado

Entrar al proyecto generado y compilar:

```powershell
cd "ms-nuevo"
mvn clean compile
```

Si el proceso termina con `BUILD SUCCESS`, el arquetipo funciona correctamente.

## Uso En La Entrega

Para la entrega EV2, este directorio demuestra:

- Codigo fuente del arquetipo Maven usado como base.
- Archivo de configuracion `pom.xml`.
- Descriptor `archetype-metadata.xml`.
- Guia breve de uso mediante este `README.md`.

El arquetipo fue generado originalmente desde `ms-academico` usando:

```powershell
.\mvnw.cmd -s ".\archetype-settings.xml" archetype:create-from-project
```
