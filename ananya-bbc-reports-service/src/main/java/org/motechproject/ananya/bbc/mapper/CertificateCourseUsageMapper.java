package org.motechproject.ananya.bbc.mapper;

import org.apache.ibatis.annotations.Param;
import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;
import org.motechproject.ananya.bbc.request.UsageReportRequest;

import java.util.List;

public interface CertificateCourseUsageMapper {

    Integer getCount(@Param("criteria") UsageReportRequest request);

    List<CertificateCourseUsage> getAll(@Param("criteria") UsageReportRequest request);
}
