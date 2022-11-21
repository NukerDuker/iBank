# Readme

## To build and start app
`gradle build`   
Dump in ibankFullDump.sql   

<img width="426" alt="Снимок экрана 2022-11-22 в 01 59 01" src="https://user-images.githubusercontent.com/64738590/203175464-ea0a7cf7-ddce-48fe-bfeb-0b91737a939d.png">

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
`docker build ibankapi .`   
`sudo docker run --rm -p 8080:8080 ibankapi`  

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

## What was done
1. All Api functionality
2. Operations recording to operations_tab
3. Transfer money operation
4. Used Transactional annotation for transaction management
5. Deployed on VDS
