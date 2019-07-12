package com.ins.user.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.jws.HandlerChain;

/**
 * @author : hcq
 * @date : 2019/7/12
 */
@Data
@Accessors(chain = true)
public class User {
    String addCurrentUsername;
    String addCurrentUserPassword;
    String addCurrentUserBio;
    String addCurrentUserPhoto;
}
