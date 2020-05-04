package kadr25.web;

import kadr25.dao.post.dao.PostDao;
import kadr25.dao.post.impl.PostDaoImpl;
import kadr25.model.Author;
import kadr25.model.Categorie;
import kadr25.model.MyFiles;
import kadr25.model.Post;
import kadr25.service.posts.impl.PostsService;
import kadr25.service.posts.service.PostsServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "UploadServletADD", urlPatterns = "/uc")
public class UploadServletADD extends HttpServlet {

    PostDao postDao = new PostDaoImpl();
    PostsService postsService = new PostsServiceImpl(postDao);

    //    UPLOAD
    private static final String UPLOAD_DIRECTORY = "uploaded";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 300;  //3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 100;  //10MB
    private static final int REQUEST_SIZE = 1024 * 1024 * 500;   //50MB
//    UPLOAD

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> filesList = new ArrayList<>();
        String filePath = " ";
        String fileName = " ";
        String newFileName = " ";
        String opType = " ";

        if (!ServletFileUpload.isMultipartContent(request)) {
            response.getWriter().println("Does not Support");
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(REQUEST_SIZE);
        System.out.println(getServletContext().getRealPath(""));

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        Post post = new Post();
        Categorie categorie = new Categorie();
        Author author = new Author();

        try {
            List formsItems = upload.parseRequest(request);
            Iterator iter = formsItems.iterator();


            while (iter.hasNext()) {
                UUID uuid = UUID.randomUUID();
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    fileName = new File(item.getName()).getName();
                    System.out.println(fileName);
                    if (fileName.length() > 0) {
                        newFileName = fileName.replace(fileName.substring(0, fileName.lastIndexOf(".")), uuid.toString());

                        filePath = uploadPath + File.separator + newFileName;

                        String str = "uploaded" + File.separator + newFileName;
                        filesList.add(str);

                        File storeFile = new File(filePath);

                        item.write(storeFile);
                    } else
                        filesList.add("none");
                } else {
                    if (item.getFieldName().equalsIgnoreCase("update")) {
                        opType = "update";
                    } else if (item.getFieldName().equalsIgnoreCase("add")) {
                        opType = "add";
                    }
                    if (item.getFieldName().equalsIgnoreCase("postId")) {
                        Integer postId = Integer.parseInt(item.getString());
                        post.setId(postId);
                    }
                    if (item.getFieldName().equalsIgnoreCase("editor1")) {
                        String editor1 = item.getString();
                        post.setHeading(editor1);
                    }
                    if (item.getFieldName().equalsIgnoreCase("editor2")) {
                        String editor2 = item.getString();
                        post.setContext(editor2);
                    }
                    if (item.getFieldName().equalsIgnoreCase("userName")) {
                        Integer userName = Integer.parseInt(item.getString());
                        author.setId(userName);
                    }
                    if (item.getFieldName().equalsIgnoreCase("catName")) {
                        Integer catName = Integer.parseInt(item.getString());
                        categorie.setId(catName);

                    }
                    post.setAuthor(author);
                    post.setCategorie(categorie);

                }
            }
            post.setFileName(filesList);
            System.out.println(opType);

            if (opType == "update") {
                boolean isUpdated = postsService.updatePost(post);
            } else if (opType == "add") {
                boolean isAdded = postsService.addPost(post);
            }
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error:" + ex.getMessage());
            ex.printStackTrace();
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
