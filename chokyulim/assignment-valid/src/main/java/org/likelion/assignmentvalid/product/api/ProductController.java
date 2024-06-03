// 서비스 요청 받아서

package org.likelion.assignmentvalid.product.api;


import jakarta.validation.Valid;
import org.likelion.assignmentvalid.common.dto.BaseResponse;
import org.likelion.assignmentvalid.common.error.SuccessCode;
import org.likelion.assignmentvalid.product.api.dto.request.ProductSaveReqDto;
import org.likelion.assignmentvalid.product.api.dto.request.ProductUpdateReqDto;
import org.likelion.assignmentvalid.product.api.dto.response.ProductInfoResDto;
import org.likelion.assignmentvalid.product.api.dto.response.ProductListResDto;
import org.likelion.assignmentvalid.product.application.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public BaseResponse<ProductInfoResDto> productSave(@RequestBody @Valid ProductSaveReqDto productSaveReqDto) {
        ProductInfoResDto productInfoResDto = productService.productSave(productSaveReqDto);
        return BaseResponse.success(SuccessCode.PRODUCT_SAVE_SUCCESS, productInfoResDto);
    }

    @GetMapping
    public BaseResponse<ProductListResDto> productFindAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            @RequestParam(value = "sort", defaultValue = "productId,asc") String sort
    ) {
        Pageable pageable;
        if (sort.isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.by("productId").ascending());
        } else {
            String[] sortParams = sort.split(",");
            Sort sortOrder = Sort.by(Sort.Direction.fromString(sortParams[1]), sortParams[0]);
            pageable = PageRequest.of(page, size, sortOrder);
        }
        ProductListResDto productListResDto = productService.productFindAll(pageable);

        return BaseResponse.success(SuccessCode.GET_SUCCESS, productListResDto);
    }

    @PatchMapping("/{productId}")
    public BaseResponse<ProductInfoResDto> productUpdate(@PathVariable Long productId,
                                                @RequestBody ProductUpdateReqDto productupdateReqDto) {
        ProductInfoResDto productInfoResDto = productService.productUpdate(productId, productupdateReqDto);
        return BaseResponse.success(SuccessCode.PRODUCT_UPDATE_SUCCESS, productInfoResDto);
    }

    @DeleteMapping("/{productId}")
    public BaseResponse<ProductInfoResDto> productDelete(@PathVariable Long productId) {
        ProductInfoResDto productInfoResDto = productService.productDelete(productId);
        return BaseResponse.success(SuccessCode.PRODUCT_DELETE_SUCCESS, productInfoResDto);
    }
}
