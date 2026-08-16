# Bitcast

> An open-source, self-hosted video streaming platform built from the ground up.

Bitcast is a project for designing, implementing, and evolving a
video streaming platform while documenting the engineering problems,
constraints, and architectural decisions encountered along the way.

The project starts simple and grows incrementally:

```
CLI -> API -> Local Storage -> Distributed Architecture
```

Each stage introduces new constraints, new trade-offs,
and new engineering questions worth exploring.

## Current state

V1 focuses on basic video upload and retrieval through a REST API
backed by local filesystem storage.

- Spring Boot 4 / Java 21
- Multipart upload with UUID-based identification
- Video streaming with HTTP Range support

See [docs/v1.md](docs/v1.md) for the full design notes.

## How to run

Prerequisites: Java 21+

```bash
git clone https://github.com/watashi-00/bitcast.git
cd bitcast
./mvnw spring-boot:run
```

The server starts at `http://localhost:8080`.

## Project structure

```
src/main/java/com/watashi/bitcast/
  controllers/   # REST endpoints
  dto/           # Data transfer objects
  repository/    # Storage layer
  schemas/       # Domain models
  services/      # Business logic
docs/            # Design notes per version
```

## License

[MIT](LICENSE)
