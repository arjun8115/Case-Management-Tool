CREATE TABLE session_table
(
    session_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    token character varying(255) COLLATE pg_catalog."default" NOT NULL,
    role character varying(255) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    uuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    expires_in smallint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    expires_at timestamp without time zone NOT NULL,
    
    CONSTRAINT session_table_pkey PRIMARY KEY (session_id)
)