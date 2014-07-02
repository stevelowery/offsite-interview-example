offsite-interview-example
=========================

An example of our offsite interview problem utilizing jukito(junit, guice, and mockito), hibernate-entitymanager, guice-persist with hsqldb


##Launching the hsqldb database
To launch the database, simply run the `db.launch` file in eclipse or from the root of the project file, run the following:
```java
java -cp lib/hsqldb-2.3.2.jar org.hsqldb.server.Server --database.0 file:db/interview --dbname.0 interview`
```
