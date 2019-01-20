DROP DATABASE IF EXISTS `task_management`;
CREATE DATABASE `task_management` DEFAULT CHARSET utf8 COLLATE utf8_bin;
GRANT ALL PRIVILEGES ON `task_management`.* TO task_management@localhost IDENTIFIED BY 'password';
