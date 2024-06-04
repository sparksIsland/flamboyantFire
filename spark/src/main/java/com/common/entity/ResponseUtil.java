/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT China Mobile (SuZhou) Software Technology Co.,Ltd. 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * CMSS Co.,Ltd. The programs may be used and/or copied only with written
 * permission from CMSS Co.,Ltd. or in accordance with the terms and conditions
 * stipulated in the agreement/contract under which the program(s) have been
 * supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.common.entity;

import com.common.enums.BaseResponseEnum;
import com.common.exception.ApplicationException;
import com.common.exception.SystemException;
import org.springframework.util.Assert;

/**
 * Response util.
 */

/**
 * @Author: freemarker
 * @Description: ResponseUtil
 * @Date: 2019-10-18
 * @Version: 1.0
 */
public final class ResponseUtil {

    /**
     * private constructor.
     */
    private ResponseUtil() {

    }

    /**
     * Wrap null response body of success.
     *
     * @return ResponseDto
     */
    public static ResponseDto<Void> wrapSuccess() {

        return new ResponseDto<Void>(BaseResponseEnum.SUCCESS);
    }

    /**
     * Wrap response body of success.
     *
     * @param body
     *            returned object
     * @return ResponseDto
     */
    public static <T> ResponseDto<T> wrapSuccess(final T body) {

        final ResponseDto<T> responseDto = new ResponseDto<>();
        responseDto.setCode(BaseResponseEnum.SUCCESS.getCode());
        responseDto.setMessage(BaseResponseEnum.SUCCESS.getCode());
        responseDto.setBody(body);
        return responseDto;
    }

    /**
     * @param code
     *            error code
     * @param message
     *            error message
     * @return ResponseDto
     */
    public static ResponseDto<Void> wrapException(final String code, final String message) {

        Assert.notNull(code, "Return code can't be null");
        final ResponseDto<Void> responseDto = new ResponseDto<>();
        responseDto.setCode(code);
        responseDto.setMessage(message);
        return responseDto;
    }

    /**
     * @param e
     *            ApplicationException
     * @return ResponseDto
     */
    public static ResponseDto<Void> wrapException(final ApplicationException e) {

        Assert.notNull(e, "ApplicationException can't be null");
        final ResponseDto<Void> responseDto = new ResponseDto<>();
        responseDto.setCode(e.getCode());
        responseDto.setMessage(e.getMessage());
        return responseDto;
    }

    /**
     * @param e
     *            Exception
     * @return ResponseDto
     */
    public static ResponseDto<Void> wrapException(final Exception e) {

        return wrapException(new SystemException(e));
    }

}
