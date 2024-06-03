package org.likelion.assignmentvalid.product.api.dto.response;

import lombok.Builder;
import org.likelion.assignmentvalid.product.domain.Part;
import org.likelion.assignmentvalid.product.domain.Product;

@Builder
public record ProductInfoResDto(
        // 이름, 나이, 파트가 궁금하대요 그거 보내달래요...
        String name,
        int price,
        Part part
) {
    public static ProductInfoResDto from(Product product) {
        return ProductInfoResDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .part(product.getPart())
                .build();
    }
}
