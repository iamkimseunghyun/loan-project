package io.laaf.loan.controller;

import io.laaf.loan.dto.CounselDTO;
import io.laaf.loan.dto.ResponseDTO;
import io.laaf.loan.service.CounselService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static io.laaf.loan.dto.CounselDTO.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/counsels")
public class CounselController extends AbstractController {

    private final CounselService counselService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request) {
        return ok(counselService.create(request));
    }

    @GetMapping("/{counselId}")
    public ResponseDTO<Response> get(@PathVariable Long counselId) {
        return ok(counselService.get(counselId));
    }



}
