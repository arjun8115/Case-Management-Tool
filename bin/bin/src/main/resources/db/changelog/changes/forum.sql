CREATE TABLE forum_table
(
    fuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    forum_id character varying(255) COLLATE pg_catalog."default",
    forum_category character varying(255) COLLATE pg_catalog."default" NOT NULL,
    forum_dir character varying(255) COLLATE pg_catalog."default" NOT NULL,
    forum_group character varying(255) COLLATE pg_catalog."default" NOT NULL,
    full_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    short_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    
    CONSTRAINT forum_table_pkey PRIMARY KEY (fuid)
)