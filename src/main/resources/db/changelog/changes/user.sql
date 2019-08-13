CREATE TABLE user_table
(
    uuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_name character varying(255) COLLATE pg_catalog."default",
    first_name character varying(255) COLLATE pg_catalog."default" ,
    last_name character varying(255) COLLATE pg_catalog."default" ,
    email character varying(255) COLLATE pg_catalog."default",
    mobile_no character varying(255) COLLATE pg_catalog."default" ,
    password text COLLATE pg_catalog."default" NOT NULL,
    auth character varying(255) COLLATE pg_catalog."default" NOT NULL,
    role character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    
    CONSTRAINT user_table_pkey PRIMARY KEY (uuid)
)