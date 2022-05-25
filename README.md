# FoodHaven

An application for all food lovers that do not have time to make intricate meals but still like homemade food. 
Some of the main features include:
- Creating an account and managing it
- Browsing a variety of recipes on the site
- Viewing additional details of particular recipe such as a nutritional value breakdown
- Adding a recipe
- Rating and reviewing recipes

You can watch a short demo of project functionalities. The video shows all the features on the frontend and some backend features. 

## Backend (Microservices)
The project consists of following four main microservices:
- [User service](/user-service)
- [Recipe service](/project-service)
- [Rating service](/rating-service)
- [Ingredient service](/ingredient-service)

Alongside those, there are 4 utility microservices: 
- [Configuration service](/configuration-service) - for centralizing the configuration files for other services
- [Eureka server](/eureka-server) - load balancer
- [System events service](/system-events-service) - for in-app logging of all activities
- [Gateway service](/api-gateway) - a single-point entry for the backend

## Web Application
This project consists of a web application that showcases the functionalities developed on the backend. The web application is developed using React framework and you can find the source code [in this folder](/frontend). 

## Startup
You can run the application utilizing the docker setup. In order to do so, follow the next steps:
- Make sure you have **docker** installed (and make sure you are registered to Docker Hub)
- Navigate to the root of the project
- run ``sudo docker-compose up `` (be aware that the first time you run this, it may take around 15 minutes to build the project)
- navigate to **localhost** in your browser to start using the web application (make sure that ports 3000 and 8088 on your machine are free, because the app uses those ports to serve the webapp and expose the backend)

## Technological stack
- Spring Boot
- MySQL
- React
- Rabbit MQ

# Contributors
- [Aya Ali Al Zayat](https://github.com/aalialzaya1)
- [Vahidin Hasić](https://github.com/vhasic)
- [Hana Veladžić](https://github.com/hveladzic2)
