package cz.tul.data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Martin on 03.04.2017.
 */

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="Image_Tag",
            joinColumns={@JoinColumn(name="Image_id")},
            inverseJoinColumns = {@JoinColumn(name="Tag_id")})
    private Set<Tag> tags = new HashSet<Tag>();


    @Column(name = "url")
    private String url;
    private String name;
    private Date dateCreate;
    private Date dateEdit;
    private int likes;
    private int dislikes;

    public Image() {
    }

    public Image(String url, String name, Date dateCreate,
                 Date dateEdit, int likes, int dislikes, User user) {
        this.url = url;
        this.name = name;
        this.dateCreate = dateCreate;
        this.dateEdit = dateEdit;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(Date dateEdit) {
        this.dateEdit = dateEdit;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
