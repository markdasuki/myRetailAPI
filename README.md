# Mark Dasuki myRetail

# Tools and Technologies used
1. Java 11
2. Spring Boot
3. MongoDB
4. Gradle
5. JUnit
6. IntelliJ IDEA
7. Windows 10

# Setup
1. Install Java 11
    - https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html
2. Install MongoDB
    - https://docs.mongodb.com/manual/installation/
3. Install Gradle
    - https://gradle.org/install/

# Start Application
1. Create \data\db folder in chosen folder if \data\db folder is not set up yet
2. Go to MongoDB bin folder
```
\MongoDB\Server\4.0\bin
```
2. Start mongoDB
```
mongod --port 27017 --dbpath C:\...\data\db
```

```
gradle bootRun
```

# Run tests
```
gradle clean test
```
# Tests using Postman
