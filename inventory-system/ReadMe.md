# Inventory Management Microservices 

### What is it?


### Use case

.

Below are the screens of the application.

![alt tag](https://github.com/vmudigal/microservices-sample/blob/version-5/documents/screens/_Web%20App/01.%20Home.png?raw=true)

Clicking on the tab's one or two the data that you see on the screen is based on the data fetched by the respective service by calling its database.

### Components Integrated & Tools Usage
##### API Gateway

Netflix Zuul is a the reverse proxy server which acts as the API Gateway for accessing the micro services behind the gateway which routes the request to the respective service. Microservice’s stay behind reverse proxy server and needs to be consumed via api gateway. The api-gateway micro service with docker profile runs on port 8080 and can be accessed by http://localhost:8080 .

Configuration done in API Gateway for Routing:
```
zuul:
  sensitive-headers:

  prefix: /api
  routes:
    ims-ms: /ims/**
  ribbon:
    eager-load:
      enabled: true

ribbon:
  ReadTimeout: 120000
  ConnectTimeout: 10000
  MaxautoRetries: 1
  MaxAutoRetriesNextServer: 1
```

##### Service registration and discovery

Registration and discovery is taken care by Eureka.During the startup of the individual services, they register with service registration service those details such as host name, port etc. by which the services can be accessed. Once the service is registered to the Eureka, consul checks for the health of the service by sending a heartbeat for the health check path and health check interval that has been registered with Consul. Requests to the micro-services has to be routed through api-gateway during with the api-gateway contacts discovery service to get the information required to send the request to the intended microservice.

### Technology

Microservices sample project uses a number of open source projects to work properly:

* [SpringBoot] - Application framework
* [Zuul] - API Gateway (Load Balancer)
* [Eureka] - Service registration and Discovery
* [Swagger] - API documentation

### Tools

* [Java] - Programming
* [Gradle] - Build
* [Git] - Version control


### Development

Below are the steps to bring up the development environment and get started.

1) Install Git, Java and Gradle</br>
2) For the project using https://github.com/vmudigal/microservices-sample.git
3) 

6) Access the Application at http://localhost/</br></br>


### Help

Feel free to reach "karthik9193@gmail.com" incase of any concerns.

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job.)

[Bootstrap]: <http://twitter.github.com/bootstrap/>
[SpringBoot]: <https://projects.spring.io/spring-boot/>
[Zuul]: <https://github.com/Netflix/zuul/wiki>
[Maven]: <https://maven.apache.org>
[Git]: <https://git-scm.com>
[Java]: <https://go.java>
[Swagger]: <https://swagger.io/>
   