CREATE TABLE case_table
(
    cuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    case_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    case_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    case_year character varying(255) COLLATE pg_catalog."default" NOT NULL,
    case_title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    forum_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    case_status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    applicant character varying(255) COLLATE pg_catalog."default" NOT NULL,
    respondant character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_by character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    
    CONSTRAINT case_table_pkey PRIMARY KEY (cuid)
)