#Shard Example
This project is a demo for how to access multiple shards based on the user name.
- Users with names starting A/a to M/m are stored in shard1
- Users with names starting N/n to Z/z are stored in shard2.<br>

`AbstractRoutingDataSource` is used to dynamically change the configuration of which shard to use.<br>

MYSQL is the database used.

<h3>Running the application</h3>
To run the application, create 2 schemas in your mysql databse(install if you don't have) called shard1, shard2).<bR>
Run the application using `mvn spring-boot:run`.<br>
Once the application is up make a POST call to the url `localhist:8080/shardTest/addUser` like shown below

```concept
curl -v -X POST localhost:8080/shardTest/addUser -d '{"firstName":"tester", "lastName":"test","age":"33"}' -H "Content-Type: application/json"
``` 

