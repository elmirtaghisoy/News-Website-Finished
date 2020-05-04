package kadr25.dao.post.impl;

import kadr25.DbHelper;
import kadr25.dao.post.dao.PostDao;
import kadr25.model.Author;
import kadr25.model.Categorie;
import kadr25.model.MyFiles;
import kadr25.model.Post;
import kadr25.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class PostDaoImpl implements PostDao {

    @Override
    public List<Post> getPosts() throws Exception {
        List<Post> postsList = new ArrayList<Post>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT P.ID POST_ID,\n" +
                "       P.HEADING,\n" +
                "       P.CONTEXT,\n" +
                "       P.CREATED_AT,\n" +
                "       F.PATH,\n" +
                "       F.ROLE\n" +
                "  FROM FILES F INNER JOIN POSTS P ON F.POST_ID = P.ID\n" +
                " WHERE F.ROLE = 1 AND F.ACTIVE = 1 AND P.ACTIVE = 1";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Post post = new Post();
                    MyFiles myFiles = new MyFiles();
                    post.setId(rs.getInt("POST_ID"));
                    post.setHeading(rs.getString("HEADING"));
                    post.setContext(rs.getString("CONTEXT"));
                    post.setCreated_at(rs.getDate("CREATED_AT"));
                    myFiles.setFileName(rs.getString("PATH"));
                    myFiles.setRole(rs.getInt("ROLE"));
                    post.setFiles(myFiles);
                    postsList.add(post);
                }
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return postsList;
    }

    @Override
    public List<Post> getLastPosts() throws Exception {
        List<Post> postsList = new ArrayList<Post>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT P.ID POST_ID,\n" +
                "       P.HEADING,\n" +
                "       P.CONTEXT,\n" +
                "       P.CREATED_AT,\n" +
                "       P.LIKES,\n" +
                "       C.ID CAT_ID,\n" +
                "       C.CAT_NAME,\n" +
                "       A.ID AUTH_ID,\n" +
                "       A.NAME,\n" +
                "       A.SURNAME,\n" +
                "       F.PATH,\n" +
                "       F.ROLE\n" +
                "  FROM FILES F\n" +
                "       INNER JOIN POSTS P\n" +
                "          ON F.POST_ID = P.ID\n" +
                "       INNER JOIN AUTHORS A\n" +
                "          ON P.AUTHOR_ID = A.ID\n" +
                "       INNER JOIN CATEGORIES C\n" +
                "          ON P.CATEGORY_ID = C.ID\n" +
                " WHERE F.ROLE = 1 AND P.ACTIVE = 1 ORDER BY P.CREATED_AT DESC";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {

                    Post post = new Post();
                    post.setId(rs.getInt("POST_ID"));
                    post.setHeading(rs.getString("HEADING"));
                    post.setContext(rs.getString("CONTEXT"));
                    post.setCreated_at(rs.getDate("CREATED_AT"));
                    post.setLikes(rs.getInt("LIKES"));

                    Author author = new Author();
                    author.setId(rs.getInt("AUTH_ID"));
                    author.setName(rs.getString("NAME"));
                    author.setSurname(rs.getString("SURNAME"));
                    post.setAuthor(author);

                    Categorie categorie = new Categorie();
                    categorie.setId(rs.getInt("CAT_ID"));
                    categorie.setCategoryName(rs.getString("CAT_NAME"));
                    post.setCategorie(categorie);

                    MyFiles myFiles = new MyFiles();
                    myFiles.setFileName(rs.getString("PATH"));
                    myFiles.setRole(rs.getInt("ROLE"));
                    post.setFiles(myFiles);

                    postsList.add(post);
                }
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return postsList;
    }

    @Override
    public List<Post> getMostViewedPosts() throws Exception {
        List<Post> postsList = new ArrayList<Post>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "  SELECT P.ID POST_ID,\n" +
                "         P.HEADING,\n" +
                "         P.CONTEXT,\n" +
                "         P.CREATED_AT,\n" +
                "         P.LIKES,\n" +
                "         P.VIEWS,\n" +
                "         C.ID CAT_ID,\n" +
                "         C.CAT_NAME,\n" +
                "         A.ID AUTH_ID,\n" +
                "         A.NAME,\n" +
                "         A.SURNAME,\n" +
                "         F.PATH,\n" +
                "         F.ROLE\n" +
                "    FROM FILES F\n" +
                "         INNER JOIN POSTS P\n" +
                "            ON F.POST_ID = P.ID\n" +
                "         INNER JOIN AUTHORS A\n" +
                "            ON P.AUTHOR_ID = A.ID\n" +
                "         INNER JOIN CATEGORIES C\n" +
                "            ON P.CATEGORY_ID = C.ID\n" +
                "   WHERE F.ROLE = 1 AND P.ACTIVE = 1\n" +
                "ORDER BY P.VIEWS DESC, P.LIKES DESC ";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {

                    Post post = new Post();
                    post.setId(rs.getInt("POST_ID"));
                    post.setHeading(rs.getString("HEADING"));
                    post.setContext(rs.getString("CONTEXT"));
                    post.setCreated_at(rs.getDate("CREATED_AT"));
                    post.setLikes(rs.getInt("LIKES"));

                    Author author = new Author();
                    author.setId(rs.getInt("AUTH_ID"));
                    author.setName(rs.getString("NAME"));
                    author.setSurname(rs.getString("SURNAME"));
                    post.setAuthor(author);

                    Categorie categorie = new Categorie();
                    categorie.setId(rs.getInt("CAT_ID"));
                    categorie.setCategoryName(rs.getString("CAT_NAME"));
                    post.setCategorie(categorie);

                    MyFiles myFiles = new MyFiles();
                    myFiles.setFileName(rs.getString("PATH"));
                    myFiles.setRole(rs.getInt("ROLE"));
                    post.setFiles(myFiles);

                    postsList.add(post);
                }
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return postsList;
    }

    @Override
    public List<Post> getPostsByCatId(Integer catId) throws Exception {
        List<Post> postsList = new ArrayList<Post>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT P.ID POST_ID,\n" +
                "       P.HEADING,\n" +
                "       P.CONTEXT,\n" +
                "       P.CREATED_AT,\n" +
                "       P.LIKES,\n" +
                "       C.ID CAT_ID,\n" +
                "       C.CAT_NAME,\n" +
                "       A.ID AUTH_ID,\n" +
                "       A.NAME,\n" +
                "       A.SURNAME,\n" +
                "       F.PATH,\n" +
                "       F.ROLE\n" +
                "  FROM FILES F\n" +
                "       INNER JOIN POSTS P\n" +
                "          ON F.POST_ID = P.ID\n" +
                "       INNER JOIN AUTHORS A\n" +
                "          ON P.AUTHOR_ID = A.ID\n" +
                "       INNER JOIN CATEGORIES C\n" +
                "          ON P.CATEGORY_ID = C.ID\n" +
                " WHERE F.ROLE = 1 AND P.ACTIVE = 1 AND C.ID = "+catId+" ORDER BY P.CREATED_AT DESC";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {

                    Post post = new Post();
                    post.setId(rs.getInt("POST_ID"));
                    post.setHeading(rs.getString("HEADING"));
                    post.setContext(rs.getString("CONTEXT"));
                    post.setCreated_at(rs.getDate("CREATED_AT"));
                    post.setLikes(rs.getInt("LIKES"));

                    Author author = new Author();
                    author.setId(rs.getInt("AUTH_ID"));
                    author.setName(rs.getString("NAME"));
                    author.setSurname(rs.getString("SURNAME"));
                    post.setAuthor(author);

                    Categorie categorie = new Categorie();
                    categorie.setId(rs.getInt("CAT_ID"));
                    categorie.setCategoryName(rs.getString("CAT_NAME"));
                    post.setCategorie(categorie);

                    MyFiles myFiles = new MyFiles();
                    myFiles.setFileName(rs.getString("PATH"));
                    myFiles.setRole(rs.getInt("ROLE"));
                    post.setFiles(myFiles);

                    postsList.add(post);
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return postsList;
    }


    @Override
    public List<Post> getDateCategory() throws Exception {
        List<Post> postsList = new ArrayList<Post>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT P.ID POST_ID,\n" +
                "       P.HEADING,\n" +
                "       P.CONTEXT,\n" +
                "       P.CREATED_AT,\n" +
                "       P.LIKES,\n" +
                "       C.ID CAT_ID,\n" +
                "       C.CAT_NAME,\n" +
                "       A.ID AUTH_ID,\n" +
                "       A.NAME,\n" +
                "       A.SURNAME,\n" +
                "       F.PATH,\n" +
                "       F.ROLE\n" +
                "  FROM FILES F\n" +
                "       INNER JOIN POSTS P\n" +
                "          ON F.POST_ID = P.ID\n" +
                "       INNER JOIN AUTHORS A\n" +
                "          ON P.AUTHOR_ID = A.ID\n" +
                "       INNER JOIN CATEGORIES C\n" +
                "          ON P.CATEGORY_ID = C.ID\n" +
                " WHERE F.ROLE = 1 AND P.ACTIVE = 1 AND C.ID = 36 ORDER BY P.CREATED_AT DESC";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {

                    Post post = new Post();
                    post.setId(rs.getInt("POST_ID"));
                    post.setHeading(rs.getString("HEADING"));
                    post.setContext(rs.getString("CONTEXT"));
                    post.setCreated_at(rs.getDate("CREATED_AT"));
                    post.setLikes(rs.getInt("LIKES"));

                    Author author = new Author();
                    author.setId(rs.getInt("AUTH_ID"));
                    author.setName(rs.getString("NAME"));
                    author.setSurname(rs.getString("SURNAME"));
                    post.setAuthor(author);

                    Categorie categorie = new Categorie();
                    categorie.setId(rs.getInt("CAT_ID"));
                    categorie.setCategoryName(rs.getString("CAT_NAME"));
                    post.setCategorie(categorie);

                    MyFiles myFiles = new MyFiles();
                    myFiles.setFileName(rs.getString("PATH"));
                    myFiles.setRole(rs.getInt("ROLE"));
                    post.setFiles(myFiles);

                    postsList.add(post);
                }
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return postsList;
    }

    @Override
    public List<Post> getPostsByParentId(Integer parentId) throws Exception {
        List<Post> postsList = new ArrayList<Post>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT P.ID POST_ID,\n" +
                "                       P.HEADING,\n" +
                "                       P.CONTEXT,\n" +
                "                       P.CREATED_AT,\n" +
                "                       P.LIKES,\n" +
                "                       C.ID CAT_ID,\n" +
                "                       C.CAT_NAME,\n" +
                "                       A.ID AUTH_ID,\n" +
                "                       A.NAME,\n" +
                "                       A.SURNAME,\n" +
                "                       F.PATH,\n" +
                "                       F.ROLE\n" +
                "                  FROM FILES F\n" +
                "                       INNER JOIN POSTS P\n" +
                "                          ON F.POST_ID = P.ID\n" +
                "                       INNER JOIN AUTHORS A\n" +
                "                          ON P.AUTHOR_ID = A.ID\n" +
                "                       INNER JOIN CATEGORIES C\n" +
                "                          ON P.CATEGORY_ID = C.ID\n" +
                "                 WHERE F.ROLE = 1 AND P.ACTIVE = 1 AND C.PARENT_ID="+parentId+" ORDER BY P.CREATED_AT DESC";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {

                    Post post = new Post();
                    post.setId(rs.getInt("POST_ID"));
                    post.setHeading(rs.getString("HEADING"));
                    post.setContext(rs.getString("CONTEXT"));
                    post.setCreated_at(rs.getDate("CREATED_AT"));
                    post.setLikes(rs.getInt("LIKES"));

                    Author author = new Author();
                    author.setId(rs.getInt("AUTH_ID"));
                    author.setName(rs.getString("NAME"));
                    author.setSurname(rs.getString("SURNAME"));
                    post.setAuthor(author);

                    Categorie categorie = new Categorie();
                    categorie.setId(rs.getInt("CAT_ID"));
                    categorie.setCategoryName(rs.getString("CAT_NAME"));
                    post.setCategorie(categorie);

                    MyFiles myFiles = new MyFiles();
                    myFiles.setFileName(rs.getString("PATH"));
                    myFiles.setRole(rs.getInt("ROLE"));
                    post.setFiles(myFiles);

                    postsList.add(post);
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return postsList;
    }

    @Override
    public List<Post> getPostsForSlide() throws Exception {
        List<Post> postsList = new ArrayList<Post>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "  SELECT P.ID POST_ID,\n" +
                "         P.HEADING,\n" +
                "         P.CONTEXT,\n" +
                "         P.CREATED_AT,\n" +
                "         P.LIKES,\n" +
                "         C.ID CAT_ID,\n" +
                "         C.CAT_NAME,\n" +
                "         A.ID AUTH_ID,\n" +
                "         A.NAME,\n" +
                "         A.SURNAME,\n" +
                "         F.PATH,\n" +
                "         F.ROLE\n" +
                "    FROM FILES F\n" +
                "         INNER JOIN POSTS P\n" +
                "            ON F.POST_ID = P.ID\n" +
                "         INNER JOIN AUTHORS A\n" +
                "            ON P.AUTHOR_ID = A.ID\n" +
                "         INNER JOIN CATEGORIES C\n" +
                "            ON P.CATEGORY_ID = C.ID\n" +
                "   WHERE F.ROLE = 1 AND P.ACTIVE = 1" +
                "   ORDER BY P.CREATED_AT DESC";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {

                    Post post = new Post();
                    post.setId(rs.getInt("POST_ID"));
                    post.setHeading(rs.getString("HEADING"));
                    post.setContext(rs.getString("CONTEXT"));
                    post.setCreated_at(rs.getDate("CREATED_AT"));
                    post.setLikes(rs.getInt("LIKES"));

                    Author author = new Author();
                    author.setId(rs.getInt("AUTH_ID"));
                    author.setName(rs.getString("NAME"));
                    author.setSurname(rs.getString("SURNAME"));
                    post.setAuthor(author);

                    Categorie categorie = new Categorie();
                    categorie.setId(rs.getInt("CAT_ID"));
                    categorie.setCategoryName(rs.getString("CAT_NAME"));
                    post.setCategorie(categorie);

                    MyFiles myFiles = new MyFiles();
                    myFiles.setFileName(rs.getString("PATH"));
                    myFiles.setRole(rs.getInt("ROLE"));
                    post.setFiles(myFiles);

                    postsList.add(post);
                }
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return postsList;
    }

    @Override
    public boolean addPost(Post post) throws Exception {
        boolean isAdded = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String lastPost = "SELECT MAX(ID) ID FROM POSTS";

        String addToPostsTable = "INSERT INTO POSTS (ID,\n"
                + "                      HEADING,\n"
                + "                      CONTEXT,\n"
                + "                      CREATED_AT,\n"
                + "                      AUTHOR_ID,\n"
                + "                      CATEGORY_ID)\n"
                + "     VALUES(POSTS_SEQ.NEXTVAL,?,?,?,?,?)";

        String addToStatisticsTable = "INSERT INTO STATISTICS (ID,AUTHOR_ID,POST_ID) VALUES(STATISTICS_SEQ.NEXTVAL,?,?)";

        String addFilePostTable = "INSERT INTO FILES (ID,POST_ID,PATH,ROLE) VALUES(FILES_SEQ.NEXTVAL,?,?,?)";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(addToPostsTable);
                ps.setString(1, post.getHeading());
                ps.setString(2, post.getContext());

                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                ps.setDate(3, date);
                ps.setInt(4, post.getAuthor().getId());
                ps.setInt(5, post.getCategorie().getId());
                ps.execute();

                ps = c.prepareStatement(lastPost);
                rs = ps.executeQuery();
                int lastPostId = 0;
                if (rs.next()) {
                    lastPostId = rs.getInt("ID");
                }


                ps = c.prepareStatement(addToStatisticsTable);
                ps.setInt(1, post.getAuthor().getId());
                ps.setInt(2, lastPostId);
                ps.executeQuery();


                List<String> filesList = post.getFileName();

                int i = filesList.size();
                for (String filename : filesList) {
                    ps = c.prepareStatement(addFilePostTable);
                    ps.setInt(1, lastPostId);
                    ps.setString(2, filename);
                    generalFileSelector(ps, i, "add");
                    i--;
                    ps.executeQuery();
                }

                //  c.commit();
                isAdded = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, null, ps);
        }
        return isAdded;
    }

    @Override
    public Post getPostById(Integer id) throws Exception {
        Post post = new Post();
        Author author = new Author();
        Categorie categorie = new Categorie();
        List<String> filesList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String data = "SELECT A.ID AUTHOR_ID," +
                "       A.NAME,\n" +
                "       A.SURNAME,\n" +
                "       C.ID CAT_ID," +
                "       C.CAT_NAME,\n" +
                "       P.ID POST_ID,\n" +
                "       P.HEADING,\n" +
                "       P.CONTEXT,\n" +
                "       P.CREATED_AT,\n" +
                "       F.ROLE\n" +
                "  FROM FILES F\n" +
                "       INNER JOIN POSTS P\n" +
                "          ON F.POST_ID = P.ID\n" +
                "       INNER JOIN CATEGORIES C\n" +
                "          ON P.CATEGORY_ID = C.ID\n" +
                "       INNER JOIN AUTHORS A\n" +
                "          ON P.AUTHOR_ID = A.ID\n" +
                " WHERE F.ROLE = 1 AND F.ACTIVE = 1 AND P.ACTIVE = 1 AND P.ID = ?";

        String files = "SELECT ID PATH FROM FILES F WHERE F.ACTIVE = 1 AND F.POST_ID=?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(data);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    post.setId(rs.getInt("POST_ID"));
                    post.setHeading(rs.getString("HEADING"));
                    post.setContext(rs.getString("CONTEXT"));
                    post.setCreated_at(rs.getDate("CREATED_AT"));
                    author.setId(rs.getInt("AUTHOR_ID"));
                    author.setName(rs.getString("NAME"));
                    author.setSurname(rs.getString("SURNAME"));
                    post.setAuthor(author);
                    categorie.setId(rs.getInt("CAT_ID"));
                    categorie.setCategoryName(rs.getString("CAT_NAME"));
                    post.setCategorie(categorie);
                }
                ps = c.prepareStatement(files);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    filesList.add(rs.getString("PATH"));
                }
                post.setFileName(filesList);
