package org.likelion.likelionassignmentcrud.orders.api;


import jakarta.validation.Valid;
import org.likelion.likelionassignmentcrud.common.dto.BaseResponse;
import org.likelion.likelionassignmentcrud.common.error.SuccessCode;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersSaveReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersUpdateReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersInfoResDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersListResDto;
import org.likelion.likelionassignmentcrud.orders.application.OrdersService;
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
    //수정
    //주문 작성
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<OrdersInfoResDto> ordersSave(@RequestBody @Valid OrdersSaveReqDto ordersSaveReqDto) {
        OrdersInfoResDto ordersInfoResDto = ordersService.ordersSave(ordersSaveReqDto);
        return BaseResponse.success(SuccessCode.ORDERS_SAVE_SUCCESS, ordersInfoResDto);
    }

    // 주문 전체 조회
    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<OrdersListResDto> ordersFindAll() {
        OrdersListResDto ordersListResDto = ordersService.ordersFindAll();
        return BaseResponse.success(SuccessCode.GET_SUCCESS, ordersListResDto);
    }

    // 주문 Id에 따라 주문 한개 조회
    @GetMapping("/{ordersId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<OrdersInfoResDto> ordersFindById(@PathVariable("odersId") Long ordersId){
        OrdersInfoResDto ordersInfoResDto = ordersService.ordersFindById(ordersId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, ordersInfoResDto);
    }

    // 고객에 따라 주문 전체 조회
    @GetMapping("/{usersId}/orders")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<OrdersListResDto> myPostFindAll(@PathVariable("usersId") Long usersId) {
        OrdersListResDto ordersListResDto = ordersService.ordersFindUsers(usersId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, ordersListResDto);
    }

    // 수정
    // 주문 수정
    @PatchMapping("/{ordersId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<OrdersInfoResDto> ordersUpdate(@PathVariable("ordersId") Long ordersId,
                                               @RequestBody @Valid OrdersUpdateReqDto ordersUpdateReqDto) {
        OrdersInfoResDto ordersInfoResDto = ordersService.ordersUpdate(ordersId, ordersUpdateReqDto);
        return BaseResponse.success(SuccessCode.ORDERS_UPDATE_SUCCESS, ordersInfoResDto);
    }

    //주문 삭제
    @DeleteMapping("/{ordersId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<OrdersInfoResDto> ordersDelete(@PathVariable("ordersId") Long ordersId){
        OrdersInfoResDto ordersInfoResDto = ordersService.ordersDelete(ordersId);
        return BaseResponse.success(SuccessCode.ORDERS_DELETE_SUCCESS, ordersInfoResDto);
    }

}