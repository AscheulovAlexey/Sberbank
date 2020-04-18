package com.sberbank.task2;

import java.util.List;
import java.util.Map;

/*
Есть строка, состоящая из слов. Все слова в ней разделены одним пробелом.
Нужно преобразовать строку в такую структуру данных, которая группирует слова по первой букве в слове.
Затем вывести только группы, содержащие более одного элемента.
Группы должны быть отсортированы в алфавитном порядке.
Слова внутри группы нужно сортировать по убыванию количества символов;
если количество символов равное, то сортировать в алфавитном порядке.

Пример строки: «сапог сарай арбуз болт бокс биржа»
Отсортированная строка: [б=[биржа, бокс, болт], c=[caпог, сарай]]
*/

public class Main {

    public static void main(String[] args) {

        String inputString = "Сапог саРай арбуз болт БоКс биржа с лалала проверка море кто арка а б бум серьга";
        System.out.println("Входная строка:\n" + inputString);

        Map<String, List<String>> dictionary = SortStringService.transformStringToSortedMap(inputString);
        System.out.println("\nОтсортированная строка:\n" + dictionary);
    }

}
