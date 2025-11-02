package lotto.global;

public enum ErrorMessage {
    NOT_DIVIDED_INTO_1000("구입 금액은 1,000 단위로 나누어지는 수여야 합니다."),
    INVALID_PRICE_NUMBER_TYPE("구입 금액은 숫자여야 합니다."),

    INVALID_LOTTO_NUMBER_TYPE("당첨 로또 번호는 숫자여야 합니다."),
    INVALID_LOTTO_NUMBER_LENGTH("당첨 로또 번호의 개수는 총 6개여야 합니다."),
    DUPLICATED_LOTTO_NUMBER("당첨 로또 번호에는 중복된 번호가 들어갈 수 없습니다."),
    INVALID_LOTTO_NUMBER_RANGE("당첨 로또 번호는 1부터 45 사이의 숫자여야 합니다."),

    INVALID_BONUS_NUMBER_TYPE("보너스 번호는 숫자여야 합니다."),
    DUPLICATED_BONUS_LOTTO_NUMBER("보너스 번호는 당첨 로또 번호와 중복될 수 없습니다."),
    INVALID_BONUS_NUMBER_RANGE("보너스 번호는 1부터 45 사이의 숫자여야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
