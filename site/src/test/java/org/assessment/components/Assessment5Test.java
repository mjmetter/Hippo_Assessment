package org.assessment.components;

import org.hippoecm.repository.HippoRepository;
import org.hippoecm.repository.HippoRepositoryFactory;
import org.junit.Before;
import org.junit.Test;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;
import javax.jcr.observation.ObservationManager;

public class Assessment5Test {

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
    public void registerListener() throws RepositoryException, InterruptedException {
        ObservationManager manager = session.getWorkspace().getObservationManager();
        MyListener myListener = new MyListener();
        manager.addEventListener(
                myListener,
                Event.NODE_ADDED,
                session.getRootNode().getNode("content").getPath(),
                true,
                null,
                null,
                true);

        while (!myListener.isFinished()) {
            Thread.sleep(10);
        }
    }

}

class MyListener implements EventListener {

    private int counter;

    public MyListener() {
        this.counter = 0;
    }

    @Override
    public void onEvent(EventIterator eventIterator) {
        while (eventIterator.hasNext()) {
            try {
//                System.out.println("Event occured: " + eventIterator.nextEvent().getIdentifier());
                System.out.println("Path of event occured: " + eventIterator.nextEvent().getPath());
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
        }
        counter++;
    }

    // stop after 5 events
    public boolean isFinished() {
        return counter > 4;
    }
}
