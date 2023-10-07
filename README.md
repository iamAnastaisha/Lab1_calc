# Lab1_calc
Данное приложение разрабатывалось на Windows 10. Версия jdk : 19.
Для запуска у себя на компьютере необходимо:
1. Скачать jdk-19. Ссылка на скачивание: [download](https://download.oracle.com/java/19/archive/jdk-19.0.2_windows-x64_bin.exe) (возможно понадобится впн)
2. Прописать следующее в переменных окружения:
В системных переменных создать новую переменную JAVA_HOME = путь_к_файлу (пример C:\Program Files\Java\jdk-19) и добавить в PATH путь_к_файлу\bin
Возможно, аналогичные действия надо будет проделать для конкретного пользователя. Это зависит от того, от чьего имени запускается cmd.
3. Склонировать себе данный проект.
4. В cmd перейти в папку проекта (в папку src).
5. Выполнить команду
```bash
javac Calc.java
```
для компиляции.
8. Выполнить команду 
```bash
java Calc
```
 для запуска.

На данный момент полностью выполнен Шаг 1 лабораторной, а также сделаны несколько пунктов из Шага 2 и Шага 3.
