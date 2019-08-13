CREATE TABLE case_table
(

    cuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    case_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    case_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    case_year character varying(255) COLLATE pg_catalog."default" NOT NULL,
    case_title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    forum_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    case_status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    client character varying(255) COLLATE pg_catalog."default" NOT NULL,
    respondant character varying(255) COLLATE pg_catalog."default" NOT NULL,
    category character varying(255) COLLATE pg_catalog."default" NOT NULL,
    is_deleted boolean NOT NULL DEFAULT FALSE,
    uuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default",
	created_at timestamp without time zone NOT NULL,
	updated_at timestamp without time zone NOT NULL,
    
    CONSTRAINT case_table_pkey PRIMARY KEY (cuid)
)

