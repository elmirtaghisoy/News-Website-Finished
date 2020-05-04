package kadr25.web;

import kadr25.DbHelper;
import kadr25.dao.categorie.dao.CategorieDao;
import kadr25.dao.categorie.impl.CategorieDaoImpl;
import kadr25.model.Author;
import kadr25.model.Categorie;
import kadr25.service.categorie.impl.CategorieService;
import kadr25.service.categorie.service.CategorieServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "CategoriesServlet", urlPatterns = "/cc")
public class CategoriesServlet extends HttpServlet {
    CategorieDao categorieDao = new CategorieDaoImpl();
    CategorieService categorieService = new CategorieServiceImpl(categorieDao);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter pw = response.getWriter();
            String action = null;
            String address = null;
            boolean isExistAdress = false;
            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }

            if (action.equalsIgnoreCase("getCategories")) {
                isExistAdress = true;
                List<Categorie> categoriesListForMain = categorieService.getMainCategories();
                List<Categorie> allCategorieList = categorieService.getCategories();
                request.setAttribute("allCategories", allCategorieList);
                request.setAttribute("mainCategories", categoriesListForMain);
                address = "/WEB-INF/pages/categories.jsp";
            } else if (action.equalsIgnoreCase("getMainCategories")) {
                isExistAdress = true;
                List<Categorie> categoriesListForMain = categorieService.getMainCategories();
                request.setAttribute("mainCategories", categoriesListForMain);
                address = "/WEB-INF/clientPages/static/header.jsp";
            } else if (action.equalsIgnoreCase("getCategoriesForClient")) {
                isExistAdress = true;
                List<Categorie> subCategories = categorieService.getSubCategories();
                request.setAttribute("subCategories", subCategories);
                address = "/WEB-INF/clientPages/views/otherCategories.jsp";
            } else if (action.equalsIgnoreCase("getSubCategories")) {
                isExistAdress = true;
                Integer parentId = Integer.parseInt(request.getParameter("parentId"));
                List<Categorie> subCategoriesList = categorieService.getSubCategories(parentId);
                request.setAttribute("subCategories", subCategoriesList);
                request.setAttribute("catName", categorieService.getCategoryName(parentId));
                address = "/WEB-INF/pages/views/subCategories.jsp";
            } else if (action.equalsIgnoreCase("getSubCategoriesClient")) {
                isExistAdress = true;
                Integer parentId = Integer.parseInt(request.getParameter("parentId"));
                List<Categorie> subCategoriesList = categorieService.getSubCategories(parentId);
                request.setAttribute("subCategories", subCategoriesList);
                address = "/WEB-INF/clientPages/views/subCategories.jsp";
            } else if (action.equalsIgnoreCase("getCategorieByIdForBRC")) {
                isExistAdress = true;
                Integer catId = Integer.parseInt(request.getParameter("catId"));
                Categorie categorie = categorieService.getCategorieById(catId);
                request.setAttribute("categorie", categorie);
                address = "/WEB-INF/clientPages/views/breadcumb.jsp";
            } else if (action.equalsIgnoreCase("getSubCategoriesForBRC")) {
                String myReq = request.getParameter("parentId");
                if (myReq != null) {
                    Integer catId = Integer.parseInt(myReq);
                    List<Categorie> subCategoriesList = categorieService.getSubCategories(catId);
                    isExistAdress = true;
                    request.setAttribute("subCategories", subCategoriesList);
                }
                address = "/WEB-INF/clientPages/views/subCategoriesInBRC.jsp";
            } else if (action.equalsIgnoreCase("addCategorie")) {
                String catName = request.getParameter("catName");
                Categorie categorie = new Categorie();
                categorie.setCategoryName(catName);
                boolean isAdded = categorieService.addCategorie(categorie);
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("getUnUsedCategories")) {
                isExistAdress = true;
                String parentName = request.getParameter("parentName");
                List<Categorie> unUsedSubCategorieList = categorieService.getUnUsedSubCategories(parentName);
                request.setAttribute("unUsedSubCategorieList", unUsedSubCategorieList);
                address = "/WEB-INF/pages/views/addSubCategorie.jsp";
            } else if (action.equalsIgnoreCase("addSubCategorie")) {
                String catName = request.getParameter("catName");
                Integer parentId = Integer.parseInt(request.getParameter("parentId"));
                Categorie categorie = new Categorie();
                categorie.setCategoryName(catName);
                categorie.setParent_id(parentId);

                boolean isUpdated = categorieService.addSubCategory(categorie);
                if (isUpdated) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("updateCategoryName")) {
                Integer id = Integer.parseInt(request.getParameter("parentId"));
                String name = request.getParameter("catName");
                Categorie categorie = new Categorie();
                categorie.setId(id);
                categorie.setCategoryName(name);
                boolean isUpdated = categorieService.updateCategory(categorie);
                if (isUpdated) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("deleteCategory")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                Categorie categorie = new Categorie();
                categorie.setId(id);
                categorie.setActive(0);
                boolean isUpdated = categorieService.deleteCategory(categorie);
                if (isUpdated) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("addToMainMenu")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                Categorie categorie = new Categorie();
                categorie.setId(id);
                boolean isAdded = categorieService.addCategoryToMenu(categorie);
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("deleteFromMainMenu")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                Categorie categorie = new Categorie();
                categorie.setId(id);
                boolean isAdded = categorieService.deleteCategoryFromMenu(categorie);
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
            } else if (action.equalsIgnoreCase("updateSubsOfParent")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                Categorie categorie = new Categorie();
                categorie.setId(id);
                boolean isAdded = categorieService.updateSubsOfParent(categorie);
                if (isAdded) {
                    pw.write("success");
                } else {
                    pw.write("fail");
                }
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
