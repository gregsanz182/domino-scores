

SELECT category_id as original_categoria_id, 
    name as nombre
FROM category;

SELECT c.city_id AS original_ciudad_id, 
    c.city AS ciudad, 
    p.country_id AS pais_id, 
    p.country AS pais
FROM city c 
JOIN country p ON (c.country_id = p.country_id);

SELECT customer_id as original_cliente_id, 
    CONCAT(first_name, ' ', last_name) as nombre, 
    email as correo 
FROM customer;

SELECT film_id as original_pelicula_id, 
    title as titulo, 
    rental_duration as duracion_alquiler 
FROM film;

SELECT staff_id as original_personal_id, 
    CONCAT(first_name, ' ', last_name) as nombre, 
    email as correo, 
    active as activo 
FROM staff;

SELECT s.store_id AS original_tienda_id,
	manager_staff_id AS gerente_id,
	a.address_id AS direccion_id,
	a.address AS direccion,
	c.city AS ciudad
FROM store s JOIN address a ON ( s.address_id = a.address_id)
JOIN city c ON (a.city_id = c.city_id);

SELECT DISTINCT rental_date::date as fecha, 
    date_part('year',rental_date) as anio, 
    date_part('month',rental_date) as numero_mes,
    to_char(rental_date,'TMMonth') as nombre_mes,
    date_part('week',rental_date) as semana,
    to_char(rental_date,'TMDay') as nombre_dia
FROM rental;

SELECT DISTINCT fecha::date as fecha, 
    date_part('year',fecha) as anio, 
    date_part('month',fecha) as numero_mes,
    to_char(fecha,'TMMonth') as nombre_mes,
    date_part('week',fecha) as semana,
    to_char(fecha,'TMDay') as nombre_dia
FROM (SELECT rental_date as fecha 
    FROM rental UNION (SELECT payment_date as fecha FROM payment))x;


SELECT tp.idtiempo, 
    t.idtienda, 
    c.idcategoria, 
    count(*) as cantidad_alquiler
FROM rental r 
JOIN inventory i ON ( r.inventory_id = i.inventory_id)
JOIN dwh.tienda t ON (i.store_id = t.original_tienda_id)
JOIN film_category fc ON (fc.film_id = i.film_id)
JOIN dwh.categoria c ON (c.original_categoria_id = fc.category_id)
JOIN dwh.tiempo tp ON ( tp.fecha = r.rental_date::date )
GROUP BY tp.idtiempo, t.idtienda, c.idcategoria
ORDER BY 1 asc, 2 asc, 3 asc;


SELECT t.idtiempo as tiempo_id, 
    cli.idcliente as cliente_id, 
    ciu.idciudad as ciudad_id, 
    cat.idcategoria as categoria_id, 
    COUNT(r.rental_id) as cantidad_alquiler
FROM rental r
JOIN inventory inv ON (r.inventory_id = inv.inventory_id)
JOIN film f ON(inv.film_id = f.film_id)
JOIN film_category fc ON(fc.film_id = f.film_id)
JOIN customer cus ON(cus.customer_id = r.customer_id)
JOIN address adr ON(adr.address_id = cus.address_id)
JOIN dwh.tiempo t ON (t.fecha = r.rental_date::date)
JOIN dwh.cliente cli ON(cli.original_cliente_id = cus.customer_id)
JOIN dwh.ciudad ciu ON(ciu.original_ciudad_id = adr.city_id)
JOIN dwh.categoria cat ON(cat.original_categoria_id=fc.category_id)
GROUP BY t.idtiempo, cli.idcliente, ciu.idciudad, cat.idcategoria;

SELECT dp.idpersonal as personal_id, 
    dt.idtiempopago as tiempopago_id, 
    desempenno.fecha::date, 
    sum((CASE WHEN (desempenno.pago = 0 and desempenno.renta<>-1) THEN 1 
            ELSE 0 END)) as cantidad_alquiler,
    sum(desempenno.pago) as monto_alquiler 
FROM (SELECT rental_date as fecha, 
        staff_id as staff, 
        0 as pago, 
        rental_id as renta 
        FROM rental UNION (SELECT payment_date, 
                                staff_id, 
                                amount, 
                                -1 
                                FROM payment)) as desempenno
JOIN dwh.personal dp on(desempenno.staff=dp.original_personal_id)
JOIN dwh.tiempopago dt on(desempenno.fecha::date=dt.fecha)
GROUP BY dp.idpersonal, dt.idtiempopago, desempenno.fecha::date 
ORDER BY desempenno.fecha::date;

SELECT dt.idTiempo AS tiempo, 
    dc.idCategoria AS categoria, 
    SUM(nt.rrdate::date-dt.fecha::date) AS dias
FROM (SELECT fc.category_id AS category, 
        r.rental_date AS rdate, 
        r.return_date AS rrdate, *
      FROM rental AS r
      JOIN inventory AS i
      ON r.inventory_id=i.inventory_id
      JOIN film AS f
      ON i.film_id=f.film_id
      JOIN film_category AS fc
      ON fc.film_id = f.film_id
      WHERE r.return_date IS NOT NULL) AS nt
JOIN dwh.categoria AS dc
ON dc.original_categoria_id=nt.category
JOIN dwh.tiempo AS dt
ON dt.fecha=nt.rdate::date
GROUP BY dt.idTiempo, dc.idCategoria;

SELECT * 
FROM inventario_hechos() as x(tiempo_id int ,
        pelicula_id int, 
        cantidad_alquileres int, 
        cantidad_peliculas_disponibles int, 
        proporcion float, 
        cantidad_incidencias int);

SELECT
	tiempo_id,
	categoria_id,
	cantidad_dias
FROM dwh.alquiler;