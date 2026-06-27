# Plataforma Libro de Clases Digital

Repositorio principal documental para la Evaluacion 3 de Fullstack III.

## Caso

El proyecto corresponde a una plataforma de libro de clases digital para el Colegio Bernardo O'Higgins. La solucion permite registrar y consultar informacion academica, asistencias y anotaciones de estudiantes mediante una arquitectura basada en microservicios, BFF, API Gateway, descubrimiento de servicios (Eureka) y frontend React.

## Arquitectura General

```text
                         [ frontend-libroclases :5173 ]
                                    |
                                    v
                         [ api-gateway :8080 ]
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

El frontend consume el **API Gateway** en `http://localhost:8080/api/v1`. El JWT se valida en el BFF; el Gateway reenvia el header `Authorization`.

## Repositorios

Ver detalle en `repositorios.txt`. Resumen:

| Componente | Repositorio | Responsabilidad |
|---|---|---|
| Eureka Server | https://github.com/crishuinca/fsk3-eureka-server | Registro y descubrimiento de microservicios. |
| Microservicio academico | https://github.com/crishuinca/fsk3-ms-academico | Cursos, asignaturas, estudiantes, evaluaciones y notas. |
| Microservicio asistencia | https://github.com/crishuinca/fsk3-ms-asistencia | Asistencias y anotaciones. |
| BFF | https://github.com/crishuinca/fsk3-bff | Auth JWT, orquestacion, Circuit Breaker, Docker MySQL compartido. |
| API Gateway | https://github.com/crishuinca/fsk3-api-gateway | Punto de entrada unico del frontend, enrutamiento y CORS. |
| Frontend | https://github.com/crishuinca/fsk3-frontend | Login, permisos por rol, consultas/registro, E2E Playwright. |

Rama de trabajo EV3 en los repos de componentes: `feature/huinca`.

## Puertos Locales

| Componente | Puerto | URL |
|---|---:|---|
| MySQL (Docker) | 3306 | localhost:3306 |
| eureka-server | 8761 | http://localhost:8761 |
| ms-academico | 8081 | http://localhost:8081/swagger-ui.html |
| ms-asistencia | 8082 | http://localhost:8082/swagger-ui.html |
| bff-libroclases | 8083 | http://localhost:8083/swagger-ui.html |
| **api-gateway** | **8080** | http://localhost:8080/actuator/health |
| frontend-libroclases | 5173 | http://localhost:5173 |

## Como Ejecutar

1. **MySQL:** en el repo `fsk3-bff`, ejecutar `docker compose up -d` (ver `docs/MYSQL.md` en ese repo).
2. **Eureka:** `fsk3-eureka-server` en puerto 8761.
3. **ms-academico** (8081).
4. **ms-asistencia** (8082).
5. **bff-libroclases** (8083).
6. **api-gateway** (8080) — debe arrancar despues del BFF.
7. **frontend:** `npm install` y `npm run dev` (5173).

Al primer arranque se crean usuarios demo (`inspector` / `profesor`, clave `clave123`) y curso predeterminado (ID 1) si las bases estan vacias.

### Demo rapida

1. Login como `profesor` o `inspector` (`clave123`).
2. Consultar perfil, anotaciones o asistencia de un estudiante (ID 1 si ya existe).
3. Registrar anotacion y asistencia (profesor/inspector).
4. Inspector: crear usuario alumno vinculado a estudiante.

## Comandos Principales

Backends (en cada repo):

```bash
./mvnw spring-boot:run
./mvnw test
./mvnw test -Dtest="*Integration*"
./mvnw verify
```

MySQL (solo en `fsk3-bff`):

```bash
docker compose up -d
docker compose ps
```

Frontend:

```bash
npm install
npm run dev
npm run test:coverage
npm run test:e2e
npm run build
npm run build:lib
npm pack
```

Para E2E: stack backend levantado + `npm run dev` en otra terminal, luego `npm run test:e2e`.

## Tests y cobertura

- **Unitarios:** JaCoCo en `mvn verify` (backends), Vitest en frontend (`npm run test:coverage`). Minimo **80%** en capa service (backends) y en lineas/statements/functions (frontend).
- **Integracion:** `@SpringBootTest` + MockMvc/WebTestClient en BFF, ms-academico, ms-asistencia y api-gateway. Ejecutar con `mvn test -Dtest="*Integration*"`.
- **E2E:** Playwright en frontend. Recorre login, perfil, anotaciones, asistencia, registro de ambos y cierre de sesion (rol profesor).
- **CI:** GitHub Actions (`ci-sonar.yml`) en cada repo de componente.
- **SonarCloud:** requiere `SONAR_TOKEN` y `SONAR_ORGANIZATION` en GitHub.



## Arquetipo Maven

El codigo fuente del arquetipo Maven usado como base para backend esta en:

```text
arquetipos/ms-academico-archetype
```

Ese directorio contiene:

- `pom.xml` del arquetipo.
- `src/` con los recursos del arquetipo.
- `README.md` con instrucciones para instalarlo y generar nuevos proyectos.

## Entregables

Este repositorio principal contiene o enlaza:

- `repositorios.txt` con todos los links de GitHub.
- **PDF:** Analisis de Patrones y Arquetipos.
- **PDF:** Plan de Branching.
- Codigo fuente del arquetipo Maven en `arquetipos/ms-academico-archetype`.
- Evidencias de Swagger, Eureka, Gateway, frontend, tests unitarios, integracion, E2E, cobertura, GitHub Actions y SonarCloud.
- Pasos para ejecutar la demo (seccion anterior).

Los PDF se entregan segun indica la rubrica; no se sustituyen por documentacion Markdown en este repo.


