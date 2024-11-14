package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models;

import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.*;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.converters.CountryCodeMapper;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address", schema = "works")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "street", length = 150)
    private String street;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "country")
    private Short country;

    @Column(name = "number", length = 20)
    private String number;

    @Column(name = "zipcode", length = 7)
    private String zipcode;

    public Address(String street, String city, Short country, String number) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.number = number;
    }

    public Address(String number, String street, String city, String zipcode, Short country) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
    }

    @Override
    public String toString() {
        // Kiểm tra xem country có hợp lệ không trước khi gọi getByCode
        if (country != null) {
            CountryCode countryCode = CountryCode.getByCode(country); // Sử dụng getByCode thay vì getByNumericCode
            if (countryCode != null) {
                return number + "," + street + ',' + city + ',' + zipcode + ',' + countryCode.getName();
            }
        }
        // Nếu country không hợp lệ hoặc không tìm thấy mã quốc gia, trả về "Unknown"
        return number + "," + street + ',' + city + ',' + zipcode + ", Unknown";
    }

}