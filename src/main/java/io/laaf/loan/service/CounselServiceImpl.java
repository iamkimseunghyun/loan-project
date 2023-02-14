package io.laaf.loan.service;

import io.laaf.loan.domain.Counsel;
import io.laaf.loan.exception.BaseException;
import io.laaf.loan.exception.ResultType;
import io.laaf.loan.repository.CounselRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static io.laaf.loan.dto.CounselDTO.Request;
import static io.laaf.loan.dto.CounselDTO.Response;

@Service
@RequiredArgsConstructor
public class CounselServiceImpl implements CounselService {

    private final CounselRepository counselRepository;

    private final ModelMapper modelMapper;

    @Override
    public Response create(Request request) {
        Counsel counsel = modelMapper.map(request, Counsel.class);
        counsel.setAppliedAt(LocalDateTime.now());

        Counsel created = counselRepository.save(counsel);

        return modelMapper.map(created, Response.class);
    }

    @Override
    public Response get(Long counselId) {
        Counsel counsel = counselRepository.findById(counselId).orElseThrow(() -> {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        });
        return modelMapper.map(counsel, Response.class);
    }
}
