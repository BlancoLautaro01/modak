package app.notification.model;

public class Constants {

    public static final String MINUTE_QUERY = "EXTRACT(MINUTE FROM acc.creation_date) = (SELECT date_part('minute', (SELECT current_timestamp)))";
    public static final String HOUR_QUERY = "EXTRACT(HOUR FROM acc.creation_date) = (SELECT date_part('hour', (SELECT current_timestamp)))";
    public static final String DAY_QUERY = "EXTRACT(DAY FROM acc.creation_date) = (SELECT date_part('day', (SELECT current_timestamp)))";
    public static final String WEEK_QUERY = "EXTRACT(WEEK FROM acc.creation_date) = (SELECT date_part('week', (SELECT current_timestamp)))";
    public static final String MONTH_QUERY = "EXTRACT(MONTH FROM acc.creation_date) = (SELECT date_part('month', (SELECT current_timestamp)))";
    public static final String YEAR_QUERY = "EXTRACT(YEAR FROM acc.creation_date) = (SELECT date_part('year', (SELECT current_timestamp)))";
}
