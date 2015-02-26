package org.assessment.components;

import org.hippoecm.repository.HippoRepository;
import org.hippoecm.repository.HippoRepositoryFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;

public class Assessment4Test {

    private Session session;

    @Before
    public void setUp() throws RepositoryException {
        String repoUrl="rmi://localhost:1099/hipporepository";
        String username="admin";
        char[] password="admin".toCharArray();

        HippoRepository repository = HippoRepositoryFactory.getHippoRepository(repoUrl);

        session = repository.login(username,password);

        Node root = session.getRootNode();
        root.addNode("books");
    }

    @After
    public void cleanUp() throws RepositoryException {
        Node root = session.getRootNode();
        root.getNode("books").remove();

        session.save();
    }

    @Test
    public void addBook() throws RepositoryException {
        Node root = session.getRootNode();
        Node booksNode = root.getNode("books");

        // throws unsupportedRepositoryOperationException
        // booksNode.setPrimaryType("nt:unstructured");

        Node newBook = booksNode.addNode("Never ending story");
        newBook.setProperty("name", "Never ending story");
        newBook.setProperty("author", "Michael Ende");

        Node chapter1 = newBook.addNode("Chapter 1");
        chapter1.setProperty("name", "The beginning of the beginning");

        Node paragraph = chapter1.addNode("first paragraph");
        paragraph.setProperty("message", "Every real story is a never ending story.");

        Node node = root.getNode("books/Never ending story/Chapter 1/first paragraph");
        System.out.println(node.getPath());
        System.out.println(node.getProperty("message").getString());
    }

}
