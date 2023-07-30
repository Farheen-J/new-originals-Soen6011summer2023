package com.sep.backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public enum ApplicationStatus
{
    //status 0 -> applied by candidate
    //status 1 -> rejected by employer
    //status 2 -> interview with employer
    //status 3 -> accepted by employer

    APPLIED_BY_CANDIDATE(0),
    REJECTED_BY_EMPLOYER(1),
    INTERVIEW_WITH_EMPLOYER(2),
    ACCEPTED_BY_EMPLOYER(3);

    private int status;


}
