package org.likelion.likelionassignmentcrud.developer.application;

import org.likelion.likelionassignmentcrud.common.error.ErrorCode;
import org.likelion.likelionassignmentcrud.common.exception.NotFoundException;
import org.likelion.likelionassignmentcrud.developer.api.dto.request.DeveloperSaveReqDto;
import org.likelion.likelionassignmentcrud.developer.api.dto.request.DeveloperUpdateReqDto;
import org.likelion.likelionassignmentcrud.developer.api.dto.response.DeveloperInfoResDto;
import org.likelion.likelionassignmentcrud.developer.api.dto.response.DeveloperListResDto;
import org.likelion.likelionassignmentcrud.developer.domain.Developer;
import org.likelion.likelionassignmentcrud.developer.domain.repository.DeveloperRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DeveloperService {
    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    // 개발사 저장
    @Transactional
    public DeveloperInfoResDto developerSave(DeveloperSaveReqDto developerSaveReqDto) {
        Developer developer = Developer.builder()
                .name(developerSaveReqDto.name())
                .country(developerSaveReqDto.country())
                .establishedDate(developerSaveReqDto.establishedDate())
                .build();

        developerRepository.save(developer);
        return DeveloperInfoResDto.from(developer);
    }

    // 개발사 조회(모두)
    public DeveloperListResDto developerFindAll(Pageable pageable) {
        Page<Developer> developers = developerRepository.findAll(pageable);

        List<DeveloperInfoResDto> developerInfoResDtoList = developers.stream()
                .map(DeveloperInfoResDto::from)
                .toList();

        return DeveloperListResDto.from(developerInfoResDtoList);
    }

    // 개발사 조회(하나)
    public DeveloperInfoResDto developerFindOne(Long developerId) {
        Developer developer = developerRepository.findById(developerId).orElseThrow(
                () -> new NotFoundException(ErrorCode.DEVELOPERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.DEVELOPERS_NOT_FOUND_EXCEPTION.getMessage()
                                + developerId)
        );

        return DeveloperInfoResDto.from(developer);
    }

    // 개발사 업데이트(이름, 국가, 설립일)
    @Transactional
    public DeveloperInfoResDto developerUpdate(Long developerId, DeveloperUpdateReqDto developerUpdateReqDto) {
        Developer developer = developerRepository.findById(developerId).orElseThrow(
                () -> new NotFoundException(ErrorCode.DEVELOPERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.DEVELOPERS_NOT_FOUND_EXCEPTION.getMessage()
                                + developerId)
        );

        developer.update(developerUpdateReqDto);
        return DeveloperInfoResDto.from(developer);
    }

    // 개발사 삭제
    @Transactional
    public DeveloperInfoResDto developerDelete(Long developerId) {
        Developer developer = developerRepository.findById(developerId).orElseThrow(
                () -> new NotFoundException(ErrorCode.DEVELOPERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.DEVELOPERS_NOT_FOUND_EXCEPTION.getMessage()
                                + developerId)
        );

        developerRepository.delete(developer);
        return DeveloperInfoResDto.from(developer);
    }
}
