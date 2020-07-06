# LRUSpring

This maven project runs as Spring boot application.

Steps to start 
1) Download Eureka server application on https://github.com/wprathamesh/SpringBootServer.git and start the application.
2) Download the LRUSpring, and run mvn clean install.
3) Run class com.prathamesh.assignment.espressif.EspressifApplication. This will register prathamesh-lru-service to above discovery server.

API's exposed:
/getitem(String item) - Returns item if present in cache, else item not found.
/additem(String item) - Adds the item to the cache if not already present.
/getallitems()        - Returns all items present in cache.


Test class
1) Added test to add 4 items, it will in turn delete 2 elements internally and keep the 2 items only.
2) Added test case to assert on expected items present in cache.

Note : The cache is added for 2 size
