create table if not exists Users(
	email varchar(300) not null primary key,
	password varchar(300) not null,
	enabled boolean not null default true,
	first_name varchar(300),
	second_name varchar(300),
	city varchar(300),
	country varchar(300)
);

create table if not exists Authorities (
	user_email varchar(300) not null,
	authority varchar(300) not null,
	constraint fk_authorities_users
	        foreign key(user_email) references Users(email) on delete cascade
);

create unique index ix_auth_username on Authorities (user_email,authority);


create table if not exists Book(
    book_id serial primary key,
    title varchar(300) not null,
    book_language varchar(300) not null,
    price double precision not null,
    image_link text
);

create table if not exists Genre(
    genre_id serial primary key,
    genre_name varchar(300) not null
);

create table if not exists Book_Genre(
    book_id integer not null,
    genre_id integer not null,
    CONSTRAINT fk_book_id
        foreign key (book_id) references Book(book_id) on delete cascade,
    CONSTRAINT fk_genre_id
        foreign key (genre_id) references Genre(genre_id) on delete cascade
);

create unique index ix_book_genre on Book_Genre (book_id, genre_id);

create table if not exists Author(
    author_id serial primary key,
    author_first_name varchar(300) not null,
    author_second_name varchar(300) not null,
    creative_pseudonym varchar(300) not null
);

create table if not exists Book_Author(
     book_id integer not null,
     author_id integer not null,
     CONSTRAINT fk_book_id
            foreign key (book_id) references Book(book_id) on delete cascade,
     CONSTRAINT fk_author_id
            foreign key (author_id) references Author(author_id) on delete cascade
);

create unique index ix_book_author on Book_Author (book_id, author_id);



create table if not exists Review(
    review_id serial primary key,
    book_id integer not null,
    user_email varchar(300) not null,
    description text not null,
    CONSTRAINT fk_book_id
        foreign key (book_id) references Book(book_id) on delete cascade,
    CONSTRAINT fk_username
        foreign key (user_email) references Users(email) on delete cascade
);

create unique index ix_review on Review (book_id, user_email);



create table if not exists Evaluate_Review(
    review_id integer not null,
    user_email varchar(300) not null,
    evaluation integer not null,
    CONSTRAINT fk_review_id
        foreign key (review_id) references Review(review_id) on delete cascade,
    CONSTRAINT
        foreign key (user_email) references Users(email) on delete cascade
);

create unique index ix_evaluate_review on Evaluate_Review (review_id, user_email);



create table if not exists Rating_Book(
    book_id integer not null,
    user_email varchar(300) not null,
    rating integer not null,
    CONSTRAINT fk_book_id
        foreign key (book_id) references Book(book_id) on delete cascade,
    CONSTRAINT fk_username
            foreign key (user_email) references Users(email) on delete cascade
);

create unique index ix_rating_book on Rating_Book (book_id, user_email);
