package net.hft.dbproject.weatherapp.enums;

/**
 *
 * @author Jan
 */
public enum CSSFile {

    CSS_TEST {
                @Override
                public String toString() {
                    return "unknown";
                }
            },
    CSS_DEFAULT {
                @Override
                public String toString() {
                    return "/styles/CommonStyles.css";
                }
            };

}
