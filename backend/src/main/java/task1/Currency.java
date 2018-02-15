package task1;

import java.util.Date;

public class Currency {

    private static final String SPACE = " ";

    private String code;

    private Float value;

    private Long timestamp;

    public Currency(final String code, final Float value, final Long timestamp) {
        this.code = code;
        this.value = value;
        this.timestamp = timestamp;
    }

    public Float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return code + SPACE + value.toString() + new Date(timestamp);
    }
}