package org.likelion.likelionassignmentcrud.order.api.dto;

import jakarta.validation.Valid;
import org.likelion.likelionassignmentcrud.common.dto.BaseResponse;
import org.likelion.likelionassignmentcrud.common.error.SuccessCode;
import org.likelion.likelionassignmentcrud.order.api.dto.request.OrderSaveReqDto;
import org.likelion.likelionassignmentcrud.order.api.dto.request.OrderUpdateReqDto;
import org.likelion.likelionassignmentcrud.order.api.dto.response.OrderInfoResDto;
import org.likelion.likelionassignmentcrud.order.api.dto.response.OrderListResDto;
import org.likelion.likelionassignmentcrud.order.application.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<OrderInfoResDto> orderSave(@RequestBody @Valid OrderSaveReqDto orderSaveReqDto) {
        OrderInfoResDto orderInfoResDto = orderService.orderSave(orderSaveReqDto);

        return BaseResponse.success(SuccessCode.ORDER_SAVE_SUCCESS, orderInfoResDto);
    }

    @GetMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<OrderListResDto> orderFindMember(@PathVariable("memberId") Long memberId) {
        OrderListResDto orderListResDto = orderService.orderFindMember(memberId);

        return BaseResponse.success(SuccessCode.GET_SUCCESS, orderListResDto);
    }

    @PatchMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<OrderInfoResDto> orderUpdate(@PathVariable("orderId") Long orderId, @RequestBody @Valid OrderUpdateReqDto orderUpdateReqDto) {
        OrderInfoResDto orderInfoResDto = orderService.orderUpdate(orderId, orderUpdateReqDto);

        return BaseResponse.success(SuccessCode.ORDER_UPDATE_SUCCESS, orderInfoResDto);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<OrderInfoResDto> orderDelete(@PathVariable("orderId") Long orderId) {
        OrderInfoResDto orderInfoResDto = orderService.orderDelete(orderId);

        return BaseResponse.success(SuccessCode.ORDER_DELETE_SUCCESS, orderInfoResDto);
    }
}
