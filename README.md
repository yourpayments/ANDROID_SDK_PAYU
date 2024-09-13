# «Твои платежи», SDK для OS Android

![](https://repository-images.githubusercontent.com/638835276/ff494b04-d65b-4843-8759-e85c689a7e80)

[НКО «Твои Платежи»](https://YPMN.ru/ "Платёжная система для сайтов, платформ и приложений") - платёжная система для сайтов, платформ, игр и приложений.


# Задачи

1. Отправить заказ по протоколу ALU
а) Создаем объект ALURequestBuilder, наполняем его данными с помощью соответствующих методов.
б) Выполняем запрос на сервер:
ALUResponse response = new PAYUHttpRest().submitOrderALU(requestBuilder.build());
в) ALUResponse - ответ на запрос.

2. Отправить заказ по протоколу LU
а) Создаем объект LURequestBuilder, наполняем его данными с помощью соответствующих методов.
б) Программно или с помощью разметки добавляем объект LUPurchaseView.
Есть возможность наследоваться от него и переопределить onPageStarted() и onPageFinished().
в) Выполняем запрос с данными, с помощью метода postUrl, объекта LUPurchaseView.

3. 	Проверить статус заказа
а) Создаем объект IOSRequestBuilder, наполняем его данными с помощью соответствующих методов.
б) Выполняем запрос на сервер:
IOSResponse response = new PAYUHttpRest().checkOrderIOS(requestBuilder.build());
в) IOSResponse - ответ на запрос.

4.   Отправить сообщение по протоколу IDN
а) Создаем объект IDNRequestBuilder, наполняем его данными с помощью соответствующих методов.
б) Выполняем запрос на сервер:
IDNResponse response = new PAYUHttpRest().sendDeliveryNotificationIDN(requestBuilder.build());
в) IDNResponse - ответ на запрос.

5. 	Отправить сообщение по протоколу IRN
а) Создаем объект IRNRequestBuilder, наполняем его данными с помощью соответствующих методов.
б) Выполняем запрос на сервер:
IRNResponse response = new PAYUHttpRest().sendRefundNotificationIRN(requestBuilder.build());
в) IRNResponse - ответ на запрос.

# PAYUHttpRest
Класс для выполнения рест колов на сервер. Должен выполнятся в беграунде. Ответ на запрос и его детализация, соответсвуют модели ответа, соответственно для всех запросов ответ разный.

# PayUSdkTest
Максимальная сумма платежа на тестовом аккаунте – 2 рубля.
