package io.laaf.loan.service;

import io.laaf.loan.domain.Counsel;
import io.laaf.loan.exception.BaseException;
import io.laaf.loan.exception.ResultType;
import io.laaf.loan.repository.CounselRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static io.laaf.loan.dto.CounselDTO.Request;
import static io.laaf.loan.dto.CounselDTO.Response;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CounselServiceTest {

    @InjectMocks
    CounselServiceImpl counselService;

    @Mock
    private CounselRepository counselRepository;

    @Test
    void Should_ReturnResponseOfNewCounselEntity_When_RequestCounsel() {
        Counsel entity = Counsel.builder()
                .name("Member Kim")
                .cellPhone("010-1111-2222")
                .email("abc@email.com")
                .memo("대출 받고 싶습니다.")
                .zipCode("123456")
                .address("서울특별시 강남구 대치동")
                .addressDetail("101동 101호")
                .build();

        Request request = Request.builder()
                .name("Member Kim")
                .cellPhone("010-1111-2222")
                .email("abc@email.com")
                .memo("대출 받고 싶습니다.")
                .zipCode("123456")
                .address("서울특별시 강남구 대치동")
                .addressDetail("101동 101호")
                .build();

        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);

        Response actual = counselService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
    }

    @Test
    void Should_ReturnResponseOfExistCounselEntity_When_RequestExistCounselId() {
        Long findId = 1L;

        Counsel entity = Counsel.builder()
                .counselId(1L)
                .build();

        when(counselRepository.findById(findId)).thenReturn(Optional.ofNullable(entity));

        Response actual = counselService.get(1L);

        assertThat(actual.getCounselId()).isSameAs(findId);
    }

    @Test
    void Should_ThrowException_When_RequestNotExistCounselId() {
        Long findId = 2L;

        when(counselRepository.findById(findId)).thenThrow(new BaseException(ResultType.SYSTEM_ERROR));

        Assertions.assertThrows(BaseException.class, () -> counselService.get(findId));
    }
}