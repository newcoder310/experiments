This is a sample project for my demos

NGINX
   Demonstrates the use of nginx as a reverse proxy and a load balancer.
   The project contains a file called `nginx.conf` which needs to be modified based on the ports.
To use it as a simple reverse proxy   
   ```ttp {
    server {
        listen 8080;
        server_name localhost;

        location / {
            proxy_pass http://localhost:8081;
            proxy_set_header  X-Real-IP $remote_addr;
	    proxy_set_header  X-Forwarded-For $proxy_add_x_forwarded_for;
	    proxy_set_header  Host $http_host;
        }
    }
    }

Modify the above properties to change add the proxy address.

To Use it as a load balancer 

```
http {
upstream backend {
	server localhost:8081;
	server localhost:8082;
}
    server {
        listen 8085;
        server_name localhost;

        location / {
            proxy_pass http://backend;
            proxy_set_header  X-Real-IP $remote_addr;
	    proxy_set_header  X-Forwarded-For $proxy_add_x_forwarded_for;
	    proxy_set_header  Host $http_host;
        }
    }
}
```

    
    
    





