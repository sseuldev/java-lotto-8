package lotto.domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoAmountTest {

    @DisplayName("예외: 로또 구입 금액이 숫자가 아닌 경우")
    @Test
    void validatePriceNumberTypeTest() {
        String input = "12a";

        assertThatThrownBy(() -> new LottoAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 숫자여야 합니다.");
    }

    @DisplayName("예외: 로또 구입 금액이 1,000 단위로 나누어 떨어지지 않는 경우")
    @Test
    void validatePriceDividedTest() {
        String input = "12345";

        assertThatThrownBy(() -> new LottoAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 1,000 단위로 나누어지는 수여야 합니다.");
    }

    @DisplayName("구입 금액에 따른 로또의 개수를 올바르게 반환한다.")
    @Test
    void calculateLottoCountTest() {
        LottoAmount lottoAmount = new LottoAmount("5000");
        int amount = lottoAmount.calculateLottoAmount();

        assertThat(amount).isEqualTo(5);
    }
}
