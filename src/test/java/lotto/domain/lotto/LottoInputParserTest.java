package lotto.domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoInputParserTest {

    @DisplayName("예외: 로또 번호 중 숫자가 아닌 게 존재하는 경우")
    @Test
    void validateLottoNumberTypeTest() {
        String input = "1, 2, 3, 4, @, 5";

        assertThatThrownBy(() -> LottoInputParser.parseNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 로또 번호는 숫자여야 합니다.");
    }

    @DisplayName("로또 번호가 올바르게 파싱된다.")
    @Test
    void successNumberParsingTest() {
        String input = "1, 2, 3, 4, 5, 6";

        assertThat(LottoInputParser.parseNumbers(input)).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }
}
