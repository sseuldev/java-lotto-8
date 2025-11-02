package lotto.domain.generator;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

import static lotto.global.Constant.*;

public class RandomNumbersGenerator implements NumbersGenerator {

    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER, LOTTO_NUMBERS_LENGTH);
    }
}
