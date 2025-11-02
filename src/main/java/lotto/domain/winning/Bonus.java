package lotto.domain.winning;

import static lotto.global.Constant.*;
import static lotto.global.ErrorMessage.*;

public class Bonus {

    private final int bonusNumber;

    public Bonus(String bonus) {
        int bonusNumber = changeToInt(bonus);
        validateNumberRange(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private int changeToInt(String bonus) {
        try {
            return Integer.parseInt(bonus);
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER_TYPE.getMessage());
        }
    }

    private void validateNumberRange(int bonusNumber) {
        if (bonusNumber < MINIMUM_LOTTO_NUMBER || bonusNumber > MAXIMUM_LOTTO_NUMBER) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER_RANGE.getMessage());
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
