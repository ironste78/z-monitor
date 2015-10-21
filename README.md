# z-monitor
Z-Monitor: A Monitoring Tool for IEEE 802.15.4 Wireless Personal Area Networks
README for Z-Monitor version 3.0 [website: http://www.z-monitor.org/]

Authors/Contact: <dev@z-monitor.org>
The list of all contributors is available at http://www.z-monitor.org/index.php?title=Contributors

I- Description:

Z-Monitor is a monitoring tool for IEEE802.15.4/ZigBee and 6LowPAN based WSN written in Java
Z-Monitor presents the following features:
	1- Packet Sniffing.
	2- Traffic timeline
	3- Packet Analysis.
	
II- Compatibility issues
	1- Compatible with TinyOS 2.1/JDK 1.6(openJDK 1.6)
	2- Tested only with TelosB motes (not tested with MICAz and other platforms). 


III- Application:
+ tknsniffer: contains the sniffer application that must be embedded in the base station (i.e. TelosB mote attached to the PC that captures packets)
  it was developed by Jan Hauer, the author of the TKN 15.4 implementation.
+ ZigBee/6LowPAN network: Z-monitor can analyze the following implementations:
  ZigBee implementation: 
    - TinyOS ZigBee Cluster-Tree over the IEEE 802.15.4 implementation (https://github.com/tinyos/tinyos-main/tree/master/tos/lib/zigbee).
  6LowPAN implementations:
	-BLIP: to install blip on motes, have a look at blip tutorial (http://docs.tinyos.net/index.php/BLIP_Tutorial). 
	 To be able to sniff packets with tknsniffer application, you have to change the channel in makefile of the two applications IPBaseStation and UDPEcho to 26.
	-Contiki implementation: Contiki applications can be found in /contiki2.x/examples(http://www.sics.se/contiki/). These applications incluse also basic RPL examples.

IV- Directories:
- Z-Monitor which contains:
	+ History: folder where to save captured packets from the GUI to a text and XML file.
	+ ZM: contains the executable file of the Z-Monitor Sniffer.
	+ image: contains the icons and images of the sniffer.

V- Z-Monitor Installation and execution:
You must have a TelosB sensor that is attached to the PC through a USB port, this device is called the "base station".
You  need to install the Sniffer Application "tknsniffer/SnifferC.nc" on the base station. 
This base station aims to bridge the traffic between the WSN and the PC.

There are two possible ways to run the sniffing application:
	1. By run JAR file: from Z-Monitor directory in a command line as follows:
		java -jar "ZM/Zmonitor.jar"
	2. By run the binary files:
		java zmonitor


To enable the different operation modes of Z-Monitor, we implemented a boot up choice where the user can select which mode to run, when launching
the execution of Z-Monitor. In particular, the user should choose between the following options:
    1. The standalone version is the traditional mode, where a node acts as a sniffer showing the local captured and analyzed packets.
	2. The Provider version: Same as standalone, but forced to save packets into a local or remote Z-Server.
	3. Client version: without any special hardware attached to the Z-Monitor, the user connects to the Z-Server and analyses the traffic of the interested
       network in real-time or by looking at the past recent history stored in the database.

	   

VI- ZServer installation:

In order to be able to work with the distributed sniffing feature of Z-Monitor, it is necessary to create a ZSrever, which is a local or remote server machine, 
hosting the database where packets are stored.

The easiest way to get your own Z-Server is to follow the next steps:
    1. Install mysql server (Ubuntu based):
       user@ubuntu#sudo aptitude install mysql-common mysql-server
	2. Be sure that you allow the connections from localhost and from remote host to your mysql server.
      (Ubuntu based) Edit file /etc/mysql/my.cnf, changing the value of parameter bind-address, from the localhost to your local IP, or public IP 
      (take into consideration that mysql by default use port 3306, for your NAT configuration). If the mysql server is located in the same PC
      where you are starting Z-Monitor Client version, comment the line "bind-address = XXXXXX" to do not specify nothing related
	  to bind-address. Then, restart mysql service,
      user@ubuntu#service mysql restart
	3. First action is to create a database where to restore the copy of the database attached 
	   user@ubuntu#mysql -u root -p. It will query you about the password for user root or any specified,if root does not defined the password yet, 
	   this command will allow “mysqladmin -u root -p newpass”)
       mysql>create database guidelines;
	4. Allow connections to this new database 
	   mysql>GRANT ALL PRIVILEGES ON guidelines.* TO [user]@[host] IDENTIFIED BY [userpassword];
       mysql>FLUSH PRIVILEGES;
    5. Copy the DB attached to you local server. These commands are useful.
       user@ubuntu#mysqldump -u root -p[password] guidelines > db-backup.out -> to generate backup of your database
       user@ubuntu#mysql -u root -p[password] guidelines < db-backup.out <- to restore your backup of your database
Regarding to section 1.2, user “generic” with pass “generic” is already created, but the
administrator of Z-Server can create new users easily using tools as Mysql Workbench
http://www.mysql.com/products/workbench/

VII- Working with the distributed sniffing mode:

In the distributed sniffing mode, Z-Monitor has the feature of distributed sniffing in order to monitor larger networks, i.e., those where only one sniffer 
is not able to record the whole	traffic. Basically, by exploiting the possibility of the standalone version to access a remote database, and by using two 
extra IDs as keys for the database (an ID for the network and one for the user), a client-server architecture has been implemented. More in detail, multiple
 standalone versions of Z-Monitor (which are named as provider, in this case, and are composed by a host PC7 with a sniffing hardware attached) collect local
pictures of the network and send the data through out-of-band wireless or wired links (e.g., WLAN) to a global remote Z-Server, hosting the database. 
Consequently, a remote user running a Z-Monitor Client can monitor the full network in real-time by accessing the Z-Server and continuously refreshing the new 
data from the database.  

- When you choose the standalone mode, you will capture the local packets (as in version 2.1).

- If you choose the Provider version, it will be the same as Standalone, but with the possibility to save packets into Z-Server DataBase. To do that,
you only need to enable the connection with the Z-Server.
In this interface, after enable the connection check-box, two different parts will appear.
   + The first part is related with the Z-Server connection (servername, port, database, user and pass required) . Due to debug purpose information about the default 
     localhost server is loaded, the user can change it in a free-way. The default values are (localhost, 3306, prueba, root and your password root).
   + The second part is related to the way of work of the distributed version of the tool (user, pass and id of the task to be monitored). User and ID task 
     are required in order to allow a lot of user remotely working as same time and maybe with different tasks simultaneously. If you wish to create a new task, 
	 you should left the task ID field in blank. After configuration correctly, the Z-Server database access, and the user start the sniffing process, packets
	 start to arrive and they are automatically saved in Z-Server.

	 
- If you choose the Client version, the sniffer that is attached to the PC is not required. When the tool initiated, it will ask for the same parameters
as the Provider version. First, the parameters that are related to Z-Server connection, and secondl, the parameters related to the user and the task that 
contains the packets to be shown in a remote manner. When the user starts the capture process, the packets are consulted from the Z-Server according to 
the task chosen. Basic security issues had been taken into account, i.e. one user can only monitored tasks those allow to him. It is mandatory to select the task
ID that you want to capture.

