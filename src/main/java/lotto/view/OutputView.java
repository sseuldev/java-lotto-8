package lotto.view;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.TotalLotto;
import lotto.domain.winning.LottoRank;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private final String ln = System.lineSeparator();

    public void showTotalLotto(TotalLotto totalLotto) {
        String lottos = totalLotto.getLottos().stream()
                .map(Lotto::toString)
                .collect(Collectors.joining(ln));

        System.out.println(ln + totalLotto.getLottos().size() + "개를 구매했습니다.");
        System.out.println(lottos);
    }

    public void showWinningStatistics(Map<LottoRank, Integer> lottoResult) {
        System.out.println(ln + "당첨 통계" + ln + "---");

        Arrays.stream(LottoRank.values())
                .sorted(Comparator.reverseOrder())
                .filter(rank -> rank != LottoRank.NONE)
                .forEach(rank -> showRankStatistics(rank, lottoResult));
    }

    private void showRankStatistics(LottoRank rank, Map<LottoRank, Integer> lottoResult) {
        int count = lottoResult.getOrDefault(rank, 0);
        System.out.println(formatWinningStatistics(rank, count));
    }

    private String formatWinningStatistics(LottoRank rank, int count) {
        int matchCount = rank.getMatchCount();
        int reward = rank.getReward();

        if (rank == LottoRank.SECOND) {
            return String.format("%d개 일치, 보너스 볼 일치 (%s원) - %d개",
                    matchCount, String.format("%,d", reward), count);
        }
        return String.format("%d개 일치 (%s원) - %d개",
                matchCount, String.format("%,d", reward), count);
    }

    public void showProfitRate(BigDecimal profitRate) {
        System.out.println("총 수익률은 " + profitRate + "%입니다.");
    }
}
