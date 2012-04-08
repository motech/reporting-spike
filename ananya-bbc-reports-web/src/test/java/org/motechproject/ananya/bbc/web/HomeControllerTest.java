package org.motechproject.ananya.bbc.web;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HomeControllerTest {

    @Test
    public void shouldReturnHomeView(){
        HomeController homeController = new HomeController();

        ModelAndView modelAndView = homeController.showHome();

        assertThat(modelAndView.getViewName(), is("home"));
    }
}
