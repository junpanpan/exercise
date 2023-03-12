
REST API ENDPOINTS
GET /tournaments  --find all tournaments
GET /tournaments/{id}
POST /tournaments
PUT /tournaments/{id}
DELETE /tournaments/{id}
GET /tournaments/{id}/players  -- find all player for this tournament

POST /tournament/{id}/player
DELETE /player/{id}


## Task

The application is going through its final code review, but serious doubts were
raised about the quality of the application. The reviewers recommend a full
rewrite of the backend application.

task is to redesigning the REST endpoints and having properly defined behaviour.

Testing should be done with both unit tests, integration tests, and you testing
the application in practice by running requests against it. 


## Scope

Focus only on the backend.

### Business scope

> We need you to provide us with an API for registering in and managing
> tournaments and participants ("players" from hereon out).
>
> A tournament is some kind of event or challenge going on which has an ID,
> a name, and some kind of reward prize. All prizes will be in EUR to begin
> with, but we may want to expand to other markets with other currencies later
> on.
>
> A player will have an ID and a name. They may register to tournaments; store
> this however you want, so long as we can get all players in a tournament
> somehow.

## Running the application

If you use Linux or macOS:

  - Open a terminal.
  - Run `./mvnw spring-boot:run`.
    * If this fails, ensure it is actually executable and retry: `chmod +x ./mvnw`.
	* If it still fails for reasons you cannot figure out, please contact us.
  - Head to [http://localhost:8080](http://localhost:8080/) to see the frontend.

If you use Windows:

  - Open a Windows Terminal, Command Shell, or PowerShell.
  - Run `.\mvnw.cmd spring-boot:run`.
	* If it fails for reasons you cannot figure out, please contact us.
  - Head to [http://localhost:8080](http://localhost:8080/) to see the frontend.

NOTE: You will need Java 11 (or newer) set up and in use for this to work.
