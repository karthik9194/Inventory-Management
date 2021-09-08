CREATE TABLE IF NOT EXISTS public.cashier_terminal
(
    terminalid bigint NOT NULL,
    cashier character varying(255) COLLATE pg_catalog."default",
    activeindicator boolean NOT NULL,
    CONSTRAINT cashier_terminal_pkey PRIMARY KEY (terminalid)
)

TABLESPACE pg_default;

ALTER TABLE public.cashier_terminal
    OWNER to "Inventory";