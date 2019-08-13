CREATE TABLE invoice_table
(
    iuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    cuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    uuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    Type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    amount INTEGER NOT NULL,
    is_deleted boolean NOT NULL DEFAULT FALSE,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    Date character varying(255) COLLATE pg_catalog."default" NOT NULL,
    status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT invoice_table_pkey PRIMARY KEY (iuid)
)