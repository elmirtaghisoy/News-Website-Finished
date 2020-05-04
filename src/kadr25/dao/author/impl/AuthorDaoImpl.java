package kadr25.dao.author.impl;

import kadr25.DbHelper;
import kadr25.dao.author.dao.AuthorDao;
import kadr25.model.Author;
import kadr25.model.Categorie;
import kadr25.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {

    @Override
    public List<Author> getAuthors() throws Exception {
        List<Author> authorList = new ArrayList<Author>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID,\n" +
                "       NAME,\n" +
                "       SURNAME,\n" +
                "       PROFESSION,\n" +
                "       EMAIL,\n" +
                "       PHONE\n" +
                "  FROM AUTHORS\n" +
                " WHERE ACTIVE = 1";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Author author = new Author();
                    author.setId(rs.getInt("ID"));
                    author.setName(rs.getString("NAME"));
                    author.setSurname(rs.getString("SURNAME"));
                    author.setProfession(rs.getString("PROFESSION"));
                    author.setEmail(rs.getString("EMAIL"));
                    author.setPhone(rs.getString("PHONE"));
                    authorList.add(author);
                }
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return authorList;
    }

    @Override
    public boolean addAuthor(Author author) throws Exception {
        boolean isAdded = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String lastAuthor = "SELECT MAX(ID) ID FROM AUTHORS";

        String addToAuthorsTable = "INSERT INTO AUTHORS (ID,\n"
                + "                      NAME,\n"
                + "                      SURNAME,\n"
                + "                      PROFESSION,\n"
                + "                      EMAIL,\n"
                + "                      PHONE)\n"
                + "     VALUES(AUTHORS_SEQ.NEXTVAL,?,?,?,?,?)";

        String addToStatisticsTable = "INSERT INTO STATISTICS (ID,AUTHOR_ID) VALUES(STATISTICS_SEQ.NEXTVAL,?)";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(addToAuthorsTable);
                ps.setString(1, author.getName());
                ps.setString(2, author.getSurname());
                ps.setString(3, author.getProfession());
                ps.setString(4, author.getEmail());
                ps.setString(5, author.getPhone());
                ps.execute();

                ps = c.prepareStatement(lastAuthor);
                rs = ps.executeQuery();
                int authorId = 0;
                if (rs.next()) {
                    authorId = rs.getInt("ID");
                }

                ps = c.prepareStatement(addToStatisticsTable);
                ps.setInt(1, authorId);
                ps.execute();
             //  c.commit();
                isAdded = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return isAdded;
    }

    @Override
    public Author getAuthorById(Integer id) throws Exception {
        Author author = new Author();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT ID,\n" +
                "       NAME,\n" +
                "       SURNAME,\n" +
                "       PROFESSION,\n" +
                "       EMAIL,\n" +
                "       PHONE\n" +
                "  FROM AUTHORS\n" +
                " WHERE ID = ? AND ACTIVE = 1";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    author.setId(rs.getInt("ID"));
                    author.setName(rs.getString("NAME"));
                    author.setSurname(rs.getString("SURNAME"));
                    author.setProfession(rs.getString("PROFESSION"));
                    author.setEmail(rs.getString("EMAIL"));
                    author.setPhone(rs.getString("PHONE"));
                }
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }

        return author;
    }

    @Override
    public boolean updateAuthor(Author author) throws Exception {
        boolean isUpdated = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE AUTHORS SET NAME = ?,SURNAME = ?,PROFESSION = ?,PHONE = ?,EMAIL = ? WHERE ID = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, author.getName());
                ps.setString(2, author.getSurname());
                ps.setString(3, author.getProfession());
                ps.setString(4, author.getPhone());
                ps.setString(5, author.getEmail());
                ps.setLong(6, author.getId());
                ps.execute();
           //     c.commit();
                isUpdated = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, null, ps);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAuthor(Author author) throws Exception {
        boolean isDeleted = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE AUTHORS SET ACTIVE = 0 WHERE ID = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, author.getId());
                ps.execute();
              //  c.commit();
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

}
