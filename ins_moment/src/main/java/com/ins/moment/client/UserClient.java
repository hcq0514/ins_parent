package com.ins.moment.client;

import com.ins.api.moment.MomentControllerApi;
import com.ins.api.user.UserControllerApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("INS-USER-SERVICE")
public interface UserClient extends UserControllerApi {

}
