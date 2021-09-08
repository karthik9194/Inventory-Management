CREATE TABLE IF NOT EXISTS public.order_details
(
    id bigint NOT NULL,
    order_id character varying(15) COLLATE pg_catalog."default" NOT NULL,
    product_id bigint NOT NULL,
    quanity bigint NOT NULL,
    amout_txn numeric(9,2) NOT NULL,
    date_created date,
    order_status character(10) COLLATE pg_catalog."default" NOT NULL,
    terminal_id bigint,
    store_id character varying COLLATE pg_catalog."default",
    CONSTRAINT order_pkey PRIMARY KEY (id),
    CONSTRAINT fkey_order_product FOREIGN KEY (product_id)
        REFERENCES public.product (productid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkey_order_terminal FOREIGN KEY (terminal_id)
        REFERENCES public.cashier_terminal (terminalid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

ALTER TABLE public.order_details
    OWNER to "Inventory";