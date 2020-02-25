

#########################################################
-------- Sql script -------


CREATE USER IF NOT EXISTS jwduser IDENTIFIED BY 'pass';

DROP DATABASE IF EXISTS jwddb;
CREATE DATABASE jwddb DEFAULT CHARACTER SET utf8;

USE jwddb;

GRANT ALL ON jwddb.* TO 'jwduser'@'%';

FLUSH PRIVILEGES;

#########################################################