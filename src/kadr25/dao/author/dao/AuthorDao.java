package kadr25.dao.author.dao;

import kadr25.model.Author;
import kadr25.model.Categorie;

import java.util.List;

public interface AuthorDao {

    List<Author> getAuthors() throws Exception;

    boolean addAuthor(Author author) throws Exception;

    Author getAuthorById(Integer id) throws Exception;

    boolean updateAuthor(Author author) throws Exception;

    boolean deleteAuthor(Author author) throws Exception;
}
