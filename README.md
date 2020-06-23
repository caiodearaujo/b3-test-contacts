# B3 Test - Contacts
![Build Status](https://travis-ci.org/caiodearaujo/b3-test-contacts.svg?branch=master)

Angular WEB project and Java Spring Boot API project

## API Specs

The documentation of these API can be read on [SwaggerHub](https://app.swaggerhub.com/apis/caiodearaujo/ContactsAPI/1.0.0)
- The file [swagger.yaml](https://github.com/caiodearaujo/b3-test-contacts/blob/master/swagger.yaml) contains Swagger API documentation

## Run with Docker

For run with docker, execute:

```sh
    # first execute maven goals
    $ mvn clean install
    # after maven goals executed, run:
    $ docker-compose build
    # after docker build the two projects, run:
    $ docker-compose up
```
WEB was running on port 82:

> http://localhost:82/

API wan running on port 8900:

> http://localhost:8900/api/

## CSV files

The file .csv inside app/files was generated with a online data generator. Put a csv file inside app/files/ for Java read and import te valid lines. The invalid lines will be logged on java terminal, for view log you can run

```sh
$ docker-compose logs contacts-api
```