//                post.setFileName(getFilesById(id));
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return post;
    }

    @Override
    public List<Post> getPostsByDate(String date) throws Exception {
        List<Post> postsList = new ArrayList<Post>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT P.ID POST_ID,\n" +
                "       P.HEADING,\n" +
                "       P.CONTEXT,\n" +
                "       P.CREATED_AT,\n" +
                "       P.LIKES,\n" +
                "       C.ID CAT_ID,\n" +
                "       C.CAT_NAME,\n" +
                "       A.ID AUTH_ID,\n" +
                "       A.NAME,\n" +
                "       A.SURNAME,\n" +
                "       F.PATH,\n" +
                "       F.ROLE\n" +
                "  FROM FILES F\n" +
                "       INNER JOIN POSTS P\n" +
                "          ON F.POST_ID = P.ID\n" +
                "       INNER JOIN AUTHORS A\n" +
                "          ON P.AUTHOR_ID = A.ID\n" +
                "       INNER JOIN CATEGORIES C\n" +
                "          ON P.CATEGORY_ID = C.ID\n" +
                " WHERE F.ROLE = 1 AND P.ACTIVE = 1 AND P.CREATED_AT = TO_DATE('"+date+"','YYYY-MM-DD') ORDER BY P.CREATED_AT DESC";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {

                    Post post = new Post();
                    post.setId(rs.getInt("POST_ID"));
                    post.setHeading(rs.getString("HEADING"));
                    post.setContext(rs.getString("CONTEXT"));
                    post.setCreated_at(rs.getDate("CREATED_AT"));
                    post.setLikes(rs.getInt("LIKES"));

                    Author author = new Author();
                    author.setId(rs.getInt("AUTH_ID"));
                    author.setName(rs.getString("NAME"));
                    author.setSurname(rs.getString("SURNAME"));
                    post.setAuthor(author);

                    Categorie categorie = new Categorie();
                    categorie.setId(rs.getInt("CAT_ID"));
                    categorie.setCategoryName(rs.getString("CAT_NAME"));
                    post.setCategorie(categorie);

                    MyFiles myFiles = new MyFiles();
                    myFiles.setFileName(rs.getString("PATH"));
                    myFiles.setRole(rs.getInt("ROLE"));
                    post.setFiles(myFiles);

                    postsList.add(post);
                }
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return postsList;
    }

    @Override
    public Post getPostByIdCl(Integer id) throws Exception {
        Post post = new Post();
        Author author = new Author();
        Categorie categorie = new Categorie();
        List<String> filesList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String data = "SELECT A.ID AUTHOR_ID," +
                "       A.NAME,\n" +
                "       A.SURNAME,\n" +
                "       C.ID CAT_ID," +
                "       C.CAT_NAME,\n" +
                "       P.ID POST_ID,\n" +
                "       P.HEADING,\n" +
                "       P.CONTEXT,\n" +
                "       P.CREATED_AT,\n" +
                "       F.ROLE,\n" +
                "       P.LIKES,\n" +
                "       P.VIEWS\n" +
                "  FROM FILES F\n" +
                "       INNER JOIN POSTS P\n" +
                "          ON F.POST_ID = P.ID\n" +
                "       INNER JOIN CATEGORIES C\n" +
                "          ON P.CATEGORY_ID = C.ID\n" +
                "       INNER JOIN AUTHORS A\n" +
                "          ON P.AUTHOR_ID = A.ID\n" +
                " WHERE F.ROLE = 1 AND F.ACTIVE = 1 AND P.ACTIVE = 1 AND P.ID = ?";

        String files = "SELECT ID PATH FROM FILES F WHERE F.ACTIVE = 1 AND F.POST_ID=?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(data);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    post.setId(rs.getInt("POST_ID"));
                    post.setHeading(rs.getString("HEADING"));
                    post.setContext(rs.getString("CONTEXT"));
                    post.setCreated_at(rs.getDate("CREATED_AT"));
                    post.setLikes(rs.getInt("LIKES"));
                    post.setViews(rs.getInt("VIEWS"));
                    author.setId(rs.getInt("AUTHOR_ID"));
                    author.setName(rs.getString("NAME"));
                    author.setSurname(rs.getString("SURNAME"));
                    post.setAuthor(author);
                    categorie.setId(rs.getInt("CAT_ID"));
                    categorie.setCategoryName(rs.getString("CAT_NAME"));
                    post.setCategorie(categorie);
                }
                ps = c.prepareStatement(files);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    filesList.add(rs.getString("PATH"));
                }
                post.setFileName(filesList);
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return post;
    }

    @Override
    public List<MyFiles> getFilesById(Integer id) throws Exception {
        List<MyFiles> filesList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String files = "SELECT ID,PATH FROM FILES F WHERE F.ACTIVE = 1 AND F.POST_ID=? AND PATH != 'none'";
        try {
            c = DbHelper.getConnetion();

            if (c != null) {
                ps = c.prepareStatement(files);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    MyFiles file = new MyFiles();
                    file.setId(rs.getInt("ID"));
                    file.setFileName(rs.getString("PATH"));
                    filesList.add(file);
                }
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return filesList;
    }

    @Override
    public boolean deletePostFile(MyFiles myFile) throws Exception {
        boolean isDeleted = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE FILES SET ACTIVE = 0 WHERE ID = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, myFile.getId());
                ps.execute();
                //   c.commit();
                isDeleted = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, null, ps);
        }
        return isDeleted;
    }

    @Override
    public boolean selectPostCover(MyFiles myFile) throws Exception {
        boolean isSelected = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String selectPostId = "SELECT POST_ID FROM FILES WHERE ID = ?";
        String sql0 = "UPDATE FILES SET ROLE = 0 WHERE ROLE = 1 AND POST_ID = ?";
        String sql = "UPDATE FILES SET ROLE = 1 WHERE ID = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {

                ps = c.prepareStatement(selectPostId);
                ps.setInt(1, myFile.getId());
                rs = ps.executeQuery();
                int postId = 0;
                if (rs.next()) {
                    postId = rs.getInt("POST_ID");
                }

                ps = c.prepareStatement(sql0);
                ps.setInt(1, postId);
                ps.execute();
                //     c.commit();

                ps = c.prepareStatement(sql);
                ps.setInt(1, myFile.getId());
                ps.execute();
                //   c.commit();
                isSelected = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, null, ps);
        }
        return isSelected;
    }

    @Override
    public boolean updatePost(Post post) throws Exception {
        boolean isUpdated = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String addToPostsTable = "UPDATE POSTS SET"
                + "                      HEADING = ?,\n"
                + "                      CONTEXT = ?,\n"
                + "                      UPDATED_AT = ?,\n"
                + "                      AUTHOR_ID = ?,\n"
                + "                      CATEGORY_ID = ?\n"
                + "                      WHERE ID = ?";

        String addToStatisticsTable = "UPDATE STATISTICS SET AUTHOR_ID = ? WHERE POST_ID = ?";

        String addFilePostTable = "INSERT INTO FILES (ID,POST_ID,PATH,ROLE) VALUES(FILES_SEQ.NEXTVAL,?,?,?)";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(addToPostsTable);
                ps.setString(1, post.getHeading());
                ps.setString(2, post.getContext());

                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                ps.setDate(3, date);
                ps.setInt(4, post.getAuthor().getId());
                ps.setInt(5, post.getCategorie().getId());
                ps.setInt(6, post.getId());
                ps.execute();


                ps = c.prepareStatement(addToStatisticsTable);
                ps.setInt(1, post.getAuthor().getId());
                ps.setInt(2, post.getId());
                ps.executeQuery();


                List<String> filesList = post.getFileName();

                int i = filesList.size();
                for (String filename : filesList) {
                    ps = c.prepareStatement(addFilePostTable);
                    ps.setInt(1, post.getId());
                    ps.setString(2, filename);
                    generalFileSelector(ps, i, "upd");
                    i--;
                    ps.executeQuery();
                }

                //   c.commit();
                isUpdated = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, null, ps);
        }
        return isUpdated;
    }

    @Override
    public boolean deletePost(Post post) throws Exception {
        boolean isDeleted = false;
        Connection c = null;
        PreparedStatement ps = null;
        String deleteFromPosts = "UPDATE POSTS SET ACTIVE = 0 WHERE ID = ?";
        String deleteFromStatistics = "UPDATE STATISTICS SET ACTIVE = 0 WHERE POST_ID = ?";
        String deleteFromFiles = "UPDATE FILES SET ACTIVE = 0 WHERE POST_ID = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                Integer postId = post.getId();
                ps = c.prepareStatement(deleteFromPosts);
                ps.setLong(1, postId);
                ps.execute();

                ps = c.prepareStatement(deleteFromStatistics);
                ps.setLong(1, postId);
                ps.execute();

                ps = c.prepareStatement(deleteFromFiles);
                ps.setLong(1, postId);
                ps.execute();

                //   c.commit();
                isDeleted = true;

            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, null, ps);
        }
        return isDeleted;
    }

    @Override
    public List<Post> getSearchedData(String searchSql) throws Exception {
        List<Post> postsList = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT P.ID POST_ID,\n" +
                "       P.HEADING,\n" +
                "       P.CONTEXT,\n" +
                "       P.CREATED_AT,\n" +
                "       F.PATH,\n" +
                "       F.ROLE,\n" +
                "       P.LIKES,\n" +
                "       P.VIEWS,\n" +
                "       A.ID AUTHOR_ID,\n" +
                "       A.NAME AUTHOR_NAME,\n" +
                "       A.SURNAME AUTHOR_SURNAME,\n" +
                "       C.ID CAT_ID,\n" +
                "       C.CAT_NAME\n" +
                "  FROM FILES F\n" +
                "       INNER JOIN POSTS P\n" +
                "          ON F.POST_ID = P.ID\n" +
                "       INNER JOIN AUTHORS A\n" +
                "          ON P.AUTHOR_ID = A.ID\n" +
                "       INNER JOIN CATEGORIES C\n" +
                "          ON P.CATEGORY_ID = C.ID\n" +
                " WHERE P.ACTIVE = 1 AND F.ROLE = 1 " + searchSql;

        System.out.println(sql);
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {

                    Post post = new Post();
                    post.setId(rs.getInt("POST_ID"));
                    post.setHeading(rs.getString("HEADING"));
                    post.setContext(rs.getString("CONTEXT"));
                    post.setCreated_at(rs.getDate("CREATED_AT"));
                    post.setLikes(rs.getInt("LIKES"));
                    post.setViews(rs.getInt("VIEWS"));

                    Author author = new Author();
                    author.setId(rs.getInt("AUTHOR_ID"));
                    author.setName(rs.getString("AUTHOR_NAME"));
                    author.setSurname(rs.getString("AUTHOR_SURNAME"));
                    post.setAuthor(author);

                    Categorie categorie = new Categorie();
                    categorie.setId(rs.getInt("CAT_ID"));
                    categorie.setCategoryName(rs.getString("CAT_NAME"));
                    post.setCategorie(categorie);

                    MyFiles myFiles = new MyFiles();
                    myFiles.setFileName(rs.getString("PATH"));
                    myFiles.setRole(rs.getInt("ROLE"));
                    post.setFiles(myFiles);

                    postsList.add(post);
                }
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return postsList;
    }

    public void generalFileSelector(PreparedStatement ps, int loop, String op) throws Exception {
        if (op == "upd") {
            ps.setInt(3, 0);
        } else {
            if (loop == 1) {
                ps.setInt(3, 1);
            } else {
                ps.setInt(3, 0);
            }
        }

    }

}
