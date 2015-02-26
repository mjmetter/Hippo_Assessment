package org.assessment.components;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.repository.HippoRepository;
import org.hippoecm.repository.HippoRepositoryFactory;
import org.junit.Before;
import org.junit.Test;

import javax.jcr.*;

public class Assessment2Test {

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
    public void traverseContent() throws RepositoryException {
        Node root = session.getRootNode();
        iterativeTraverse(0, root);
    }

    private void iterativeTraverse(final int depth, final Node root) throws RepositoryException {
        NodeIterator iterator = root.getNodes();

        //depth first iterate through all nodes
        while (iterator.hasNext()) {
            Node node = iterator.nextNode();
            printSingleNode(depth, node);
            iterativeTraverse( depth + 1, node);
        }
    }

    private void printSingleNode(final int numSpaces, final Node node) throws RepositoryException {
        String start = StringUtils.repeat("-", numSpaces);
        System.out.println(start + node.getPath());

        PropertyIterator properties = node.getProperties();
        while (properties.hasNext()) {
            Property property=properties.nextProperty();
            if (property.getDefinition().isMultiple()) {
                System.out.print(start + property.getName() + " = [ ");
                for (Value value : property.getValues()) {
                    System.out.print(value.getString() + " ");
                }
                System.out.println("]");
            }
            else {
                System.out.println(start + property.getName() + " = " + property.getString());
            }
        }

    }
}
