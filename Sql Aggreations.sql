-- Сумма за период
	SELECT SUM(cost) AS TotalSum FROM SaleProduct where date BETWEEN 146611080000 AND 1460754000000;

-- Средняя стоимость покупок
  SELECT AVG(cost) AS AvgCost FROM SaleProduct where date BETWEEN 146611080000 AND 1460754000000;

-- Самая дорогая покупка
  SELECT MAX(cost) AS MaxCost FROM SaleProduct where date BETWEEN 146611080000 AND 1460754000000;

--
  SELECT productId, COUNT(productId) AS NumberOfProducts
  FROM SaleProduct
  GROUP BY productId;