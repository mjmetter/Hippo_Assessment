package org.assessment.components;

import org.hippoecm.repository.HippoRepository;
import org.hippoecm.repository.HippoRepositoryFactory;
import org.junit.Before;
import org.junit.Test;

import javax.jcr.*;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

public class Assessment3Test {

    private Session session;

    @Before
    public void setUp() throws RepositoryException {
        String repoUrl="rmi://localhost:1099/hipporepository";
        String username="admin";
        char[] password="admin".toCharArray();

        HippoRepository repository = HippoRepositoryFactory.getHippoRepository(repoUrl);

        session = repository.login(username,password);
    }

    @Test
    public void searchContent() throws RepositoryException {
//        String xpathQuery = "//*[jcr:contains(.,'fox')]";
//        final String xpathQuery = "//*[jcr:contains(.,'system')]";
        final String xpathQuery = "//element(*, hippo:document) ";
        final QueryManager queryManager = session.getWorkspace().getQueryManager();
        final Query query = queryManager.createQuery(xpathQuery, "xpath");
        printResult( query.execute() );
    }

    private void printResult(final QueryResult result) throws RepositoryException {
        System.out.println("Results after searching:");
        NodeIterator iterator = result.getNodes();
        while(iterator.hasNext()) {
            Node node = iterator.nextNode();
            System.out.println(" - Node location: " + node.getPath());
        }
    }

}
