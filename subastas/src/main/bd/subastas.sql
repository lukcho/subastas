CREATE SEQUENCE seq_sub_cat_cab
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_sub_cat_cab
  OWNER TO postgres;

CREATE SEQUENCE seq_sub_cat_det
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_sub_cat_det
  OWNER TO postgres;
  
CREATE SEQUENCE seq_sub_items
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_sub_items
  OWNER TO postgres;

 CREATE SEQUENCE seq_sub_ofertas
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_sub_ofertas
  OWNER TO postgres;

/*==============================================================*/
/* Table: SUB_CAT_CAB                                           */
/*==============================================================*/
create table SUB_CAT_CAB (
   CATC_ID              INT4                  not null DEFAULT nextval('seq_sub_cat_cab'::regclass),
   CATC_NOMBRE          VARCHAR(200)         null,
   CATC_VALOR           CHAR(4)              null,
   constraint PK_SUB_CAT_CAB primary key (CATC_ID)
);

/*==============================================================*/
/* Table: SUB_CAT_DET                                           */
/*==============================================================*/
create table SUB_CAT_DET (
   CATD_ID              INT4                 not null DEFAULT nextval('seq_sub_cat_det'::regclass),
   CATC_ID              INT4                 null,
   CATD_ID_PADRE        INT4                 null,
   CATD_NOMBRE          VARCHAR(200)         null,
   CATD_ESTADO          CHAR(1)              null,
   constraint PK_SUB_CAT_DET primary key (CATD_ID)
);

/*==============================================================*/
/* Table: SUB_ITEMS                                             */
/*==============================================================*/
create table SUB_ITEMS (
   ITEM_ID              INT4                 not null DEFAULT nextval('seq_sub_items'::regclass),
   ITEM_NOMBRE          VARCHAR(255)         null,
   ITEM_DESCRIPCION     VARCHAR(500)         null,
   ITEM_CARACTERISTICAS VARCHAR(500)         null,
   ITEM_IMAGEN          VARCHAR(255)         null,
   ITEM_VALOR_BASE      NUMERIC              null,
   ITEM_VALOR_VENTA     NUMERIC              null,
   ITEM_FECHA_SUBASTA_INICIO TIMESTAMP            null,
   ITEM_FECHA_SUBASTA_FIN TIMESTAMP            null,
   ITEM_GANADOR_DNI     INT4		          null,
   ITEM_USUARIO_REGISTRO VARCHAR(255)         null,
   ITEM_ESTADO          CHAR(1)              null,
   constraint PK_SUB_ITEMS primary key (ITEM_ID)
);

/*==============================================================*/
/* Table: SUB_OFERTAS                                           */
/*==============================================================*/
create table SUB_OFERTAS (
   OFER_ID              INT4                 not null DEFAULT nextval('seq_sub_ofertas'::regclass),
   POS_ID               VARCHAR(200)         null,
   ITEM_ID              INT4                 null,
   OFER_VALOR_OFERTA    NUMERIC              null,
   OFER_FECHA_OFERTA    TIMESTAMP            null,
   constraint PK_SUB_OFERTAS primary key (OFER_ID)
);

/*==============================================================*/
/* Table: SUB_POSTULANTES                                       */
/*==============================================================*/
create table SUB_POSTULANTES (
   POS_ID               VARCHAR(200)         not null,
   POS_FECHA_REGISTRO   TIMESTAMP            null,
   POS_NOMBRE           VARCHAR(200)         null,
   POS_APELLIDO         VARCHAR(200)         null,
   POS_DIRECCION        VARCHAR(200)         null,
   POS_CORREO           VARCHAR(200)         null,
   POS_TELEFONO         VARCHAR(20)          null,
   POS_PASSWORD         VARCHAR(200)         null,
   POS_INSTITUCION      VARCHAR(200)         null,
   POS_GERENCIA         VARCHAR(200)         null,
   POS_AREA             VARCHAR(200)         null,
   POS_ESTADO           CHAR(1)              null,
   constraint PK_SUB_POSTULANTES primary key (POS_ID)
);

alter table SUB_CAT_DET
   add constraint FK_SUB_CAT__REFERENCE_SUB_CAT_ foreign key (CATC_ID)
      references SUB_CAT_CAB (CATC_ID)
      on delete restrict on update restrict;

alter table SUB_OFERTAS
   add constraint FK_SUB_OFER_REFERENCE_SUB_POST foreign key (POS_ID)
      references SUB_POSTULANTES (POS_ID)
      on delete restrict on update restrict;

alter table SUB_OFERTAS
   add constraint FK_SUB_OFER_REFERENCE_SUB_ITEM foreign key (ITEM_ID)
      references SUB_ITEMS (ITEM_ID)
      on delete restrict on update restrict;
