//package com.example.demo.model.test;
//
//import java.io.Serializable;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//import Paper;
//import User;
//import org.springframework.data.domain.Persistable;
//
//public class Subscribe implements Serializable, Persistable<Long> {
//
//    @Id
//    @Column(name = "id", unique = true, nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @JoinColumn
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @Column(name = "paper_id")
//    private Paper paper;
//
//    @JoinColumn
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @Column(name = "user_id")
//    private User user;
//
//    @Override
//    public Long getId() {
//        return id;
//    }
//
//    @Override
//    public boolean isNew() {
//        return id == null;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Paper getPaper() {
//        return paper;
//    }
//
//    public void setPaper(Paper paper) {
//        this.paper = paper;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//}
