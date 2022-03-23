-- ユーザー追加
INSERT INTO users(user_name, created_user, updated_user) VALUES ('MB00001', 'SYSTEM', 'SYSTEM');

-- ロール追加
INSERT INTO role(role_name, created_user, updated_user) VALUES ('SYSTEM_ADMIN_ROLE', 'SYSTEM', 'SYSTEM');
INSERT INTO role(role_name, created_user, updated_user) VALUES ('ADMIN_ROLE', 'SYSTEM', 'SYSTEM');
INSERT INTO role(role_name, created_user, updated_user) VALUES ('USER_ROLE', 'SYSTEM', 'SYSTEM');

-- ユーザーにロール追加
INSERT INTO user_role(user_id, role_id, created_user, updated_user) VALUES (1, 1, 'SYSTEM', 'SYSTEM');

-- 本マスタ追加
INSERT INTO books(title, original_author, drawer, publisher, created_user, updated_user) VALUES
('ノーゲーム・ノーライフ', '榎宮祐', '榎宮祐', 'KADOKAWA書店', 'SYSTEM', 'SYSTEM'),
('賭ケグルイ', '河本ほむら', '尚村透', 'スクウェア・エニックス', 'SYSTEM', 'SYSTEM');

INSERT INTO user_books(user_id, book_id, created_user, updated_user) VALUES (1, 1, 'SYSTEM', 'SYSTEM');

INSERT INTO book_info(book_id, title, release_date, created_user, updated_user) VALUES
(1, 'ノーゲーム・ノーライフ (1)', '2012-04-25', 'SYSTEM', 'SYSTEM'),
(1, 'ノーゲーム・ノーライフ (2)', '2012-09-25', 'SYSTEM', 'SYSTEM'),
(1, 'ノーゲーム・ノーライフ (3)', '2013-01-25', 'SYSTEM', 'SYSTEM'),
(1, 'ノーゲーム・ノーライフ (4)', '2013-06-25', 'SYSTEM', 'SYSTEM'),
(1, 'ノーゲーム・ノーライフ (5)', '2013-11-25', 'SYSTEM', 'SYSTEM'),
(1, 'ノーゲーム・ノーライフ (6)', '2014-04-25', 'SYSTEM', 'SYSTEM'),
(1, 'ノーゲーム・ノーライフ (7)', '2015-07-24', 'SYSTEM', 'SYSTEM'),
(1, 'ノーゲーム・ノーライフ (8)', '2015-12-25', 'SYSTEM', 'SYSTEM'),
(1, 'ノーゲーム・ノーライフ (9)', '2016-08-25', 'SYSTEM', 'SYSTEM'),
(1, 'ノーゲーム・ノーライフ (10)', '2018-02-24', 'SYSTEM', 'SYSTEM'),
(2, '賭ケグルイ (1)', '2014-10-22', 'SYSTEM', 'SYSTEM'),
(2, '賭ケグルイ (2)', '2014-12-22', 'SYSTEM', 'SYSTEM'),
(2, '賭ケグルイ (3)', '2015-06-22', 'SYSTEM', 'SYSTEM'),
(2, '賭ケグルイ (4)', '2015-12-22', 'SYSTEM', 'SYSTEM'),
(2, '賭ケグルイ (5)', '2016-05-21', 'SYSTEM', 'SYSTEM'),
(2, '賭ケグルイ (6)', '2017-02-22', 'SYSTEM', 'SYSTEM'),
(2, '賭ケグルイ (7)', '2017-06-22', 'SYSTEM', 'SYSTEM'),
(2, '賭ケグルイ (8)', '2017-08-22', 'SYSTEM', 'SYSTEM'),
(2, '賭ケグルイ (9)', '2018-01-27', 'SYSTEM', 'SYSTEM'),
(2, '賭ケグルイ (10)', '2018-08-22', 'SYSTEM', 'SYSTEM'),
(2, '賭ケグルイ (11)', '2019-03-22', 'SYSTEM', 'SYSTEM'),
(2, '賭ケグルイ (12)', '2019-12-21', 'SYSTEM', 'SYSTEM'),
(2, '賭ケグルイ (13)', '2020-06-22', 'SYSTEM', 'SYSTEM'),
(2, '賭ケグルイ (14)', '2021-02-22', 'SYSTEM', 'SYSTEM');
