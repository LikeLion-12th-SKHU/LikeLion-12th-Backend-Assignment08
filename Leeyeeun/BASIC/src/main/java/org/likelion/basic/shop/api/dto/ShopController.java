package org.likelion.likelionassignmentcrud.shop.api.dto;

import org.likelion.likelionassignmentcrud.shop.api.dto.request.ShopSaveReqDto;
import org.likelion.likelionassignmentcrud.shop.api.dto.request.ShopUpdateReqDto;
import org.likelion.likelionassignmentcrud.shop.api.dto.response.ShopListResDto;
import org.likelion.likelionassignmentcrud.shop.application.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) { this.shopService = shopService; }

    @PostMapping("/save")
    public ResponseEntity<String> shopSave(@RequestBody ShopSaveReqDto shopSaveReqDto) {
        shopService.shopSave(shopSaveReqDto);
        return new ResponseEntity<>("내용 저장!", HttpStatus.CREATED);
    }

    @GetMapping("/{shopId}")
    public ResponseEntity<ShopListResDto> myShopFindAll(@PathVariable("shopId") Long customerId) {
        ShopListResDto shopListResDto = shopService.shopFindMember(customerId);
        return new ResponseEntity<>(shopListResDto, HttpStatus.OK);
    }

    @PatchMapping("/{shopId}")
    public ResponseEntity<String> shopUpdate(@PathVariable("shopId") Long shopId,
                                                 @RequestBody ShopUpdateReqDto shopUpdateReqDto) {
        shopService.shopUpdate(shopId, shopUpdateReqDto);
        return new ResponseEntity<>("내용 수정", HttpStatus.OK);
    }

    @DeleteMapping("/{shopId}")
    public ResponseEntity<String> shopDelete(@PathVariable("shopId") Long shopId) {
        shopService.shopDelete(shopId);
        return new ResponseEntity<>("내용 삭제", HttpStatus.OK);
    }

}
