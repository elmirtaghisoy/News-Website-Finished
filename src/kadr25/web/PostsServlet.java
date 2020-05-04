package kadr25.web;

import javafx.geometry.Pos;
import kadr25.DbHelper;
import kadr25.dao.author.dao.AuthorDao;
import kadr25.dao.author.impl.AuthorDaoImpl;
import kadr25.dao.categorie.dao.CategorieDao;
import kadr25.dao.categorie.impl.CategorieDaoImpl;
import kadr25.dao.post.dao.PostDao;
import kadr25.dao.post.impl.PostDaoImpl;
import kadr25.model.*;
import kadr25.service.author.impl.AuthorService;
import kadr25.service.author.service.AuthorServiceImpl;
import kadr25.service.categorie.impl.CategorieService;
import kadr25.service.categorie.service.CategorieServiceImpl;
import kadr25.service.posts.impl.PostsService;
import kadr25.service.posts.service.PostsServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.sql.Connection;
import java.util.List;

@javax.servlet.annotation.WebServlet(name = "PostsServlet", urlPatterns = "/pc")
public class PostsServlet extends javax.servlet.http.HttpServlet {
    PostDao postDao = new PostDaoImpl();
    PostsService postsService = new PostsServiceImpl(postDao);

    AuthorDao authorDao = new AuthorDaoImpl();
    AuthorService authorService = new AuthorServiceImpl(authorDao);

