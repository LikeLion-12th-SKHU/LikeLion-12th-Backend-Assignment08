package org.likelion.likelioncrud.consumer.application;

import org.likelion.likelioncrud.common.exception.NotFoundException;
import org.likelion.likelioncrud.consumer.api.dto.request.ConsumerSaveReqDto;
import org.likelion.likelioncrud.consumer.api.dto.request.ConsumerUpdateReqDto;
import org.likelion.likelioncrud.consumer.api.dto.response.ConsumerInfoResDto;
import org.likelion.likelioncrud.consumer.api.dto.response.ConsumerListResDto;
import org.likelion.likelioncrud.consumer.domain.Consumer;
import org.likelion.likelioncrud.consumer.domain.repository.ConsumerRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.likelion.likelioncrud.common.error.ErrorCode;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly =true)
public class ConsumerService {
    private final ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    // Create
    @Transactional
    public ConsumerInfoResDto consumerSave(ConsumerSaveReqDto consumerSaveReqDto) {
        Consumer consumer = Consumer.builder()
                .name(consumerSaveReqDto.name())
                .age(consumerSaveReqDto.age())
                .email(consumerSaveReqDto.email())
                .part(consumerSaveReqDto.part())
                .build();

        consumerRepository.save(consumer);
        return ConsumerInfoResDto.from(consumer);
    }

    // Read All
    public ConsumerListResDto consumerFindAll(Pageable pageable) {
        Page<Consumer> consumers = consumerRepository.findAll(pageable);

        List<ConsumerInfoResDto> consumerInfoResDtoList = consumers.stream()
                .map(ConsumerInfoResDto::from)
                .collect(Collectors.toList());

        return ConsumerListResDto.from(consumerInfoResDtoList);
    }

    // Read One
    public ConsumerInfoResDto consumerFindOne(Long consumerId) {
        Consumer consumer = consumerRepository.findById(consumerId).orElseThrow(
                () -> new NotFoundException(ErrorCode.CONSUMERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.CONSUMERS_NOT_FOUND_EXCEPTION.getMessage()
                                + consumerId));

        return ConsumerInfoResDto.from(consumer);

    }

    //update
    @Transactional
    public ConsumerInfoResDto consumerUpdate(Long consumerId, ConsumerUpdateReqDto consumerUpdateReqDto){
        //준영속 상태!!
        Consumer consumer = consumerRepository.findById(consumerId).orElseThrow
                (() -> new NotFoundException(ErrorCode.CONSUMERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.CONSUMERS_NOT_FOUND_EXCEPTION.getMessage()
                                + consumerId)
                );
        consumer.update(consumerUpdateReqDto);
        return ConsumerInfoResDto.from(consumer);
    }

    //delete
    @Transactional
    public ConsumerInfoResDto consumerDelete(Long consumerId){
        Consumer consumer = consumerRepository.findById(consumerId).orElseThrow(
                () -> new NotFoundException(ErrorCode.CONSUMERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.CONSUMERS_NOT_FOUND_EXCEPTION.getMessage()
                                + consumerId)
        );

        consumerRepository.delete(consumer);
        return ConsumerInfoResDto.from(consumer);
    }

}
