### Before you run Application

You need to create database.

```
mysql> create database onestop_batch6;
Query OK, 1 row affected (0.04 sec)

mysql> create user 'onestop_batch6'@'localhost' identified by 'onestop_batch6';
Query OK, 0 rows affected (0.04 sec)

mysql> grant all privileges on onestop_batch6.* to 'onestop_batch6'@'localhost'; 
Query OK, 0 rows affected (0.01 sec)
```