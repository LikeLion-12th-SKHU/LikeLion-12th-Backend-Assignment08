package org.likelion.likelioncrud.order.api.dto;

import org.likelion.likelioncrud.common.dto.BaseResponse;
import org.likelion.likelioncrud.common.error.SuccessCode;
import org.likelion.likelioncrud.order.api.dto.request.OrderSaveReqDto;
import org.likelion.likelioncrud.order.api.dto.request.OrderUpdateReqDto;
import org.likelion.likelioncrud.order.api.dto.response.OrderInfoResDto;
import org.likelion.likelioncrud.order.api.dto.response.OrderListResDto;
import org.likelion.likelioncrud.order.application.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //글 작성
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<OrderInfoResDto> orderSave(@RequestBody @Valid OrderSaveReqDto orderSaveReqDto) {
         OrderInfoResDto orderInfoResDto = orderService.orderSave(orderSaveReqDto);
        return BaseResponse.success(SuccessCode.ORDER_SAVE_SUCCESS, orderInfoResDto);
    }

    //전체조회
    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<OrderListResDto> orderFindAll() {
        OrderListResDto orderListResDto = orderService.orderFindAll();
        return BaseResponse.success(SuccessCode.GET_SUCCESS, orderListResDto);
    }

    //고객 id에 따라 한 개 조회
    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<OrderInfoResDto> orderFindById(@PathVariable("orderId") Long orderId) {
        OrderInfoResDto orderInfoResDto = orderService.orderFindById(orderId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, orderInfoResDto);
    }

    //고객에 따라 전체조회
    @GetMapping("/{consumerId}/orders")
    public BaseResponse<OrderListResDto> orderFindByALL(@PathVariable("consumerId") Long consumerId) {
        OrderListResDto orderListResDto = orderService.orderFindConsumer(consumerId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, orderListResDto);
    }

    //수정
    @PatchMapping("/{orderId}")
    public BaseResponse<OrderInfoResDto> orderUpdate(@PathVariable("orderId") Long orderId,
                                            @Valid @RequestBody OrderUpdateReqDto orderUpdateReqDto) {
         OrderInfoResDto orderInfoResDto= orderService.orderUpdate(orderId, orderUpdateReqDto);
        return BaseResponse.success(SuccessCode.CONSUMER_UPDATE_SUCCESS, orderInfoResDto);
    }

    //삭제
    @DeleteMapping("/{orderId}")
    public BaseResponse<OrderInfoResDto> orderDelete(@PathVariable("orderId") Long orderId) {
         OrderInfoResDto orderInfoResDto = orderService.orderDelete(orderId);
        return BaseResponse.success(SuccessCode.CONSUMER_DELETE_SUCCESS, orderInfoResDto);
    }
}
