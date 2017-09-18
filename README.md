# http-server
Java HTTP server implementing https://github.com/8thlight/cob_spec

#### how to build the server:
clone repo
`$ mvn clean install`

#### how to run the tests:
`$ mvn clean install`
-or-
`$ java -classpath /PATH/TO/REPO/http-server/target/test-classes:/PATH/TO/REPO/http-server/target/classes:/PATH/TO/MAVEN/.m2/repository/junit/junit/4.12/junit-4.12.jar:/PATH/TO/MAVEN/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar com.ian.RunTests`
replace `/PATH/TO/REPO` and `/PATH/TO/MAVEN`with your absolute paths to the repo and maven

#### how to start the server:
`$ java -jar target/http-server-1.0-SNAPSHOT.jar`
Starts default server on port 5000, directory /httpserver/public

connect locally with a browser or client socket: `localhost:5000`

You can also specify a port number with `-p [port]` and/or public directory with `-d [PATH/TO/DIRECTORY]`
If you're short on time `-dp [PATH/TO/DIRECTORY] [port]` and `-pd [port] [PATH/TO/DIRECTORY]` can also be used.