# cryptoCurrencyWatcher
crypto currency watcher

в application.properties нужно указать имя пользователя и пороль для соединения с бд
в папке src/dump расположен бэкап бд, который нужно импортировать (MySql)
название схемы crypto / таблицы coin

запросы:

Просмотр списка доступных криптовалют:
http://localhost:8080/api/cryptowatcher/


Просмотр актуальной цены для указаной криптовалюты:
  1. BTC
     http://localhost:8080/api/cryptowatcher/90   
  2. ETH
     http://localhost:8080/api/cryptowatcher/80
  3. SOL
     http://localhost:8080/api/cryptowatcher/48543
     
 Записать в лог сообщение о изменении цены более чем на 1%
  примеры
  1.  http://localhost:8080/api/cryptowatcher/notify?name=Iman&symbol=SOL
  2.  http://localhost:8080/api/cryptowatcher/notify?name=Irina&symbol=BTC
 
