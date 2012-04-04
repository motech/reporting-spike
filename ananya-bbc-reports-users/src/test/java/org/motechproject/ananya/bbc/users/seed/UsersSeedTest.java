package org.motechproject.ananya.bbc.users.seed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.ananya.bbc.users.repository.DataAccessTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-Users.xml")
public class UsersSeedTest {

    @Autowired
    private UsersSeed usersSeed;
    
    @Autowired
    private DataAccessTemplate template;
    
    @Test
    public void testLoadDefaultUsers() throws Exception {
        usersSeed.loadDefaultUsers();
    }
}
