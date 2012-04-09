package org.motechproject.ananya.bbc.service;


import org.junit.Ignore;
import org.junit.Test;
import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;

import java.util.List;

import static junit.framework.Assert.assertNotNull;

public class CertificateCourseReportServiceIT extends SpringIntegrationTest {

    private CertificateCourseReportService courseReportService;

    @Test
    @Ignore
    public void shouldFetchFromReportingDBViaMyBatis(){
        List<CertificateCourseUsage> usageReports = courseReportService.getUsageReport();
        assertNotNull(usageReports);
    }

}
