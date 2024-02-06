package com.tenco.bank.dto;

import lombok.Data;

@Data
public class KakaoAccount {

	public Boolean profileNicknameNeedsAgreement;
	public Boolean profileImageNeedsAgreement;
	public Profile profile;

}
