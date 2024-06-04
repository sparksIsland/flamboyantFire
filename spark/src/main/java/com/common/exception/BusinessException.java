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
package com.common.exception;


/**
 * Business exception.
 */

import com.common.enums.BaseResponseEnum;
import com.common.entity.IResponse;

/**
 * @Author: freemarker
 * @Description: BusinessException
 * @Date: 2019-10-18
 * @Version: 1.0
 */
public class BusinessException extends ApplicationException {

    private static final long serialVersionUID = -3346733232647550691L;

    /**
     * DEFAULT_BUSINESS_CODE.
     */
    public static final String DEFAULT_BUSINESS_CODE = BaseResponseEnum.BUSINESS_EXCEPTION.getCode();

    /**
     * DEFAULT_BUSINESS_MESSAGE.
     */
    public static final String DEFAULT_BUSINESS_MESSAGE = BaseResponseEnum.BUSINESS_EXCEPTION.getMessage();

    private final String code;

    private final String message;

    private final Exception exception;

    /**
     * default constructor.
     */
    public BusinessException() {

        super();
        this.code = DEFAULT_BUSINESS_CODE;
        this.message = DEFAULT_BUSINESS_MESSAGE;
        this.exception = null;
    }

    /**
     * @param message error message
     */
    public BusinessException(final String message) {

        super(DEFAULT_BUSINESS_CODE, message);
        this.code = DEFAULT_BUSINESS_CODE;
        this.message = message;
        this.exception = null;
    }

    /**
     * @param code    error code
     * @param message error message
     */
    public BusinessException(final String code, final String message) {

        super(code, message);
        this.code = code;
        this.message = message;
        this.exception = null;
    }

    public BusinessException(final String code, final String message, final Exception exception) {

        this.code = code;
        this.message = message;
        this.exception = exception;
    }

    /**
     * @param code    error code
     * @param message error message
     */
    public BusinessException(final String code, final String message, final Object... args) {

        this.code = code;
        this.message = String.format(message, args);
        this.exception = null;
    }

    /**
     * @param response Class or enum that implememts IResponse
     */
    // public BusinessException(final IResponse<T> response) {
    //
    //     super(response.getCode(), response.getMessage());
    //     this.code = response.getCode();
    //     this.message = response.getMessage();
    //     this.exception = null;
    // }

    @Override
    public String getCode() {

        return code;
    }

    @Override
    public String getMessage() {

        return message;
    }

    @Override
    public Exception getException() {

        return exception;
    }

}
