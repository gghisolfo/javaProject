mvn clean package 
jar tf target/demo-1.0-SNAPSHOT.jar | findstr Main
java -jar target/demo-1.0-SNAPSHOT.jar

