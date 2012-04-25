package org.motechproject.ananya.bbc.web;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public void shouldFetchReportDataBasedOnTheRequestParameters() throws ParseException {
        Date startDate = new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2010");
        Date endDate = new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2011");
        when(request.getParameter("from")).thenReturn("5");
        when(request.getParameter("to")).thenReturn("11");
        when(request.getParameter("startDate")).thenReturn("01/01/2010");
        when(request.getParameter("endDate")).thenReturn("01/01/2011");
        when(request.getParameterMap()).thenReturn(new HashMap());

        controller.serveData(request);

        verify(reportService, never()).getCount(Matchers.<UsageReportRequest>any());
        ArgumentCaptor<UsageReportRequest> captor = ArgumentCaptor.forClass(UsageReportRequest.class);
        verify(reportService).getUsageReport(captor.capture());

        UsageReportRequest usageReportRequest = captor.getValue();

        assertEquals(startDate, usageReportRequest.getStartDate());
        assertEquals(endDate, usageReportRequest.getEndDate());
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
        when(reportService.getCount(Matchers.<UsageReportRequest>any())).thenReturn(count);

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
