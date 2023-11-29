package br.com.makersweb.mscartoes.domain;

import java.util.Objects;
import java.util.UUID;

/**
 * @author aaristides
 */
public class CartaoID {

    private final String value;

    private CartaoID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static CartaoID unique() {
        return CartaoID.from(UUID.randomUUID());
    }

    public static CartaoID from(final String anId) {
        return new CartaoID(anId);
    }

    public static CartaoID from(final UUID anId) {
        return new CartaoID(anId.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CartaoID that = (CartaoID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

}
