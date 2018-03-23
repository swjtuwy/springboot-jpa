package com.example.demo.result;


import java.util.List;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultApi {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping(value = "/test")
    public void test(){
        System.out.println("22222222");
        Author author = authorRepository.findByname("test");
    }


    @GetMapping(value = "/test1")
    public void addData(){
        //导入测试数据
        for(int i=1;i<10;++i){
            Book book=new Book();
            Author author=new Author();
            author.setName("ar "+i);
            author.setAge(i*20);

            book.setName("bk "+i);
            book.setPrice(i * 15);
            book.setAuthor(author);

            bookRepository.save(book);
        }
        List<Book> list = bookRepository.findAll();
        System.out.println("size: " + list.size());

        list.forEach(book -> {System.out.println(book.toString());});
    }


}
