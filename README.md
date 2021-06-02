# Reto Intercorp

### Desafío a resolver:

* **Microservicio desarrollado en JAVA Spring boot**
* **API Rest documentada en Swagger**
* **Deployado en AWS o algún CLOUD + código subido en GITHUB**
* **Endpoint de Entrada POST /creacliente:**
    - Nombre
    - Apellido
    - Edad
    - Fecha de nacimiento
* **Endpoint de salida GET  /kpideclientes:**
    - Promedio edad entre todos los clientes
    - Desviación estándar entre las edades de todos los clientes
* **Endpoint de salida GET /listclientes:**
    - Lista de personas con todos los datos
    - Fecha probable de muerte de cada una

### Solución:
[]()


- Se desarrollo el microservicio en Java 11 utilizando [arquitectura hexagonal](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)).
- Para el almacenamiento de datos se utilizo [PostgreSQL](https://www.postgresql.org/) + [JDBC](https://jdbc.postgresql.org/).
- Test unitarios utilizando [Junit](https://junit.org/junit4/), [Mockito](https://site.mockito.org/), y [MockMVC](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/MockMvc.html).
- Test de arquitectura utilizando [ArchUnit](https://www.archunit.org/).
- Swagger generado mediante [Springfox](https://springfox.github.io/springfox/)
- Deployado en [Heroku](https://dashboard.heroku.com/).

## Docker
Se puede levantar el proyecto mediante docker compose:
``` bash
cd src/docker && docker-compose up -d
```

## Documentacion
[![View in Swagger](http://jessemillar.github.io/view-in-swagger-button/button.svg)](https://fast-retreat-49389.herokuapp.com/swagger-ui.html#/)

https://fast-retreat-49389.herokuapp.com/swagger-ui.html#/


## Collection
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/30cbe886d2e98a38e14b)

https://www.getpostman.com/collections/30cbe886d2e98a38e14b

## Creación de Database:

| Nombre de campo | Tipo de dato |
|-----------------|--------------|
| id              | integer      |
| first_name      | text         |
| last_name       | text         |
| age             | int          |
| birthdate       | date         |

```sql
create table client
(
    id  serial not null constraint clients_pk primary key,
    first_name text,
    last_name  text,
    age        integer,
    birthdate  date
);

SET search_path = public
```

