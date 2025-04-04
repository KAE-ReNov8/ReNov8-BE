package befly.common.exception;

import befly.common.code.BaseCodeDto;
import befly.common.code.BaseCodeInterface;
import lombok.AllArgsConstructor;

/*
    API 진행중 모든 Exception은 RestApiException으로 전달
 */
@AllArgsConstructor
public class RestApiException extends RuntimeException{
    private final BaseCodeInterface errorCode;

    public BaseCodeDto getErrorCode() {
        return this.errorCode.getCode();
    }
}
