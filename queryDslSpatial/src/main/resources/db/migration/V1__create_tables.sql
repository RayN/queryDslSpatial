CREATE TABLE public.company (
	id int8 NOT NULL,
	cnpj varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	"location" geometry(POLYGON, 4326) NULL,
	CONSTRAINT empresa_pkey PRIMARY KEY (id)
);



CREATE TABLE public.employee (
	id bigserial NOT NULL,
	"name" varchar(255) NOT NULL,
	company_id int8 NULL,
	"location" geometry(POLYGON, 4326) NULL,
	CONSTRAINT funcionario_pkey PRIMARY KEY (id),
	CONSTRAINT fk4cm1kg523jlopyexjbmi6y54j FOREIGN KEY (company_id) REFERENCES company(id)
);


