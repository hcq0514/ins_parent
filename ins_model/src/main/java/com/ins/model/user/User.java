package com.ins.model.user;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : hcq
 * @date : 2019/7/12
 */
@Data
@Accessors(chain = true)
public class User {
    private String addCurrentUsername;
    private String addCurrentUserPassword;
    private String addCurrentUserBio;
    private String addCurrentUserPhoto;
}
