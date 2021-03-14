# springboot-kafka-hibernate

## Description

This project contains 3 sub projects. A simple user service RESTful (simple-service-user) containing two tables: a user table and an address table. The User to Address table is a `ManyToOne` and is facilitated by Hibernate. The following is currently supported on the user service:

* CreateUser
* CreateAddress
* Get User By Email
* Get User By Country

The CreateUser API has a path variable allowing for an event to be published to a consumer over Kafka. The Simple User consumer project (simple-consumer-user) listens on a `create-user` topic. The consumer service simply prints the received topic to the console in a format `UserCreated with ID: n`
The user service leverages a postgres database (simple-db-user) that can be initialized with a db passed as an environment variable.

## Prerequisites

Docker desktop must be installed and a make utility will be required. Each project employs a makefile with specific targets allowing for working with docker and Kubernetes.

## Steps to Run

The project can be run by making all three docker images from the project target folders using the `make image` command. A make utility must be installed (see appendix). The `simple-service-user` project contains a compose strategy. It contains a series of compose yaml files that will stand up a complete running environment. From the simple-service-user project execute `make run` from the command line. The following containers will launch (use `docker ps -a` command to ensure you have the three required images built):

* simple-service-user
* simple-db-user (postgres)
* simple-consumer-user
* zookeeper
* kafka
* pgadmin

Once running you can visit the swagger page for the user service (this will take a few minutes the first time):
`http://localhost:8700/test/api/1.0/swagger-ui.html`

Let's now visit `localhost:5454`. You will see the PGadmin login. Our login as set in our compose file is `admin@mydomain.com` and the password is `password`.

The first thing we will need to do is setup our connection. Right click Servers and select `create` followed by `server`. Go to the connection tab. We need to enter our `Host`.

A word about Docker networking:
We started all our containers from one command and therefore we have a singular network in which our services are running. In docker the service name in our docker file is addressable. Our service is `postgres`:

```bash
..
services:
  `postgres:`
    image: acme.io/postgres
...
```

Therefore we are going to enter:

```bash
host: `postgres`.
port: `5432`
Maintenance Database: `postgres`
Username: `postgres`
Password: `password`
```

Click `Save`

And we should be in.!
Navigate in the database folder to our `userdb/schemas/tables/`.Right click the table and get all rows.

You should see created users and addresses.

### Kafak Comsumer

If you select `true` for publish when creating a user a message will be sent via kafka to the consumer. The following is what you will see in the command console after you post the user:

```cmd
2021-03-14 18:10:53,509  INFO org.springframework.kafka.KafkaListenerEndpointContainer#0-0-C-1 com.acme.unified.user.consumer.kafka.KafkaConsumer receiveMessage - Received message='UserCreated with ID: 2' on topic='create-user'

```

## Known Issues

PgAdmin4 and Postgres both use local volumes and can be found in the `/db/` folder. `db_data` contains the database and `pga4` the PgAdmin4 data. In this way the database will persist between runs. PgAdmin4 may not start in some cases and the folder `pga4` has to be deleted. Likewise if you need to start fresh you can simply delete both folders.

# Appendix

### GNU Make

A basic make utility will be required for building local cluster instances on docker. Download GNU make from `http://www.gnu.org/software/make/`. Download the latest version from a mirror.

Unpack the version to a folder in you `env` folder.

You can also find an installable version 3.8.1 at sourceforge.

"https://sourceforge.net/projects/gnuwin32/files/make/3.81/make-3.81.exe/download?use_mirror=managedway&download="

Once installed you will need to add a path for it in your environment variables.

```bash
GNUMAKE_HOME c:\develop\env\GnuWin-3.8.1
GNUMAKE = %GNUMAKE_HOME%\bin

```
