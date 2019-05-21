CREATE TABLE IF NOT EXISTS stack_user (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(32),
    last_name VARCHAR(32),
    email_address VARCHAR(64),
    password VARCHAR(64)
);


CREATE TABLE IF NOT EXISTS question(
     id serial PRIMARY KEY,
     user_id INT NOT NULL REFERENCES stack_user (id),
     title VARCHAR(32) NOT NULL,
     text VARCHAR(64) NOT NULL,
     creation_time TIME
);

CREATE TABLE IF NOT EXISTS theTag(
    id serial PRIMARY KEY,
    tag_name VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS tag_question(
    id serial PRIMARY KEY,
    question_id INT NOT NULL REFERENCES question(id),
    tag_id INT NOT NULL REFERENCES theTag(id),
    UNIQUE (question_id,tag_id )
);


CREATE TABLE IF NOT EXISTS answer
(
  id            serial PRIMARY KEY,
  question_id   INT         NOT NULL REFERENCES question (id),
  user_id       INT         NOT NULL REFERENCES stack_user (id),
  text          VARCHAR(64) NOT NULL,
  creation_time TIME
);


CREATE TABLE IF NOT EXISTS vote_answer
(
  id            serial PRIMARY KEY,
  user_id       INT         NOT NULL REFERENCES stack_user (id),
  answer_id     INT         NOT NULL REFERENCES answer (id),
  vote_value    INT,
  UNIQUE (user_id,answer_id )
);

CREATE TABLE IF NOT EXISTS vote_question
(
  id            serial PRIMARY KEY,
  user_id       INT         NOT NULL REFERENCES stack_user (id),
  question_id   INT         NOT NULL REFERENCES question (id),
  vote_value    INT,
  UNIQUE (user_id,question_id )
);