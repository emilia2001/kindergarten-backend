package kindergarten.management.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoints {
    // LOGIN
    public static final String LOGIN ="/login";
    public static final String REGISTER = "/register";

    // TEACHER
    public static final String TEACHER ="/teacher";

    // ADMIN
    public static final String ADMIN = "admin";

    // GROUP
    public static final String GROUP = "/group";
    public static final String GET_SPOTS_COUNT_BY_ID = "/spots/{id}";

    // CHILDREN
    public static final String CHILDREN = "/children";

    // ATTENDANCE
    public static final String ATTENDANCE = "/attendance";
    public static final String GET_ALL_ATTENDANCES_BY_MONTH = "/all/{month}";

    // PAYMENT
    public static final String PAYMENT = "/payment";
    public static final String GET_ALL_PAYMENTS_BY_MONTH = "/all/month/{month}";
    public static final String GET_ALL_PAYMENTS_BY_PARENT = "/all/id/{id}";
    public static final String CHARGE = "/charge";
    public static final String CHARGE_BY_ADMIN = "/charge-by-admin";
    public static final String SEND_EMAIL = "send-email";

    // PAYMENT CONFIRMATION
    public static final String PAYMENT_CONFIRMATION = "/payment-confirmation";
    public static final String NEXT_ID = "/next-id";


    // ANNOUNCEMENT
    public static final String ANNOUNCEMENT = "/announcement";

    // REQUESTS
    public static final String REGISTRATION_REQUEST = "/registration-request";
    public static final String EXTENSION_REQUEST = "/extension-request";
    public static final String UPDATE_BY_PARENT = "/update-by-parent";
    public static final String UPDATE_BY_ADMIN = "/update-by-admin";

    // GENERAL
    public static final String GET_ALL = "all";
    public static final String GET_ALL_BY_ID = "all/{id}";
    public static final String ADD = "/add";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String DELETE_BY_ID = "/delete/{id}";
    public static final String GET_BY_ID = "/get/{id}";


}
