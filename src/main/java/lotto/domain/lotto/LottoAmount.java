package lotto.domain.lotto;

import static lotto.global.Constant.REFERENCE_DIVISION_NUMBER;
import static lotto.global.ErrorMessage.INVALID_PRICE_NUMBER_TYPE;
import static lotto.global.ErrorMessage.NOT_DIVIDED_INTO_1000;

public class LottoAmount {

    private final int money;

    public LottoAmount(String money) {
        int payMoney = changeToInt(money);
        validateDivided(payMoney);
        this.money = payMoney;
    }

    public int calculateLottoAmount() {
        return money / REFERENCE_DIVISION_NUMBER;
    }

    private int changeToInt(String money) {
        try {
            return Integer.parseInt(money);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_PRICE_NUMBER_TYPE.getMessage());
        }
    }

    private void validateDivided(int payMoney) {
        if (payMoney % REFERENCE_DIVISION_NUMBER != 0) {
            throw new IllegalArgumentException(NOT_DIVIDED_INTO_1000.getMessage());
        }
    }
}
