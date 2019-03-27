# Mark Dasuki myRetail

# Tools and Technologies used
1. Java 11
2. Spring Boot
3. MongoDB
4. Gradle
5. JUnit
6. IntelliJ IDEA
7. Windows 10
8. Postman

# Setup
1. Install Java 11
    - https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html
    - Java 8 will work as well, as gradle file was modified to work on Java 8, but devlopment was done on Java 11
    - System enviornment variable needs to include ...\Java\jdk-11.0.2\bin
2. Install MongoDB
    - https://docs.mongodb.com/manual/installation/
3. Install Gradle
    - https://gradle.org/install/
    - System enviornment variable needs to include \gradle-5.2.1\bin
4. Install Postman 
    - https://www.getpostman.com/downloads/

# Clone Repo
```
git clone https://github.com/markdasuki/myRetailAPI.git
cd myRetailAPI
```

# Start Application
1. Create \data\db directory in chosen location if \data\db directory is not set up yet
2. Go to MongoDB bin folder in terminal
```
\MongoDB\Server\4.0\bin
```
2. Start mongoDB 
    - Note: You only need to add dbpath if it is not setup on your mongoDB yet
    - Note: Port flag is optional since this API will assume the default port
```
mongod --port 27017 --dbpath ...\data\db
```
3. Go to myRetail folder in terminal
4. Start myRetail service with gradle
```
gradle bootRun
```

# Run tests
Requires myRetail API to be running since it performs functional testing
```
gradle clean test
```
# Using Postman to access myRetail API
### Valid Get Request
![Valid Get Request](https://github.com/markdasuki/myRetailAPI/blob/master/Postman%20Images/ValidGetRequest.png)
### Invalid Get Requests
![Invalid Get Request](https://github.com/markdasuki/myRetailAPI/blob/master/Postman%20Images/InvalidGetRequestNotInDatastore.png)
![Invalid Get Request](https://github.com/markdasuki/myRetailAPI/blob/master/Postman%20Images/InvalidGetRequestNotInRedsky.png)
### Valid Put Request
![Valid Put Request](https://github.com/markdasuki/myRetailAPI/blob/master/Postman%20Images/ValidPutRequest.png)
### Invalid Put Requests
![Invalid Put Request](https://github.com/markdasuki/myRetailAPI/blob/master/Postman%20Images/InvalidPutRequestIdMismatch.png)
![Invalid Put Request](https://github.com/markdasuki/myRetailAPI/blob/master/Postman%20Images/InvalidPutRequestNoCurrentPrice.png)
![Invalid Put Request](https://github.com/markdasuki/myRetailAPI/blob/master/Postman%20Images/InvalidPutRequestNotInDatastore.png)
