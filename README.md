# z-monitor
Z-Monitor: A Monitoring Tool for IEEE 802.15.4 Wireless Personal Area Networks
README for Z-Monitor version 3.0 [website: www.z-monitor.org]

Authors/Contact: <dev@z-monitor.org>
The list of all contributors is available at www.z-monitor.org/index.php?title=Contributors

I - Description
Z-Monitor is a monitoring tool for IEEE802.15.4/ZigBee and 6LowPAN based WSN written in Java
Z-Monitor presents the following features:
	1. Packet Sniffing.
	2. Traffic Timeline.
	3. Packet Analysis.


II - Compatibility issues
	1. Compatible with TinyOS 2.1/JDK 1.6(openJDK 1.6)
	2. Tested only with TelosB motes (not tested with MICAz and other platforms). 


III - Application
+ packetsniffer: contains the sniffer application that must be embedded in the base station (i.e. TelosB mote attached to the PC that captures packets). It was developed by Jan Hauer, the author of the TKN 15.4 implementation and is available at https://github.com/tinyos/tinyos-main/tree/master/apps/tests/tkn154/packetsniffer.
  
+ ZigBee/6LowPAN network: Z-monitor can analyze the following implementations:
  ZigBee implementation: 
	- TinyOS ZigBee Cluster-Tree over the IEEE 802.15.4 implementation (https://github.com/tinyos/tinyos-main/tree/master/tos/lib/zigbee).
	
  6LowPAN implementations:
	- BLIP: 
	  To install blip on motes, have a look at blip tutorial (http://tinyos.stanford.edu/tinyos-wiki/index.php/BLIP_Tutorial). 
	  To be able to sniff packets with packetsniffer application, you have to change the channel in makefile of the two applications IPBaseStation and UDPEcho to 26.
	- Contiki implementation:
	  Contiki applications can be found in /contiki/examples (www.contiki-os.org). These applications incluse also basic RPL examples.


IV - Directories
- Z-Monitor which contains:
	+ History: folder where to save captured packets from the GUI to a text and XML file.
	+ ZM: contains the executable file of the Z-Monitor Sniffer.
	+ image: contains the icons and images of the sniffer.


V - Z-Monitor Installation and execution
You must have a TelosB sensor that is attached to the PC through a USB port, this device is called the "base station".
You  need to install the Sniffer Application "packetsniffer/SnifferC.nc" on the base station. 
This base station aims to bridge the traffic between the WSN and the PC.

There are two possible ways to run the sniffing application:
	1. By run JAR file: from Z-Monitor directory in a command line as follows:
		java -jar "ZM/Zmonitor.jar"

	2. By run the binary files:
		java zmonitor

To enable the different operation modes of Z-Monitor, we implemented a boot up choice where the user can select which mode to run, when launching the execution of Z-Monitor. In particular, the user should choose between the following options:
    1. The Standalone version:
		it is the traditional mode, where a node acts as a sniffer showing the local captured and analyzed packets.
	
	2. The Provider version:
		same as standalone, but forced to save packets into a local or remote Z-Server.
	
	3. The Client version:
		without any special hardware attached to the Z-Monitor, the user connects to the Z-Server and analyses the traffic of the interested network in real-time or by looking at the past recent history stored in the database.


VI - ZServer installation:
In order to be able to work with the distributed sniffing feature of Z-Monitor, it is necessary to create a ZServer, which is a local or remote server machine, hosting the database where packets are stored.

The easiest way to get your own Z-Server is to follow the next steps by issuing commands to the terminal (Ubuntu based):
	1. Install mysql server:
		sudo aptitude install mysql-common mysql-server
		
	2. Be sure that you allow the connections from localhost and from remote host to your mysql server:
		Edit file /etc/mysql/my.cnf, changing the value of parameter bind-address, from the localhost to your local or public IP (take into consideration that mysql by default use port 3306, for your NAT configuration). If the mysql server is located in the same PC where you are starting Z-Monitor Client version, comment the line "bind-address = XXXXXX" to do not specify anything related to bind-address. Then, restart mysql service:
		service mysql restart
		
	3. First action is to create a database where to restore the copy of the database included in this release:
		mysql -u root -p
		It will query you about the password for user root or any specified, if root did not define any password yet, this command will allow “mysqladmin -u root -p newpass”)
		mysql>create database guidelines;
		
	4. Allow connections to this new database:
		mysql>GRANT ALL PRIVILEGES ON guidelines.* TO [user]@[host] IDENTIFIED BY [userpassword];
		mysql>FLUSH PRIVILEGES;
		
	5. Copy the DB included in this release into you local server. These commands are handy.
		To generate backup of your database:
			mysqldump -u root -p[password] guidelines > db-backup.out
		To restore your backup of your database:
			mysql -u root -p[password] guidelines < db-backup.out

An user "generic" with pass "generic" is already created, but the administrator of Z-Server can create new users easily using tools such as Mysql Workbench, available at www.mysql.com/products/workbench


VII - Working with the distributed sniffing mode:
In the distributed sniffing mode, Z-Monitor has the feature of distributed sniffing in order to monitor larger networks, i.e., those where only one sniffer is not able to record the whole	traffic. Basically, by exploiting the possibility of the standalone version to access a remote database, and by using two extra IDs as keys for the database (an ID for the network and one for the user), a client-server architecture has been implemented.
More in detail, multiple standalone versions of Z-Monitor (which are named as provider, in this case, and are composed by a host PC7 with a sniffing hardware attached) collect local pictures of the network and send the data through out-of-band wireless or wired links (e.g., WLAN) to a global remote Z-Server, hosting the database. 
Consequently, a remote user running a Z-Monitor Client can monitor the full network in real-time by accessing the Z-Server and continuously refreshing the new data from the database.

- When the Standalone mode is chosen, local packets are captured.

- If the Provider version is chosen, it will be the same as Standalone, but with the possibility to save packets into Z-Server DataBase. To do that,
you only need to enable the connection with the Z-Server.
After checked the connection check-box, two different parts will appear.
	+ The first part is related with the Z-Server connection (servername, port, database, user and pass required). For debugging purposes, the default localhost server is loaded, but the user can change it. The default values are (localhost, 3306, prueba, root and your password root).
	
	+ The second part is related to how the distributed version of the tool should run (user, pass and task's ID to be monitored). User and task's ID are needed to allow multiple users to work remotely at the same time. To create a new task, the task's ID field should be left as blank. 

If the configuration is correct, when the user starts the sniffing process, packets start to arrive and they are automatically saved in the Z-Server's database.
	 
- If the Client version is chosen, the sniffer device attached to the PC is not required. When the tool initiates, it will ask for the same parameters
as the Provider version. First, the parameters that are related to the Z-Server connection, and second, the parameters related to the user and the task that contains the packets to be shown in a remote session. When the user starts the capture process, the packets are extracted from the Z-Server's database, according to the task chosen. Basic security issues have been taken into account, i.e. one user can only monitor tasks for which he is allowed. It is mandatory to select the task's ID that you want to capture.
