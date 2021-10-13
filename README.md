# Activity tracking
Tracking activity across different system

## Prerequisites

* **Java 11 (Open JDK)**

## Develop

1. Checkout project with `git clone ...`
2. Build project: `./gradlew clean build`
3. Optionally if it is needed to change some property which is defined in
   application.properties `src/main/resources/application.properties` 
<br />



## Running project with Docker :
1. Build jar `./gradlew clean build`
2. Create docker image: `docker build -t tracker:1 .`
3. Check docker image: `docker images | grep tracker`
4. Run docker: `docker run -d --name trackerApp -p 8080:8080 tracker:1 .`
   <br />


## H2 console
http://localhost:8080/h2-adminer

## Endpoint Usage
1. Start session : POST http://localhost:8080/tracker/startSession <br />
    Request body : ___{
   "userId": "4d5a5928-b8b4-4d76-a4cb-68805c918a11",
   "machineId": "7a4c71c8-2199-461e-83c3-8845adafc592",
   "startAt": "2021-10-13T10:13:53.856013",
   "orgId": 11232
   }___
    
2. End Session: POST http://localhost:8080/tracker/endSession <br />
   Request body :   ___{
   "sessionId": "4d5a5928-b8b4-4d76-a4cb-68805c918a13",
   "endAt": "2021-10-13T10:13:53.856013"
   }___

3. Add event: POST http://localhost:8080/tracker/addEvents <br />
   Request body :  ___{
   "sessionId": "4d5a5928-b8b4-4d76-a4cb-68805c918a13",
   "events": [
                {
                    "eventAt": "2021-10-13T10:13:53.856013",
                    "eventType": "MACHINE_ON",
                    "payload": "payload"
                }     
              ]
    }___
