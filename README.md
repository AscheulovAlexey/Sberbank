# Задания

1) Программа для сортировки массива, использующую метод пузырька.
2) Программа для сортировки входной строки с несколькими условиями:
```
- Группировка слов по первой букве в слове
- Вывод только тех групп, которые содержат более одного элемента
- Сортировка групп в алфавитном порядке
- Сортировка слов внутри группы по убыванию количества символов
(если количество символов равное, то сортировка выполняется в алфавитном порядке)
```
3) Консольная утилита для скачивания файлов по HTTP с несколькими условиями:
```
- На вход принимает список ссылок в текстовом файле
- Скачивает эти файлы и кладет в указанную папку на локальном диске.
- Скачивает несколько файлов одновременно (в несколько потоков, например, 3 потока)
- Выдерживает ограничение на скорость загрузки (например, 500 килобайт в секунду)
```
### Описание для 3 программы

Для работы программа используются:
```
- Java
- Apache Tika
- Guava RateLimiter
```
Для запуска программы необходимо добавить 2 аргумента для метода Main:
```
1 - путь к файлу со ссылками (например, /Users/YourName/Downloads/links.txt)
2 - путь к папке, где будут сохраняться файлы (например, /Users/YourName/Downloads/javadownload/)
```
Описание работы программы:
```
1. На старте программа загружает текстовый файл с ссылками из указанной директории.

2. Далее пользователю предлагается выбрать количество потоков для скачивания.
Скачивание файлов в несколько поток реализовано с помощью ExecutorService.

3. После пользователь выбирает ограничение скорости для всех потоков.
Ограничение реализовано с помощью библиотеки Guava RateLimiter.
Создается глобальная переменная, к которой обращаются все потоки.

4. Каждый поток начинает скачивать отдельный файл.
Если директория до папки отсутствует - программа создаст директорию автоматически.

Файл сохранется в указанную пользователем директорию. Имя файла задается из двух параметров.
1 - Метод FilenameUtils.getBaseName(url.getPath()) забирает из ссылки наименование файла
2 - Расширение файла получается из метаданных скачиваемого файла с помощью библиотеки Apache Tika

Такой способ позволяет не только называть, но и корректно определять расширение файлов. Например.
1 - https://c.imgz.jp/382/43091382/43091382b_b_02_500.jpg - 43091382b_b_02_500 + .ipeg
2 - https://dl1.mp3party.net/download/9234185 - 9234185 + .mpeg

5. Во время скачивания отображается процент загрузки файла (10-20-30..100)

6. После завершения скачивания файла для текущего потока отображается несколько параметров.
Объем файла, время скачивания и скорость скачивания.
Скорость файла может быть отображаться большей, если размер файла меньше ограничиваемой скорости.
Например, размер файла - 150кб, скорость - 512кб/с.

7. После завершения скачивания всех файлов отображается несколько параметров.
Объем всех скачанных файлов, общее время скачивания и скорость скачивания для всех потоков.
Данные выводятся только после завершения выполнения всех потоков.
```
### Пример выполнения программы:
Весь процесс отображается в консоли

```
Требуемое количество потоков (1 поток - 1 файл) для скачивания. Например, 1,2,3...n
Введите число: 
3

Требуемое ограничение скорости скачивания. Например, 512 = 512кб/с.
Введите число: 
500

Скачивание началось...

Начал скачивать файл. Поток: pool-1-thread-1
Ссылка: https://dl1.mp3party.net/download/9234185

Начал скачивать файл. Поток: pool-1-thread-3
Ссылка: https://sneakers-magazine.com/wp-content/uploads/2018/03/off-white-x-nike-air-vapormax-flyknit-aa3831-002-on-feet-side.jpg

Начал скачивать файл. Поток: pool-1-thread-2
Ссылка: https://i.pinimg.com/originals/e8/e6/24/e8e624392defff2400cb62fd7f8f4c6c.jpg

Скачано 10% файла. Поток: pool-1-thread-3
Скачано 20% файла. Поток: pool-1-thread-3
Скачано 30% файла. Поток: pool-1-thread-3
Скачано 40% файла. Поток: pool-1-thread-3
Скачано 50% файла. Поток: pool-1-thread-3
Скачано 60% файла. Поток: pool-1-thread-3
Скачано 70% файла. Поток: pool-1-thread-3
Скачано 10% файла. Поток: pool-1-thread-2
Скачано 80% файла. Поток: pool-1-thread-3
Скачано 90% файла. Поток: pool-1-thread-3
Скачано 100% файла. Поток: pool-1-thread-3

Завершена загрузка файла из потока: pool-1-thread-3
Размер файла: 130 kb
Время скачивания: 0.007561867
Скорость скачивания: 17233 kb/s

Начал скачивать файл. Поток: pool-1-thread-3
Ссылка: https://images.solecollector.com/complex/images/c_fill,dpr_2.0,f_auto,fl_lossy,q_auto,w_680/g6pi6gttj8ckfpcmfkja/white-black-nike-flyknit-trainer-on-feet-5

Скачано 20% файла. Поток: pool-1-thread-2
Скачано 10% файла. Поток: pool-1-thread-3
Скачано 20% файла. Поток: pool-1-thread-3
Скачано 30% файла. Поток: pool-1-thread-2
Скачано 30% файла. Поток: pool-1-thread-3
Скачано 40% файла. Поток: pool-1-thread-3
Скачано 50% файла. Поток: pool-1-thread-3
Скачано 60% файла. Поток: pool-1-thread-3
Скачано 70% файла. Поток: pool-1-thread-3
Скачано 80% файла. Поток: pool-1-thread-3
Скачано 90% файла. Поток: pool-1-thread-3
Скачано 100% файла. Поток: pool-1-thread-3

Завершена загрузка файла из потока: pool-1-thread-3
Размер файла: 236 kb
Время скачивания: 0.667574548
Скорость скачивания: 158 kb/s
.....
.....
скачивание остальных файлов
.....
.....
Скачано 90% файла. Поток: pool-1-thread-3
Скачано 100% файла. Поток: pool-1-thread-3

Завершена загрузка файла из потока: pool-1-thread-3
Размер файла: 33244 kb
Время скачивания: 62.037140685
Скорость скачивания: 225 kb/s

-------------------
Завершена загрузка всех файлов!
Размер всех файлов: 33244 kb
Общее время скачивания: 66.558667528
Скорость скачивания для всех потоков: 499 kb/s

```
