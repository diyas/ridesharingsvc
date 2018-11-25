Requirement:
- Springboot
- Tomcat
- Mysql

1. (Connection) As a Driver, I can connect to Ride Sharing system
	URL:http://localhost:8080/api/auth/register_driver
	Method: POST
	Parameter: Body
```json

{
"userName":"String",
"name":"String",
"email":"String",
"password":"String"
}
```
	URL:http://localhost:8080/api/auth/signin
	Method: POST
	Parameter: Body
```json
{
"userName":"String",
"password":"String"
}

```
2. (Connection) As a Passenger, I can connect to Ride Sharing system
	URL:http://localhost:8080/api/auth/signup
	Method: POST
	Parameter: Body
```json
{
"userName":"String",
"name":"String",
"email":"String",
"password":"String"
}
```
	URL:http://localhost:8080/api/auth/signin
	Method: POST
	Parameter: Body
```json
{
"userName":"String",
"password":"String"
}

```
3. (Connection) As a Driver, I can send my presence to Ride Sharing system
4. (Connection) As a Passenger, I can send my presence to Ride Sharing system

5. (Pairing) As a Passenger, I can send requests to Driver
	URL:http://localhost:8080/api/order
	Method: POST
	Parameter: Body
```json
{
"latFrom":"String",
"lonFrom":"String",
"latTo":"String",
"lonTo":"String",
"note":"String",
"stsOrder":"String"
}
```
6. (Pairing) As a Driver, I can receive requests from Passenger
7. (Pairing) As a Driver, I can accept requests from Passenger
8. (Pairing) As a Passenger, I can receive accepted requests from Drivers
9. (Approaching) As a Driver, I can send my location
10. (Approaching) As a Passenger, I can receive Driver location
11. (Driving) As a Driver, I can start the trip
12. (Driving) As a Driver, I can end the trip