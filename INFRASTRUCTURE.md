Hackathon infrastructure details
====

# Endpoints

/!\ Service endpoints are available using internal DNS (i.e. on the server). See setup hints below.

| Service | Endpoint | REST Endpoint |
| ------- | -------- | ------------ |
| Kafka   | kafka.service.ocean:9092 | n/a |
| Zookeeper | zookeeper.service.ocean:2181 | n/a |
| Schema Registry | schema-registry.service.ocean:8081 | https://schema-registry.api.axa.sqooba.io |
| Kafka Connect | connect.service.ocean:8083 | https://connect.api.axa.sqooba.io |
| Kafka REST | kafka-rest.service.ocean:8082 | https://kafka-rest.api.axa.sqooba.io |
| KSQL | ksql.service.ocean:8088 | https://ksql.api.axa.sqooba.io |
| Confluent Control Center | control-center.service.ocean:9021 | https://control-center.api.axa.sqooba.io |
| MongoDB | mongodb.service.ocean:27017 | n/a |
| Kibana | kibana.service.ocean:5601 | https://kibana.api.axa.sqooba.io |

# EC2 Instances

s. Slack channel


# SSH Setup

Edit your `~/.ssh/config` file, and add the following section.

```
Host axanode1
  Hostname 18.184.19.115
  User centos
  IdentityFile ~/path/to/node1.pem
  PreferredAuthentications publickey
  ControlPath ~/.ssh/ssh_control_%h_%p_%r
```

Please adapt `IdentityFile` to your need,
and repeat the same section for the other nodes (updating `Hostname` to point to EIP in the table above)

You can now `ssh axanode1`

# DNS Setup

Using sshuttle and forwarding the DNS, you'll be able to resolve the DNS on your laptop
and thus have a consistent experience for the setup available on the team nodes.

```
sshuttle -r axanode1 --dns --to-ns 172.31.38.55 172.31.0.0/16
```
