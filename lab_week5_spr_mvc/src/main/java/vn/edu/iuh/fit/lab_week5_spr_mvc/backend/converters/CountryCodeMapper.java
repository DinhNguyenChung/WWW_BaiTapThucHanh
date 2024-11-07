package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.converters;

import com.neovisionaries.i18n.CountryCode;

public class CountryCodeMapper {
    public static Short mapToShort(CountryCode countryCode) {
        switch (countryCode) {
            case VN:
                return 1; // Gán giá trị Short cho Việt Nam
            case US:
                return 2; // Gán giá trị Short cho Mỹ
            // Thêm các quốc gia khác nếu cần
            default:
                return null; // Hoặc throw IllegalArgumentException
        }
    }
    public static String mapToString(Short countryCode) {
        if (countryCode == null) {
            return "";
        }
        switch (countryCode) {
            case 1:
                return "VietNam";
            case 2:
                return "United States";
            // Thêm các quốc gia khác nếu cần
            default:
                return "Unknown";
        }
    }
}
