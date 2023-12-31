CREATE TABLE IF NOT EXISTS users
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(50),
    login VARCHAR(32) NOT NULL,
    email VARCHAR(254) NOT NULL,
    birthday DATE NOT NULL,
    photo VARCHAR,
    CONSTRAINT unique_email UNIQUE(email),
    CONSTRAINT unique_login UNIQUE(login)
);

CREATE TABLE IF NOT EXISTS posts
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    post VARCHAR(4000) NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    photo VARCHAR,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS comments
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    comment VARCHAR(4000) NOT NULL,
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
    photo VARCHAR,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    post_id BIGINT NOT NULL REFERENCES posts(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS reactions --TODO: Добавить ON CONFLICT DO UPDATE
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    post_id BIGINT NOT NULL REFERENCES posts(id) ON DELETE CASCADE,
    reaction VARCHAR(20) NOT NULL,
    CONSTRAINT unique_reaction UNIQUE(user_id, post_id)
);