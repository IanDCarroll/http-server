# http-server
Java HTTP server implementing https://github.com/8thlight/cob_spec

#### how to build the server:
clone repo
`$ mvn clean install`

#### how to run the tests:
replace `/PATH/TO/` with your absolute paths to the repo and maven
`$ java -classpath /PATH/TO/http-server/target/test-classes:/PATH/TO/http-server/target/classes:/PATH/TO/.m2/repository/junit/junit/4.12/junit-4.12.jar:/PATH/TO/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar com.ian.RunTests`

#### how to start the server:
`$ java -jar target/http-server-1.0-SNAPSHOT.jar [optional port(default 5000)]`
connect locally with a browser or client socket: `localhost:5000`