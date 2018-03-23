package com.example.demo.model;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;

import org.springframework.data.domain.Persistable;

@Entity
public class Book implements Serializable, Persistable<Long> {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private float price;

    @Column(name = "time")
    private Date time;

    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    //book与作者是多对一的关系，JoinColumn用来加入外键，Cacade 用来级联，CascadeType.Remove 指级联删除，被关联表元祖删除，关联表的对应元祖也会被删除，这里book是关联表，通过外键字段author(在数据库中为author_id外键）关联author表，author是被关联表
    // 同样 CascadeType.Persist则是级联存储，其他几个 对应他们的本身单词意思
    //CascadType.ALL 则包含了所有级联操作
    private Author author;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", time=" + time +
                ", author=" + author +
                '}';
    }
}
