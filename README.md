# springtainer-activemq

[![Maven Central](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/avides/springboot/springtainer/springtainer-activemq/maven-metadata.xml.svg)](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.avides.springboot.springtainer%22%20AND%20a%3A%22springtainer-activemq%22)
[![Build](https://github.com/springtainer/springtainer-activemq/workflows/release/badge.svg)](https://github.com/springtainer/springtainer-activemq/actions)
[![Nightly build](https://github.com/springtainer/springtainer-activemq/workflows/nightly/badge.svg)](https://github.com/springtainer/springtainer-activemq/actions)
[![Coverage report](https://sonarcloud.io/api/project_badges/measure?project=springtainer_springtainer-activemq&metric=coverage)](https://sonarcloud.io/dashboard?id=springtainer_springtainer-activemq)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=springtainer_springtainer-activemq&metric=alert_status)](https://sonarcloud.io/dashboard?id=springtainer_springtainer-activemq)
[![Technical dept](https://sonarcloud.io/api/project_badges/measure?project=springtainer_springtainer-activemq&metric=sqale_index)](https://sonarcloud.io/dashboard?id=springtainer_springtainer-activemq)

### Dependency
```xml
<dependency>
	<groupId>com.avides.springtainer</groupId>
	<artifactId>springtainer-activemq</artifactId>
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
<!-- Springtainer -->
<logger name="com.github.dockerjava.jaxrs" level="WARN" />
<logger name="com.github.dockerjava.core.command" level="WARN" />
<logger name="org.apache.http" level="WARN" />
```

## Labels
The container exports multiple labels to analyze running springtainers:
- `SPRINGTAINER_SERVICE=activemq`
- `SPRINGTAINER_IMAGE=${embedded.container.activemq.docker-image}`
- `SPRINGTAINER_STARTED=$currentTimestamp`
