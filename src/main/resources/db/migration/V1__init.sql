create table public.users (
                              id integer primary key not null default nextval('users_id_seq1'::regclass),
                              country character varying,
                              email character varying,
                              name character varying,
                              adress character varying,
                              phone integer,
                              employee_id integer,
                              is_deleted boolean,
                              foreign key (employee_id) references public.card (id)
                                  match simple on update no action on delete no action
);
create unique index users_id_key on users using btree (id);

