package org.likelion.likelionassignmentcrud.club.application;

import org.likelion.likelionassignmentcrud.club.api.dto.request.ClubSaveReqDto;
import org.likelion.likelionassignmentcrud.club.api.dto.request.ClubUpdateReqDto;
import org.likelion.likelionassignmentcrud.club.api.dto.response.ClubInfoResDto;
import org.likelion.likelionassignmentcrud.club.api.dto.response.ClubListResDto;
import org.likelion.likelionassignmentcrud.club.domain.Club;
import org.likelion.likelionassignmentcrud.club.domain.repository.ClubRepository;
import org.likelion.likelionassignmentcrud.common.error.ErrorCode;
import org.likelion.likelionassignmentcrud.common.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClubService {
    private final ClubRepository clubRepository;

    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    // Create
    @Transactional
    public ClubInfoResDto clubSave(ClubSaveReqDto clubSaveReqDto) {
        Club club = Club.builder()
                .name(clubSaveReqDto.name())
                .clubType(clubSaveReqDto.clubType())
                .part(clubSaveReqDto.part())
                .build();

        clubRepository.save(club);
        return ClubInfoResDto.from(club);
    }

    // Read All
    public ClubListResDto clubFindAll(Pageable pageable) {
        Page<Club> clubs = clubRepository.findAll(pageable);

        List<ClubInfoResDto> clubInfoResDtoList = clubs.stream()
                .map(ClubInfoResDto::from)
                .toList();

        return ClubListResDto.from(clubInfoResDtoList);
    }

    // Read One
    public ClubInfoResDto clubFindOne(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(
                () -> new NotFoundException(ErrorCode.CLUBS_NOT_FOUND_EXCEPTION,
                        ErrorCode.CLUBS_NOT_FOUND_EXCEPTION.getMessage() + clubId));

        return ClubInfoResDto.from(club);
    }

    // Update
    @Transactional
    public ClubInfoResDto clubUpdate(Long clubId, ClubUpdateReqDto clubUpdateReqDto) {
        Club club = clubRepository.findById(clubId).orElseThrow(
                () -> new NotFoundException(ErrorCode.CLUBS_NOT_FOUND_EXCEPTION,
                        ErrorCode.CLUBS_NOT_FOUND_EXCEPTION.getMessage() + clubId));
        club.update(clubUpdateReqDto);
        return ClubInfoResDto.from(club);
    }

    // Delete
    @Transactional
    public ClubInfoResDto clubDelete(Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow(
                () -> new NotFoundException(ErrorCode.CLUBS_NOT_FOUND_EXCEPTION,
                        ErrorCode.CLUBS_NOT_FOUND_EXCEPTION.getMessage() + clubId));

        clubRepository.delete(club);
        return ClubInfoResDto.from(club);
    }
}