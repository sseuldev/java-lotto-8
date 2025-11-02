package lotto.domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @DisplayName("예외: 로또 번호의 개수가 7개 이상인 경우")
    @Test
    void validateLottoNumberLengthTest() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 로또 번호의 개수는 총 6개여야 합니다.");
    }

    @DisplayName("예외: 로또 번호 중 중복된 숫자가 있는 경우")
    @Test
    void validateDuplicatedLottoTest() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 로또 번호에는 중복된 번호가 들어갈 수 없습니다.");
    }

    @DisplayName("예외: 로또 번호가 1과 45 사이의 숫자가 아닌 경우")
    @Test
    void validateRangeOfLottoNumberTest() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 50)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    @DisplayName("로또 번호가 올바르게 정렬된다.")
    @Test
    void lottoSortTest() {
        Lotto lotto = new Lotto(List.of(6, 5, 4, 3, 2, 1));

        assertThat(lotto.getNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("로또 번호가 출력 형식에 맞게 반환된다.")
    @Test
    void lottoFormatTest() {
        Lotto lotto = new Lotto(List.of(6, 5, 4, 3, 2, 1));

        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }
}
