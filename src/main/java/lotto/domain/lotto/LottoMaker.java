package lotto.domain.lotto;

import lotto.domain.generator.NumbersGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoMaker {

    private final int amount;
    private final NumbersGenerator numbersGenerator;

    public LottoMaker(LottoAmount lottoAmount, NumbersGenerator numbersGenerator) {
        this.amount = lottoAmount.calculateLottoAmount();
        this.numbersGenerator = numbersGenerator;
    }

    public TotalLotto makeLottos() {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            List<Integer> numbers = numbersGenerator.generate();
            lottos.add(new Lotto(numbers));
        }
        return new TotalLotto(lottos);
    }
}
