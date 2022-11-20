# Readme

## Run docker postgres db
docker run --rm -dit --name ibankApiDB -p 5433:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=ibank -e PGDATA=/var/lib/postgresql/data/pgdata postgres:14

## To start the application:
1. Clone repository
2. Build jar
3. Locate file additional.properties in the same folder with jar file (You can use additional.properties.example)

## Build docker image 
docker build ibankapi .
docker run --rm -ti -e JAVA_OPTS="-Xms50M -Xmx100M" ibankapi

## To build and start app
gradle --debug build -Ptarget=build/classes/main
![DB structure](https://user-images.githubusercontent.com/64738590/200120972-8eeee6d8-11a9-4e9b-9a0b-440a7bb33bad.png)  
Dump in plain_dump.sql
