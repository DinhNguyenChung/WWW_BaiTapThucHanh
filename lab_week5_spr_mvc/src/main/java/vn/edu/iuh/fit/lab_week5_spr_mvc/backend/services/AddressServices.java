package vn.edu.iuh.fit.lab_week5_spr_mvc.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.models.Address;
import vn.edu.iuh.fit.lab_week5_spr_mvc.backend.repositories.AddressRepository;

@Service
public class AddressServices {
    @Autowired
    private AddressRepository addressRepository;
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }
}
