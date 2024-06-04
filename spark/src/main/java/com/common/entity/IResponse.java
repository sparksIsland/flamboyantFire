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

import java.io.Serializable;

/**
 * Response interface
 */

/**
 * @Author: freemarker
 * @Description: IResponse
 * @Date: 2019-10-18
 * @Version: 1.0
 */
@SuppressWarnings({ "PMD.AbstractMethodOrInterfaceMethodMustUseJavadocRule" })
public interface IResponse<T> extends Serializable {

    /**
     * @return response code
     */
    String getCode();

    /**
     * @return response message
     */
    String getMessage();

    /**
     * @return response body
     */
    T getBody();

}
