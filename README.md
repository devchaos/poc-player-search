# Proof of Concept - Player Search Service with Elasticsearch

Applications Built With:
- Spring Boot - https://projects.spring.io/spring-boot/
- Spring Initializr -  https://start.spring.io/
- Project Lombok - https://projectlombok.org/
- Spring WebFlux (Web Reactive) - https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html
- Spring Cloud Stream - http://cloud.spring.io/spring-cloud-stream/
- Spring Kafka - http://projects.spring.io/spring-kafka/
- Spring Data MongoDB (also with Reactive Driver) - https://projects.spring.io/spring-data-mongodb/
- Spring Data Elasticsearch (using TCP connector) - https://projects.spring.io/spring-data-elasticsearch/

Stack
- Apache Kafka - https://kafka.apache.org/
- Mongo DB - https://www.mongodb.com/
- Elasticsearch - https://www.elastic.co/

Component Diagram

![Component Diagram](https://raw.githubusercontent.com/devchaos/poc-player-search/master/images/POC_Player_Search.png)

Running the Applications
1. You need Docker
2. `git clone` this repository on your local machine
3. on your terminal, navigate in the folder `poc-player-search` created by the `git clone`
4. start Zookeeper, Kafka, Elasticsearch and MongoDB Docker Containers from your terminal typing `docker-compose up -d`
- 4.1. this can take a while until the images get downloaded
- 4.2. when the process finishes, please make sure you have 4 containers running. use `docker ps`
5. navigate to folder `player-service`
- 5.1. execute `mvn clean install`
- 5.2. after the mvn goals executed do `java -jar target/player-service-0.0.1-SNAPSHOT.jar`
- 5.3. this service runs on port 8080
6. navigate to folder `player-search-service`
- 6.1. execute `mvn clean install`
- 6.2. after the mvn goals executed do `java -jar target/player-search-service-0.0.1-SNAPSHOT.jar`
- 6.3. this service runs on port 8081

Importing and Running the Project on IntelliJ
1. Install Lombok Plugin on your IntelliJ
2. `git clone` this project to a folder at your choice
3. On IntelliJ, create new "Project From Existing Sources..." pointing to the folder created with `git clone`
- 3.1. On the Wizard remove the selection of any suggested source folders and finish the import
4. Set the SDK project to Java 1.8
5. Import both modules using "Add Maven Projects"
6. You can run `clean install` maven goals on both projects to check if the import was successful
7. To run player-service application go to `io.devchaos.player.service.PlayerServiceApplication` and run the application
- 7.1. player-service runs on port 8080
8. To run player-search-service go to `io.devchaos.player.search.service.PlayerSearchServiceApplication` and run the application
- 8.1 player-search-service-runs on port 8081

_-- there is no parent pom for the applications, that's why the project needs to be imported as described above --_ 

_-- the applications are a exploratory POC and do not have any tests (for now) --_

##### Requesting the Endpoints (as an example)

###### player-service

```
CREATE PLAYER

POST http://localhost:8080/players
Content-Type: application/json

{
	"firstName": "Dev",
	"lastName": "Chaos",
	"email": "devchaos.io@gmail.com"
}
```
```
UPDATE PLAYER

PUT http://localhost:8080/players/{{playerId}}
Content-Type: application/json

{
	"firstName": "Some Dev",
	"lastName": "Some Chaos"
}
```

```
GET ALL PLAYERS

GET /players HTTP/1.1
Host: http://localhost:8080

```

###### player-search-service

```
SEARCH PLAYERS

GET http://localhost:8081/search?firstName=dev&lastName=chaos&email=*
```