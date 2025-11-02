package lotto.domain.winning;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoAmount;
import lotto.domain.lotto.TotalLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class WinningTest {

    @DisplayName("예외: 보너스 번호와 로또 번호 중 중복된 숫자가 있는 경우")
    @Test
    void validateDuplicatedBonusTest() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Bonus duplicatedBonus = new Bonus("1");

        assertThatThrownBy(() -> new Winning(winningLotto, duplicatedBonus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 당첨 로또 번호와 중복될 수 없습니다.");
    }

    @DisplayName("전체 로또의 최종 당첨 결과 개수를 올바르게 반환한다.")
    @ParameterizedTest
    @MethodSource("lottoResultTestData")
    void winningResultTest(List<Lotto> lottoList, Map<LottoRank, Integer> expectedResults) {
        TotalLotto totalLotto = new TotalLotto(lottoList);
        Bonus bonus = new Bonus("17");
        Winning winning = new Winning(new Lotto(List.of(1, 2, 3, 4, 5, 6)), bonus);

        Map<LottoRank, Integer> result = winning.lottoWinningResult(totalLotto);

        assertThat(result).isEqualTo(expectedResults);
    }

    static Stream<Arguments> lottoResultTestData() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                                new Lotto(List.of(1, 2, 3, 4, 5, 17)),
                                new Lotto(List.of(1, 2, 3, 4, 5, 7)),
                                new Lotto(List.of(1, 2, 3, 4, 8, 9)),
                                new Lotto(List.of(1, 2, 3, 10, 11, 12)),
                                new Lotto(List.of(8, 9, 10, 11, 12, 13))
                        ),
                        Map.of(
                                LottoRank.FIRST, 1,
                                LottoRank.SECOND, 1,
                                LottoRank.THIRD, 1,
                                LottoRank.FOURTH, 1,
                                LottoRank.FIFTH, 1,
                                LottoRank.NONE, 1
                        )
                )
        );
    }

    @DisplayName("로또의 수익률 계산이 올바르게 이루어진다.")
    @Test
    void calculateProfitRateTest() {
        TotalLotto totalLotto = new TotalLotto(List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(1, 2, 3, 4, 5, 17)),
                new Lotto(List.of(1, 2, 3, 4, 7, 8))
        ));

        Bonus bonus = new Bonus("17");
        Winning winning = new Winning(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), bonus
        );

        Map<LottoRank, Integer> rankCounts = winning.lottoWinningResult(totalLotto);
        LottoAmount lottoAmount = new LottoAmount("3000");

        BigDecimal profitRate = winning.calculateProfitRate(rankCounts, lottoAmount);
        BigDecimal expectedProfitRate = BigDecimal.valueOf(2000000000 + 30000000 + 50000)
                .divide(BigDecimal.valueOf(3000), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(1, RoundingMode.HALF_UP);

        assertThat(profitRate).isEqualTo(expectedProfitRate);
    }
}
