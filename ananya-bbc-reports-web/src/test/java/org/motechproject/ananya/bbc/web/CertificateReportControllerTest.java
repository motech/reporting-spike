package org.motechproject.ananya.bbc.web;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.ananya.bbc.service.CertificateCourseReportService;
import org.springframework.web.servlet.ModelAndView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.MockitoAnnotations.initMocks;

public class CertificateReportControllerTest {

    @Mock
    private CertificateCourseReportService reportService;
    private CertificateReportController controller;

    @Before
    public void setUp() {
        initMocks(this);
        controller = new CertificateReportController(reportService);
    }

    @Test
    public void shouldShowReportLayoutView() {
        ModelAndView modelAndView = controller.show();
        assertThat(modelAndView.getViewName(), is("certificatecourse/report"));
    }
}
