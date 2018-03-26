package com.roaddb.paper.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.data.domain.Persistable;

@Entity
public class Category implements Serializable, Persistable<Long> {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "category")
    private String category;

    @Column(name = "catrgory_name")
    private String catrgoryName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private List<Category> children;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Paper> papers;

    public Category() {
    }

    public Category(Long parentId, String category, String catrgoryName) {
        this.parentId = parentId;
        this.category = category;
        this.catrgoryName = catrgoryName;
    }

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCatrgoryName() {
        return catrgoryName;
    }

    public void setCatrgoryName(String catrgoryName) {
        this.catrgoryName = catrgoryName;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", category='" + category + '\'' +
                ", catrgoryName='" + catrgoryName + '\'' +
                ", children=" + children +
                ", papers=" + papers +
                '}';
    }
}
