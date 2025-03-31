
-- CREATE DATABASE
DROP DATABASE IF EXISTS groomboard;
CREATE DATABASE goormboard;
Grant all privileges on goormboard.* to 'changi1122'@'%';


-- CREATE TABLE
use goormboard;

DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS users;

-- users 테이블 생성
CREATE TABLE IF NOT EXISTS users (
     login_id	VARCHAR(255) PRIMARY KEY,
     `password`	VARCHAR(512) NOT NULL,
     username	VARCHAR(20) NOT NULL,
     user_role	VARCHAR(20) NOT NULL,
     created_at	DATETIME
) ENGINE=InnoDB;
DESCRIBE users;

-- board 테이블 생성
CREATE TABLE IF NOT EXISTS board (
     post_id		INT PRIMARY KEY AUTO_INCREMENT COMMENT '게시글 ID',
     title		VARCHAR(100) NOT NULL COMMENT '제목',
     body		MEDIUMTEXT NOT NULL COMMENT '본문',
     category	VARCHAR(20) NOT NULL COMMENT '카테고리',
     user_id		VARCHAR(255) NOT NULL,
     created_at	DATETIME,
     edited_at	DATETIME,
     CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (login_id)
) ENGINE=InnoDB;

DESCRIBE board;

