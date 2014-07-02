###offsite-interview-example

An example of our offsite interview problem utilizing jukito(junit, guice, and mockito), hibernate-entitymanager, guice-persist with hsqldb

This example uses [guice-persist](https://code.google.com/p/google-guice/wiki/GuicePersist) to fire up a JPA `EntityManager` (in this case, Hibernate's implementation) and the [hsqldb](http://www.hsqldb.org/) database engine.

##Launching the hsqldb database
To launch the database, simply run the `db.launch` file in eclipse or from the root of the project file, run the following:
```java
java -cp lib/hsqldb-2.3.2.jar org.hsqldb.server.Server --database.0 file:db/example --dbname.0 example
```
##Launching the hsqldb database manager
To launch the database manager (when the database is already running), which is used to query the database, simply run the `db-manager.launch` file in eclipse or from the root of the project file, run the following:
```java
java -cp lib/hsqldb-2.3.2.jar org.hsqldb.util.DatabaseManagerSwing --url jdbc:hsqldb:hsql://localhost/example
```

##Links
* [Google Guice](https://code.google.com/p/google-guice/) - Dependency Injection
* [Guice Persist](https://code.google.com/p/google-guice/wiki/GuicePersist) - Using Guice with JPA
* [HSQLDB](http://www.hsqldb.org/) - 100% Java Database
* [Hibernate](http://hibernate.org/orm/documentation/) - A very popular ORM
* [Mockito](https://code.google.com/p/mockito/) - A mocking testing framework
* [Jukito](https://github.com/ArcBees/Jukito) - A testing framework using Mockito and Guice
* [Arrested Development](http://www.imdb.com/title/tt0367279/) - A TV show from the mid 2000's on which the example domain model is based
