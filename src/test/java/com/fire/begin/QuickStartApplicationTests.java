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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public void testSimpleObject() {
        String a = "1";
        String b = "2";

        Diff diff = generalComparator.compare(a, b);

        log.error(JsonUtils.toJson(diff));


    }

    @Test
    public void testObjectCompare() {
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


    @Test
    public void testSimpleList() {

        List<String> list = Arrays.asList("1", "2");
        List<String> list1 = Arrays.asList("1", "3");

        Diff diff = generalComparator.compare(list, list1);

        log.error(JsonUtils.toJson(diff));
    }


    @Test
    public void testObjectList(){
        Student student = new Student();
        student.setId("1");


        Student student1 = new Student();
        student1.setId("2");

        Diff diff = generalComparator.compare(Collections.singletonList(student), Collections.singletonList(student1));

        log.error(JsonUtils.toJson(diff));
    }


}
