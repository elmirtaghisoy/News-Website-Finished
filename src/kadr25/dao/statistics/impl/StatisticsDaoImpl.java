package kadr25.dao.statistics.impl;

import kadr25.DbHelper;
import kadr25.dao.statistics.dao.StatisticsDao;
import kadr25.model.Author;
import kadr25.model.Post;
import kadr25.model.Statistics;
import kadr25.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatisticsDaoImpl implements StatisticsDao {
    @Override
    public List<Statistics> getGeneralAuthorsStatistics() throws Exception {
        List<Statistics> statisticsList = new ArrayList<Statistics>();
        Connection c = null;
        PreparedStatement generalPS = null, additionalPS = null;
        ResultSet generalRS = null, additionalRS = null;
        String sql = "SELECT A.ID AUTHOR_ID,\n" +
                "       A.NAME,\n" +
                "       A.SURNAME,\n" +
                "       A.PROFESSION,\n" +
                "       A.PHONE,\n" +
                "       A.EMAIL,\n" +
                "       P.ID POST_ID\n" +
                "       FROM STATISTICS S\n" +
                "FULL JOIN AUTHORS A\n" +
                "ON S.AUTHOR_ID = A.ID\n" +
                "FULL JOIN POSTS P\n" +
                "ON S.POST_ID = P.ID WHERE A.ACTIVE = 1 ORDER BY A.ID ASC";

        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                generalPS = c.prepareStatement(sql);
                generalRS = generalPS.executeQuery();
                Integer authorIdLast = 0;
                while (generalRS.next()) {
                    Statistics statistics = new Statistics();
                    Author author = new Author();
                    Integer authorId = generalRS.getInt("AUTHOR_ID");
                    if (!authorId.equals(authorIdLast)) {
                        author.setId(authorId);
                        author.setName(generalRS.getString("NAME"));
                        author.setSurname(generalRS.getString("SURNAME"));
                        author.setProfession(generalRS.getString("PROFESSION"));
                        author.setPhone(generalRS.getString("PHONE"));
                        author.setEmail(generalRS.getString("EMAIL"));
                        statistics.setAuthor(author);
                        Post post = new Post();
                        post.setId(generalRS.getInt("POST_ID"));

                        additionalPS = c.prepareStatement(" SELECT SUM(P.LIKES) LIKES \n" +
                                "   FROM POSTS P INNER JOIN AUTHORS A ON P.AUTHOR_ID = A.ID\n" +
                                " WHERE A.ACTIVE = 1 AND A.ID =" + authorId + "");
                        additionalRS = additionalPS.executeQuery();
                        additionalRS.next();
                        post.setLikes(additionalRS.getInt("LIKES"));

                        additionalPS = c.prepareStatement("  SELECT SUM(P.VIEWS) VIEWS\n" +
                                "   FROM POSTS P INNER JOIN AUTHORS A ON P.AUTHOR_ID = A.ID\n" +
                                " WHERE A.ACTIVE = 1 AND A.ID =" + authorId + "");
                        additionalRS = additionalPS.executeQuery();
                        additionalRS.next();
                        post.setViews(additionalRS.getInt("VIEWS"));

                        additionalPS = c.prepareStatement("   SELECT COUNT(P.ID) POST_COUNT\n" +
                                "   FROM POSTS P INNER JOIN AUTHORS A ON P.AUTHOR_ID = A.ID\n" +
                                " WHERE A.ACTIVE = 1 AND A.ID =" + authorId + "");
                        additionalRS = additionalPS.executeQuery();
                        additionalRS.next();
                        statistics.setPostCounts(additionalRS.getInt("POST_COUNT"));

                        statistics.setPost(post);
                        statisticsList.add(statistics);
                        authorIdLast = authorId;
                    } else continue;
                }
            } else {
                System.out.println("Error in connection !!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, additionalRS, additionalPS);
            JdbcUtility.close(c, generalRS, generalPS);
        }
        return statisticsList;
    }

    @Override
    public List<Statistics> getStatisticsByMonth(Integer month, Integer id) throws Exception {
        List<Statistics> statisticsListByMonth = new ArrayList<Statistics>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String byMonth = " AND CREATED_AT >=TO_DATE('01." + month + ".2020','DD.MM.YYYY') AND CREATED_AT <= TO_DATE('01." + (month + 1) + ".2020','DD.MM.YYYY')";

        String sql = "SELECT A.ID AUTHOR_ID,    \n" +
                "       P.HEADING,\n" +
                "       P.ID POST_ID,\n" +
                "       P.CREATED_AT,\n" +
                "       P.LIKES,\n" +
                "       P.VIEWS\n" +
                "  FROM POSTS P INNER JOIN AUTHORS A\n" +
                "  ON P.AUTHOR_ID = A.ID \n" +
                " WHERE P.ACTIVE = 1 AND A.ID = ?" + byMonth;
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Statistics statistics = new Statistics();
                    Author author = new Author();
                    Integer authorId = rs.getInt("AUTHOR_ID");
                        author.setId(authorId);
                        statistics.setAuthor(author);

                        Post post = new Post();
                        post.setId(rs.getInt("POST_ID"));
                        post.setHeading(rs.getString("HEADING"));
                        post.setLikes(rs.getInt("LIKES"));
                        post.setViews(rs.getInt("VIEWS"));
                        post.setCreated_at(rs.getDate("CREATED_AT"));
                        statistics.setPost(post);
                        statisticsListByMonth.add(statistics);
                }
            } else {
                System.out.println("Error in connection !!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return statisticsListByMonth;
    }
}
