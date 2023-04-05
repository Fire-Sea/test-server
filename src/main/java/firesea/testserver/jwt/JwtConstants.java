package firesea.testserver.jwt;

public class JwtConstants {

    // Expiration Time
    public static final long MINUTE = 1000 * 60;
    public static final long HOUR = 60 * MINUTE;
    public static final long DAY = 24 * HOUR;
    public static final long MONTH = 30 * DAY;

    //Access Token 만료 시간 : 10분
    public static final long AT_EXP_TIME =  10 * MINUTE;

    //refresh Token 만료 시간 : 30분
    public static final long RT_EXP_TIME =  30 * MINUTE;

    // Secret
    public static final String JWT_SECRET_AT = "firesea test server 1";
    public static final String JWT_SECRET_RT = "firesea test server 2";

    // Header
    public static final String AT_HEADER = "access_token";
    public static final String RT_HEADER = "refresh_token";
    public static final String TOKEN_HEADER_PREFIX = "Bearer ";
}
