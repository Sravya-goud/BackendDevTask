Weather by IP - Spring Boot Backend API 
---------------------------------------
This project is a Spring Boot REST API that retrieves current weather information based on an IP address.  
It integrates external services (IP geolocation + weather data) and is built with best practices in API design, security, caching, and modular structure.

---

# Features:

- Get user location (city, country) from IP address
- Fetch current weather data (temperature, humidity, description)
- Stateless RESTful API with query or header-based IP input
- Modular project structure (Controller, Service, Utility, Exception Handling)
- In-memory caching (10 min per city) using Caffeine
- Rate limiting (5 requests/min per client IP)
- Robust error handling with custom exceptions
- Docker-ready & deployable
- Unit tested and Postman tested

---

# Technologies Used:

- Java 17
- Spring Boot
- OpenWeatherMap API
- IPInfo (or similar IP Geolocation API)
- Caffeine Cache
- Bucket4j (rate limiting)
- Maven
- Postman (API testing)

---

##  How to Run the Project

### Prerequisites

- Java 17+
- Maven
- Internet connection (for external API calls)
- Valid OpenWeatherMap API Key

### Configuration

Edit `src/main/resources/application.properties`:
```properties
weather.api.key=YOUR_OPENWEATHERMAP_API_KEY
server.port=9090
