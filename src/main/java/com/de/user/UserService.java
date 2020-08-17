package com.de.user;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.de.newsletteruser.MailingUserRepository;
import com.de.user.mapper.UserMapper;


@Service
@Transactional
public class UserService {
	private final int NEWSLATTER_NO = 0;
	private final int NEWSLATTER_YES = 1;
	
	@Autowired
	UserRepository sr;
	
	@Autowired
	MailingUserRepository mr;
	
	@Autowired
	UserMapper sm;

	public Optional<Users> findById(int seq) throws Exception {
		return sr.findById(seq);
	}
	
	public Page<Users> findAll(Pageable pageable) throws Exception {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);

		// 기본 정렬 설정
		String[] sort = pageable.getSort().toString().split(": ");
		String sortColumn = "registerDate";
		Direction direction = Sort.Direction.DESC;

		// 사용자 설정에 따른 변경
		if(1 < sort.length && !"UNSORTED".equals(sort[0])) {
			if("ASC".equals(sort[1])) direction = Sort.Direction.ASC;
			sortColumn = sort[0];
		}

		pageable = PageRequest.of(page, 5, new Sort(direction, sortColumn));
		Page<Users> list = sr.findAll(pageable);
		return list;
	}
	
	public void updateUser(Users vo, boolean newslater) throws Exception {
		// 사용자 정보 업데이트
		Optional<Users> e = sr.findById(vo.getUserNo());
		if (e.isPresent()) {
			e.get().setUserName(vo.getUserName());
			e.get().setUserEmail(vo.getUserEmail());
			e.get().setAboutMe(vo.getAboutMe());
			e.get().setUserUrl(vo.getUserUrl());
			// 회사명 추가필요
			sr.save(vo);
		}
		
		// 뉴스레터 업데이트
//		Optional<MailingUserList> mailingUser = mr.findById(vo.getUserNo());
//		if(mailingUser.isPresent()) {
//			if(newslater) mailingUser.get().setActive(NEWSLATTER_YES);
//			else mailingUser.get().setActive(NEWSLATTER_NO);
//			
//			mailingUser.get().setEmail(vo.getUserEmail());
//			mailingUser.get().setName(vo.getUserName());
//			mr.save(mailingUser.get());
//		}
	}

	public boolean updateUserPw(UserPwVO vo) {
		boolean updateVal = false;
		Optional<Users> e = sr.findById(vo.getUserNo());
		if (e.isPresent()) {
			if(e.get().getUserPassword().equals(vo.getUserPassword())) {
				e.get().setUserPassword(vo.getUserPasswordNew());
				sm.updateUserPw(vo);
				updateVal = true;
			}
		}
		return updateVal;
	}
}