package io.laaf.loan.controller;


import io.laaf.loan.dto.ResponseDTO;
import io.laaf.loan.dto.ResultObject;

public abstract class AbstractController {

    protected <T> ResponseDTO<T> ok() {
        return ok(null, ResultObject.getSuccess());
    }

    protected <T> ResponseDTO<T> ok(T data) {
        return ok(data, ResultObject.getSuccess());
    }

    protected <T> ResponseDTO<T> ok(T data, ResultObject result) {
        ResponseDTO obj = new ResponseDTO();
        obj.setResult(result);
        obj.setData(data);

        return obj;
    }
}
