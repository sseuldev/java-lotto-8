package lotto.domain.lotto;

import java.util.List;

public class TotalLotto {

    private final List<Lotto> lottos;

    public TotalLotto(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
