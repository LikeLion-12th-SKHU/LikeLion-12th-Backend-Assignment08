package org.likelion.basic.shop.api.dto;

import jakarta.validation.Valid;
import org.likelion.basic.common.dto.BaseResponse;
import org.likelion.basic.common.error.SuccessCode;
import org.likelion.basic.shop.api.dto.request.ShopSaveReqDto;
import org.likelion.basic.shop.api.dto.request.ShopUpdateReqDto;
import org.likelion.basic.shop.api.dto.response.ShopInfoResDto;
import org.likelion.basic.shop.api.dto.response.ShopListResDto;
import org.likelion.basic.shop.application.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) { this.shopService = shopService; }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<ShopInfoResDto> shopSave(@RequestBody @Valid ShopSaveReqDto shopSaveReqDto) {
        ShopInfoResDto shopInfoResDto = shopService.shopSave(shopSaveReqDto);
        return BaseResponse.success(SuccessCode.SHOP_SAVE_SUCCESS, shopInfoResDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ShopListResDto> shopFindAll() {
        ShopListResDto shopListResDto = shopService.shopFindAll();
        return BaseResponse.success(SuccessCode.GET_SUCCESS, shopListResDto);
    }

    @GetMapping("/{shopId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ShopInfoResDto> shopFindById(@PathVariable("shopId") Long shopId) {
        ShopInfoResDto shopInfoResDto = shopService.shopFindById(shopId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, shopInfoResDto);
    }

    @GetMapping("/{shopId}/shops")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ShopListResDto> myShopFindAll(@PathVariable("shopId") Long customerId) {
        ShopListResDto shopListResDto = shopService.shopFindMember(customerId);
        return BaseResponse.success(SuccessCode.GET_SUCCESS, shopListResDto);
    }

    @PatchMapping("/{shopId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ShopInfoResDto> shopUpdate(@PathVariable("shopId") Long shopId,
                                                 @RequestBody @Valid ShopUpdateReqDto shopUpdateReqDto) {
        ShopInfoResDto shopInfoResDto = shopService.shopUpdate(shopId, shopUpdateReqDto);
        return BaseResponse.success(SuccessCode.SHOP_UPDATE_SUCCESS, shopInfoResDto);
    }

    @DeleteMapping("/{shopId}")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<ShopInfoResDto> shopDelete(@PathVariable("shopId") Long shopId) {
        ShopInfoResDto shopInfoResDto = shopService.shopDelete(shopId);
        return BaseResponse.success(SuccessCode.SHOP_DELETE_SUCCESS, shopInfoResDto);
    }

}
