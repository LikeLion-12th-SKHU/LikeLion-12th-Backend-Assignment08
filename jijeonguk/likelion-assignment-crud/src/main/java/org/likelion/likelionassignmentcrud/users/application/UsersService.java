package org.likelion.likelionassignmentcrud.users.application;

import org.likelion.likelionassignmentcrud.common.error.ErrorCode;
import org.likelion.likelionassignmentcrud.common.exception.NotFoundException;
import org.likelion.likelionassignmentcrud.users.api.dto.request.UsersSaveReqDto;
import org.likelion.likelionassignmentcrud.users.api.dto.request.UsersUpdateReqDto;
import org.likelion.likelionassignmentcrud.users.api.dto.response.UsersInfoResDto;
import org.likelion.likelionassignmentcrud.users.api.dto.response.UsersListResDto;
import org.likelion.likelionassignmentcrud.users.domain.Users;
import org.likelion.likelionassignmentcrud.users.domain.repository.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // Create
    // 수정
    @Transactional
    public UsersInfoResDto usersSave(UsersSaveReqDto usersSaveReqDto) {
        Users users = Users.builder()
                .name(usersSaveReqDto.name())
                .phoneNumber(usersSaveReqDto.phoneNumber())
                .email(usersSaveReqDto.email())
                .option(usersSaveReqDto.option())
                .build();

        usersRepository.save(users);
        return UsersInfoResDto.from(users);
    }

    // Read All
    public UsersListResDto usersFindAll(Pageable pageable) {
        Page<Users> usersList = usersRepository.findAll(pageable);

        List<UsersInfoResDto> usersInfoResDtoList = usersList.stream()
                .map(UsersInfoResDto::from)
                .collect(Collectors.toList());

            return  UsersListResDto.from(usersInfoResDtoList);
    }

    // Read One
    public UsersInfoResDto usersFindOne(Long usersId) {
        Users users = usersRepository.findById(usersId).orElseThrow(
                () -> new NotFoundException(ErrorCode.USERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.USERS_NOT_FOUND_EXCEPTION.getMessage()
                        + usersId));

        return UsersInfoResDto.from(users);
    }

    //Update
    @Transactional
    public UsersInfoResDto usersUpdate(Long usersId, UsersUpdateReqDto usersUpdateReqDto) {
        Users users = usersRepository.findById(usersId).orElseThrow(
                () -> new NotFoundException(ErrorCode.USERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.USERS_NOT_FOUND_EXCEPTION.getMessage()
                + usersId)
        );

        users.update(usersUpdateReqDto);
        return UsersInfoResDto.from(users);
    }

    //Delete
    @Transactional
    public UsersInfoResDto usersDelete(Long usersId) {
        Users users = usersRepository.findById(usersId).orElseThrow(
                () -> new NotFoundException(ErrorCode.USERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.USERS_NOT_FOUND_EXCEPTION.getMessage()
                + usersId)
        );

        usersRepository.delete(users);
        return UsersInfoResDto.from(users); // 저장된 고객 정보를 UesrsInfoResDto로 변환하여 반환한다.
    }

}
