package kadr25.dao.categorie.dao;

import kadr25.model.Categorie;

import java.util.List;

public interface CategorieDao {
    List<Categorie> getCategories() throws Exception;

    Categorie getCategorieById(Integer id) throws Exception;

    boolean addCategorie(Categorie categorie) throws Exception;

    boolean addSubCategory(Categorie categorie) throws Exception;

    boolean updateCategory(Categorie categorie) throws Exception;

    boolean deleteCategory(Categorie categorie) throws Exception;

    boolean addCategoryToMenu(Categorie categorie) throws Exception;

    boolean deleteCategoryFromMenu(Categorie categorie) throws Exception;

    boolean updateSubsOfParent(Categorie categorie) throws Exception;

}
