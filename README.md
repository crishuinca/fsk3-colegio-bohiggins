# Plataforma Libro de Clases Digital

Repositorio principal documental para la Evaluacion 2 de Fullstack III.

## Caso

El proyecto corresponde a una plataforma de libro de clases digital para el Colegio Bernardo O'Higgins. La solucion permite registrar y consultar informacion academica, asistencias y anotaciones de estudiantes mediante una arquitectura basada en microservicios, BFF, descubrimiento de servicios (Eureka) y frontend React.

## Arquitectura General

```text
                         [ frontend-libroclases :5173 ]
                                    |
                                    v
                         [ bff-libroclases :8083 ]
                          /         |          \
                         v          v           v
              [ ms-academico ]  [ ms-asistencia ]  [ MySQL: libroclases_auth ]
                   :8081            :8082
                         \          /
                          v        v
                    [ eureka-server :8761 ]

MySQL (Docker en repo BFF): libroclases_academico | libroclases_asistencia | libroclases_auth
```

Cada microservicio usa **su propia base de datos** (database per service). El motor MySQL es compartido en local por practicidad; no hay tablas compartidas entre servicios.

## Repositorios

Ver detalle en `repositorios.txt`. Resumen:

| Componente | Repositorio | Responsabilidad |
|---|---|---|
| Eureka Server | https://github.com/crishuinca/fsk3-eureka-server | Registro y descubrimiento de microservicios. |
| Microservicio academico | https://github.com/crishuinca/fsk3-ms-academico | Cursos, asignaturas, estudiantes, evaluaciones y notas. |
| Microservicio asistencia | https://github.com/crishuinca/fsk-ms-asistencia | Asistencias y anotaciones. |
| BFF | https://github.com/crishuinca/fsk3-bff | Auth JWT, orquestacion, Circuit Breaker, Docker MySQL compartido. |
| Frontend | https://github.com/crishuinca/fsk3-frontend-libroclases | Login, permisos por rol, consultas/registro y empaquetado NPM. |

## Puertos Locales

| Componente | Puerto | URL |
|---|---:|---|
| MySQL (Docker) | 3306 | localhost:3306 |
| eureka-server | 8761 | http://localhost:8761 |
| ms-academico | 8081 | http://localhost:8081/swagger-ui.html |
| ms-asistencia | 8082 | http://localhost:8082/swagger-ui.html |
| bff-libroclases | 8083 | http://localhost:8083/swagger-ui.html |
| frontend-libroclases | 5173 | http://localhost:5173 |

## Como Ejecutar

1. **MySQL:** en el repo `bff-libroclases`, ejecutar `docker compose up -d` (ver `docs/MYSQL.md` en ese repo).
2. **Eureka:** `eureka-server` en puerto 8761.
3. **ms-academico** (8081).
4. **ms-asistencia** (8082).
5. **bff-libroclases** (8083).
6. **frontend-libroclases:** `npm install` y `npm run dev` (5173).

Al primer arranque se crean usuarios demo (`inspector` / `profesor`, clave `clave123`) y curso predeterminado (ID 1) si las bases estan vacias.

### Demo rapida

1. Login como `inspector` o `profesor`.
2. Inspector: crear usuario alumno (registra estudiante en ms-academico).
3. Profesor/inspector: registrar asistencia o anotacion para un estudiante existente.
4. Consultar perfil por ID o RUT.

## Comandos Principales

Backends (en cada repo):

```bash
./mvnw spring-boot:run
./mvnw verify
```

MySQL (solo en `bff-libroclases`):

```bash
docker compose up -d
docker compose ps
```

Frontend:

```bash
npm install
npm run dev
npm run test:coverage
npm run build
npm run build:lib
npm pack
```

## Tests y cobertura

- Backends: JaCoCo en `mvn verify`, minimo **80%** en capa `service`.
- Frontend: Vitest con `npm run test:coverage`, minimo **80%** en lineas/statements/functions.
- CI: GitHub Actions (`ci-sonar.yml`) en cada repo de componente.
- SonarCloud: requiere `SONAR_TOKEN` y `SONAR_ORGANIZATION` en GitHub.

## Arquetipo Maven

El codigo fuente del arquetipo Maven usado como base para backend esta en:

```text
arquetipos/ms-academico-archetype
```

Ese directorio contiene:

- `pom.xml` del arquetipo.
- `src/` con los recursos del arquetipo.
- `README.md` con instrucciones para instalarlo y generar nuevos proyectos.

## Entregables EV2

Este repositorio principal contiene o enlaza:

- `repositorios.txt` con todos los links de GitHub.
- **PDF:** Analisis de Patrones y Arquetipos.
- **PDF:** Plan de Branching.
- Codigo fuente del arquetipo Maven en `arquetipos/ms-academico-archetype`.
- Evidencias de Swagger, Eureka, frontend, tests, cobertura, GitHub Actions y SonarCloud.
- Pasos para ejecutar la demo (seccion anterior).

Los PDF se entregan segun indica la rúbrica EV2; no se sustituyen por documentacion Markdown en este repo.
