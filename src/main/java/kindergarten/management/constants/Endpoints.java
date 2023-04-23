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


    // GROUP
    public static final String GROUP = "/group";
    public static final String GET_ALL_GROUPS ="/all";

    // CHILDREN
    public static final String CHILDREN = "/children";
    public static final String GET_ALL_CHLDREN = "/all";
    public static final String ADD_CHILD = "/add";
}
