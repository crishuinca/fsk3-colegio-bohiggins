# Plataforma Libro de Clases Digital

Repositorio principal documental para la Evaluacion 2 de Fullstack III.

## Caso

El proyecto corresponde a una plataforma de libro de clases digital para el Colegio Bernardo O'Higgins. La solucion permite registrar y consultar informacion academica, asistencias y anotaciones de estudiantes mediante una arquitectura basada en microservicios, BFF y frontend React.

## Arquitectura General

```text
frontend-libroclases
        |
        v
bff-libroclases
   |          |
   v          v
ms-academico  ms-asistencia
```

## Repositorios

| Componente | Repositorio | Responsabilidad |
|---|---|---|
| Microservicio academico | https://github.com/crishuinca/fsk3-ms-academico | Cursos, asignaturas, estudiantes, evaluaciones y notas. |
| Microservicio asistencia | https://github.com/crishuinca/fsk-ms-asistencia | Asistencias y anotaciones. |
| BFF | https://github.com/crishuinca/fsk3-bff | Orquesta informacion entre microservicios para el frontend. |
| Frontend | https://github.com/crishuinca/fsk3-frontend | Interfaz React, roles simulados y empaquetado NPM. |

## Puertos Locales

| Componente | Puerto | Swagger / URL |
|---|---:|---|
| ms-academico | 8081 | http://localhost:8081/swagger-ui.html |
| ms-asistencia | 8082 | http://localhost:8082/swagger-ui.html |
| bff-libroclases | 8083 | http://localhost:8083/swagger-ui.html |
| frontend-libroclases | 5173 | http://localhost:5173 |

## Como Ejecutar

1. Iniciar `ms-academico`.
2. Iniciar `ms-asistencia`.
3. Iniciar `bff-libroclases`.
4. Iniciar `frontend-libroclases`.
5. Cargar datos demo desde Swagger si la base H2 esta vacia.
6. Probar desde el frontend buscando un estudiante por ID o RUT.

## Comandos Principales

Backends:

```powershell
.\mvnw.cmd spring-boot:run
.\mvnw.cmd verify
```

Frontend:

```powershell
npm install
npm run dev
npm run test:coverage
npm run build
npm run build:lib
npm pack
```

## Arquetipo Maven

El codigo fuente del arquetipo Maven usado como base para backend esta en:

```text
arquetipos/ms-academico-archetype
```

Ese directorio contiene:

- `pom.xml` del arquetipo.
- `src/` con los recursos del arquetipo.
- `README.md` con instrucciones para instalarlo y generar nuevos proyectos.

Con esto se cumple la parte de la entrega que solicita codigo fuente del arquetipo, archivos de configuracion y guia breve de uso.

## Evidencias Para La Entrega

Este repositorio principal contiene o enlaza:

- `repositorios.txt` con todos los links de GitHub.
- Documentos de patrones y branching.
- Codigo fuente del arquetipo Maven en `arquetipos/ms-academico-archetype`.
- Evidencias de Swagger, frontend, tests, cobertura, GitHub Actions y SonarCloud.
- Pasos para ejecutar la demo.


