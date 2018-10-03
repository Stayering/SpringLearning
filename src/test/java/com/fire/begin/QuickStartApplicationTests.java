package com.fire.begin;

import com.fire.begin.compare.base.Diff;
import com.fire.begin.compare.base.GeneralComparator;
import com.fire.begin.compare.base.SingleValueCompare;
import com.fire.begin.modle.Book;
import com.fire.begin.modle.Student;
import com.fire.begin.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QuickStartApplicationTests {


    @Autowired
    SingleValueCompare stringSingleValueCompare;


    @Autowired
    GeneralComparator generalComparator;

    @Test
    public void contextLoads() {

        System.out.println("kkkk");
    }


    @Test
    public void testCompare() {
        Student student = new Student();
        student.setId("1");

        Book book = new Book();
        book.setId("book");
        book.setAuthor("strong");
        book.setName("gone with wind");

        student.setBook(book);

        Student student1 = new Student();
        student1.setId("2");

        Book book1 = new Book();
        book1.setId("book1");
        student1.setBook(book1);

        Diff diff = generalComparator.compare(student, student1);

        log.error(JsonUtils.toJson(diff));
    }


}
