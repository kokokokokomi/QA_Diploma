# Тестирование сценария покупки тура через веб-сервис "Путешествие дня", взаимодействующий с СУБД и API Банка.

## Документация

1. План автоматизации - [Plan.md](https://github.com/komisuomi/QA_Diploma/blob/master/docs/PLAN.md)
2. Отчётные документы по итогам тестирования -  [Report.md]()
3. Отчётные документы по итогам автоматизации - [Summary.md]()

## Описание работы веб-сервиса:
Сервис позволяет купить тур с помощью двух способов: оплата по дебетовой карте; с помощью кредита.
Сервис НЕ обрабатывает данные по картам, а пересылает их банку (через симулятор на Node.js):
* сервису платежей (Payment Gate);
* кредитному сервису (Credit Gate).

Симулятор позволяет для заданного набора карт генерировать предопределённые ответы.

Приложение должно в собственной СУБД сохранять информацию о том, каким способом был совершён платёж (карта/кредит) и резолюцию (успешно/неуспешно). Данные карт сохранять не допускается.

Заявлена поддержка двух СУБД: MySQL и PostgreSQL.

## Руководство по запуску SUT:
1. Запустить Docker.
2. Запустить контейнеры.
* Команда для запуска контейнеров:

```
docker-compose up
```
3. Запустить приложение. Для этого в командной строке указать через флаг -D url к необходимой СУБД.
* Команда для запуска приложения c mysql:

```
java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar ./artifacts/aqa-shop.jar 

```
* Команда для запуска приложения c psql:

```
java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar ./artifacts/aqa-shop.jar 

```
4. Запустить тесты. Для этого в командной строке указать через флаг -D url к необходимой СУБД.
* Команда для запуска тестов c mysql:

```
gradlew test -Dselenide.headless=true -Durlbd=jdbc:mysql://localhost:3306/app --info
```
* Команда для запуска тестов c psql:

```
gradlew test -Dselenide.headless=true -Durlbd=jdbc:postgresql://localhost:5432/app --info
```

5. При необходимости отключения SUT, находясь в терминале Intellij IDEA, нажать клавиши `CTRL+C`.
6. При необходимости отключения контейнеров ввести в терминале Intellij IDEA команду `docker-compose down`.
