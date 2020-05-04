package kadr25.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Post {
    private Integer id;
    private String heading;
    private String context;

    private Author author;

    private List<String> fileName;

    private Map<Integer,String> fileData;

    private MyFiles files;

    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    private Categorie categorie;

    private Integer likes;
    private Integer views;

    private int postType;

    public Map<Integer, String> getFileData() {
        return fileData;
    }

    public void setFileData(Map<Integer, String> fileData) {
        this.fileData = fileData;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public MyFiles getFiles() {
        return files;
    }

    public void setFiles(MyFiles files) {
        this.files = files;
    }

    public List<String> getFileName() {
        return fileName;
    }

    public void setFileName(List<String> fileName) {
        this.fileName = fileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", heading='" + heading + '\'' +
                ", context='" + context + '\'' +
                ", author=" + author +
                ", fileName=" + fileName +
                ", fileData=" + fileData +
                ", files=" + files +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", deleted_at=" + deleted_at +
                ", categorie=" + categorie +
                ", likes=" + likes +
                ", views=" + views +
                ", postType=" + postType +
                '}';
    }
}