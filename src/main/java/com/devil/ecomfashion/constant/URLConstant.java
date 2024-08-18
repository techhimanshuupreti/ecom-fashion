package com.devil.ecomfashion.constant;

public class URLConstant {

    public static final String STRIKE = "*";

    //Swagger configuration URL and Whitelist URL
    public static final String V2_API_DOCS = "/v2/api-docs";
    public static final String V3_API_DOCS = "/v3/api-docs";
    public static final String CONFIGURATION_UI = "/configuration/ui";
    public static final String CONFIGURATION_SECURITY = "/configuration/security";
    public static final String V3_ALL_API_DOCS = "/v3/api-docs/**";
    public static final String SWAGGER_RESOURCES = "/swagger-resources";
    public static final String SWAGGER_RESOURCES_ALL = "/swagger-resources/**";
    public static final String SWAGGER_UI_ALL = "/swagger-ui/**";
    public static final String SWAGGER_UI_HTML = "/swagger-ui.html";
    public static final String WEBJARS = "/webjars/**";

    // Auth URL's
    public static final String AUTH_BASE = "/api/v1/auth";
    public static final String AUTH_ALL = "/api/v1/auth/**";
    public static final String USER_REGISTER = "/register";
    public static final String USER_LOGIN = "/login";
    public static final String USER_REFRESH_TOKEN = "/refresh-token";
    public static final String USER_LOGOUT = "/logout";

    // USER URL's
    public static final String USER_BASE = "/api/v1/user";

    // CART URL's
    public static final String CART_BASE = "/api/v1/carts";

    // Category URL's
    public static final String CATEGORY_BASE = "/api/v1/categories";

    // Sub Category URL's
    public static final String SUBCATEGORY_BASE = "/api/v1/sub-categories";

    // Product URL's
    public static final String PRODUCT_BASE = "/api/v1/products";
    public static final String PRODUCT_BY_CATEGORY = "/api/v1/products/categories/**";
    public static final String PRODUCT_BY_SUB_CATEGORY = "/api/v1/products/sub-categories/**";

    public static final String[] ENDPOINT_WHITELIST = {
            V2_API_DOCS,
            V3_API_DOCS,
            CONFIGURATION_UI,
            CONFIGURATION_SECURITY,
            V3_ALL_API_DOCS,
            SWAGGER_RESOURCES,
            SWAGGER_RESOURCES_ALL,
            SWAGGER_UI_ALL,
            SWAGGER_UI_HTML,
            WEBJARS,
            AUTH_ALL,
            CATEGORY_BASE,
            SUBCATEGORY_BASE,
            PRODUCT_BASE,
            PRODUCT_BY_CATEGORY,
            PRODUCT_BY_SUB_CATEGORY
    };
}
