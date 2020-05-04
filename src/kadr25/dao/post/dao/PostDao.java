package kadr25.dao.post.dao;

import javafx.geometry.Pos;
import kadr25.model.MyFiles;
import kadr25.model.Post;

import java.util.List;

public interface PostDao {
    List<Post> getPosts() throws Exception;

    List<Post> getPostsForSlide() throws Exception;

    List<Post> getLastPosts() throws Exception;

    List<Post> getMostViewedPosts() throws Exception;

    List<Post> getPostsByCatId(Integer catId) throws Exception;

    List<Post> getDateCategory() throws Exception;

    List<Post> getPostsByParentId(Integer parentId) throws Exception;

    boolean addPost(Post post) throws Exception;

    Post getPostById(Integer id) throws Exception;

    List<Post> getPostsByDate(String date) throws Exception;

    Post getPostByIdCl(Integer id) throws Exception;

    boolean deletePostFile(MyFiles myFile) throws Exception;

    List<MyFiles> getFilesById(Integer id) throws Exception;

    boolean selectPostCover(MyFiles myFiles) throws Exception;

    boolean updatePost(Post post) throws Exception;

    boolean deletePost(Post post) throws Exception;

    List<Post> getSearchedData(String searchSql) throws Exception;
}
