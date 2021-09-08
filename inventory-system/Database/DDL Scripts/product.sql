CREATE TABLE IF NOT EXISTS public.product
(
    productid bigint NOT NULL,
    productname character varying(500) COLLATE pg_catalog."default" NOT NULL,
    upccode character varying(250) COLLATE pg_catalog."default" NOT NULL,
    stockcount bigint,
    storenumber bigint,
    price numeric(5,2),
    CONSTRAINT product_pkey PRIMARY KEY (productid),
    CONSTRAINT upc_code_unique UNIQUE (upccode)
)

TABLESPACE pg_default;

ALTER TABLE public.product
    OWNER to "Inventory";