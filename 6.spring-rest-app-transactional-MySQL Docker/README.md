# Adding MySQL - Docker - Permanent DB

### Run this command:

`docker run --detach --env MYSQL_ROOT_PASSWORD=password --env MYSQL_USER=username --env MYSQL_PASSWORD=timothy --env MYSQL_DATABASE=learnspring --name learnspringdocker --publish 3306:3306 mysql:8-oracle`

### Remove h2 dependency and add MySQL dependency:

        <dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>