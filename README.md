# Inventory Management Microservices 

### What is it?
Inventory Management System(ims) built which helps to handling products stock warehouse, billing, Order and payment services for a retail Business.



### Components Integrated & Tools Usage
##### API Gateway

Netflix Zuul is a the reverse proxy server which acts as the API Gateway for accessing the micro services behind the gateway which routes the request to the respective service. Microservice’s stay behind reverse proxy server and needs to be consumed via api gateway. 
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

Registration and discovery is taken care by Eureka.During the startup of the individual services, they register with service registration service those details such as host name, port etc. by which the services can be accessed. 

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
2) For the project clone it using https://github.com/karthik9194/Inventory-Management.git
3) Have to run the microservices in an order as below
4) Start the Eureka Discovery Server and followed by zuul Gateway server
5) Start the Inventory Management service(ims) and can be accessed either through Swagger end-point http://localhost:8013/swagger-ui.html
6) Access the Application at http://localhost:8013/</br></br>


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
   
