#Spring Batch
A simple spring batch application that reads a `csv` file, processes it
and stores in the `h2` databse.
<br/>
 The input file can be found at the location `spring-batch/src/main/resources/users.csv`
 
 <h3>Instructions to Run</h1>  To run the project, execute `mvn spring-boot:run` from the root folder.<br>
 The h2 console can be opened from the browser `http://localhost:8081/h2-console`. If a simple connect does not work, replace the `jdbc url` value to something similar to `jdbc:h2:mem:2c4abffb-1c2c-4116-8d25-c11d6244b253` which can be found in the startup logs.
 To trigger the read of the csv file, hit the url `http://localhost:8080/load/test` from the browser. Check the tables in the `h2` database.
 