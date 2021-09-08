CREATE TABLE IF NOT EXISTS public.payment
(
    payment_id bigint NOT NULL,
    payment_type character(12) COLLATE pg_catalog."default" NOT NULL,
    order_id bigint NOT NULL,
    "Amount" numeric(9,2),
    CONSTRAINT payment_pkey PRIMARY KEY (payment_id)
)

TABLESPACE pg_default;

ALTER TABLE public.payment
    OWNER to "Inventory";