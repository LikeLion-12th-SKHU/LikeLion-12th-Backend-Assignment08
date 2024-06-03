package org.likelion.assignmentvalid.orders.api;

import jakarta.validation.Valid;
import org.likelion.assignmentvalid.common.dto.BaseResponse;
import org.likelion.assignmentvalid.common.error.SuccessCode;
import org.likelion.assignmentvalid.orders.api.dto.request.OrdersSaveReqDto;
import org.likelion.assignmentvalid.orders.api.dto.request.OrdersUpdateReqDto;
import org.likelion.assignmentvalid.orders.api.dto.response.OrdersInfoResDto;
import org.likelion.assignmentvalid.orders.api.dto.response.OrdersListResDto;
import org.likelion.assignmentvalid.orders.application.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<OrdersInfoResDto> ordersSave(@RequestBody @Valid OrdersSaveReqDto ordersSaveReqDto) {
        OrdersInfoResDto ordersInfoResDto = ordersService.ordersSave(ordersSaveReqDto);
        return BaseResponse.success(SuccessCode.ORDERS_SAVE_SUCCESS, ordersInfoResDto);
    }

    // 상품에 따른 주문리스트 불러오기
    @GetMapping("/{productId}")
    public BaseResponse<OrdersListResDto> myOrdersFindAll(@PathVariable Long productId) {
        OrdersListResDto ordersListResDto =  ordersService.ordersFindProduct(productId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, ordersListResDto);
    }

    // 업데이트
    @PatchMapping("/{ordersId}")
    public BaseResponse<OrdersInfoResDto> ordersUpdate(@PathVariable Long ordersId,
                                              @RequestBody OrdersUpdateReqDto ordersUpdateReqDto) {
        OrdersInfoResDto ordersInfoResDto = ordersService.ordersUpdate(ordersId, ordersUpdateReqDto);
        return BaseResponse.success(SuccessCode.ORDERS_UPDATE_SUCCESS, ordersInfoResDto);
    }

    @DeleteMapping("/{ordersId}")
    public BaseResponse<OrdersInfoResDto> ordersDelete(@PathVariable Long ordersId) {
        OrdersInfoResDto ordersInfoResDto = ordersService.ordersDelete(ordersId);
        return BaseResponse.success(SuccessCode.ORDERS_DELETE_SUCCESS, ordersInfoResDto);
    }

}
