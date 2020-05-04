package kadr25.service.author.impl;

import kadr25.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAuthors() throws Exception;

    boolean addAuthor(Author author) throws Exception;

    Author getAuthorById(Integer id) throws Exception;

    boolean updateAuthor(Author author) throws Exception;

    boolean deleteAuthor(Author author) throws Exception;
}
