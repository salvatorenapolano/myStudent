package dev.sn.mystudent.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserRequest {

    private String name;
    private String phone;
    private String address;
}
