
package com.tenco.bank.dto;

import lombok.Data;

@Data
public class Profile {

    public String nickname;
    public String thumbnailImageUrl;
    public String profileImageUrl;
    public Boolean isDefaultImage;
}
