package com.example.WordsManager;

import com.example.WordsManager.services.sortingService.WordSorter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * эта точка входа, предоставляет функционал по сохранению обьектов Voice и Word из локального хранилища в БД
 * перед сохранением ведется просчет поля sorting_value у обьектов Word
 */
@SpringBootApplication
public class SavingDatabaseApplication {
    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(SavingDatabaseApplication.class,args);

        //замер времени
        long startTime = System.currentTimeMillis();

        //Подготовка
        //Перекачиваем книги из репы в массивы, составляем мапу по словам
        //Запрашиваем из базы все слова
        WordSorter wordSorter = context.getBean(WordSorter.class);

        //переводим count из мапы в список из БД
        wordSorter.setCount();

        //вывод количества слов(фраз) неудачников
        wordSorter.printNumberWordsWithZeroCount();

        //отправляем неудачников на повторный длительный поиск
        wordSorter.searchPhrases(50);

        //еще раз переводим count из мапы в список из БД
        wordSorter.setCount();

        //выводим на экран оставшиеся редкоупотребляемые слова
        wordSorter.printAllWordsWithCountBelowX(50);

        //закоментируй следующие две строки если не уверен
        wordSorter.updateDateInBD();//отправит данные в БД
        wordSorter.setUpdateAt();//даем пользовательским телефонам понять, что данные обновились


        //замер времени
        System.out.println("Время выполнения операции: "+(System.currentTimeMillis()-startTime)+" милисек.");
        System.out.println("Закончено.");

        SpringApplication.exit(context);




    }
}
