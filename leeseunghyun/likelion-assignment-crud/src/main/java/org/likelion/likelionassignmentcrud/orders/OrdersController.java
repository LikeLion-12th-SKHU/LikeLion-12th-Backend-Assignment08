package org.likelion.likelionassignmentcrud.orders;

import jakarta.validation.Valid;
import org.likelion.likelionassignmentcrud.common.dto.BaseResponse;
import org.likelion.likelionassignmentcrud.common.error.SuccessCode;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersSaveReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersUpdateReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersInfoResDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersListResDto;
import org.likelion.likelionassignmentcrud.orders.application.OrdersService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<OrdersInfoResDto> ordersSave(@RequestBody @Valid OrdersSaveReqDto ordersSaveReqDto) {
        OrdersInfoResDto ordersInfoResDto = ordersService.ordersSave(ordersSaveReqDto);
        return BaseResponse.success(SuccessCode.ORDERS_SAVE_SUCCESS, ordersInfoResDto);
    }

    @GetMapping("/{foodId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<OrdersListResDto> myOrdersFindAll(@PathVariable("foodId") Long foodId) {
        OrdersListResDto ordersListResDto = ordersService.ordersFindFood(foodId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, ordersListResDto);
    }

    @PatchMapping("/{ordersId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<OrdersInfoResDto> ordersUpdate(@PathVariable("ordersId") Long ordersId,
                                                       @RequestBody @Valid OrdersUpdateReqDto ordersUpdateReqDto) {
        OrdersInfoResDto ordersInfoResDto = ordersService.ordersUpdate(ordersId, ordersUpdateReqDto);
        return BaseResponse.success(SuccessCode.ORDERS_UPDATE_SUCCESS, ordersInfoResDto);
    }

    @DeleteMapping("/{ordersId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<OrdersInfoResDto> ordersDelete(@PathVariable("ordersId") Long ordersId) {
        OrdersInfoResDto ordersInfoResDto = ordersService.ordersDelete(ordersId);
        return BaseResponse.success(SuccessCode.ORDERS_DELETE_SUCCESS, ordersInfoResDto);
    }
}
