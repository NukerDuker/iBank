--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Debian 14.5-1.pgdg110+1)
-- Dumped by pg_dump version 14.4

-- Started on 2022-11-22 01:38:44 MSK

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 212 (class 1259 OID 16398)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 16386)
-- Name: operations_tab; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.operations_tab (
    id bigint NOT NULL,
    amount bigint,
    date timestamp without time zone,
    operation_type integer,
    user_id bigint
);


ALTER TABLE public.operations_tab OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16385)
-- Name: operations_tab_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.operations_tab ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.operations_tab_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 211 (class 1259 OID 16391)
-- Name: user_tab; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_tab (
    id bigint NOT NULL,
    balance bigint,
    first_name character varying(255),
    last_name character varying(255)
);


ALTER TABLE public.user_tab OWNER TO postgres;

--
-- TOC entry 3316 (class 0 OID 16386)
-- Dependencies: 210
-- Data for Name: operations_tab; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.operations_tab (id, amount, date, operation_type, user_id) FROM stdin;
1	10	2022-11-22 00:55:56.82	1	1
2	10	2022-11-22 00:55:56.881	0	1
3	10	2022-11-22 00:56:39.209	1	3
4	10	2022-11-22 00:56:39.26	0	3
5	10	2022-11-22 00:59:38.941	1	5
6	10	2022-11-22 00:59:38.989	0	5
\.


--
-- TOC entry 3317 (class 0 OID 16391)
-- Dependencies: 211
-- Data for Name: user_tab; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_tab (id, balance, first_name, last_name) FROM stdin;
6	100	Roman	Zubkov
7	0	Nick	Kick
8	0	Nick	Kick
9	0	Nick	Kick
10	0	Nick	Kick
\.


--
-- TOC entry 3324 (class 0 OID 0)
-- Dependencies: 212
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 10, true);


--
-- TOC entry 3325 (class 0 OID 0)
-- Dependencies: 209
-- Name: operations_tab_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.operations_tab_id_seq', 6, true);


--
-- TOC entry 3173 (class 2606 OID 16390)
-- Name: operations_tab operations_tab_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.operations_tab
    ADD CONSTRAINT operations_tab_pkey PRIMARY KEY (id);


--
-- TOC entry 3175 (class 2606 OID 16397)
-- Name: user_tab user_tab_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_tab
    ADD CONSTRAINT user_tab_pkey PRIMARY KEY (id);


-- Completed on 2022-11-22 01:38:44 MSK

--
-- PostgreSQL database dump complete
--

