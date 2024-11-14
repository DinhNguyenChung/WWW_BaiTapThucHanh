package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.converters;

import com.neovisionaries.i18n.CountryCode;

public class CountryCodeMapper {
    public static Short mapToShort(CountryCode countryCode) {
        switch (countryCode) {
            case VN:
                return 1;  // Việt Nam
            case US:
                return 2;  // Hoa Kỳ
            case CA:
                return 3;  // Canada
            case FR:
                return 4;  // Pháp
            case DE:
                return 5;  // Đức
            case JP:
                return 6;  // Nhật Bản
            case CN:
                return 7;  // Trung Quốc
            case IN:
                return 8;  // Ấn Độ
            case GB:
                return 9;  // Vương Quốc Anh
            case AU:
                return 10; // Úc
            case IT:
                return 11; // Ý
            case BR:
                return 12; // Brazil
            case RU:
                return 13; // Nga
            case KR:
                return 14; // Hàn Quốc
            case MX:
                return 15; // Mexico
            case ES:
                return 16; // Tây Ban Nha
            case ID:
                return 17; // Indonesia
            case SA:
                return 18; // Ả Rập Saudi
            case AR:
                return 19; // Argentina
            case NL:
                return 20; // Hà Lan
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
                return "Vietnam";
            case 2:
                return "United States";
            case 3:
                return "Canada";
            case 4:
                return "France";
            case 5:
                return "Germany";
            case 6:
                return "Japan";
            case 7:
                return "China";
            case 8:
                return "India";
            case 9:
                return "United Kingdom";
            case 10:
                return "Australia";
            case 11:
                return "Italy";
            case 12:
                return "Brazil";
            case 13:
                return "Russia";
            case 14:
                return "South Korea";
            case 15:
                return "Mexico";
            case 16:
                return "Spain";
            case 17:
                return "Indonesia";
            case 18:
                return "Saudi Arabia";
            case 19:
                return "Argentina";
            case 20:
                return "Netherlands";
            // Thêm các quốc gia khác nếu cần
            default:
                return "Unknown";
        }
    }
}
