package org.motechproject.ananya.bbc.mapper;

import org.apache.ibatis.annotations.Param;
import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;

import java.util.List;

public interface CertificateCourseUsageMapper {
    Integer getCount();
    List<CertificateCourseUsage> getAll(@Param("limit") int limit, @Param("offset") int offset);
}
