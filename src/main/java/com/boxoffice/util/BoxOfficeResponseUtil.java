package com.boxoffice.util;

import com.boxoffice.entity.ErrorResponse;

public class BoxOfficeResponseUtil {
    public static ErrorResponse getErrorResponse() {
        return new ErrorResponse("Invalid input, Please verify and try again!! ");
    }

}
