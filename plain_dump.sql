--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Debian 14.5-1.pgdg110+1)
-- Dumped by pg_dump version 14.4

-- Started on 2022-11-05 15:47:21 MSK

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
-- TOC entry 210 (class 1259 OID 16392)
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
-- TOC entry 209 (class 1259 OID 16385)
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
-- TOC entry 3308 (class 0 OID 16385)
-- Dependencies: 209
-- Data for Name: user_tab; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_tab (id, balance, first_name, last_name) FROM stdin;
1	12	Test	First
2	100102	Test	Testing
\.


--
-- TOC entry 3315 (class 0 OID 0)
-- Dependencies: 210
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 2, true);


--
-- TOC entry 3168 (class 2606 OID 16391)
-- Name: user_tab user_tab_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_tab
    ADD CONSTRAINT user_tab_pkey PRIMARY KEY (id);


-- Completed on 2022-11-05 15:47:21 MSK

--
-- PostgreSQL database dump complete
--

