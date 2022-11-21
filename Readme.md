# Readme

## Run docker postgres db
docker run --rm -dit --name ibankApiDB -p 5433:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=ibank -e PGDATA=/var/lib/postgresql/data/pgdata postgres:14

## To start the application:
1. Clone repository
2. Build jar with gradle build
3. Locate file additional.properties in the same folder with jar file (You can use additional.properties.example)
4. Execute jar

## Build docker image 
Additional properties can be uploaded in the image, you can specify directory to bootstrap file in Dockerfile.
Commands: 
docker build ibankapi .
sudo docker run --rm -p 8080:8080 ibankapi

## To build and start app
gradle build
![DB structure](https://user-images.githubusercontent.com/64738590/200120972-8eeee6d8-11a9-4e9b-9a0b-440a7bb33bad.png)  
Dump in plain_dump.sql

## Simple some-kind-of-documentation
Deployed here: http://109.196.164.34:8080

Endpoints:  

`GET /api/dev/fillDb` - creates two new users and returns their ids.

`GET /getBalance/{userId}` - return requested user.

`POST /deposit` - put money to requested user.
```json
{
  "userId": 3, 
  "value": 10
}
```
`POST /withdrew` - take money from user.
```json
{
  "userId": 3,
  "value": 10
}
```
`POST /getOperationList` - get operations of requested user.
```json
{
  "userId": 3, //required
  "startDate": "2022-11-08", //optional
  "endDate": "2022-11-21"    //optional
}
```
`POST /transferMoney` - transferes money between two users.
```json
{
  "userId": 2,
  "userToId": 1,
  "value": 90000
}
```