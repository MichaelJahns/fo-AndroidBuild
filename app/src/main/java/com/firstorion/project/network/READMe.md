This package should only be used for network-related implementations.
Network dependency (Retrofit, HttpUrlConnection etc.) is not expected to be seen in any other package.

For the REST Api, you are expected to use this Fake REST Api: https://jsonplaceholder.typicode.com/
Please read the guide on this website to get a better understanding of the json response.


To get the posts:
GET https://jsonplaceholder.typicode.com/posts

To upload a post:
POST https://jsonplaceholder.typicode.com/posts


To get a user information:
https://jsonplaceholder.typicode.com/users/{userId}