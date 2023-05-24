# supreme-succotash

This task simulates a client retrieving information via REST API from a server.
Client and server are services belonging to different companies.
The server takes some time to create the information, but as soon as it is available the
client should attempt (and succeed) in retrieving the data. Also, the server is rate
limited.

Create an HTTP service (server) with the following specification:
1. The service has a single endpoint with the following signature and request body
   structure
   POST /api/create
   {"requestId": "{a random alphanumeric string}"}
2. The endpoint is rate limited - it only accepts 2 request / s / unique requestId.

3. The endpoint creates the data, but it takes 5-10s to create it (no less than 5s, no
   longer than 10s). The value of created data is another random alphanumeric string.
   This alphanumeric string is not bound to any requirements.
   Upon receiving a subsequent request for the same requestId, the same content is
   provided in the response.

4. The endpoint responds immediately with either. 
   A:
   {"requestId”: "{{id}}", "created": true, "content": "{{data}}"} - if the resource already
   exists
   or B:
   {"requestId”: "{{id}}", "created": false} - if the resource is not created yet
   Challenge 1
   2
   or C:
   HTTP response code 429 (Too Many Requests) and an empty body if rate limit was
   exceeded

5. 
   The project uses the following:
   Java 17
   Spring Boot 3
   Maven
