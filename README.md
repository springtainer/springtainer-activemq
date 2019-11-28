# springboot-testcontainer-activemq

[![Maven Central](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/avides/springboot/testcontainer/springboot-testcontainer-activemq/maven-metadata.xml.svg)](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.avides.springboot.testcontainer%22%20AND%20a%3A%22springboot-testcontainer-activemq%22)
[![Build](https://github.com/springboot-testcontainer/springboot-testcontainer-activemq/workflows/release/badge.svg)](https://github.com/springboot-testcontainer/springboot-testcontainer-activemq/actions)
[![Nightly build](https://github.com/springboot-testcontainer/springboot-testcontainer-activemq/workflows/nightly/badge.svg)](https://github.com/springboot-testcontainer/springboot-testcontainer-activemq/actions)
[![Coverage report](https://sonarcloud.io/api/project_badges/measure?project=springboot-testcontainer_springboot-testcontainer-activemq&metric=coverage)](https://sonarcloud.io/dashboard?id=springboot-testcontainer_springboot-testcontainer-activemq)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=springboot-testcontainer_springboot-testcontainer-activemq&metric=alert_status)](https://sonarcloud.io/dashboard?id=springboot-testcontainer_springboot-testcontainer-activemq)
[![Technical dept](https://sonarcloud.io/api/project_badges/measure?project=springboot-testcontainer_springboot-testcontainer-activemq&metric=sqale_index)](https://sonarcloud.io/dashboard?id=springboot-testcontainer_springboot-testcontainer-activemq)

### Dependency
```xml
<dependency>
	<groupId>com.avides.springboot.testcontainer</groupId>
	<artifactId>springboot-testcontainer-activemq</artifactId>
	<version>1.0.0-RC2</version>
	<scope>test</scope>
</dependency>
```

### Configuration
Properties consumed (in `bootstrap.properties`):
- `embedded.container.activemq.enabled` (default is `true`)
- `embedded.container.activemq.startup-timeout` (default is `30`)
- `embedded.container.activemq.docker-image` (default is `rmohr/activemq:5.15.9-alpine`)
- `embedded.container.activemq.port` (default is `61616`)

Properties provided (in `application-it.properties`):
- `embedded.container.activemq.host`
- `embedded.container.activemq.port`

Example for minimal configuration in `application-it.properties`:
```
spring.activemq.broker-url=tcp://${embedded.container.activemq.host}:${embedded.container.activemq.port}
```

## Logging
To reduce logging insert this into the logback-configuration:
```xml
<!-- Testcontainers -->
<logger name="com.github.dockerjava.jaxrs" level="WARN" />
<logger name="com.github.dockerjava.core.command" level="WARN" />
<logger name="org.apache.http" level="WARN" />
```

## Labels
The container exports multiple labels to analyze running testcontainers:
- `TESTCONTAINER_SERVICE=activemq`
- `TESTCONTAINER_IMAGE=${embedded.container.activemq.docker-image}`
- `TESTCONTAINER_STARTED=$currentTimestamp`
