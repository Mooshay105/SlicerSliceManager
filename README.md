# Slicer Slice Manager
## ABOUT
Slicer Slice Manager Is A Spigot / Paper Minecraft Plugin That Is Like A Plot Plugin But The User Gets A One By Infinity Slice Of The World!
## INSTALL
### Install The Dependencies:
#### Debian:
```
sudo apt install mysql
```
### Set Up The SQL DB
First Open MySQL By Running:
```
sudo mysql
```
Now We Will Create A User And SQL DB:
(Note: `<your-username>` and `<your-password>` are placeholders so please replace them with the username and password!)
```
CREATE DATABASE SlicesDatabase;

CREATE USER <your-username>@localhost IDENTIFIED BY '<your-password>';

GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON wordpress.* TO wordpress@localhost;

FLUSH PRIVILEGES;
```
Now in the plugin config file put your username and password so it look like this:
(Note: `<your-username>` and `<your-password>` are placeholders so please replace them with the username and password!)
```
host: 'localhost'
port: 3306
database: 'SlicesDatabase'
username: '<your-username>'
password: '<your-password>'
```

Done!
## HELP / FAQ
Comming Soon
Until Then Please Just Submit An Github Issue