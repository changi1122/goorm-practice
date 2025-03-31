use goormboard;

-- users 생성/삭제
INSERT INTO users(login_id, `password`, username, user_role, created_at)
VALUES
('user01', 'pass01', 'user01', 'USER', NOW()),
('user02', 'pass02', 'user02', 'USER', NOW());

SELECT * FROM users;

DELETE FROM users WHERE login_id LIKE 'user%';


-- board 생성/수정/삭제
INSERT INTO board(title, body, category, user_id, created_at)
VALUES ('제목', '본문', '카테고리', 'user01', NOW());

SELECT * FROM board;

UPDATE board SET body='수정한 본문' WHERE post_id = 1;

SELECT * FROM board;

DELETE FROM board WHERE post_id = 1;



