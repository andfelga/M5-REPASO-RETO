create table estado(
	id_estado serial PRIMARY KEY,
	descripcion VARCHAR(50) not null
);

select * from estado;
insert into estado values(0, 'Pendiente');
insert into estado values(1, 'Aprobado');
insert into estado values(2, 'rechazado');

select * from prestamo;

create table prestamo(
	id_prestamo serial PRIMARY KEY,
	monto NUMERIC(15, 2) not null,
	tasa_interes numeric(8, 5) not null,
	plazo int not null,
	id_estado int REFERENCES estado(id_estado),
	id_cliente bigint references cliente(cliente_id)
);


create table cliente(
  cliente_id bigint primary key,
  nombre varchar(50) not null,
  direccion varchar(100) ,
  telefono varchar(20) ,
  email  varchar(100) not null
);
insert into cliente values(1037575512, 'Andres Garcia', 'Calle 57A # 31-70', '3017540958', 'melcaya123@hotmail.com');

select * from cliente;

create table historia_prestamo(
	id_historia serial primary key,
	prestamos_id bigint references prestamo(id_prestamo),
	tipo_transaccion VARCHAR(50),
	monto_prestamo NUMERIC(15, 2),
	fecha_prestamo timestamp
)



select p.id_prestamo, p.monto, p.tasa_interes, p.plazo, h.tipo_transaccion, h.fecha_prestamo
from historia_prestamo h 
join prestamo p on h.prestamos_id = p.id_prestamo
join cliente  c on c.cliente_id = p.id_cliente
join estado   e on e.id_estado = p.id_estado
where p.id_prestamo in( select p.id_prestamo
					from prestamo p
					where p.id_cliente =  1037575512
					order by p.id_prestamo desc limit 3) 
order by h.fecha_prestamo ;




SELECT p.id_prestamo, p.monto, p.tasa_interes, p.plazo, c.cliente_id, c.nombre, c.telefono, c.email, c.direccion, e.descripcion
     FROM prestamo p JOIN cliente c ON c.cliente_id = p.id_cliente
     JOIN estado e on e.id_estado = p.id_estado
     WHERE p.id_prestamo =10