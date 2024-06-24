package app.notification.model.enums;

import app.notification.exception.cases.InvalidParameterException;

import java.time.temporal.WeekFields;
import java.util.Arrays;

import static app.notification.model.Constants.DAY_QUERY;
import static app.notification.model.Constants.HOUR_QUERY;
import static app.notification.model.Constants.MINUTE_QUERY;
import static app.notification.model.Constants.MONTH_QUERY;
import static app.notification.model.Constants.WEEK_QUERY;
import static app.notification.model.Constants.YEAR_QUERY;

public enum PeriodTypeEnum {

    MINUTE("MINUTE",
            MINUTE_QUERY + " AND " +
                    HOUR_QUERY + " AND " +
                    DAY_QUERY + " AND " +
                    MONTH_QUERY + " AND " +
                    YEAR_QUERY),

    HOUR("HOUR",
            HOUR_QUERY + " AND " +
                    DAY_QUERY + " AND " +
                    MONTH_QUERY + " AND " +
                    YEAR_QUERY),

    DAY("DAY",
            DAY_QUERY + " AND " +
                    MONTH_QUERY + " AND " +
                    YEAR_QUERY),

    WEEK("WEEK",
            WEEK_QUERY + " AND " +
                    MONTH_QUERY + " AND " +
                    YEAR_QUERY),

    MONTH("MONTH",
            MONTH_QUERY + " AND " +
                    YEAR_QUERY),

    YEAR("YEAR", YEAR_QUERY);

    PeriodTypeEnum(String name, String queryFilter) {
        this.name = name;
        this.queryFilter = queryFilter;
    }

    private final String name;
    private final String queryFilter;

    public String getName() {
        return name;
    }

    public String getQueryFilter() {
        return queryFilter;
    }

    public static PeriodTypeEnum fromString(String name) throws InvalidParameterException {
        for(PeriodTypeEnum i: PeriodTypeEnum.values()) {
            if(i.getName().equals(name.toUpperCase())) {
                return i;
            }
        }

        throw new InvalidParameterException("Invalid 'name' request: " + name + ". " +
                "Available values are: " + Arrays.toString(PeriodTypeEnum.values()));

    }
}
