package org.motechproject.ananya.bbc.users.repository;

import org.motechproject.ananya.bbc.users.domain.MenuLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AllMenuLinks {

    @Autowired
    private DataAccessTemplate template;

    public void add(MenuLink menuLink) {
        template.save(menuLink);
    }

    public List<MenuLink> getAll() {
        return template.loadAll(MenuLink.class);
    }
}
