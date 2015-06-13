create table crawled_pages (
    crawler smallint not null,
    url varchar(1024) not null UNIQUE,
    crawl_date timestamp not null DEFAULT current_timestamp,
    content text not null
);

create table recipes (
    recipe_id serial primary key,
    recipe_name varchar(1024) not null,
    recipe_url varchar(1024) not null,
    created timestamp not null DEFAULT current_timestamp
);

create table ingredients (
    ingredient_id serial primary key,
    ingredient_name varchar(1024) not null,
    created timestamp not null DEFAULT current_timestamp
);

create table ingredient_amount (
    recipe_id integer not null REFERENCES recipes,
    ingredient_id integer not null REFERENCES ingredients,
    amount varchar(1024) not null
);
