CREATE TABLE doc_table
(

    duid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    uuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    cuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    file_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    file_url character varying(255) COLLATE pg_catalog."default" NOT NULL,
    mime_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
	created_at timestamp without time zone NOT NULL,
    
    CONSTRAINT docs_table_pkey PRIMARY KEY (duid)
)
