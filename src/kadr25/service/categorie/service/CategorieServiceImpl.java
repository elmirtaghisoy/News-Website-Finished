package kadr25.service.categorie.service;

import kadr25.dao.categorie.dao.CategorieDao;
import kadr25.model.Categorie;
import kadr25.service.categorie.impl.CategorieService;

import java.util.ArrayList;
import java.util.List;

public class CategorieServiceImpl implements CategorieService {
    private CategorieDao categorieDao;

    public CategorieServiceImpl(CategorieDao categorieDao) {
        this.categorieDao = categorieDao;
    }

    @Override
    public List<Categorie> getAllCategories() throws Exception {
        return categorieDao.getCategories();
    }

    @Override
    public List<Categorie> getCategories() throws Exception {
        List<Categorie> getCategories = new ArrayList<>();
        List<Categorie> categoriesList = categorieDao.getCategories();
        for (Categorie categorie : categoriesList) {
            if (categorie.getRole() != 1) {
                getCategories.add(categorie);
            }
        }
        return getCategories;
    }

    @Override
    public Categorie getCategorieById(Integer id) throws Exception {
        return categorieDao.getCategorieById(id);
    }

    @Override
    public List<Categorie> getMainCategories() throws Exception {
        List<Categorie> mainCategorieList = new ArrayList<>();
        List<Categorie> categoriesList = categorieDao.getCategories();
//        Burada categorie.getId() de "Diger" kateqoriyasi nezerde tutulur.
        for (Categorie categorie : categoriesList) {
            if (categorie.getRole() == 1 && categorie.getId() != 33) {
                mainCategorieList.add(categorie);
            }
        }
        return mainCategorieList;
    }

    @Override
    public List<Categorie> getSubCategories(Integer parentId) throws Exception {
        List<Categorie> subCategories = new ArrayList<>();
        List<Categorie> categoriesList = categorieDao.getCategories();
        for (Categorie categorie : categoriesList) {
            if (categorie.getParent_id() == parentId) {
                subCategories.add(categorie);
            } else
                continue;
        }
        return subCategories;
    }

    public List<Categorie> getSubCategories() throws Exception {
        List<Categorie> subCategories = new ArrayList<>();
        List<Categorie> categoriesList = categorieDao.getCategories();
        for (Categorie categorie : categoriesList) {
            if (categorie.getParent_id() != 0) {
                subCategories.add(categorie);
            } else
                continue;
        }
        return subCategories;
    }

    @Override
    public List<Categorie> getUnUsedSubCategories(String parentName) throws Exception {
        List<Categorie> subCategories = new ArrayList<>();
        List<Categorie> categoriesList = categorieDao.getCategories();
        for (Categorie categorie : categoriesList) {
            if (categorie.getParent_id() == 0 && !categorie.getCategoryName().equals(parentName) && categorie.getRole() ==0) {
                subCategories.add(categorie);
            } else
                continue;
        }
        return subCategories;
    }

    @Override
    public String getCategoryName(Integer id) throws Exception {
        String catName = null;
        List<Categorie> categoriesList = categorieDao.getCategories();
        for (Categorie categorie : categoriesList) {
            if (categorie.getId() == id) {
                catName = categorie.getCategoryName();
            }
        }
        return catName;
    }

    @Override
    public boolean addCategorie(Categorie categorie) throws Exception {
        return categorieDao.addCategorie(categorie);
    }

    @Override
    public boolean addSubCategory(Categorie categorie) throws Exception {
        return categorieDao.addSubCategory(categorie);
    }

    @Override
    public boolean updateCategory(Categorie categorie) throws Exception {
        return categorieDao.updateCategory(categorie);
    }

    @Override
    public boolean deleteCategory(Categorie categorie) throws Exception {
        return categorieDao.deleteCategory(categorie);
    }


    public boolean checkMainCategory(Integer id) throws Exception {
        List<Categorie> categoriesList = categorieDao.getCategories();
        boolean isExist = false;
        for (Categorie categorie : categoriesList) {
            if (categorie.getId() == id) {
                if (categorie.getRole() == 0 && categorie.getParent_id()==0){
                    isExist = true;
                }
            }

        }
        return isExist;
    }

    @Override
    public boolean addCategoryToMenu(Categorie categorie) throws Exception {
        if (checkMainCategory(categorie.getId()))
            return categorieDao.addCategoryToMenu(categorie);
        else return false;
    }

    @Override
    public boolean deleteCategoryFromMenu(Categorie categorie) throws Exception {
        return categorieDao.deleteCategoryFromMenu(categorie);
    }

    @Override
    public boolean updateSubsOfParent(Categorie categorie) throws Exception {
        return categorieDao.updateSubsOfParent(categorie);
    }
}
