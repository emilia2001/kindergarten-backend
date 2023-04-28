package kindergarten.management.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoints {
    // LOGIN
    public static final String LOGIN ="/login";
    public static final String REGISTER = "/register";

    // TEACHER
    public static final String TEACHER ="/teacher";
    public static final String GET_ALL_TEACHERS ="/all";
    public static final String ADD_TEACHER ="/add";
    public static final String GET_TEACHER = "/get/{id}";
    public static final String UPDATE_TEACHER = "/update/{id}";

    // GROUP
    public static final String GROUP = "/group";
    public static final String GET_ALL_GROUPS ="/all";

    // CHILDREN
    public static final String CHILDREN = "/children";
    public static final String GET_ALL_CHILDREN = "/all";
    public static final String ADD_CHILD = "/add";
    public static final String GET_CHILD = "/get/{id}";

    // ATTENDANCE
    public static final String ATTENDANCE = "/attendance";
    public static final String GET_ALL_ATTENDANCES_BY_MONTH = "/all/{month}";
    public static final String ADD_ATTENDANCES = "/add";

    // PAYMENT
    public static final String PAYMENT = "/payment";

    public static final String GET_ALL_PAYMENTS_BY_MONTH = "/all/{month}";
    public static final String UPDATE_PAYMENT = "/update/{id}";

}
