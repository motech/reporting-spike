package org.motechproject.ananya.bbc.mapper;

import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;
import org.motechproject.ananya.bbc.domain.CertificateCourseUsage;

import java.util.List;

public interface CertificateCourseUsageMapper {

    Integer getCount(@Param("startTimeId") DateTime startTimeId,
                     @Param("endTimeId") DateTime endTimeId);

    List<CertificateCourseUsage> getAll(@Param("limit") int limit,
                                        @Param("offset") int offset,
                                        @Param("startDate") DateTime startDate,
                                        @Param("endDate") DateTime endDate);
}
