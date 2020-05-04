package kadr25.service.posts.service;

import kadr25.dao.post.dao.PostDao;
import kadr25.model.AdvancedSearch;
import kadr25.model.MyFiles;
import kadr25.model.Post;
import kadr25.service.posts.impl.PostsService;

import java.util.ArrayList;
import java.util.List;

public class PostsServiceImpl implements PostsService {
    private PostDao postDao;

    public PostsServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public List<Post> getPosts() throws Exception {
        return postDao.getPosts();
    }

    @Override
    public List<Post> getPostsForSlide() throws Exception {
        List<Post> pl = postDao.getPostsForSlide();
        List<Post> postList = new ArrayList<>();
        int i = 0;
        for (Post post : pl) {
            i++;
            if (i == 4) {
                break;
            } else {
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public List<Post> getLastPosts() throws Exception {
        return postDao.getLastPosts();
    }

    @Override
    public List<Post> getMostViewedPosts() throws Exception {
        List<Post> pl = postDao.getMostViewedPosts();
        List<Post> postList = new ArrayList<>();
        int i = 0;
        for (Post post : pl) {
            i++;
            if (i == 6) {
                break;
            } else {
                postList.add(post);
            }
        }
        return postList;
    }

    @Override
    public List<Post> getDateCategory() throws Exception {
        return postDao.getDateCategory();
    }

    @Override
    public List<Post> getPostsByCatId(Integer catId) throws Exception {
        return postDao.getPostsByCatId(catId);
    }

    @Override
    public List<Post> getPostsByDate(String date) throws Exception {
        return postDao.getPostsByDate(date);

    }

    @Override
    public List<Post> getPostsByParentId(Integer parentId) throws Exception {
        return postDao.getPostsByParentId(parentId);
    }

    @Override
    public boolean addPost(Post post) throws Exception {
        return postDao.addPost(post);
    }

    @Override
    public Post getPostById(Integer id) throws Exception {
        return postDao.getPostById(id);
    }

//    Post post =
//    int postType = 0;
//        if (post.getFiles().getRole() == 1) {
//        String fileName = post.getFiles().getFileName();
//        if (fileName.endsWith(".png") || fileName.endsWith(".jpeg") ||
//                fileName.endsWith(".jpg") || fileName.endsWith(".img")){
//            postType = 1;
//        } else {
//            postType = 2;
//        }
//        post.setPostType(postType);
//    }


    @Override
    public Post getPostByIdForClient(Integer id) throws Exception {
        return postDao.getPostByIdCl(id);
    }

    @Override
    public boolean deletePostFile(MyFiles myFile) throws Exception {
        return postDao.deletePostFile(myFile);
    }

    @Override
    public List<MyFiles> getFilesById(Integer id) throws Exception {
        return postDao.getFilesById(id);
    }

    @Override
    public boolean selectPostCover(MyFiles myFiles) throws Exception {
        return postDao.selectPostCover(myFiles);
    }

    @Override
    public boolean updatePost(Post post) throws Exception {
        return postDao.updatePost(post);
    }

    @Override
    public boolean deletePost(Post post) throws Exception {
        return postDao.deletePost(post);
    }

    @Override
    public List<Post> getSearchedData(AdvancedSearch advs) throws Exception {
        String searchSql = "";
        if (advs.getKeyword().length() != 0) {
            searchSql += " AND (LOWER (P.HEADING) LIKE LOWER ('%" + advs.getKeyword() + "%'))";
        }
        if (advs.getCatId() != 0) {
            searchSql += " AND C.ID =" + advs.getCatId();
        }
        if (advs.getUserId() != 0) {
            searchSql += " AND A.ID =" + advs.getUserId();
        }

        if (advs.getDateFrom().length() != 0) {
            searchSql += " AND P.CREATED_AT >= TO_DATE('" + advs.getDateFrom() + "','MM/DD/YYYY')";
        }

        if (advs.getDateTo().length() != 0) {
            searchSql += "AND P.CREATED_AT <= TO_DATE('" + advs.getDateTo() + "','MM/DD/YYYY')";
        }

        if (advs.getLikeT() != 0) {
            if (advs.getLikeT() == 1) {
                searchSql += " ORDER BY P.LIKES ASC,";
            } else if (advs.getLikeT() == 2)
                searchSql += " ORDER BY P.LIKES DESC,";
        }

        if (advs.getViewT() != 0 && advs.getLikeT() != 0) {
            if (advs.getViewT() == 1) {
                searchSql += " P.VIEWS ASC ";
            } else if (advs.getViewT() == 2)
                searchSql += " P.VIEWS DESC ";
        }

        if (advs.getViewT() == 0 && advs.getLikeT() == 0) {
            if (advs.getLikeT() == 1) {
                searchSql += " ORDER BY P.VIEWS ASC";
            } else if (advs.getLikeT() == 2)
                searchSql += " ORDER BY P.VIEWS DESC";
        }

        return postDao.getSearchedData(searchSql);
    }

}
