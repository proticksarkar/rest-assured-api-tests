 FROM psrkr1/maven3.8.5-openjdk17-apitestdep
 COPY src home/apitestframework/src
 COPY target/extent-reports/test_execution_report.html home/apitestframework/test_execution_report.html
 COPY pom.xml    home/apitestframework/pom.xml
 COPY testng.xml home/apitestframework/testng.xml
 WORKDIR home/apitestframework
 ENTRYPOINT mvn clean test