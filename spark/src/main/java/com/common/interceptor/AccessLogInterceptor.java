package com.common.interceptor;

import com.common.exception.ApplicationException;
import com.common.utils.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 访问操作,单例
 */

/**
 * @Author: freemarker
 * @Description: AccessLogInterceptor
 * @Date: 2019-10-18
 * @Version: 1.0
 */
public class AccessLogInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessLogInterceptor.class);

    private static final String BEGIN_MILLIS = "_begin_millis_";

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object arg2) {

        request.setAttribute(BEGIN_MILLIS, Long.valueOf(System.currentTimeMillis()));

        final String urlWithMethod = ParamUtil.getRequestUrlWithMethod(request);
        LOGGER.info("Accept Request --> 【{}】", ParamUtil.favorit(request, urlWithMethod));

        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
                                final Object handlerObj, final Exception exception)
            throws ApplicationException {

        final long beginMillis = (long) request.getAttribute(BEGIN_MILLIS);
        //        if (RequestLogging.get().booleanValue()) {
        LOGGER.info("tran cost " + (System.currentTimeMillis() - beginMillis) + " ms");
        //        }
    }

    @Override
    public void postHandle(final HttpServletRequest arg0, final HttpServletResponse arg1, final Object arg2,
                           final ModelAndView arg3)
            throws ApplicationException {

    }

}