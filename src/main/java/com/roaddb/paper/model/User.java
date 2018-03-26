package com.roaddb.paper.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.data.domain.Persistable;

@Entity
public class User implements Serializable, Persistable<Long> {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "role")
    private Integer role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Paper> papers;

    @OneToMany(mappedBy = "deleteUser", fetch = FetchType.LAZY)
    private List<Paper> deletePapers;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "subscribe",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "paper_id", referencedColumnName = "id")})
    private Set<Paper> subPapers;


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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }

    public List<Paper> getDeletePapers() {
        return deletePapers;
    }

    public void setDeletePapers(List<Paper> deletePapers) {
        this.deletePapers = deletePapers;
    }

    public Set<Paper> getSubPapers() {
        return subPapers;
    }

    public void setSubPapers(Set<Paper> subPapers) {
        this.subPapers = subPapers;
    }
}
