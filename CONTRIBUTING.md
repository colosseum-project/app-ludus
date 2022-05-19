# Contributing

- [Contributing](#contributing)
  - [Contributing Code](#contributing-code)
    - [Quick run](#quick-run)
    - [Run with a production-style database](#run-with-a-production-style-database)

---

## Contributing Code

_Note: Theses instructions are for linux base systems. Please, adapt them if you are using another operating system._

### Quick run

1. Clone the repository to your local disk

   ```sh
   git clone https://github.com/colosseum-project/app-ludus.git
   cd ludus
   ```

2. Set the environment variables

   _If you use VSCode, you can copy the `.env.example` file as `.env` and set the variables from the dotenv file._

   ```sh
   export SPRING_PROFILES_ACTIVE=development
   export ARENA_ENDPOINT=http://localhost:8082
   ```

   _This profile use an in-memory database on H2 Database Engine._
   _You can access to the console via `/h2-console`._

3. Run the application

   ```sh
   ./gradlew bootRun
   ```

### Run with a production-style database

_We recommand to use a MariaDB database in production, as we have tested it._
_However, feel free to use another database engine supported by Spring Boot._

The following instructions use a MariaDB database that runs on a local Docker instance:

1. Clone the repository to your local disk

   ```sh
   git clone https://github.com/colosseum-project/app-ludus.git
   cd ludus
   ```

2. Spin up a MariaDB container

   ```sh
   docker run -d -p 3306:3306 \
      -e MARIADB_DATABASE=ludus \
      -e MARIADB_USER=ludus \
      -e MARIADB_PASSWORD=ludus \
      -e MARIADB_ROOT_PASSWORD=toor \
      --name ludus-mariadb mariadb:latest
   ```

   _The database schema will be automatically created by the application._

3. Set the environment variables

   _If you use VSCode, you can copy the `.env.example` file as `.env` and set the variables from the dotenv file._

   ```sh
   export SPRING_PROFILES_ACTIVE=production,debugmode
   export DB_URL=jdbc:mariadb://localhost:3306/ludus
   export DB_USERNAME=ludus
   export DB_PASSWORD=ludus
   export ARENA_ENDPOINT=http://localhost:8082
   ```

4. Run the application

   ```sh
   ./gradlew bootRun
   ```
