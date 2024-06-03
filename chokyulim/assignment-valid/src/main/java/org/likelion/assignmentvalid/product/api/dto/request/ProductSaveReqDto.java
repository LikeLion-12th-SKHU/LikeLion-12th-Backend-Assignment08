// record 써서 간결화

package org.likelion.assignmentvalid.product.api.dto.request;

import jakarta.validation.constraints.*;
import org.likelion.assignmentvalid.product.domain.Part;

public record ProductSaveReqDto( // 포장 - 여러 변수 한 개로 받으려고
     // 이름, 나이, 파트

    @NotBlank(message = "상품 이름을 필수로 입력해야 합니다.")
    @Size(min = 1, max = 10, message = "1자 이상 10자 이하로 입력하세요.")
    String name,

    @NotNull(message = "가격을 필수로 입력해야 합니다.")
    @Max(value = 5000, message = "5000원까지만 입력할 수 있습니다.")
    int price,

    @NotNull(message = "파트를 필수로 입력해야 합니다.")
    Part part
) {
}
