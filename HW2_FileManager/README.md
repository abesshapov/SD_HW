## ИДЗ-2 по дисциплине "КПО"
### Бесшапов Алексей, БПИ219
---
### Процесс взаимодействия пользователя с программой
- Взаимодействие пользователя с программой заключается в задании корневой папки, с которой будет вестись описанная в условии работа. 
- От пользователя требуется ввести существующую папку, пока такая не будет введена, программа будет запрашивать ввод повторно.
---
### Процесс работы программы с введенными данными
После задания существующей корневой папки пользователем программа выполняет операции, направленные на решение поставленной задачи:
1. Нахождение всех текстовых файлов в корневой папке, независимо от уровня вложенности.
2. Нахождение зависимостей для всех найденных текстовых файлов.
3. Проверка зависимостей на предмет цикличности, то есть: файл А зависит от файла Б, который в свою очередь зависит от файла А. Важно отметить, что зависимость при этом может быть не прямой. Например: файл А зависит от файла Б, файл Б зависит от файла В, файл В зависит от файла А - такая система зависимостей тоже циклическая.
4. В случае отсутствия циклической зависимости файлы сортируются по данному правилу: "если файл А зависит от файла В, то файл А находится ниже файла В в списке.". Данная сортировка проводится в несколько этапов: 
    - Для каждого файла находятся все зависимости на всех уровнях вложенности: то есть в том числе зависимости файлов, от которых файл зависит.
    - Затем для каждого файла определяется количество неповторяющихся зависимостей всех уровней вложенности. Чем больше это количество, тем ниже файл в списке.
      - Q: Почему для файла А, зависимого от файла Б, количество неповторяющихся зависимостей всех уровней вложенности больше?
      - A: Потому что, как минимум, у файла А на одну зависимость больше: все зависимости Б всех уровней вложенности + зависимость от самого Б.
    - Затем файлы сортируются по количеству неповторяющихся зависимостей всех уровней вложенности, составляется итоговый список.
      - Если количество одинаковое для нескольких файлов, то они выводятся в том же порядке, в котором они были обнаружены при поиске текстовых файлов (данная ситуация не описана в условии => метод решения ситуации на усмотрение разработчика).
5. Результат сортировки выводится и в соответствии с порядком содержимое файлов конкатенируется.
---
### Формат описания зависимостей
В файлах корневой директории зависимости должны описываться следующим образом:
- Путь к файлу, от которого данный файл зависим, от корневой директории.
- В начале нет знаков "\" и "/".
- Каждая зависимость с новой строчки.
- На строчках с зависимостями ничего, кроме зависимостей, нет.