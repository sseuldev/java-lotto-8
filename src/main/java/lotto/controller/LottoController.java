package lotto.controller;

import lotto.domain.generator.NumbersGenerator;
import lotto.domain.generator.RandomNumbersGenerator;
import lotto.domain.lotto.*;
import lotto.domain.winning.Bonus;
import lotto.domain.winning.LottoRank;
import lotto.domain.winning.Winning;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static lotto.domain.lotto.LottoInputParser.parseNumbers;

public class LottoController {

    private final InputView input;
    private final OutputView output;

    public LottoController(InputView input, OutputView output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        LottoAmount lottoAmount = purchase();
        TotalLotto totalLotto = makeLottos(lottoAmount);
        Winning winning = announceResults();
        calculateResults(lottoAmount, totalLotto, winning);
    }

    private LottoAmount purchase() {
        return retryInput(() -> {
            String amount = input.readPurchaseAmount();
            return new LottoAmount(amount);
        });
    }

    private TotalLotto makeLottos(LottoAmount lottoAmount) {
        NumbersGenerator randomNumbersGenerator = new RandomNumbersGenerator();

        LottoMaker lottoMaker = new LottoMaker(lottoAmount, randomNumbersGenerator);
        TotalLotto totalLotto = lottoMaker.makeLottos();

        output.showTotalLotto(totalLotto);
        return totalLotto;
    }

    private Winning announceResults() {
        Lotto winningLotto = retryInput(() -> {
            String winningNumbers = input.readWinningLottos();
            List<Integer> formatNumbers = parseNumbers(winningNumbers);
            return new Lotto(formatNumbers);
        });

        Bonus bonus = retryInput(() -> {
            String inputBonus = input.readBonusNumber();
            Bonus bonusNumber = new Bonus(inputBonus);
            new Winning(winningLotto, bonusNumber);
            return bonusNumber;
        });

        return new Winning(winningLotto, bonus);
    }

    private void calculateResults(LottoAmount lottoAmount, TotalLotto totalLotto, Winning winning) {
        Map<LottoRank, Integer> lottoResult = winning.lottoWinningResult(totalLotto);
        output.showWinningStatistics(lottoResult);

        BigDecimal profitRate = winning.calculateProfitRate(lottoResult, lottoAmount);
        output.showProfitRate(profitRate);
    }

    private <T> T retryInput(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
