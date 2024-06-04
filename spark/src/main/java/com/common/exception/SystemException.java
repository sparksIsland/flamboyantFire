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
 * System exception.
 */

import com.common.enums.BaseResponseEnum;

/**
 * @Author: freemarker
 * @Description: SystemException
 * @Date: 2019-10-18
 * @Version: 1.0
 */
@SuppressWarnings({ "PMD.AvoidCommentBehindStatementRule" })
public class SystemException extends ApplicationException {

    //    private static final Logger LOGGER = LoggerFactory.getLogger(SystemException.class);

    private static final long serialVersionUID = -8216248010896000768L;

    /**
     * DEFAULT_SYSTEM_CODE.
     */
    public static final String DEFAULT_SYSTEM_CODE = BaseResponseEnum.SYSTEM_EXCEPTION.getCode();

    /**
     * DEFAULT_SYSTEM_MESSAGE.
     */
    public static final String DEFAULT_SYSTEM_MESSAGE = BaseResponseEnum.SYSTEM_EXCEPTION.getMessage();

    private final String code;

    private final String message;

    /**
     * default constructor.
     */
    public SystemException() {

        super();
        this.code = DEFAULT_SYSTEM_CODE;
        this.message = DEFAULT_SYSTEM_MESSAGE;
    }

    /**
     * @param message
     *            error message
     */
    public SystemException(final String message) {

        super(DEFAULT_SYSTEM_CODE, message);
        this.code = DEFAULT_SYSTEM_CODE;
        this.message = message;
    }

    /**
     * @param code
     *            error code
     * @param message
     *            error message
     */
    public SystemException(final String code, final String message) {

        super(code, message);
        this.code = code;
        this.message = message;
    }

    /**
     * @param e
     *            Exception
     */
    public SystemException(final Exception e) {

        super(DEFAULT_SYSTEM_CODE, e.getMessage() == null ? DEFAULT_SYSTEM_CODE : e.getMessage());
        this.code = DEFAULT_SYSTEM_CODE;
        final StringBuilder builder = new StringBuilder();
        builder.append(DEFAULT_SYSTEM_MESSAGE);
        //        if (e.getMessage() != null) {
        builder.append(": ").append(e.getMessage()); //$NON-NLS-1$
        //        }

        //        final StringWriter stringWriter = new StringWriter();
        //        final PrintWriter printWriter = new PrintWriter(stringWriter);
        //        e.printStackTrace(printWriter);
        //        if (stringWriter != null) {
        //            builder.append("\r\n").append(stringWriter.toString()); //$NON-NLS-1$
        //        }
        //        printWriter.close();
        //        try {
        //            stringWriter.close();
        //        } catch (final IOException e1) {
        //            LOGGER.error(e.getMessage(), e1);
        //        }

        this.message = builder.toString();
    }

    @Override
    public String getCode() {

        return code;
    }

    @Override
    public String getMessage() {

        return message;
    }

}