    CategorieDao categorieDao = new CategorieDaoImpl();
    CategorieService categorieService = new CategorieServiceImpl(categorieDao);


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter pw = response.getWriter();
            String action = null;
            String address = null;
            boolean isExistAdress = false;
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }
            if (action.equalsIgnoreCase("getSlidePosts")) {
                List<Post> slidePosts = postsService.getPostsForSlide();
                if (!slidePosts.isEmpty()) {
                    isExistAdress = true;
                    request.setAttribute("slidePosts", slidePosts);
                    address = "/WEB-INF/clientPages/views/mainSlide.jsp";
                }
            } else if (action.equalsIgnoreCase("getLastPosts")) {
                List<Post> lastPosts = postsService.getLastPosts();
                if (!lastPosts.isEmpty()) {
                    isExistAdress = true;
                    request.setAttribute("lastPosts", lastPosts);
                    address = "/WEB-INF/clientPages/views/lastPosts.jsp";
                }
            } else if (action.equalsIgnoreCase("getMostViewedPosts")) {
                isExistAdress = true;
                List<Post> postList = postsService.getMostViewedPosts();
                if (!postList.isEmpty()) {
                    request.setAttribute("mostViewedPosts", postList);
                    address = "/WEB-INF/clientPages/views/mostViewed.jsp";
                }
            } else if (action.equalsIgnoreCase("getPostsByCatId")) {
                Integer catId = Integer.parseInt(request.getParameter("catId"));
                List<Post> postsByCatId = postsService.getPostsByCatId(catId);
                if (!postsByCatId.isEmpty()) {
                    isExistAdress = true;
                    request.setAttribute("postsByCatId", postsByCatId);
                    address = "/WEB-INF/clientPages/views/postsByCatId.jsp";
                }

            } else if (action.equalsIgnoreCase("getDateCategoryForClient")) {
                List<Post> postList = postsService.getDateCategory();
                if (!postList.isEmpty()) {
                    isExistAdress = true;
                    request.setAttribute("postList", postList);
                    address = "/WEB-INF/clientPages/views/dateCategory.jsp";
                }
            } else if (action.equalsIgnoreCase("getAllPosts")) {
                isExistAdress = true;
                List<Post> postList = postsService.getPosts();
                request.setAttribute("allPosts", postList);
                address = "/WEB-INF/pages/posts.jsp";
            } else if (action.equalsIgnoreCase("getDataForNewPost")) {
                List<Author> authorList = authorService.getAuthors();
                List<Categorie> categories = categorieService.getAllCategories();
                request.setAttribute("categories", categories);
                request.setAttribute("authorList", authorList);
                address = "/WEB-INF/pages/views/addNewPost.jsp";
                isExistAdress = true;
            } else if (action.equalsIgnoreCase("deletePosts")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                Post post = new Post();
                post.setId(id);

                boolean isDeleted = postsService.deletePost(post);
                if (isDeleted) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("getPostById")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                Post post = postsService.getPostById(id);
                List<Author> authorList = authorService.getAuthors();
                List<Categorie> categories = categorieService.getAllCategories();
                request.setAttribute("categories", categories);
                request.setAttribute("authorList", authorList);
                request.setAttribute("post", post);
                address = "/WEB-INF/pages/views/updatePost.jsp";
                isExistAdress = true;
            } else if (action.equalsIgnoreCase("getPostByIdForClient")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
//                System.out.println(Inet4Address.getLocalHost().getHostAddress());
                Post post = postsService.getPostByIdForClient(id);
                if (post.getId() != null) {
                    request.setAttribute("post", post);
                    address = "/WEB-INF/clientPages/views/post.jsp";
                    isExistAdress = true;
                }
            } else if (action.equalsIgnoreCase("getPostFilesById")) {
                Integer id = Integer.parseInt(request.getParameter("postId"));
                List<MyFiles> files = postsService.getFilesById(id);
                request.setAttribute("file", files);
                address = "/WEB-INF/pages/views/filesForPostsAP.jsp";
                isExistAdress = true;
            } else if (action.equalsIgnoreCase("getPostFilesByIdForClient")) {
                Integer id = Integer.parseInt(request.getParameter("postId"));
                List<MyFiles> files = postsService.getFilesById(id);
                request.setAttribute("files", files);
                address = "/WEB-INF/clientPages/views/imagePost.jsp";
                isExistAdress = true;
            } else if (action.equalsIgnoreCase("getPostsByParentId")) {
                Integer id = Integer.parseInt(request.getParameter("parentId"));
                List<Post> postsByCatId = postsService.getPostsByParentId(id);
                if (!postsByCatId.isEmpty()) {
                    request.setAttribute("postsByCatId", postsByCatId);
                    address = "/WEB-INF/clientPages/views/postsByCatId.jsp";
                    isExistAdress = true;
                }
            } else if (action.equalsIgnoreCase("deletePostFile")) {
                Integer id = Integer.parseInt(request.getParameter("fileId"));
                MyFiles myFile = new MyFiles();
                myFile.setId(id);
                boolean isDeleted = postsService.deletePostFile(myFile);
                if (isDeleted) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("selectPostCover")) {
                Integer id = Integer.parseInt(request.getParameter("fileId"));
                MyFiles myFile = new MyFiles();
                myFile.setId(id);
                boolean isSelected = postsService.selectPostCover(myFile);
                if (isSelected) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("getPostsByDateForClient")) {
                String myDate = request.getParameter("dt");
                List<Post> postsList = postsService.getPostsByDate(myDate);
                request.setAttribute("postsList", postsList);
                address = "/WEB-INF/clientPages/views/postsByDate.jsp";
                isExistAdress = true;
            } else if (action.equalsIgnoreCase("getDataForSearch")) {
                List<Author> authorList = authorService.getAuthors();
                List<Categorie> categories = categorieService.getAllCategories();
                request.setAttribute("categories", categories);
                request.setAttribute("authorList", authorList);
                address = "/WEB-INF/pages/views/searchBox.jsp";
                isExistAdress = true;
            } else if (action.equalsIgnoreCase("getSearchedData")) {
                AdvancedSearch advs = new AdvancedSearch();
                advs.setKeyword(request.getParameter("keyword"));
                advs.setDateFrom(request.getParameter("dateFrom"));
                advs.setDateTo(request.getParameter("dateTo"));
                advs.setUserId(Integer.parseInt(request.getParameter("userFrmSrc")));
                advs.setLikeT(Integer.parseInt(request.getParameter("likeFrmSrc")));
                advs.setViewT(Integer.parseInt(request.getParameter("viewFrmSrc")));
                advs.setCatId(Integer.parseInt(request.getParameter("catFrmSrc")));

                List<Post> postList = postsService.getSearchedData(advs);
                request.setAttribute("allPosts", postList);
                address = "/WEB-INF/pages/posts.jsp";
                isExistAdress = true;
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            if (isExistAdress) {
                dispatcher.forward(request, response);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
