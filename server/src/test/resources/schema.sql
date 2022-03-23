CREATE TABLE IF NOT EXISTS `users` (
    user_id int(11) unsigned AUTO_INCREMENT NOT NULL,
    user_name    VARCHAR(100) NOT NULL,
    created_user VARCHAR(100) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_user VARCHAR(100) NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (user_id)
);

CREATE TABLE IF NOT EXISTS `role` (
    role_id int(11) unsigned AUTO_INCREMENT NOT NULL,
    role_name    VARCHAR(50) NOT NULL,
    created_user VARCHAR(100) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_user VARCHAR(100) NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (role_id)
);

CREATE TABLE IF NOT EXISTS `user_role` (
    user_role_id int(11) unsigned AUTO_INCREMENT NOT NULL,
    user_id int(11) unsigned NOT NULL,
    role_id int(11) unsigned NOT NULL,
    created_user VARCHAR(100) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_user VARCHAR(100) NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (user_role_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (role_id) REFERENCES role(role_id)
);

-- 本マスタ
-- 作品名、原作、作画、出版社
CREATE TABLE IF NOT EXISTS `books` (
    book_id int(11) unsigned AUTO_INCREMENT NOT NULL,
    title VARCHAR(100) NOT NULL,
    original_author VARCHAR(100) NOT NULL,
    drawer VARCHAR(100) NOT NULL,
    publisher VARCHAR(100) NOT NULL,
    created_user VARCHAR(100) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_user VARCHAR(100) NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (book_id)
);

-- 本情報
-- 作品名、発売日、特装版
CREATE TABLE IF NOT EXISTS `book_info` (
    book_info_id int(11) unsigned AUTO_INCREMENT NOT NULL,
    book_id int(11) unsigned NOT NULL,
    title VARCHAR(200) NOT NULL,
    release_date VARCHAR(13) NOT NULL,
    special_edition BOOLEAN NOT NULL DEFAULT 0,
    created_user VARCHAR(100) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_user VARCHAR(100) NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (book_info_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);

CREATE TABLE IF NOT EXISTS `user_books` (
    user_book_id int(11) unsigned AUTO_INCREMENT NOT NULL,
    user_id int(11) unsigned NOT NULL,
    book_id int(11) unsigned NOT NULL,
    created_user VARCHAR(100) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_user VARCHAR(100) NOT NULL,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (user_book_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);
