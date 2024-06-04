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
package com.common.enums;

/**
 * Base response enum.
 */

import com.common.entity.IResponse;

/**
 * @Author: freemarker
 * @Description: BaseResponseEnum
 * @Date: 2019-10-18
 * @Version: 1.0
 */
public enum BaseResponseEnum implements IResponse<Void> {

    /**
     * "000000", "SUCCESS"
     */
    SUCCESS("000000", "SUCCESS"),

    /**
     * "100000", "业务错误"
     */
    BUSINESS_EXCEPTION("100000", "业务错误"),

    /**
     * "200000", "参数校验错误"
     */
    PARAMETER_EXCEPTION("200000", "参数校验错误"),

    /**
     * "300000", "权限不足"
     */
    AUTHORITY_EXCEPTION("300000", "权限不足"),

    /**
     * "400000", "系统错误"
     */
    SYSTEM_EXCEPTION("400000", "系统错误");

    private String code;

    private String message;

    BaseResponseEnum(final String code, final String message) {

        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {

        return this.code;
    }

    @Override
    public String getMessage() {

        return this.message;
    }

    @Override
    public Void getBody() {

        return null;
    }

}
