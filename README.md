springboot-testcontainer-activemq
=================================

[![Maven Central](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/avides/springboot/testcontainer/springboot-testcontainer-activemq/maven-metadata.xml.svg)](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.avides.springboot.testcontainer%22%20AND%20a%3A%22springboot-testcontainer-activemq%22)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/4d64ab37576249f694bbb42e7d2cab56)](https://www.codacy.com/app/avides-builds/springboot-testcontainer-activemq)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/4d64ab37576249f694bbb42e7d2cab56)](https://www.codacy.com/app/avides-builds/springboot-testcontainer-activemq)
[![Build Status](https://travis-ci.org/springboot-testcontainer/springboot-testcontainer-activemq.svg?branch=master)](https://travis-ci.org/springboot-testcontainer/springboot-testcontainer-activemq)

### Dependency
```xml
<dependency>
	<groupId>com.avides.springboot.testcontainer</groupId>
	<artifactId>springboot-testcontainer-activemq</artifactId>
	<version>0.1.0-RC2</version>
	<scope>test</scope>
</dependency>
```

### Configuration
Properties consumed (in `bootstrap.properties`):
- `embedded.container.activemq.enabled` (default is `true`)
- `embedded.container.activemq.startup-timeout` (default is `30`)
- `embedded.container.activemq.docker-image` (default is `rmohr/activemq:5.15.4-alpine`)
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
