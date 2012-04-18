package org.motechproject.ananya.bbc.web;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.motechproject.ananya.bbc.domain.ReportServeModel;
import org.motechproject.ananya.bbc.request.UsageReportRequest;
import org.motechproject.ananya.bbc.service.CertificateCourseReportService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CertificateReportControllerTest {

    @Mock
    private CertificateCourseReportService reportService;
    @Mock
    private HttpServletRequest request;
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

    @Test
    public void shouldFetchReportDataBasedOnTheRequestParameters() {
        String startDate = "01/01/2010";
        String endDate = "01/01/2011";
        when(request.getParameter("from")).thenReturn("5");
        when(request.getParameter("to")).thenReturn("11");
        when(request.getParameter("startDate")).thenReturn(startDate);
        when(request.getParameter("endDate")).thenReturn(endDate);
        when(request.getParameterMap()).thenReturn(new HashMap());

        controller.serveData(request);

        verify(reportService, never()).getCount(Matchers.<DateTime>any(), Matchers.<DateTime>any());
        ArgumentCaptor<UsageReportRequest> captor = ArgumentCaptor.forClass(UsageReportRequest.class);
        verify(reportService).getUsageReport(captor.capture());

        UsageReportRequest usageReportRequest = captor.getValue();

        assertEquals(startDate, usageReportRequest.getStartDate().toString("MM/dd/yyyy"));
        assertEquals(endDate, usageReportRequest.getEndDate().toString("MM/dd/yyyy"));
        assertEquals(6, usageReportRequest.getLimit());
        assertEquals(5, usageReportRequest.getOffset());
    }

    @Test
    public void shouldFetchReportDataWithHeaderAndCountBasedOnTheRequestParameters() {
        String startDate = "01/01/2010";
        String endDate = "01/01/2011";
        HashMap hashMap = new HashMap();
        int count = 10;
        hashMap.put("count","");
        hashMap.put("header","");
        when(request.getParameter("from")).thenReturn("5");
        when(request.getParameter("to")).thenReturn("11");
        when(request.getParameter("startDate")).thenReturn(startDate);
        when(request.getParameter("endDate")).thenReturn(endDate);
        when(request.getParameterMap()).thenReturn(hashMap);

        ArrayList<CertificateCourseUsage> usageReport = new ArrayList<CertificateCourseUsage>();
        when(reportService.getUsageReport(Matchers.<UsageReportRequest>any())).thenReturn(usageReport);
        when(reportService.getCount(new DateTime(2010,1,1,0,0),new DateTime(2011,1,1,0,0))).thenReturn(count);

        ReportServeModel reportServeModel = controller.serveData(request);

        assertEquals(usageReport, reportServeModel.getContent());
        assertEquals(count, (int)reportServeModel.getCount());
        assertNotNull(reportServeModel.getHeader());
    }

    @Test
    public void shouldServeReportModelBasedOnRequestParameters() {
        String startDate = "01/01/2010";
        String endDate = "01/01/2011";
        when(request.getParameter("from")).thenReturn("5");
        when(request.getParameter("to")).thenReturn("11");
        when(request.getParameter("startDate")).thenReturn(startDate);
        when(request.getParameter("endDate")).thenReturn(endDate);
        when(request.getParameterMap()).thenReturn(new HashMap());

        ArrayList<CertificateCourseUsage> usageReport = new ArrayList<CertificateCourseUsage>();
        when(reportService.getUsageReport(Matchers.<UsageReportRequest>any())).thenReturn(usageReport);

        ReportServeModel reportServeModel = controller.serveData(request);

        assertEquals(usageReport, reportServeModel.getContent());
        assertNull(reportServeModel.getHeader());
        assertNull(reportServeModel.getCount());
    }
}
