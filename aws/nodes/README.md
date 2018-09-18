# hack18-kafka aws access
 
#### _To access your instance:_
* Open an SSH client. (find out how to connect using PuTTY)
* Locate your corresponding private key file. The wizard automatically detects the key you used to launch the instance.
* Use this command if needed: _chmod 400 key_file_
* Connect to your instance using its Public DNS: 
 * node1: ssh -i "node1.pem" root@ec2-3-120-32-232.eu-central-1.compute.amazonaws.com
 * node2: ssh -i "node2.pem" root@ec2-35-157-140-137.eu-central-1.compute.amazonaws.com
 * node3: ssh -i "node3.pem" root@ec2-18-197-210-18.eu-central-1.compute.amazonaws.com
 * node4: ssh -i "node4.pem" root@ec2-54-93-183-3.eu-central-1.compute.amazonaws.com
 * teamnode1: ssh -i "teamnode1.pem" root@ec2-18-196-206-208.eu-central-1.compute.amazonaws.com
 * teamnode2: ssh -i "teamnode2.pem" root@ec2-52-59-188-115.eu-central-1.compute.amazonaws.com
