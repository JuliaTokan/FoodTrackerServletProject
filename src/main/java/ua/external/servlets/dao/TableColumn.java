package ua.external.servlets.dao;

/**
 * Table column's names of DB
 */
public class TableColumn {
    public final static String PRODUCT_ID = "id";
    public final static String PRODUCT_USER_ID = "user_id";
    public final static String PRODUCT_NAME = "name";
    public final static String PRODUCT_CALORIES = "calories";
    public final static String PRODUCT_PROTEIN = "protein";
    public final static String PRODUCT_FATS = "fats";
    public final static String PRODUCT_CARBOHYDRATES = "carbohydrates";
    public final static String PRODUCT_COMMON = "common";
    public final static String PRODUCT_DELETED = "deleted";

    public final static String CLIENT_ID = "id";
    public final static String CLIENT_NAME = "name";
    public final static String CLIENT_GENDER = "gender_id";
    public final static String CLIENT_AGE = "age";
    public final static String CLIENT_HEIGHT = "height";
    public final static String CLIENT_WEIGHT = "weight";
    public final static String CLIENT_NUTRITION_GOAL = "nutritionGoal_id";
    public final static String CLIENT_ACTIVITY = "activity_id";
    public final static String CLIENT_CALORIES = "calories";
    public final static String CLIENT_PROTEIN = "protein";
    public final static String CLIENT_FATS = "fats";
    public final static String CLIENT_CARBOHYDRATES = "carbohydrates";

    public final static String USER_ID = "id";
    public final static String USER_LOGIN = "login";
    public final static String USER_PASSWORD = "password";
    public final static String ROLE = "role_id";
    public final static String USER_CLIENT_ID = "client_id";

    public final static String MEAL_ID = "id";
    public final static String MEAL_USER_ID = "user_id";
    public final static String MEAL_PRODUCT_ID = "product_id";
    public final static String MEAL_PRODUCT_WEIGHT = "weight";
    public final static String MEAL_EAT_PERIOD = "eat_period_id";
    public final static String MEAL_DATE = "date";

    public final static String USER_ROLE_ID = "id";
    public final static String USER_ROLE = "role";

    public final static String GENDER_ID = "id";
    public final static String GENDER = "gender";

    public final static String EAT_PERIOD_ID = "id";
    public final static String EAT_PERIOD = "period";

    public final static String ACTIVITY_ID = "id";
    public final static String ACTIVITY = "activity";
    public final static String ACTIVITY_COEFFICIENT = "coefficient";

    public final static String NUTR_GOAL_ID = "id";
    public final static String NUTR_GOAL = "goal";
    public final static String NUTR_GOAL_COEFFICIENT = "coefficient";
}
