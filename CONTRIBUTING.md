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

2. Set environment variables

   ```sh
   export SPRING_PROFILES_ACTIVE=development
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
   docker run -d --name ludus-mariadb -e MARIADB_DATABASE=ludus -e MARIADB_USER=ludus -e MARIADB_PASSWORD=ludus -e MARIADB_ROOT_PASSWORD=toor -p 3306:3306 mariadb:latest
   ```

   _The database schema will be automatically created by the application._

3. Set environment variables

   ```sh
   export SPRING_PROFILES_ACTIVE=production,debugmode
   export DB_URL=jdbc:mariadb://localhost:3306/ludus
   export DB_USERNAME=ludus
   export DB_PASSWORD=ludus
   ```

4. Run the application

   ```sh
   ./gradlew bootRun
   ```
