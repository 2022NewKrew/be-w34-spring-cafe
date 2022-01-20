package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.user.UserDto;
import com.kakao.cafe.repository.user.H2UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

	private final H2UserRepository h2UserRepository;
	private final ModelMapper modelMapper;

	public void save(UserDto userDto) {
		h2UserRepository.save(modelMapper.map(userDto, User.class));
	}

	public List<User> findAll() {
		return h2UserRepository.findAll();
	}

	public UserDto findById(int id) {
		return modelMapper.map(h2UserRepository.findById(id), UserDto.class);
	}

	public UserDto findByAccId(String accId) {
		return modelMapper.map(h2UserRepository.findByAccId(accId), UserDto.class);
	}

	public UserDto findByName(String name) {
		return modelMapper.map(h2UserRepository.findByName(name), UserDto.class);
	}

	public void update(int id, UserDto userDto) {
		if (!Objects.equals(userDto.getPrevAccPw(), findById(id).getAccPw())) {
			throw new IllegalArgumentException("변경 전 비밀번호가 일치하지 않습니다.");
		}
		User user = modelMapper.map(userDto, User.class);
		user.setId(id);
		user.setAccPw(userDto.getNewAccPw());
		h2UserRepository.update(user);
	}

	public void login(UserDto userDto) {
		User user = h2UserRepository.findByAccId(userDto.getAccId());
		if (!Objects.equals(userDto.getAccPw(), user.getAccPw())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
	}
}
