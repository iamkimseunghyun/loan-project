package io.laaf.loan.service;

import io.laaf.loan.dto.CounselDTO;

import static io.laaf.loan.dto.CounselDTO.*;

public interface CounselService {
    Response create(Request request);

    Response get(Long counselId);
}
