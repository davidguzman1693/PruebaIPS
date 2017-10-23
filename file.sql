--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 9.6.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: articulo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE articulo (
    idarticulo integer NOT NULL,
    nombre character(20) NOT NULL,
    valor integer NOT NULL
);


ALTER TABLE articulo OWNER TO postgres;

--
-- Name: articulo_idarticulo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE articulo_idarticulo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE articulo_idarticulo_seq OWNER TO postgres;

--
-- Name: articulo_idarticulo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE articulo_idarticulo_seq OWNED BY articulo.idarticulo;


--
-- Name: clie_art; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE clie_art (
    idcliente integer NOT NULL,
    idarticulo integer NOT NULL
);


ALTER TABLE clie_art OWNER TO postgres;

--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE cliente (
    idcliente integer NOT NULL,
    nombre character(20) NOT NULL,
    apellido character(20) NOT NULL,
    edad integer NOT NULL,
    identificacion character(200) NOT NULL,
    fecnac date
);


ALTER TABLE cliente OWNER TO postgres;

--
-- Name: cliente_idcliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cliente_idcliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cliente_idcliente_seq OWNER TO postgres;

--
-- Name: cliente_idcliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cliente_idcliente_seq OWNED BY cliente.idcliente;


--
-- Name: articulo idarticulo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articulo ALTER COLUMN idarticulo SET DEFAULT nextval('articulo_idarticulo_seq'::regclass);


--
-- Name: cliente idcliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente ALTER COLUMN idcliente SET DEFAULT nextval('cliente_idcliente_seq'::regclass);


--
-- Data for Name: articulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO articulo (idarticulo, nombre, valor) VALUES (3, 'dsdsa               ', 2321321);
INSERT INTO articulo (idarticulo, nombre, valor) VALUES (4, 'dsadsa              ', 2321321);
INSERT INTO articulo (idarticulo, nombre, valor) VALUES (5, '3213dsa             ', 2321321);
INSERT INTO articulo (idarticulo, nombre, valor) VALUES (6, 'dsadsa              ', 2321321);
INSERT INTO articulo (idarticulo, nombre, valor) VALUES (7, 'dsadsa              ', 231321);
INSERT INTO articulo (idarticulo, nombre, valor) VALUES (8, 'dsa                 ', 23213);


--
-- Name: articulo_idarticulo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('articulo_idarticulo_seq', 8, true);


--
-- Data for Name: clie_art; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO cliente (idcliente, nombre, apellido, edad, identificacion, fecnac) VALUES (20, 'dsadsa              ', 'dfsadasDavd         ', 321, 'dwewq                                                                                                                                                                                                   ', NULL);
INSERT INTO cliente (idcliente, nombre, apellido, edad, identificacion, fecnac) VALUES (21, 'dsa                 ', 'dsadsa              ', 321, 'dsadsa                                                                                                                                                                                                  ', NULL);
INSERT INTO cliente (idcliente, nombre, apellido, edad, identificacion, fecnac) VALUES (17, 'dasdsa              ', 'dsadsa              ', 3221, 'dasdsadsa                                                                                                                                                                                               ', NULL);
INSERT INTO cliente (idcliente, nombre, apellido, edad, identificacion, fecnac) VALUES (26, 'dsads               ', 'dsadsa              ', 312, 'dsadsa                                                                                                                                                                                                  ', NULL);
INSERT INTO cliente (idcliente, nombre, apellido, edad, identificacion, fecnac) VALUES (28, 'dsad                ', 'dsadsadsadsa        ', 21, 'dsadsa                                                                                                                                                                                                  ', NULL);


--
-- Name: cliente_idcliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cliente_idcliente_seq', 28, true);


--
-- Name: articulo articulo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY articulo
    ADD CONSTRAINT articulo_pkey PRIMARY KEY (idarticulo);


--
-- Name: clie_art clie_art_idarticulo_idcliente_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clie_art
    ADD CONSTRAINT clie_art_idarticulo_idcliente_key UNIQUE (idarticulo, idcliente);


--
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (idcliente);


--
-- Name: clie_art clie_art_idarticulo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clie_art
    ADD CONSTRAINT clie_art_idarticulo_fkey FOREIGN KEY (idarticulo) REFERENCES articulo(idarticulo);


--
-- Name: clie_art clie_art_idcliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clie_art
    ADD CONSTRAINT clie_art_idcliente_fkey FOREIGN KEY (idcliente) REFERENCES cliente(idcliente);


--
-- PostgreSQL database dump complete
--

