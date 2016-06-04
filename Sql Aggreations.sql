-- Сумма за период
	SELECT SUM(cost) AS TotalSum FROM SaleProduct where date BETWEEN 146611080000 AND 1460754000000;

-- Средняя стоимость покупок
  SELECT AVG(cost) AS AvgCost FROM SaleProduct where date BETWEEN 146611080000 AND 1460754000000;

-- Самая дорогая покупка
  SELECT MAX(cost) AS MaxCost FROM SaleProduct where date BETWEEN 146611080000 AND 1460754000000;

-- Количество проданных товаров за период
  SELECT s.productId as id, p.name, COUNT(s.productId) AS Count
  FROM SaleProduct s
  LEFT JOIN Product p
    ON p.id = s.productId
  where date BETWEEN 146611080000 AND 1460754000000
  GROUP BY s.productId, p.name
  ORDER BY Count DESC;

-- Самый активный покупатель
  SELECT s.fio as id, s.phone, COUNT(s.phone) AS Count
  FROM SaleProduct s
  where date BETWEEN 146611080000 AND 1460754000000
  GROUP BY s.phone
  ORDER BY Count DESC;



