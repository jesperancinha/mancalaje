### Java Spring template project

This project is based on a GitLab [Project Template](https://docs.gitlab.com/ee/gitlab-basics/create-project.html).

Improvements can be proposed in the [original project](https://gitlab.com/gitlab-org/project-templates/spring).

### CI/CD with Auto DevOps

This template is compatible with [Auto DevOps](https://docs.gitlab.com/ee/topics/autodevops/).

If Auto DevOps is not already enabled for this project, you can [turn it on](https://docs.gitlab.com/ee/topics/autodevops/#enabling-auto-devops) in the project settings.

## Startup

### Backend

```bash
cd kala-game-service
mvn spring-boot:run
```

Note that I attempted to create this project with reactive programming and it is possible and it would be more efficient, however I had to rollback because of the amount of time it would take to complete it.

### Frontend

```bash
cd kala-game-web
npm start
```

### Available users

Users are currently hardcoded and there are only two:

|Username|Password|
|---|---|
|player1|admin|
|player2|admin|

### cUrl

```bash
curl -i -H "Content-Type: application/x-www-form-urlencoded" -c cookies.txt  -d "username=player1&password=admin" -X POST http://localhost:8081/login
curl -i -b cookies.txt http://localhost:8081/user
curl -i -b cookies.txt -X POST http://localhost:8081/api
curl -i -b cookies.txt -X POST http://localhost:8081/api/create
curl -i -b cookies.txt -X PUT http://localhost:8081/api/move/16/2
curl -i -b cookies.txt -X PUT http://localhost:8081/api/move/16/3

curl -i -H "Content-Type: application/x-www-form-urlencoded" -c cookies2.txt  -d "username=player2&password=admin" -X POST http://localhost:8081/login
curl -i -b cookies2.txt -X PUT http://localhost:8081/api/join/16
```

### Practical Example

Have a look at [game.sh](./game.sh) for a practical example, where player 1 wins.

This example relies on static Id's so you can only run it once after a restart.

1. [cookies.txt](./cookies.txt) holds the session for player1
2. [cookies2.txt](./cookies2.txt) holds the session for player2

### Front - End - Web application

In order to start the FE, you need to have NODE.JS installed.

Preferably try to install everything using Yarn.

```bash
yarn install
```

To start, go to [kala-game-web](./kala-game-web) and run:

```bash
npm start
```
