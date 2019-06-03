--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

-- Started on 2019-06-04 02:56:42

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2849 (class 1262 OID 26841)
-- Name: dept; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE dept WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


\connect dept

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 599 (class 1247 OID 26848)
-- Name: position; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE public."position" AS ENUM (
    'ASSISTANT',
    'TEACHER',
    'DOCENT',
    'PROFESSOR'
);


--
-- TOC entry 596 (class 1247 OID 26843)
-- Name: user_type; Type: TYPE; Schema: public; Owner: -
--

CREATE TYPE public.user_type AS ENUM (
    'USER',
    'ADMIN'
);


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 203 (class 1259 OID 26893)
-- Name: admins; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.admins (
    id integer NOT NULL,
    username character varying(20) NOT NULL,
    password character varying(100) NOT NULL,
    user_role integer NOT NULL
);


--
-- TOC entry 202 (class 1259 OID 26891)
-- Name: admins_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.admins_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2850 (class 0 OID 0)
-- Dependencies: 202
-- Name: admins_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.admins_id_seq OWNED BY public.admins.id;


--
-- TOC entry 199 (class 1259 OID 26867)
-- Name: binding; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.binding (
    id integer NOT NULL,
    lecture_hours double precision,
    practice_hours double precision,
    labs_hours double precision,
    test_hours double precision,
    course_hours double precision,
    exam_hours double precision,
    diploma_hours double precision,
    user_id integer NOT NULL,
    subject_id integer NOT NULL
);


--
-- TOC entry 2851 (class 0 OID 0)
-- Dependencies: 199
-- Name: COLUMN binding.test_hours; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.binding.test_hours IS 'Зачетные часы
';


--
-- TOC entry 198 (class 1259 OID 26865)
-- Name: discipline_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.discipline_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2852 (class 0 OID 0)
-- Dependencies: 198
-- Name: discipline_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.discipline_id_seq OWNED BY public.binding.id;


--
-- TOC entry 201 (class 1259 OID 26880)
-- Name: subjects; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.subjects (
    id integer NOT NULL,
    subject_name character varying(30) NOT NULL,
    speciality character varying(30) NOT NULL
);


--
-- TOC entry 200 (class 1259 OID 26878)
-- Name: subjects_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.subjects_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2853 (class 0 OID 0)
-- Dependencies: 200
-- Name: subjects_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.subjects_id_seq OWNED BY public.subjects.id;


--
-- TOC entry 197 (class 1259 OID 26859)
-- Name: users; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    surname character varying(30) NOT NULL,
    middle_name character varying(30),
    "position" character varying(20),
    user_role integer
);


--
-- TOC entry 196 (class 1259 OID 26857)
-- Name: table_name_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.table_name_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 196
-- Name: table_name_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.table_name_id_seq OWNED BY public.users.id;


--
-- TOC entry 2712 (class 2604 OID 26896)
-- Name: admins id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.admins ALTER COLUMN id SET DEFAULT nextval('public.admins_id_seq'::regclass);


--
-- TOC entry 2710 (class 2604 OID 26870)
-- Name: binding id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.binding ALTER COLUMN id SET DEFAULT nextval('public.discipline_id_seq'::regclass);


--
-- TOC entry 2711 (class 2604 OID 26883)
-- Name: subjects id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.subjects ALTER COLUMN id SET DEFAULT nextval('public.subjects_id_seq'::regclass);


--
-- TOC entry 2709 (class 2604 OID 26862)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.table_name_id_seq'::regclass);


--
-- TOC entry 2720 (class 2606 OID 26898)
-- Name: admins admins_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.admins
    ADD CONSTRAINT admins_pk PRIMARY KEY (id);


--
-- TOC entry 2716 (class 2606 OID 26872)
-- Name: binding discipline_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.binding
    ADD CONSTRAINT discipline_pk PRIMARY KEY (id);


--
-- TOC entry 2718 (class 2606 OID 26885)
-- Name: subjects subjects_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT subjects_pk PRIMARY KEY (id);


--
-- TOC entry 2714 (class 2606 OID 26864)
-- Name: users table_name_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT table_name_pk PRIMARY KEY (id);


--
-- TOC entry 2722 (class 2606 OID 35232)
-- Name: binding discipline_subjects_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.binding
    ADD CONSTRAINT discipline_subjects_id_fk FOREIGN KEY (subject_id) REFERENCES public.subjects(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2721 (class 2606 OID 35227)
-- Name: binding discipline_users_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.binding
    ADD CONSTRAINT discipline_users_id_fk FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


-- Completed on 2019-06-04 02:56:43

--
-- PostgreSQL database dump complete
--

