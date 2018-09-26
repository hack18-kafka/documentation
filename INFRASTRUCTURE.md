Hackathon infrastructure details
====

# Endpoints

/!\ Service endpoints are available using internal DNS (i.e. on the server). See setup hints below.

| Service | Endpoint | REST Endpoint |
| ------- | -------- | ------------ |
| Kafka   | kafka.service.ocean:9092 | n/a |
| Zookeeper | zookeeper.service.ocean:2181 | n/a |
| Schema Registry | schema-registry.service.ocean:8081 | https://schema-regirsty.api.axa.sqooba.io |
| Kafka Connect | connect.service.ocean:8083 | https://connect.api.axa.sqooba.io |
| Kafka REST | kafka-rest.service.ocean:8082 | https://kafka-rest.api.axa.sqooba.io |
| KSQL | ksql.service.ocean:8088 | https://ksql.api.axa.sqooba.io |
| MongoDB | mongodb.service.ocean:27017 | n/a |

# EC2 Instances

| Name | EIP | Private IP | OS  | RAM | Disk | Role |
| ---- | --- | ---------- | --- | --- | ---- | ---- | 
| Node1 | 18.184.19.115 | 172.31.38.55  | Centos | 16GB | 50GB | Kafka, Zookeeper, DNS, proxy |
| Node2 | 52.29.223.176 | 172.31.41.242 | Centos | 16GB | 50GB | ELK |
| Node3 | 54.93.202.198 | 172.31.33.107 | Centos | 8GB  | 50GB | Mongodb, DNS |
| Node4 | 18.195.137.44 | 172.31.37.87  | Centos | 8GB  | 8GB  | NodeJS, DNS |


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
