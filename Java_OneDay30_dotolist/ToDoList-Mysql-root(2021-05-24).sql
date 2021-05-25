create database TodoListDB;

create user tdUser@localhost;

Grant all privileges on *.* TO tdUser@localhost;

show databases;
use MYSQL;

 desc user;
 select * from user;
 
 alter user 'tdUser'@'localhost' identified with mysql_native_password
BY 'tdUser';

FLUSH privileges;