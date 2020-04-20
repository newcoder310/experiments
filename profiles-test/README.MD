#Profiles Example

 In spring profiles are a way to make applicaiton configuration available in certain envs. In this simple example, a `dev`
 profiles returns a different `hello dev` while a default profile returns `hello` message
 
 <h3>To Run the Project with dev profile</h3>
 Ensure that the application.properties has the below line uncommented
 `spring.profiles.active = dev`<br>
 then run<br>
 `mvn run:spring`
 <h3>To Run the Project with dev profile</h3>
 Ensure that the application.properties has the below line commented
  `#spring.profiles.active = dev`<br>
  then run<br>
  `mvn run:spring`