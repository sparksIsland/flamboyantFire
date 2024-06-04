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
package com.common.advice;

import com.common.entity.ResponseDto;
import com.common.entity.ResponseUtil;
import com.common.exception.ApplicationException;
import com.common.exception.ParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ControllerAdvice to handle exception.
 *
 */

/**
 * @Author: freemarker
 * @Description: ControllerExceptionAdvice
 * @Date: 2019-10-18
 * @Version: 1.0
 */
@ControllerAdvice(annotations = { Controller.class })
public class ControllerExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @Value("${application.error-code.prefix:}")
    private String errorCodePrefix;

    /**
     * Handle Validation Exception.
     *
     * @param e
     *            Exception
     * @return ResponseDto
     */
    @ExceptionHandler({ BindException.class, MethodArgumentNotValidException.class })
    @ResponseBody
    public ResponseDto<Void> handleValidationException(final Exception e) {

        List<FieldError> errors = null;
        if (e instanceof BindException) {
            errors = ((BindException) e).getFieldErrors();
        } else if (e instanceof MethodArgumentNotValidException) {
            errors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
        }
        final ResponseDto<Void> responseDto = ResponseUtil
                .wrapException(new ParameterException(getFieldErrorString(errors)));
        LOGGER.error(e.getMessage(), e);
        responseDto.setCode(errorCodePrefix + responseDto.getCode());
        return responseDto;
    }

    /**
     * Handle ApplicationException.
     *
     * @param e
     *            ApplicationException
     * @return ResponseDto
     */
    @ExceptionHandler({ ApplicationException.class })
    @ResponseBody
    public ResponseDto<Void> handleApplicationException(final ApplicationException e) {

        final ResponseDto<Void> responseDto = ResponseUtil.wrapException(e);
        if (e.getException() != null) {
            LOGGER.error(e.getException().getMessage(), e.getException());
        }
        LOGGER.error(e.getMessage(), e);
        responseDto.setCode(errorCodePrefix + responseDto.getCode());
        //        LOGGER.info(getExceptionBodyString() + JsonUtil.toLogJson(responseDto));
        return responseDto;
    }

    /**
     * Handle exception.
     *
     * @param e
     *            Exception
     * @return ResponseDto
     */
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public ResponseDto<Void> handleException(final Exception e) {

        final ResponseDto<Void> responseDto = ResponseUtil.wrapException(e);
        LOGGER.error(e.getMessage(), e);
        responseDto.setCode(errorCodePrefix + responseDto.getCode());
        //        LOGGER.info(getExceptionBodyString() + JsonUtil.toLogJson(responseDto));
        return responseDto;
    }

    /**
     * Get FieldError String.
     *
     * @param errors
     *            FieldError List
     * @return error String
     */
    private String getFieldErrorString(final List<FieldError> errors) {

        final StringBuilder sb = new StringBuilder();
        if (errors != null && !errors.isEmpty()) {
            for (final FieldError error : errors) {

                sb.append(error.getField() + ": " + error.getDefaultMessage() + "! ");
            }
        }
        return sb.toString();
    }

    public static String getExceptionBodyString() {

        return "Exception Body-->";
    }

}
