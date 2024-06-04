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

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Controller advice to wrap JSON request.
 */

/**
 * @Author: freemarker
 * @Description: JacksonRequestBodyAdvice
 * @Date: 2019-10-18
 * @Version: 1.0
 */
@ControllerAdvice(annotations = {RestController.class})
public class JacksonRequestBodyAdvice implements RequestBodyAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonRequestBodyAdvice.class);

    @Override
    public boolean supports(final MethodParameter methodParameter, final Type targetType,
                            final Class<? extends HttpMessageConverter<?>> converterType) {

        return true;
    }

    @Override
    public Object handleEmptyBody(final Object body, final HttpInputMessage inputMessage,
                                  final MethodParameter parameter, final Type targetType,
                                  final Class<? extends HttpMessageConverter<?>> converterType) {

        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(final HttpInputMessage inputMessage, final MethodParameter parameter,
                                           final Type targetType,
                                           final Class<? extends HttpMessageConverter<?>> converterType)
            throws IOException {

        return inputMessage;
    }

    @Override
    public Object afterBodyRead(final Object body, final HttpInputMessage inputMessage, final MethodParameter parameter,
                                final Type targetType, final Class<? extends HttpMessageConverter<?>> converterType) {

        LOGGER.info("Request Body --> 【{}】", JSON.toJSONString(body));
        return body;
    }

}
