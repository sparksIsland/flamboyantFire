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

import com.common.annotation.ResponseWrapperIgnore;
import com.common.entity.ResponseDto;
import com.common.entity.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Controller advice to wrap JSON response.
 */

/**
 * @Author: freemarker
 * @Description: JacksonResponseBodyAdvice
 * @Date: 2019-10-18
 * @Version: 1.0
 */
@ControllerAdvice(annotations = { RestController.class })
@Slf4j
public class JacksonResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(final MethodParameter returnType,
                            final Class<? extends HttpMessageConverter<?>> converterType) {

        return AbstractJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, final MethodParameter returnType, final MediaType mediaType,
                                  final Class<? extends HttpMessageConverter<?>> httpMessageConverter,
                                  final ServerHttpRequest request, final ServerHttpResponse response) {

        if (!(body instanceof ResponseDto)) {
            if (!returnType.hasMethodAnnotation(ResponseWrapperIgnore.class)) {
                body = ResponseUtil.wrapSuccess(body);
            }
        }
        return body;
    }

}
