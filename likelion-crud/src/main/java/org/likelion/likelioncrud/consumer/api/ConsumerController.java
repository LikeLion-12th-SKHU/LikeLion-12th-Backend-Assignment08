package org.likelion.likelionassignmentcrud.consumer.api;

import org.likelion.likelionassignmentcrud.consumer.api.dto.request.ConsumerSaveReqDto;
import org.likelion.likelionassignmentcrud.consumer.api.dto.request.ConsumerUpdateReqDto;
import org.likelion.likelionassignmentcrud.consumer.api.dto.response.ConsumerInfoResDto;
import org.likelion.likelionassignmentcrud.consumer.api.dto.response.ConsumerListResDto;
import org.likelion.likelionassignmentcrud.consumer.application.ConsumerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private final ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService){
        this.consumerService = consumerService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> consumerSave(@RequestBody ConsumerSaveReqDto consumerSaveReqDto) {
        consumerService.consumerSave(consumerSaveReqDto);
        return new ResponseEntity<>("사용자 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/consumers")
    public ResponseEntity<ConsumerListResDto> consumerFindAll() {
        ConsumerListResDto consumerListResDto = consumerService.consumerFindAll();
        return new ResponseEntity<>(consumerListResDto, HttpStatus.OK);
    }

    @GetMapping("/{consumerId}")
    public ResponseEntity<ConsumerInfoResDto> consumerFindOne(@PathVariable("consumerId") Long consumerId) {
        ConsumerInfoResDto consumerInfoResDto = consumerService.consumerFindOne(consumerId);
        return new ResponseEntity<>(consumerInfoResDto, HttpStatus.OK);
    }

    @PatchMapping("/{consumerId}")
    public ResponseEntity<String>consumerUpdate(@PathVariable("consumerId") Long consumerId,
                                              @RequestBody ConsumerUpdateReqDto consumerUpdateReqDto){
        consumerService.consumerUpdate(consumerId, consumerUpdateReqDto);
        return new ResponseEntity<>("고객 수정!",HttpStatus.OK);
    }

    @DeleteMapping("/{consumerId}")
    public ResponseEntity<String> consumerDelete(@PathVariable("consumerId") Long consumerId)
    {
        consumerService.consumerDelete(consumerId);
        return new ResponseEntity<>("고객 삭제!", HttpStatus.OK);
    }
}
