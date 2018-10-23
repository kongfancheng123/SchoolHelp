CREATE TABLE public.dt_equipment_type_info (
	id int4 NOT NULL,
	equipment_type_code varchar NULL,
	equipment_type_name varchar NULL,
	create_date timestamptz NULL,
	create_uid int4 NULL,
	write_date timestamptz NULL,
	write_uid int4 NULL,
	default1 text NULL,
	default2 text NULL,
	default3 text NULL,
	default4 jsonb NULL
)
WITH (
	OIDS=FALSE
) ;



create sequence seq_dt_equipment_type_info
increment by 1
start with 1
maxvalue 999999999;

alter table public.dt_equipment_type_info alter column id set default nextval('seq_dt_equipment_type_info');



CREATE TABLE public.dt_monitor_property (
	id int4 NOT NULL,
	equipment_property_code varchar NULL,
	equipment_property_name varchar NULL,
	equipment_property_type int4 NULL,
	create_date timestamptz NULL,
	create_uid int4 NULL,
	write_date timestamptz NULL,
	write_uid int4 NULL,
	default1 text NULL,
	default2 text NULL,
	default3 text NULL,
	default4 jsonb NULL
)
WITH (
	OIDS=FALSE
) ;

create sequence seq_dt_monitor_property
increment by 1
start with 1
maxvalue 999999999;

alter table public.dt_monitor_property alter column id set default nextval('seq_dt_monitor_property');




CREATE TABLE public.dt_monitor_property_template (
	id int4 NOT NULL,
	equipment_type varchar NULL,
	"equipment_property_template_code" varchar NULL,
	"equipment_property_template_name" varchar NULL,
	"equipment_property_template_description" varchar NULL,
	create_date timestamptz NULL,
	create_uid int4 NULL,
	write_date timestamptz NULL,
	write_uid int4 NULL
)
WITH (
	OIDS=FALSE
) ;

create sequence seq_dt_monitor_property_template
increment by 1
start with 1
maxvalue 999999999;

alter table public.dt_monitor_property_template alter column id set default nextval('seq_dt_monitor_property_template');


CREATE TABLE public.dt_monitor_property_template_bind (
	id int4 NOT NULL,
	equipment_type varchar NULL,
	"equipment_property_template_code" varchar NULL,
	"equipment_property_code" varchar NULL,
	create_date timestamptz NULL,
	create_uid int4 NULL,
	write_date timestamptz NULL,
	write_uid int4 NULL
)
WITH (
	OIDS=FALSE
) ;

create sequence seq_dt_monitor_property_template_bind
increment by 1
start with 1
maxvalue 999999999;


alter table public.dt_monitor_property_template_bind alter column id set default nextval('seq_dt_monitor_property_template_bind');


CREATE TABLE public.dt_parent_node_info (
	id int4 NOT NULL,
	parent_node_code varchar NULL,
	parent_node_name varchar NULL,
	create_date timestamptz NULL,
	create_uid int4 NULL,
	write_date timestamptz NULL,
	write_uid int4 NULL
)
WITH (
	OIDS=FALSE
) ;

create sequence seq_dt_parent_node_info
increment by 1
start with 1
maxvalue 999999999;

alter table public.dt_parent_node_info alter column id set default nextval('seq_dt_parent_node_info');




CREATE TABLE public.dt_equipment_info (
	id int4 NOT NULL,
	parent_node_code varchar NULL,
	equipment_code varchar NULL,
	equipment_name varchar NULL,
	equipment_type varchar NULL,
	"equipment_property_template_code" varchar NULL,
	"equipment_property_code" varchar NULL,
	"keyword" varchar NULL,
	data_val text NULL,
	alarm_val text NULL,
	control_val text NULL,
	update_time timestamptz NULL,
	params_update timestamptz NULL
)
WITH (
	OIDS=FALSE
) ;

ALTER TABLE public.dt_equipment_info ADD data_update timestamptz NULL;
ALTER TABLE public.dt_equipment_info ADD alarm_update timestamptz NULL;
ALTER TABLE public.dt_equipment_info ADD controller_update timestamptz NULL;

create sequence seq_dt_equipment_info
increment by 1
start with 1
maxvalue 999999999;

alter table public.dt_equipment_info alter column id set default nextval('seq_dt_equipment_info');



CREATE TABLE public.dt_equipment_realtime_data (
	id int4 NULL,
	data_code varchar NULL,
	data_name varchar NULL,
	data_value varchar NULL,
	equipment_code varchar NULL,
	create_date timestamptz NULL,
	create_uid int4 NULL,
	write_date timestamptz NULL,
	write_uid int4 NULL
)
WITH (
	OIDS=FALSE
) ;

ALTER TABLE public.dt_equipment_realtime_data ADD keyword varchar NULL;

create sequence seq_dt_equipment_realtime_data
increment by 1
start with 1
maxvalue 999999999;

alter table public.dt_equipment_realtime_data alter column id set default nextval('seq_dt_equipment_realtime_data');



CREATE TABLE public.dt_event_history_info (
	id int4 NOT NULL,
	event_code varchar NULL,
	event_type int4 NULL,
	event_value varchar NULL,
	event_state int4 NULL,
	create_date timestamptz NULL,
	create_uid int4 NULL,
	write_date timestamptz NULL,
	write_uid int4 NULL
)
WITH (
	OIDS=FALSE
) ;
ALTER TABLE public.dt_event_history_info ADD keyword varchar NULL;

create sequence seq_dt_event_history_info
increment by 1
start with 1
maxvalue 999999999;


alter table public.dt_event_history_info alter column id set default nextval('seq_dt_event_history_info');


CREATE TABLE public.dt_equipment_history_data (
	id int4 NOT NULL,
	data_code varchar NULL,
	data_name varchar NULL,
	data_value varchar NULL,
	equipment_code varchar NULL,
	create_date timestamptz NULL,
	create_uid int4 NULL,
	write_date timestamptz NULL,
	write_uid int4 NULL
)
WITH (
	OIDS=FALSE
) ;

create sequence seq_dt_equipment_history_data
increment by 1
start with 1
maxvalue 999999999;

alter table public.dt_equipment_history_data alter column id set default nextval('seq_dt_equipment_history_data');


CREATE TABLE public.dt_params_config_info (
	id int4 NOT NULL,
	ip varchar NULL,
	port varchar NULL,
	password varchar NULL,
	data_enable int4 NULL,
	alarm_enable int4 NULL,
	feed_cycle int4 NULL,
	log_level int4 NULL,
	version varchar NULL,
	create_date timestamptz NULL
)
WITH (
	OIDS=FALSE
) ;


create sequence seq_dt_params_config_info
increment by 1
start with 1
maxvalue 999999999;

alter table public.dt_params_config_info alter column id set default nextval('seq_dt_params_config_info');
