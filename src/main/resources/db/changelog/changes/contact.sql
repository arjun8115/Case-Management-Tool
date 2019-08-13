CREATE TABLE contact_table
(
    conuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    contact_number character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    uuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    cuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    is_deleted boolean NOT NULL DEFAULT FALSE,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    
    CONSTRAINT contact_table_pkey PRIMARY KEY (conuid)
)