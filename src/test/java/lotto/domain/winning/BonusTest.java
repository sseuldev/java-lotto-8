package lotto.domain.winning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusTest {

    @DisplayName("예외: 보너스 번호가 숫자가 아닌 경우")
    @Test
    void validateBonusNumberTypeTest() {
        String input = "12a";

        assertThatThrownBy(() -> new Bonus(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 숫자여야 합니다.");
    }

    @DisplayName("예외: 보너스 번호가 1과 45 사이의 숫자가 아닌 경우")
    @Test
    void validateRangeOfBonusNumberTest() {
        String input = "46";

        assertThatThrownBy(() -> new Bonus(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
    }
}
