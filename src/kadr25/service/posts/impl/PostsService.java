package kadr25.service.posts.impl;

import kadr25.model.AdvancedSearch;
import kadr25.model.MyFiles;
import kadr25.model.Post;

import java.util.List;

public interface PostsService {
    List<Post> getPosts() throws Exception;


    List<Post> getPostsForSlide() throws Exception;

    List<Post> getLastPosts() throws Exception;

    List<Post> getMostViewedPosts() throws Exception;

    List<Post> getDateCategory() throws Exception;

    List<Post> getPostsByCatId(Integer catId) throws Exception;

    List<Post> getPostsByDate(String date) throws Exception;

    List<Post> getPostsByParentId(Integer parentId) throws Exception;

    boolean addPost(Post post) throws Exception;

    Post getPostById(Integer id) throws Exception;

    Post getPostByIdForClient(Integer id) throws Exception;

    boolean deletePostFile(MyFiles myFile) throws Exception;

    List<MyFiles> getFilesById(Integer id) throws Exception;

    boolean selectPostCover(MyFiles myFiles) throws Exception;

    boolean updatePost(Post post) throws Exception;

    boolean deletePost(Post post) throws Exception;

    List<Post> getSearchedData(AdvancedSearch advs) throws Exception;


}
