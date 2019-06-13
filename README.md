# Gaming Fantasy
Command line based role playing game. Simulate gaming platform. Where player can select game and play on command line.

It is also easy to extend. For adding new game to the platform you just needs to:
  - Create the game class which implements `BaseGame` interface.
  - Add Enum key to the `GameEnum` enum.
  - Implement all the methods of BaseGame interface.

# Requirements

  - Java 8 (JDK 1.8)
  - Maven 3.6.1
  - git

### Installation

Clone the project from GitHub and build the project using maven.

```sh
$ git clone https://github.com/profmoh/GamingFantasy.git
$ cd GamingFantasy
$ mvn clean package
```
To build the Jar with skipping test you can run the command
```sh
$ mvn -Dmaven.test.skip=true  clean package
```

### Run

```sh
$ java -jar GamingFantasy-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

You can run unit tests and creates an HTML report of the code coverage information using the following command. You can find this report in the `target/site/jacoco` directory under the project root directory.
```sh
$ mvn test jacoco:report
```
You can generate the report with out running the unit tests using the command
```sh
$ mvn jacoco:report
```