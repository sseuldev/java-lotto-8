package lotto.domain.lotto;

import lotto.domain.generator.NumbersGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LottoMakerTest {

    @DisplayName("구입 금액에 맞는 개수의 랜덤 로또를 발행한다.")
    @Test
    void generateLottosTest() {
        LottoAmount lottoAmount = new LottoAmount("3000");
        NumbersGenerator fixedNumbersGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        LottoMaker lottoMaker = new LottoMaker(lottoAmount, fixedNumbersGenerator);

        TotalLotto totalLotto = lottoMaker.makeLottos();

        assertThat(totalLotto.getLottos()).hasSize(3);
        assertThat(totalLotto.getLottos())
                .allSatisfy(lotto ->
                        assertThat(lotto.getNumbers()).isEqualTo(List.of(1, 2, 3, 4, 5, 6))
                );
    }
}
