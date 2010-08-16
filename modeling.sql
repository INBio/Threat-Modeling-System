--
-- PostgreSQL database dump
--

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

--
-- Name: layers_sqnc; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE layers_sqnc
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.layers_sqnc OWNER TO postgres;

--
-- Name: layers_sqnc; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('layers_sqnc', 49, true);


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: layers; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE layers (
    name character varying NOT NULL,
    description character varying,
    uri character varying NOT NULL,
    id numeric DEFAULT nextval('layers_sqnc'::regclass) NOT NULL,
    scale character varying,
    last_update date,
    created_by character varying,
    year character varying,
    is_species_map boolean,
    display_name character varying
);


ALTER TABLE public.layers OWNER TO postgres;

--
-- Name: COLUMN layers.name; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN layers.name IS 'The name of the layer (this will be displayed in the web application)';


--
-- Name: COLUMN layers.description; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN layers.description IS 'Descriptions of the contents of the layer
';


--
-- Name: COLUMN layers.created_by; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN layers.created_by IS 'Institution that generates the layer';


--
-- Name: users_sqnc; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_sqnc
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.users_sqnc OWNER TO postgres;

--
-- Name: users_sqnc; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_sqnc', 11, true);


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users (
    user_id numeric DEFAULT nextval('users_sqnc'::regclass) NOT NULL,
    fullname character varying(500),
    username character varying(100) NOT NULL,
    password character varying(500) NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    roles character varying(500) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Data for Name: layers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY layers (name, description, uri, id, scale, last_update, created_by, year, is_species_map, display_name) FROM stdin;
IABIN_amenazas:poblados_dd	Sitio ACOSA	http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_amenazas:poblados_dd&outputFormat=GML2	36		2010-08-09	\N		f	Poblados
IABIN_Indicadores:provincias	Costa Rica	http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_Indicadores:provincias&outputFormat=GML2	28		2010-08-09	\N		f	Provincias
IABIN_Indicadores:areas	Protected areas of Costa Rica	http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_Indicadores:areas&outputFormat=GML2	26	1:50000	2010-07-30	\N	2010	t	Protected Areas of Costa Rica
IABIN_amenazas:incendios	Sitio ACOSA	http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_amenazas:incendios&outputFormat=GML2	35		2010-08-09	\N		f	Incendios
IABIN_amenazas:calles	Street map of 'La Carpintera'	http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_amenazas:calles&outputFormat=GML2	40		2010-08-10	\N		f	Street map of 'La Carpintera'
IABIN_amenazas:inundacion	Sitio ACOSA	http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_amenazas:inundacion&outputFormat=GML2	49		2010-08-09	\N		f	Inundacion
IABIN_Indicadores:meso_limite_paies		http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_Indicadores:meso_limite_paies&outputFormat=GML2	29		2010-07-14	\N		\N	Mesoamérica
IABIN_amenazas:cobertura	Land cover of 'La Carpintera'	http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_amenazas:cobertura&outputFormat=GML2	41		2010-08-10	\N		f	Land cover of 'La Carpintera'
IABIN_amenazas:riesgo_inundacion_dd	Sitio ACOSA	http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_amenazas:riesgo_inundacion_dd&outputFormat=GML2	39		2010-08-09	\N		f	Rios
IABIN_amenazas:alta_tension_dd	Sitio ACOSA	http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_amenazas:alta_tension_dd&outputFormat=GML2	30		2010-08-09	\N		f	Torres alta tension
IABIN_amenazas:generacion_electrica_dd	Sitio ACOSA	http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_amenazas:generacion_electrica_dd&outputFormat=GML2	34		2010-08-09	\N		f	Generacion electrica
IABIN_amenazas:gasolineras_dd	Sitio ACOSA	http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_amenazas:gasolineras_dd&outputFormat=GML2	33		2010-08-09	\N		f	Gasolineras
IABIN_amenazas:cobertura_dd	Sitio ACOSA	http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_amenazas:cobertura_dd&outputFormat=GML2	32		2010-08-09	\N		f	Cobertura de terreno 
IABIN_amenazas:redcamino_dd	Sitio ACOSA	http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_amenazas:redcamino_dd&outputFormat=GML2	37		2010-08-09	\N		f	Red de caminos
IABIN_amenazas:siepac_dd		http://216.75.53.105/geoserver/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=IABIN_amenazas:siepac_dd&outputFormat=GML2	31		2010-07-14	\N		\N	SIEPAC
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (user_id, fullname, username, password, enabled, roles) FROM stdin;
2	Usuario de prueba	beta	987bcab01b929eb2c07877b224215c92	t	ROLE_USER,ROLE_ADMIN
\.


--
-- Name: layer_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY layers
    ADD CONSTRAINT layer_pk PRIMARY KEY (id);


--
-- Name: system_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT system_user_pkey PRIMARY KEY (user_id);


--
-- Name: system_user_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT system_user_username_key UNIQUE (username);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

