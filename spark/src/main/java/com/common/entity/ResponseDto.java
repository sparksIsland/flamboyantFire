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


import com.common.exception.ApplicationException;

import java.util.Date;

/**
 * Common response DTO.
 */

/**
 * @Author: freemarker
 * @Description: ResponseDto
 * @Date: 2019-10-18
 * @Version: 1.0
 */
public class ResponseDto<T> implements IResponse<T> {

    private static final long serialVersionUID = 1L;

    private String code;

    private String message;

    private Date time = new Date();

    private T body;

    public ResponseDto() {

    }

    /**
     * @param response
     *            returned body
     */
    public ResponseDto(final IResponse<T> response) {

        this.code = response.getCode();
        this.message = response.getMessage();
        this.body = response.getBody();
    }

    /**
     * @param response
     *            returned body
     */
    public ResponseDto(final IResponse<T> response, final T body) {

        this.code = response.getCode();
        this.message = response.getMessage();
        this.body = body;
    }

    /**
     * @param code
     *            error code
     * @param message
     *            error message
     */
    public ResponseDto(final String code, final String message) {

        this.code = code;
        this.message = message;
    }

    /**
     * @param code
     *            error code
     * @param message
     *            error message
     * @param body
     *            returned body
     */
    public ResponseDto(final String code, final String message, final T body) {

        super();
        this.code = code;
        this.message = message;
        this.body = body;
    }

    /**
     * @param e
     *            ApplicationException
     */
    public ResponseDto(final ApplicationException e) {

        this.code = e.getCode();
        this.message = e.getMessage();
    }

    @Override
    public String getCode() {

        return code;
    }

    public void setCode(final String code) {

        this.code = code;
    }

    @Override
    public String getMessage() {

        return message;
    }

    public void setMessage(final String message) {

        this.message = message;
    }

    public Date getTime() {

        return time;
    }

    public void setTime(final Date time) {

        this.time = time;
    }

    @Override
    public T getBody() {

        return body;
    }

    public void setBody(final T body) {

        this.body = body;
    }

}
