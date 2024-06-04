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
 * Customized application exception.
 */

import com.common.enums.BaseResponseEnum;

/**
 * @Author: freemarker
 * @Description: ApplicationException
 * @Date: 2019-10-18
 * @Version: 1.0
 */
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = -5430955945096897330L;

    private final String code;

    private final String message;

    private final Exception exception;

    /**
     * default constructor.
     */
    public ApplicationException() {

        super(BaseResponseEnum.SYSTEM_EXCEPTION.getMessage());
        this.code = BaseResponseEnum.SYSTEM_EXCEPTION.getCode();
        this.message = BaseResponseEnum.SYSTEM_EXCEPTION.getMessage();
        this.exception = null;
    }

    /**
     * @param message
     *            error message
     */
    public ApplicationException(final String message) {

        super(message);
        this.code = BaseResponseEnum.SYSTEM_EXCEPTION.getCode();
        this.message = message;
        this.exception = null;
    }

    /**
     * @param code
     *            error code
     * @param message
     *            error message
     */
    public ApplicationException(final String code, final String message) {

        super(message);
        this.code = code;
        this.message = message;
        this.exception = null;
    }

    public ApplicationException(final String code, final String message, final Exception exception) {

        this.code = code;
        this.message = message;
        this.exception = exception;
    }

    public ApplicationException(final String code, final String message, final Object... args) {

        this.code = code;
        this.message = String.format(message, args);
        this.exception = null;
    }

    public String getCode() {

        return code;
    }

    @Override
    public String getMessage() {

        return message;
    }

    public Exception getException() {

        return exception;
    }

}
