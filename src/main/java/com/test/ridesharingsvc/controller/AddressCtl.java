package com.test.ridesharingsvc.controller;

import com.test.ridesharingsvc.exception.NotFound;
import com.test.ridesharingsvc.model.Address;
import com.test.ridesharingsvc.model.User;
import com.test.ridesharingsvc.model.payload.Paginate;
import com.test.ridesharingsvc.model.payload.RegisterResponse;
import com.test.ridesharingsvc.model.payload.Response;
import com.test.ridesharingsvc.repository.AddressRepo;
import com.test.ridesharingsvc.repository.UsersRepo;
import com.test.ridesharingsvc.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddressCtl {
    @Autowired
    HttpServletResponse httpResponse;

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    UsersRepo userRepo;

    @PostMapping("/address")
    public ResponseEntity<?> saveAddress(@Valid @RequestBody Address address){
        HttpStatus status;

        Long userId = Utility.getUserId(userRepo);
        status = HttpStatus.OK;
        Address result = userRepo.findByUserId(userId).map(user -> {
            address.setUsers(user);
            return addressRepo.save(address);
        }).orElseThrow(() -> new NotFound(""));
        Response resp = new Response();
        resp.setCode(httpResponse.getStatus());
        resp.setMessage("Success");
        resp.setData(new RegisterResponse(result.getAddressId()));
        return new ResponseEntity(resp, HttpStatus.resolve(httpResponse.getStatus()));
    }

    @PutMapping("/address/{address_id}")
    public ResponseEntity<?> updateAddress(@PathVariable (value = "address_id") Long addressId,
                                 @Valid @RequestBody Address addressRequest) {
        Long userId = Utility.getUserId(userRepo);
        if(!userRepo.existsById(userId)) {
            throw new NotFound("UserId " + userId + " not found");
        }

        Address result = addressRepo.findById(addressId).orElseThrow(()-> new NotFound("Not Found"));
        result.setJalan(addressRequest.getJalan());
        result.setKelurahan(addressRequest.getKelurahan());
        result.setKecamatan(addressRequest.getKecamatan());
        result.setKota(addressRequest.getKota());
        Address updateUser = addressRepo.save(result);
        Response resp = new Response();
        resp.setCode(httpResponse.getStatus());
        resp.setMessage("");
        resp.setData(updateUser);
        return new ResponseEntity(resp, HttpStatus.resolve(httpResponse.getStatus()));
    }

    @GetMapping("/address")
    public ResponseEntity<?> getAllAddress(@RequestParam(value = "limit", defaultValue = "10") int limit, @RequestParam(value = "page", defaultValue = "1") int page){
        Long userId = Utility.getUserId(userRepo);
        Pageable pageable = PageRequest.of(page - 1, limit);
        Optional<User> user = userRepo.findByUserId(userId);
        Page<Address> result = addressRepo.findByUsers(user.get(), pageable);
        long totalData = result.getTotalElements();
        long totalPage = totalData / 10;
        Paginate paginate = new Paginate();
        paginate.setLimit(limit);
        paginate.setPage(page);
        paginate.setTotalData(totalData);
        paginate.setTotalPage(totalPage);
        Response resp = new Response();
        resp.setCode(httpResponse.getStatus());
        resp.setMessage("");
        resp.setData(result.getContent());
        resp.setPaginate(paginate);
        return new ResponseEntity(resp, HttpStatus.resolve(httpResponse.getStatus()));
    }

    @DeleteMapping("/address/{address_id}")
    public ResponseEntity<?> deleteAddress(@PathVariable (value = "address_id") Long addressId) {
        Long userId = Utility.getUserId(userRepo);
        if(!userRepo.existsById(userId)) {
            throw new NotFound("UserId " + userId + " not found");
        }

        Address result = addressRepo.findById(addressId).orElseThrow(()-> new NotFound("Not Found"));
        addressRepo.delete(result);
        HttpStatus status = HttpStatus.OK;
        Response resp = new Response();
        resp.setCode(status.value());
        resp.setMessage("Deleted AddressId " + result.getAddressId());
        resp.setData(result);
        return new ResponseEntity(resp, status);
    }
}
