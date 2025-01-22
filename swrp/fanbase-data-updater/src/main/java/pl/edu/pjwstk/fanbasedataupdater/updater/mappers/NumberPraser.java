package pl.edu.pjwstk.fanbasedataupdater.updater.mappers;

public class NumberPraser {
    public static <T extends Number> T  prase(String value, Class<T> type) {
        if (value != null && !value.equals("unknown")) {
            try {
                if (type == Integer.class) {
                    return type.cast(Integer.valueOf(value));
                } else if (type == Long.class) {
                    return type.cast(Long.valueOf(value));
                } else if (type == Double.class) {
                    return type.cast(Double.valueOf(value));
                } else {
                    throw new IllegalArgumentException("Unsupported type: " + type.getSimpleName());
                }
            } catch (NumberFormatException e) {
                return prase("-1", type);
            }
        } else {
            return prase("-1", type);
        }
    }
}
