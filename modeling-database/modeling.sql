PGDMP
         4                n            modeling    8.3.9    8.3.9     �           0    0    ENCODING    ENCODING    SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS )   SET standard_conforming_strings = 'off';
                       false            �           1262    766293    modeling    DATABASE F   CREATE DATABASE modeling WITH TEMPLATE = template0 ENCODING = 'UTF8';
    DROP DATABASE modeling;
             postgres    false                        2615    2200    public    SCHEMA    CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �           0    0    SCHEMA public    COMMENT 6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3            �           0    0    public    ACL �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    3            �           1259    766324    layers_sqnc    SEQUENCE \   CREATE SEQUENCE layers_sqnc
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
 "   DROP SEQUENCE public.layers_sqnc;
       public       postgres    false    3            �           0    0    layers_sqnc    SEQUENCE SET 3   SELECT pg_catalog.setval('layers_sqnc', 48, true);
            public       postgres    false    1471            �           1259    766303    layers    TABLE w  CREATE TABLE layers (
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
    DROP TABLE public.layers;
       public         postgres    false    1738    3            �           0    0    COLUMN layers.name    COMMENT j   COMMENT ON COLUMN layers.name IS 'The name of the layer (this will be displayed in the web application)';
            public       postgres    false    1468            �           0    0    COLUMN layers.description    COMMENT V   COMMENT ON COLUMN layers.description IS 'Descriptions of the contents of the layer
';
            public       postgres    false    1468            �           0    0    COLUMN layers.created_by    COMMENT O   COMMENT ON COLUMN layers.created_by IS 'Institution that generates the layer';
            public       postgres    false    1468            �           1259    766322 
   users_sqnc    SEQUENCE [   CREATE SEQUENCE users_sqnc
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
 !   DROP SEQUENCE public.users_sqnc;
       public       postgres    false    3            �           0    0 
   users_sqnc    SEQUENCE SET 2   SELECT pg_catalog.setval('users_sqnc', 10, true);
            public       postgres    false    1470            �           1259    766311    users    TABLE 4  CREATE TABLE users (
    user_id numeric DEFAULT nextval('users_sqnc'::regclass) NOT NULL,
    fullname character varying(500),
    username character varying(100) NOT NULL,
    password character varying(500) NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    roles character varying(500) NOT NULL
);
    DROP TABLE public.users;
       public         postgres    false    1739    1740    3            �          0    766303    layers 
   TABLE DATA           y   COPY layers (name, description, uri, id, scale, last_update, created_by, year, is_species_map, display_name) FROM stdin;
    public       postgres    false    1468   �       �          0    766311    users 
   TABLE DATA           O   COPY users (user_id, fullname, username, password, enabled, roles) FROM stdin;
    public       postgres    false    1469   0       �           2606    766310    layer_pk 
   CONSTRAINT F   ALTER TABLE ONLY layers
    ADD CONSTRAINT layer_pk PRIMARY KEY (id);
 9   ALTER TABLE ONLY public.layers DROP CONSTRAINT layer_pk;
       public         postgres    false    1468    1468            �           2606    766319    system_user_pkey 
   CONSTRAINT R   ALTER TABLE ONLY users
    ADD CONSTRAINT system_user_pkey PRIMARY KEY (user_id);
 @   ALTER TABLE ONLY public.users DROP CONSTRAINT system_user_pkey;
       public         postgres    false    1469    1469            �           2606    766321    system_user_username_key 
   CONSTRAINT V   ALTER TABLE ONLY users
    ADD CONSTRAINT system_user_username_key UNIQUE (username);
 H   ALTER TABLE ONLY public.users DROP CONSTRAINT system_user_username_key;
       public         postgres    false    1469    1469            �   4  x����n�@���{r�C0�֒U�V!%�W�%���]	v��:U�F}��X'�.L|�d�X���i��x�1^%��D
��h��V�B�c߬-��~�����0����G�}��|�~�g_�����%g��AO��;�B�D�;�=���8�ϔ���-wv�t.}s��cQ���2���cl]'��˹K�f�B��a�-/���_\_�.�����m�f�08��e���r�{Ԯ8�k�O�k�R�PڐОm�k	htc��bj1���*�P��u'v9���Ը�Y8�� WG�}�� �]�QI.
a1)A`�wf�:%~\�p�_]��qI(���GV=+�]h�!w2����d٫��V'X�u+Tk�ј�P��e�T�ڟ����ː?[�R��n�M����cL1�ϙ&@���v,p��*EQ��j ��X�����Q�����n��U�?Ǜ�-J����5���Wm
��^h�2)�U��u���rQ�����[�"H�1��E��Ӫ�"��C���ܫ�F���ϛ��&�,!�TT��8!��ğ��w�{���=�      �     x����JAE��_�CO?�qc� ���JA��G�L�A�{'1��]6��[P�ev1�%v���iٖM�J��䂻�і��Ka(M�Y���)Ά��=��a{u�A�1s�p(i۴�ꅛ�LD���/�֢�"��(��	�W;�ᥡ.J2uF��۰m�A&	&�R!�H�@EY�OBc��a9���-v�8_�z���EG��l��GD��\e�u����@�D.ٺ�4�$�ɡ��Q�yp�DM^�D2Α�Z�&x��d��+�����v     