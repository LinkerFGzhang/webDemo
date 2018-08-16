SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

-- --------------------------------------------------------

--
-- 表的结构 `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(20) NOT NULL,
  `description` VARCHAR(40)          DEFAULT NULL,
  `owner_id`    INT(11)              DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
);

-- --------------------------------------------------------

--
-- 表的结构 `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id`           INT(11)     NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(20) NOT NULL,
  `password`     VARCHAR(20) NOT NULL,
  `group_id`     INT(11)     NOT NULL,
  `head_id`      INT(11)              DEFAULT NULL,
  `generic_name` VARCHAR(40)          DEFAULT NULL,
  `token`        CHAR(16)             DEFAULT NULL,
  `create_time`  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
);

-- --------------------------------------------------------

--
-- 表的结构 `upload_files`
--

CREATE TABLE IF NOT EXISTS `upload_files` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT,
  `user_id`     INT(11)     NOT NULL,
  `type`        VARCHAR(16) NOT NULL,
  `name`        VARCHAR(40) NOT NULL,
  `fs_name`     VARCHAR(16) NOT NULL,
  `suffix`      VARCHAR(8)  NOT NULL,
  `create_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

--
-- 表的结构 `cameras`
--

CREATE TABLE IF NOT EXISTS `cameras` (
  `id`          INT(11)       NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(20)   NOT NULL,
  `description` VARCHAR(40)            DEFAULT NULL,
  `user_id`     INT(11)       NOT NULL,
  `url`         VARCHAR(100)  NOT NULL,
  `width`       INT(11)       NOT NULL DEFAULT '704',
  `height`      INT(11)       NOT NULL DEFAULT '576',
  `address`     VARCHAR(50)            DEFAULT NULL,
  `longitude`   DECIMAL(9, 6) NOT NULL,
  `latitude`    DECIMAL(9, 6) NOT NULL,
  PRIMARY KEY (`id`)
);

-- --------------------------------------------------------

-- --------------------------------------------------------

--
-- 限制表 `groups`
--
ALTER TABLE `groups`
  ADD CONSTRAINT `groups_ibfk_owner_id` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`);

--
-- 限制表 `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_group_id` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  ADD CONSTRAINT `users_ibfk_head_id` FOREIGN KEY (`head_id`) REFERENCES `upload_files` (`id`);

--
-- 限制表 `upload_files`
--
ALTER TABLE `upload_files`
  ADD CONSTRAINT `upload_files_ibfk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- 限制表 `cameras`
--
ALTER TABLE `cameras`
  ADD CONSTRAINT `cameras_ibfk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

-- --------------------------------------------------------
-- 初始化数据
-- --------------------------------------------------------

INSERT INTO `groups` (`id`, `name`, `description`) VALUES
  (1, 'admin', '管理员'),
  (2, 'advanced', '高级用户'),
  (3, 'normal', '普通用户');

INSERT INTO `users` (`id`, `name`, `password`, `group_id`, `generic_name`, `create_time`) VALUES
  (1, 'mrx', '77777772', 1, '蒙儒省', '2018-01-08 01:08:20'),
  (2, 'holyland', 'holyland', 1, '滕盛弟', '2018-01-08 01:18:28'),
  (3, 'anview', 'Anview.3302', 3, '安维科技', '2018-01-08 01:18:28'),
  (4, 'testadmin', '123456', 1, '测试管理员', '2018-01-08 01:18:28'),
  (5, 'testadv', '123456', 2, '测试高级用户', '2018-01-08 01:18:28'),
  (6, 'testuser', '123456', 3, '测试用户', '2018-01-08 01:18:28');

UPDATE `groups` SET `owner_id` = 1;

-- --------------------------------------------------------
-- 初始化数据
-- --------------------------------------------------------
