package kadr25.service.author.service;

import kadr25.dao.author.dao.AuthorDao;
import kadr25.model.Author;
import kadr25.service.author.impl.AuthorService;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public List<Author> getAuthors() throws Exception {
        return authorDao.getAuthors();
    }

    @Override
    public boolean addAuthor(Author author) throws Exception {
        return authorDao.addAuthor(author);
    }

    @Override
    public Author getAuthorById(Integer id) throws Exception {
        return authorDao.getAuthorById(id);
    }

    @Override
    public boolean updateAuthor(Author author) throws Exception {
        return authorDao.updateAuthor(author);
    }

    @Override
    public boolean deleteAuthor(Author author) throws Exception {
        return authorDao.deleteAuthor(author);
    }
}
