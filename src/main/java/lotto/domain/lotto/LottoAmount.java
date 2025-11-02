package lotto.domain.lotto;

import static lotto.global.Constant.REFERENCE_DIVISION_NUMBER;
import static lotto.global.ErrorMessage.INVALID_PRICE_NUMBER_TYPE;
import static lotto.global.ErrorMessage.NOT_DIVIDED_INTO_1000;

public class LottoAmount {

    private final int amount;

    public LottoAmount(String input) {
        int lottoAmount = changeToInt(input);
        validateDivided(lottoAmount);
        this.amount = lottoAmount;
    }

    public int calculateLottoAmount() {
        return amount / REFERENCE_DIVISION_NUMBER;
    }

    private int changeToInt(String input) {
        try {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_PRICE_NUMBER_TYPE.getMessage());
        }
    }

    private void validateDivided(int lottoAmount) {
        if (lottoAmount % REFERENCE_DIVISION_NUMBER != 0) {
            throw new IllegalArgumentException(NOT_DIVIDED_INTO_1000.getMessage());
        }
    }
}
