# urbanRoute

A community-driven urban transit routing REST API where users contribute local travel routes and search for optimal paths between stops.

## Live Demo Link

🔗 

## Features

- **Multi-mode pathfinding** — BFS for fewest stops, Dijkstra for fastest and cheapest routes
- **Strategy Pattern** — algorithms switch at runtime based on search mode
- **In-memory graph cache** — adjacency list built on startup, invalidated on every write
- **JWT Authentication** — stateless auth with role-based access (USER/ADMIN)
- **Community-driven** — authenticated users contribute routes, all users can search

## Tech Stack

- Java 21 + Spring Boot 4.0.6
- PostgreSQL + Spring Data JPA
- Spring Security + JWT (JJWT)
- Docker + Render
- Swagger (SpringDoc OpenAPI)
- JUnit 5

## API Endpoints

### Auth (Public)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and get JWT token |

### Stops
| Method | Endpoint | Access |
|--------|----------|--------|
| GET | `/api/stops` | Public |
| GET | `/api/stops/{id}` | Public |
| POST | `/api/stops` | Admin only |
| DELETE | `/api/stops/{id}` | Admin only |

### Routes
| Method | Endpoint | Access |
|--------|----------|--------|
| GET | `/api/routes` | Public |
| GET | `/api/routes/{id}` | Public |
| POST | `/api/routes` | Authenticated |
| DELETE | `/api/routes/{id}` | Owner or Admin |

### Search
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/search?from={id}&to={id}&mode={SHORTEST\|FASTEST\|CHEAPEST}` | Find optimal route |

### Admin
| Method | Endpoint | Access |
|--------|----------|--------|
| GET | `/api/admin/users` | Admin only |
| DELETE | `/api/admin/users/{id}` | Admin only |

## How to Run Locally

### Prerequisites
- Java 21+
- PostgreSQL
- Maven

### Steps

1. Clone the repo
```bash
git clone https://github.com/Ashutosh875/urbanRoute.git
cd urbanRoute
```

2. Create PostgreSQL database
```sql
CREATE DATABASE urbanroute;
```

3. Configure `application-dev.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/urbanroute
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. Run the app
```bash
mvn spring-boot:run
```

5. Access Swagger UI at `http://localhost:8080/swagger-ui.html`

## Graph Implementation

The core search feature uses an in-memory adjacency list built from the database on startup:

- **SHORTEST** — BFS, returns path with fewest stops
- **FASTEST** — Dijkstra with edge weight = duration in minutes
- **CHEAPEST** — Dijkstra with edge weight = cost in rupees

A Strategy Pattern switches between algorithms at runtime without modifying the controller or service layer.