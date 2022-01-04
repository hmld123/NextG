package com.github.hmld.framework.security.core.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.github.hmld.common.encrypt.EncryptEngine;
import com.github.hmld.system.user.domain.SysUser;

public class PassWordCoders {
	public static String decodeUserPassWord(SysUser user) {
		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String password = user.getUserPassWord().getUserPassword();
			String salt = user.getUserPassWord().getSalt();
			String ps = EncryptEngine.decode(password.getBytes(), salt, salt.getBytes());
			String thpassword = passwordEncoder.encode(ps);
			return thpassword;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
