# containerized-wildfly-app
Wildfly-App with a couple of examples how to containerize and configure it.

#Usage

## Build base image
```
docker build -f Dockerfile-custom-base -t my-custom-wildfly-base .
```

## Simple installation with WAR-file

```shell script
# build war
./gradlew war
# build docker image
docker build -f Dockerfile-war -t wildfly:war .
# run container
docker run --name wildfly wildfly:war
```

## Multi Stage build: simple installation with WAR-file

```shell script
# build docker image
docker build -f Dockerfile-multistage -t wildfly:multistage .
# run container
docker run --name wildfly wildfly:multistage
```

## WAR-file installation with fixed database configuration

```shell script
# build war
./gradlew war
# build docker image
docker build -f Dockerfile-war-fixed-conf -t wildfly:war-fixed-conf .
# run container
docker run --name wildfly wildfly:war-fixed-conf
```

## WAR-file installation with database configuration via the environment

```shell script
# build war
./gradlew war
# build docker image
docker build -f Dockerfile-war-env-conf -t wildfly:war-env-conf .
# run container
docker run --name wildfly -e MYSQL_USERNAME=jboss -e MYSQL_PASSWORD=jboss -e MYSQL_HOST=172.17.0.2 wildfly:war-env-conf
```

## Simple installation with RPM-file

```shell script
# build rpm
./gradlew clean buildRpm
# build docker image
docker build -f Dockerfile-rpm -t wildfly:rpm .
# run container
docker run --name wildfly wildfly:rpm
```

## RPM-file installation with fixed database configuration via Jboss CLI

```shell script
# build rpm
# make sure to use scripts/rpm/postInstall-fixed-creds-cli.sh in build.gradle
./gradlew clean buildRpm
# build docker image
docker build -f Dockerfile-rpm-cli -t wildfly:rpm-cli .
# run container
docker run --name wildfly wildfly:rpm-cli
```

## RPM-file installation with env-configurable database configuration via Jboss CLI

```shell script
# make sure to use scripts/rpm/postInstall-env-cli.sh in build.gradle
./gradlew clean buildRpm
# build docker image
docker build -f Dockerfile-rpm-cli -t wildfly:rpm-cli-env .
# run container
docker run --name wildfly -e MYSQL_USERNAME=jboss -e MYSQL_PASSWORD=jboss -e MYSQL_HOST=172.17.0.2 wildfly:rpm-cli-env
```

## RPM-file installation via static files with fixed database configuration

```shell script
# build rpm
# make sure to use scripts/rpm/postInstall-copy-config-fixed.sh in build.gradle
./gradlew clean buildRpm
# build docker image
docker build -f Dockerfile-rpm -t wildfly:rpm-copy-fixed .
# run container
docker run --name wildfly wildfly:rpm-copy-fixed
```

## RPM-file installation via static files with env-configurable database configuration

```shell script
# build rpm
# make sure to use scripts/rpm/postInstall-copy-config-env.sh in build.gradle
./gradlew clean buildRpm
# build docker image
docker build -f Dockerfile-rpm -t wildfly:rpm-copy-env .
# run container
docker run --name wildfly -e MYSQL_USERNAME=jboss -e MYSQL_PASSWORD=jboss -e MYSQL_HOST=172.17.0.2 wildfly:rpm-copy-env
```

