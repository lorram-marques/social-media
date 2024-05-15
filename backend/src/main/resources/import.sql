INSERT INTO tb_user (name, email) VALUES ('Anderson', 'anderson@gmail.com');

INSERT INTO tb_post (text, date) VALUES ('post-placeholder', '2024-08-23T10:30:00Z');

INSERT INTO tb_comment (text, date) VALUES ('comment-placeholder', '2024-08-23T11:30:00Z');

INSERT INTO tb_post_user (post_id, user_id) VALUES (1, 1);
INSERT INTO tb_comment_user (comment_id, user_id) VALUES (1, 1);
INSERT INTO tb_post_comment (post_id, comment_id) VALUES (1, 1); 

INSERT INTO tb_likelog () VALUES ();
INSERT INTO tb_like_user (like_id, user_id) VALUES (1, 1);
INSERT INTO tb_like_post (like_id, post_id) VALUES (1, 1);