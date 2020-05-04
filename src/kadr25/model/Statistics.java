package kadr25.model;

public class Statistics {
    private Author author;
    private Post post;
    private Integer postCounts;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Integer getPostCounts() {
        return postCounts;
    }

    public void setPostCounts(Integer postCounts) {
        this.postCounts = postCounts;
    }
}
