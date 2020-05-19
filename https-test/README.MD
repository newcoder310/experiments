#HTTPS CONFIGURATION IN SPRING-BOOT
A simple project to demo the configuration of https in spring-boot.

`src/main/resource/keystore.jks` is the keystore file that contains the self signed certificate.

This certificate can be generated using the `keytool`
```
keytool -genkey -alias  trust -storetype JKS -keyalg RSA -keysize 2048 -validity <Validity> -keystore <nams>.jks
```

##Running the application
The application starts on the port `8443`. On typing `https://localhost:8443/httpsTest` in the browser,
 the certificate needs to be added to the exceptions.

