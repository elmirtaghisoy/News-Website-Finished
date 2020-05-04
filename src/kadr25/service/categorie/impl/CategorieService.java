package kadr25.service.categorie.impl;

import kadr25.model.Categorie;

import java.util.List;

public interface CategorieService {
    List<Categorie> getAllCategories() throws Exception;

    List<Categorie> getCategories() throws Exception;

    Categorie getCategorieById(Integer id) throws Exception;

    List<Categorie> getMainCategories() throws Exception;

    List<Categorie> getSubCategories(Integer parentId) throws Exception;
    
    List<Categorie> getSubCategories() throws Exception;

    List<Categorie> getUnUsedSubCategories(String parentName) throws Exception;

    String getCategoryName(Integer id) throws Exception;

    boolean addCategorie(Categorie categorie) throws Exception;

    boolean addSubCategory(Categorie categorie) throws Exception;

    boolean updateCategory(Categorie categorie) throws Exception;

    boolean deleteCategory(Categorie categorie) throws Exception;

    boolean addCategoryToMenu(Categorie categorie) throws Exception;

    boolean deleteCategoryFromMenu(Categorie categorie) throws Exception;

    boolean updateSubsOfParent(Categorie categorie) throws Exception;
}
