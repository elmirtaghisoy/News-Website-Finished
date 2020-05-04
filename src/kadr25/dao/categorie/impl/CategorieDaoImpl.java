package kadr25.dao.categorie.impl;

import kadr25.DbHelper;
import kadr25.dao.categorie.dao.CategorieDao;
import kadr25.model.Categorie;
import kadr25.util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategorieDaoImpl implements CategorieDao {

    @Override
    public List<Categorie> getCategories() throws Exception {
        List<Categorie> categoriesList = new ArrayList<Categorie>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID, CHILD, PARENT_ID, CAT_NAME, ROLE FROM CATEGORIES\n" +
                "WHERE ACTIVE=1 ORDER BY PARENT_ID ";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Categorie categorie = new Categorie();
                    categorie.setId(rs.getInt("ID"));
                    categorie.setParent_id(rs.getInt("PARENT_ID"));
                    categorie.setCategoryName(rs.getString("CAT_NAME"));
                    categorie.setRole(rs.getInt("ROLE"));
                    categorie.setChild(rs.getInt("CHILD"));
                    categoriesList.add(categorie);
                }
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return categoriesList;
    }

    @Override
    public Categorie getCategorieById(Integer id) throws Exception {
        Categorie categorie = new Categorie();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID, CHILD, PARENT_ID, CAT_NAME, ROLE FROM CATEGORIES\n" +
                "WHERE ACTIVE=1 AND ID= " + id + " ORDER BY PARENT_ID";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    categorie.setId(rs.getInt("ID"));
                    categorie.setParent_id(rs.getInt("PARENT_ID"));
                    categorie.setCategoryName(rs.getString("CAT_NAME"));
                    categorie.setRole(rs.getInt("ROLE"));
                    categorie.setChild(rs.getInt("CHILD"));
                }
            } else {
                System.out.println("Error in connection !!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, rs, ps);
        }
        return categorie;
    }

    @Override
    public boolean addCategorie(Categorie categorie) throws Exception {
        boolean isAdded = false;
        Connection c = null;
        PreparedStatement ps = null;
        String addToAuthorsTable = "INSERT INTO CATEGORIES (ID,CAT_NAME,PARENT_ID,CHILD) VALUES(CATEGORIES_SEQ.NEXTVAL,?,'0','0')";

        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(addToAuthorsTable);
                ps.setString(1, categorie.getCategoryName());
                ps.execute();
                //     c.commit();
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
    public boolean addSubCategory(Categorie categorie) throws Exception {
        boolean isAdded = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE CATEGORIES SET PARENT_ID = ? WHERE CAT_NAME = ?";
        String sql2 = "UPDATE CATEGORIES SET CHILD = 1 WHERE ID =?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, categorie.getParent_id());
                ps.setString(2, categorie.getCategoryName());
                ps.execute();

                ps = c.prepareStatement(sql2);
                ps.setInt(1, categorie.getParent_id());
                ps.execute();

                isAdded = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, null, ps);
        }
        return isAdded;
    }

    @Override
    public boolean updateCategory(Categorie categorie) throws Exception {
        boolean isUpdated = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE CATEGORIES SET CAT_NAME = ? WHERE ID = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, categorie.getCategoryName());
                ps.setInt(2, categorie.getId());
                ps.execute();
                //    c.commit();
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
    public boolean deleteCategory(Categorie categorie) throws Exception {
        boolean isDeleted = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE CATEGORIES SET ACTIVE = ? WHERE ID = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, categorie.getActive());
                ps.setLong(2, categorie.getId());
                ps.execute();
                //    c.commit();
                isDeleted = deleteCategoryFromPost(categorie.getId());
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

    public boolean deleteCategoryFromPost(Integer id) throws Exception {
        boolean isDeleted = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE POSTS SET CATEGORY_ID = 1 WHERE CATEGORY_ID =" + id + " ";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.execute();
                //    c.commit();
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
    public boolean addCategoryToMenu(Categorie categorie) throws Exception {
        boolean isAdded = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE CATEGORIES SET PARENT_ID = ?, ROLE=? WHERE ID = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, 0);
                ps.setInt(2, 1);
                ps.setInt(3, categorie.getId());
                ps.execute();
                //    c.commit();
                isAdded = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, null, ps);
        }
        return isAdded;
    }

    @Override
    public boolean deleteCategoryFromMenu(Categorie categorie) throws Exception {
        boolean isAdded = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE CATEGORIES SET ROLE=? WHERE ID = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, 0);
                ps.setInt(2, categorie.getId());
                ps.execute();
                //    c.commit();
                isAdded = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, null, ps);
        }
        return isAdded;
    }

    @Override
    public boolean updateSubsOfParent(Categorie categorie) throws Exception {
        boolean isUpdated = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql1 = "UPDATE CATEGORIES SET PARENT_ID = 0 WHERE ID = ?";
        try {
            c = DbHelper.getConnetion();
            if (c != null) {
                ps = c.prepareStatement(sql1);
                ps.setInt(1, categorie.getId());
                ps.execute();
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

}
