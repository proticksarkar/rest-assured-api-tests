## Maven-Java based REST Assured Project for API Automation Testing


## Overview

This repository contains a Maven based Java project that demonstrates API automation testing of RESTful web services using REST Assured. It incorporates Behavior-Driven Development (BDD) approach for developing the API testing framework using REST Assured. It also integrates various tools and frameworks to enhance test automation, logging and reporting capabilities. 


## Table of Contents

- [Introduction](#introduction)
- [Technology Stack](#technology-stack)
- [Pre-requisites](#pre-requisites)
- [Project Structure](#project-structure)
- [Setup and Configuration](#setup-and-configuration)
- [Writing Test Scripts](#writing-test-scripts)
- [Running Tests](#running-tests)
- [Logging](#logging)
- [Reporting](#reporting)
- [Continuous Integration (CI)](#continuous-integration-(ci))
- [Additional Information](#additional-information)
- [Acknowledgements](#acknowledgements)
    

## Introduction

This API automation testing project aims to simplify the process of automating API tests by providing a robust and scalable framework built on top of popular Java based libraries. It leverages the power of REST Assured for making API requests and validating responses. It also leverages various frameworks and tools to easily manage dependencies, simplify test development and run, generate logs, and produce comprehensive and visually appealing test execution reports.


## Technology Stack

The project includes the following components:

- **Rest Assured**: A powerful Java library for testing RESTful APIs. It allows you to write expressive and easily maintainable test scripts.
- **TestNG**: A testing framework for Java. It enables parallel test execution, flexible test configuration, and easy test management.
- **Jackson Databind**: A high-performance library to map JSON data to Java objects and vice versa.
- **JSONAssert**: A library for validating JSON data in tests.
- **Lombok**: A Java library for reducing boilerplate code by providing annotations for generating getter, setter, and other methods.
- **Data Faker**: A library for generating realistic fake data, which is useful for data-driven testing.
- **Poiji**: A simple library for deserializing Excel data to Java objects.
- **Log4j**: A logging library for creating logs to track application behavior and errors.
- **Allure Rest Assured**: An extension for Allure Framework that provides integration with Rest Assured to generate detailed and user-friendly test reports.
- **Allure TestNG**: An extension for Allure Framework that provides integration with TestNG to generate comprehensive test reports.
- **Extent Reports**: A customizable test report library for generating visually appealing report.
- **Maven**: A build automation and dependency management tool used primarily for Java projects.
- **Maven Surefire Plugin**: A Maven plugin to execute tests during the build process.
- **Docker**: A platform that enables building, deploying, running and testing applications using containerization.


## Pre-requisites

- Java Development Kit (JDK) (version 17 or above)
- Apache Maven
- IDE (Integrated Development Environment) like IntelliJ IDEA or Eclipse


## Project Structure

    ├── src
    │   ├── main
    │   │   ├── java                # Main Java source code
    │   │   │   └── com
    │   │   │       └── apiautomationtestframework
    │   │   │           ├── configloaders
    │   │   │           ├── constants
    │   │   │           ├── httpoperations
    │   │   │           ├── listeners
    │   │   │           ├── reporting
    │   │   │           ├── specbuilders
    │   │   │           └── utilities
    │   │   │
    │   │   └── resources           # Configuration files and resources    
    │   │          
    │   ├── test
    │   │   ├── java                # Test Java source code         
    │   │   │   └── com
    │   │   │       └── spotify     
    │   │   │           ├── apioperations
    │   │   │           ├── assertions
    │   │   │           ├── auth
    │   │   │           ├── constants
    │   │   │           ├── dataloaders
    │   │   │           ├── dataproviders
    │   │   │           ├── payloadbuilders
    │   │   │           ├── pojo
    │   │   │           └── tests
    │   │   │       
    │   │   │
    │   │   └── resources           # Test-specific configuration files and resources
    │   │       ├── config
    │   │       └── testdata
    │   │   
    │   └── target                  # Test execution output
    │       ├── allure-results
    │       ├── extent-reports
    │       └── logs
    │
    ├── Dockerfile                  # Container based configuration file
    ├── pom.xml                     # Maven project configuration file
    ├── README.md                   # Project documentation (this file)            
    └── testng.xml                  # TestNG configuration file
    
- The *src/main/java* directory contains the source code of the api automation test framework.
- The *src/main/resources* directory stores configuration files or any other resources required for the test framework.
- The *src/test/java* directory contains the test scripts and supporting classes.
- The *src/test/resources* directory stores configuration files, test data, or any other resources required for testing.
- The *target* directory is where the build artifacts and test artifacts like logs, reports, etc. are generated.


## Setup and Configuration

1. Clone the project repository to your local machine by running the following command:

    `git clone <repository-url>`

2. Open the project in your preferred IDE.

3. Install the required dependencies by running the following command:

    `mvn clean install`

4. Configure the framework settings, such as enabling retry mechanism for failed tests, maximum retry count, etc., in the configuration file (frameworkconfig.properties) located in *src/main/resources* directory.

5. Configure the test settings, such as base URI, authentication details, etc., in the configuration file (config.properties) located in *src/test/resources/config* directory.

6. Customize the logging and reporitng settings in the respective configuration files located in the *src/main/resources* directory if you want to change the default settings (Optional).

7. Update the test data, if needed, in the test data files (excel/json/properties) located in the *src/test/resources/testdata* directory (Optional).  

8. Build the project.


## Writing Test Scripts

To create test scripts, follow these steps:
 
1. Create a new Java class under *src/test/java/com/\<app>/tests* directory.
2. Add necessary import statements and extend the *TestHook* class.
3. Use the provided annotations, such as *@Test*, *@BeforeMethod*, *@AfterMethod*, etc., from the TestNG framework to define test methods and setup/teardown methods.
4. Utilize REST Assured methods for making API requests, Jackson Databind for JSON serialization/deserialization, etc.
5. Use the available utilities and helper classes to simplify common testing tasks.


## Running Tests

**IDE**

 Run tests using testng.xml present in project's root directory using IDE like IntelliJ IDEA or Eclipse.

**Maven**

Execute the following command in the project's root directory to run tests using Maven Surefire Plugin:

    mvn test

**Docker**

You can use the current base image, defined in the Dockerfile, to run the tests or you can use any other supported/compatible image (pre-configured or custom) as the base image in Dockerfile to run the tests inside the docker container.

1. Follow the below mentioned approach if you want to build the current docker image, defined in the Dockerfile, and run tests using that docker image. 

    i. *Build Docker Image:*

        docker build -t <image_name>:<tag> .

    ii. *Run Docker Image:*

        docker run <image_name>:<tag>

2. Follow the below mentioned approach if you want to build custom docker image, push it to Docker Hub and run tests using that custom docker image, that is pushed to Docker Hub.

    i. *Build Docker Image:*

        docker build -t <image_name>:<tag> .

    ii. *Create Docker Image on top of a Container (Base Image):*

        docker commit <container_id>
        docker tag <image_id> <new_image_name>
        docker build -t <new_image_name>:<tag> .

    iii. *Push Docker Image to Docker Hub:*

        docker login
        docker push <image_name>:<tag>

    iv. Edit the Dockerfile to use the custom docker image, that is pushed to Docker Hub, as the new base image to run the tests inside docker container.

    v. *Run Docker Image:*

        docker run <image_name>:<tag>

The tests will be executed inside the Docker container, as defined in Dockerfile.


## Logging

**Log4j**  

Log4j is a feature-rich logging framework that offers extensive capabilities for managing and analyzing application logs. It allows you to capture log messages with context-rich information, making it easier to troubleshoot issues and analyze test results. The log configuration can be found in log4j2.xml file present in *src/main/resources* directory. You can customize the log levels and appenders according to your requirements.

The execution logs can be found under */target/logs* directory as defined in the configuration. You can open the generated logs in your preferred editor.


## Reporting

*This project includes two reporting frameworks:*

**Allure Reporting**  

Allure Reporting library generates interactive and visually appealing reports. After running the tests, Allure generates detailed HTML reports with test results, including test steps, assertions, and other configured test details. The report artifacts can be found in */target/allure-results* directory, as defined in the configuration, after the test execution. You can view the Allure report by executing the following command:

`allure serve <generated_report_path>`  

You can open the generated Allure report in your preferred browser.

**Extent Reporting**  

Extent Reporting library provides rich and interactive HTML reports with detailed information about test executions. The execution reports can be found in */target/extent-reports* directory as defined in the configuration. You can open the generated Extent report in your preferred browser.


## Continuous Integration (CI)

This project can be integrated with a CI/CD pipeline of your choice, such as Jenkins. Configure the pipeline to build the project, restore dependencies, execute tests, and generate the required reports.


## Additional Information

For more details on the usage of the various tools and frameworks incorporated in this project, refer to their respective documentations:

[REST Assured](https://github.com/rest-assured/rest-assured)  
[TestNG](https://testng.org/doc/)  
[Jackson-databind](https://github.com/FasterXML/jackson-databind)  
[JSONAssert](https://github.com/skyscreamer/JSONassert)  
[Lombok](https://projectlombok.org/)  
[DataFaker](https://github.com/datafaker-net/datafaker)  
[Poiji](https://github.com/ozlerhakan/poiji)  
[Log4j](https://logging.apache.org/log4j/)  
[Allure Reporting](https://docs.qameta.io/allure/)  
[Extent Reporting](https://www.extentreports.com/docs/versions/5/java/)  
[Maven](https://maven.apache.org/)  
[Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)  
[Docker](https://www.docker.com/)  


## Acknowledgements

Thanks to the creators and maintainers of the tools and frameworks used in this project.

