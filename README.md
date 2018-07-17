# Kitchen

As the name suggested, this is the code for the kitchen.

### Prerequisites

Ensure all tools uses the same java version  
java -version  
javac -version  
mvn -v  
pom.xml `<properties><java.version>1.8</java.version></properties>`  

Install Java 8  
sudo add-apt-repository ppa:openjdk-r/ppa  
sudo apt-get update  
sudo apt-get install openjdk-8-jdk  
ref: https://stackoverflow.com/questions/14788345/how-to-install-the-jdk-on-ubuntu-linux  

Connect to Heroku Database  
Goto https://data.heroku.com/ > Click on the DB > Setting > View Credentials...  
Setup JDBC connection with the info above.  
JDBC URL needed additional parameter as ?sslmode=require  
E.g. jdbc:postgresql://ec2-54-83-1-94.compute-1.amazonaws.com:5432/d310ohnlsi5lof?sslmode=require  

### FAQ

For Java 9, add the following in POM.xml  

		`<dependency>`  
		`    <groupId>javax.xml.bind</groupId>`  
		`    <artifactId>jaxb-api</artifactId>`  
		`    <version>2.2.11</version>`  
		`</dependency>`  
		`<dependency>`  
		`    <groupId>com.sun.xml.bind</groupId>`  
		`    <artifactId>jaxb-core</artifactId>`  
		`    <version>2.2.11</version>`  
		`</dependency>`  
		`<dependency>`  
		`    <groupId>com.sun.xml.bind</groupId>`  
		`    <artifactId>jaxb-impl</artifactId>`  
		`    <version>2.2.11</version>`  
		`</dependency>`  
