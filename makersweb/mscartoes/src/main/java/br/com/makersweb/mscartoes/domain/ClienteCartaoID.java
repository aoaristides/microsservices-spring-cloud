package br.com.makersweb.mscartoes.domain;

import java.util.Objects;
import java.util.UUID;

/**
 * @author aaristides
 */
public class ClienteCartaoID {

    private final String value;

    private ClienteCartaoID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static ClienteCartaoID unique() {
        return ClienteCartaoID.from(UUID.randomUUID());
    }

    public static ClienteCartaoID from(final String anId) {
        return new ClienteCartaoID(anId);
    }

    public static ClienteCartaoID from(final UUID anId) {
        return new ClienteCartaoID(anId.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ClienteCartaoID that = (ClienteCartaoID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

}
