package lotto.domain.winning;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoAmount;
import lotto.domain.lotto.TotalLotto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.stream.Collectors;

import static lotto.global.Constant.REFERENCE_DIVISION_NUMBER;
import static lotto.global.ErrorMessage.DUPLICATED_BONUS_LOTTO_NUMBER;

public class Winning {

    private final Lotto winningLotto;
    private final Bonus bonus;

    public Winning(Lotto winningLotto, Bonus bonus) {
        validateDuplicatedBonus(winningLotto, bonus);
        this.winningLotto = winningLotto;
        this.bonus = bonus;
    }

    public Map<LottoRank, Integer> lottoWinningResult(TotalLotto totalLotto) {
        return totalLotto.getLottos().stream()
                .map(this::calculateRank)
                .collect(Collectors.toMap(
                        rank -> rank,
                        rank -> 1,
                        Integer::sum
                ));
    }

    public BigDecimal calculateProfitRate(Map<LottoRank, Integer> rankCounts, LottoAmount lottoAmount) {
        int totalPrize = calculateTotalPrize(rankCounts);
        int totalMoney = calculateTotalMoney(lottoAmount);

        return calculateRate(totalPrize, totalMoney);
    }

    private void validateDuplicatedBonus(Lotto winningLotto, Bonus bonus) {
        if (winningLotto.getNumbers().contains(bonus.getBonusNumber())) {
            throw new IllegalArgumentException(DUPLICATED_BONUS_LOTTO_NUMBER.getMessage());
        }
    }

    private LottoRank calculateRank(Lotto lotto) {
        int matchCount = (int) lotto.getNumbers().stream()
                .filter(winningLotto.getNumbers()::contains)
                .count();
        boolean hasBonusNumber = lotto.getNumbers().contains(bonus.getBonusNumber());

        return LottoRank.recordRank(matchCount, hasBonusNumber);
    }

    private int calculateTotalPrize(Map<LottoRank, Integer> rankCounts) {
        return rankCounts.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getReward() * entry.getValue())
                .sum();
    }

    private int calculateTotalMoney(LottoAmount lottoAmount) {
        return lottoAmount.calculateLottoAmount() * REFERENCE_DIVISION_NUMBER;
    }

    private BigDecimal calculateRate(int totalPrize, int totalMoney) {
        return BigDecimal.valueOf(totalPrize)
                .divide(BigDecimal.valueOf(totalMoney), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(2, RoundingMode.HALF_UP);
    }

}
