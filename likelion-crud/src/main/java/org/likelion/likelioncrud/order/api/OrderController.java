package org.likelion.likelionassignmentcrud.order.api;

import org.likelion.likelionassignmentcrud.order.api.dto.request.OrderSaveReqDto;
import org.likelion.likelionassignmentcrud.order.api.dto.request.OrderUpdateReqDto;
import org.likelion.likelionassignmentcrud.order.api.dto.response.OrderListResDto;
import org.likelion.likelionassignmentcrud.order.application.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> orderSave(@RequestBody OrderSaveReqDto orderSaveReqDto) {
        orderService.orderSave(orderSaveReqDto);
        return new ResponseEntity<>("주문 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/{consumerId}")
    public ResponseEntity<OrderListResDto> myPostFindAll(@PathVariable("consumerId") Long consumerId) {
        OrderListResDto orderListResDto = orderService.orderFindConsumer(consumerId);
        return new ResponseEntity<>(orderListResDto, HttpStatus.OK);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<String> orderUpdate(@PathVariable("orderId") Long orderId,
                                             @RequestBody OrderUpdateReqDto orderUpdateReqDto) {
        orderService.orderUpdate(orderId, orderUpdateReqDto);
        return new ResponseEntity<>("주문 수정", HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> orderDelete(@PathVariable("orderId") Long orderId) {
        orderService.orderDelete(orderId);
        return new ResponseEntity<>("주문 삭제", HttpStatus.OK);
    }
}
