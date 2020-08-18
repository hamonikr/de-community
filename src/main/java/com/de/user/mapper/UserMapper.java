package com.de.user.mapper;

import java.util.List;

import com.de.enterprise.Enterprises;
import com.de.user.UserPwVO;

public interface UserMapper {
	public void updateUserPw(UserPwVO vo);
	public List<Enterprises> getEnterList(String enterName);
}