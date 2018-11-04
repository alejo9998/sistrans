--REQUEREMIENTO 1
SELECT C.SUCURSAL,SUM(D.TOTALPAGADO)AS TOTAL
FROM(
SELECT *
FROM(
SELECT *
FROM A_ESTANTE )A INNER JOIN A_PRODUCTOSUCURSAL B ON A.IDESTANTE=B.ESTANTE) C INNER JOIN A_COMPRA D 
ON c.IDPRODUCTOSUCURSAL = d.productosucursal
WHERE FECHA BETWEEN 0 AND 1 
GROUP BY C.SUCURSAL;

--requerimiento 3
SELECT SUM(B.VOLUMENEMPAQUE)/A.VOLUMEN AS INDICE_USO,A.IDBODEGA
FROM(
SELECT *
FROM A_BODEGA ) A INNER JOIN A_PRODUCTOSUCURSAL B ON A.IDBODEGA = B.BODEGA
GROUP BY A.VOLUMEN,A.IDBODEGA;

SELECT SUM(B.VOLUMENEMPAQUE)/A.VOLUMEN AS INDICE_USO,A.IDESTANTE
FROM(
SELECT *
FROM A_ESTANTE ) A INNER JOIN A_PRODUCTOSUCURSAL B ON A.IDESTANTE = B.ESTANTE
GROUP BY A.VOLUMEN,A.IDESTANTE;

--REQUEREMIENTO 4

--PRODUTO POR PESO
SELECT*
FROM A_PRODUCTOSUCURSAL
WHERE PESOEMPAQUE BETWEEN 1 AND 2;

--PRODUCTO POR VOLUMEN
SELECT*
FROM A_PRODUCTOSUCURSAL
WHERE VOLUMENEMPAQUE BETWEEN 1 AND 2;

--PRODUCTO POR CATEGORIA
SELECT *
FROM A_PRODUCTOSUCURSAL 
WHERE CATEGORIA =;

--PRODUCTO POR TIPO
SELECT *
FROM A_PRODUCTOSUCURSAL 
WHERE TIPO =;

--PRODUCTO POR CIUDAD
SELECT B.*
FROM(
SELECT *
FROM(
SELECT *
FROM A_SUCURSAL) D INNER JOIN A_ESTANTE F ON D.IDSUCURSAL = F.SUCURSAL) A INNER JOIN A_PRODUCTOSUCURSAL B ON A.IDESTANTE = B.ESTANTE 
WHERE A.CIUDAD = ;

--PRODUCTO POR SUCURSAL
SELECT B.*
FROM(
SELECT *
FROM(
SELECT *
FROM A_SUCURSAL) D INNER JOIN A_ESTANTE F ON D.IDSUCURSAL = F.SUCURSAL) A INNER JOIN A_PRODUCTOSUCURSAL B ON A.IDESTANTE = B.ESTANTE 
WHERE A.SUCURSAL = ;


--requerimineto 5
Select b.* from a_ordenpedido a inner join
a_productoproveedor b on a.productoproveedor = b.idproductoproveedor;

--requerimiento 6 
select b.* from (a_compra a inner join a_cliente b on a.cliente= b.identificacion)
where a.fecha BETWEEN 
'03/11/18' and '05/11/18';

-- requerimiento 7

Select cantidadDeCompras, cliente from(
select d.cliente,count(d.cliente) as cantidadDeCompras 
from a_estante c inner join(
Select a.cliente,b.estante from a_compra a inner join 
a_productosucursal b on a.productosucursal=b.idproductosucursal ) d on
c.idestante=d.estante 
where c.sucursal=3171  group by cliente)
where cantidadDeCompras >= (Select MONTHS_BETWEEN((Select current_date from dual),'05/02/18')*2 from dual)
;

--requerimineto 8
-- mayor demanda
Select fecha, sum(cantidad) as sumTotal from (
Select fecha,cantidad from (
select productosucursal, fecha ,cantidad from a_compra 
where fecha BETWEEN (Select current_date from dual) and '04/02/18') a inner join a_productosucursal b 
on a.productosucursal =b.idproductosucursal
where b.tipo= 'albarrotes') group by fecha order by fecha asc;

--mayor dinero
Select fecha, sum(totalpagado) as sumTotal from (
Select fecha,totalpagado from (
select productosucursal, fecha,totalpagado from a_compra 
where fecha BETWEEN (Select current_date from dual) and '04/02/18') a inner join a_productosucursal b 
on a.productosucursal =b.idproductosucursal
where b.tipo= 'albarrotes') group by fecha order by fecha asc
;
--  menor demanda
Select fecha, sum(cantidad) as sumTotal from (
Select fecha,cantidad from (
select productosucursal, fecha ,cantidad from a_compra 
where fecha BETWEEN (Select current_date from dual) and '04/02/18') a inner join a_productosucursal b 
on a.productosucursal =b.idproductosucursal
where b.tipo= 'albarrotes') group by fecha order by fecha desc;
