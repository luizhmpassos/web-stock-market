
## Use to run mysql db docker image, optional if you are not using a local mysqldb
# docker run --name mysql -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -v {localVolume}:/var/lib/mysql -p 3306:3306 -d mysql

# connect to mysql and run as root user
# docker exec -it {imageName} bash
# mysql -u root
#Create Databases
CREATE DATABASE webstockmarket_dev;
CREATE DATABASE webstockmarket_prod;

#Create database service accounts
CREATE USER 'webstockmarket_dev_user'@'localhost' IDENTIFIED BY 'root';
CREATE USER 'webstockmarket_prod_user'@'localhost' IDENTIFIED BY 'root';
CREATE USER 'webstockmarket_dev_user'@'%' IDENTIFIED BY 'root';
CREATE USER 'webstockmarket_prod_user'@'%' IDENTIFIED BY 'root';

#Database grants
GRANT ALL ON webstockmarket_dev.* TO 'webstockmarket_dev_user'@'localhost';
GRANT ALL ON webstockmarket_prod.* TO 'webstockmarket_prod_user'@'localhost';
GRANT ALL ON webstockmarket_dev.* TO 'webstockmarket_dev_user'@'%';
GRANT ALL ON webstockmarket_prod.* TO 'webstockmarket_prod_user'@'%';