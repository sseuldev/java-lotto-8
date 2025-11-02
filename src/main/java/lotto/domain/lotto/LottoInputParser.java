package lotto.domain.lotto;

import java.util.Arrays;
import java.util.List;

import static lotto.global.Constant.DELIMITER;
import static lotto.global.ErrorMessage.INVALID_LOTTO_NUMBER_TYPE;

public class LottoInputParser {

    public static List<Integer> parseNumbers(String input) {
        try {
            return Arrays.stream(input.split(DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_TYPE.getMessage());
        }
    }
}
