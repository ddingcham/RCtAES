package example.api.supports;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Parameters {
    public static ParametersBuilder with(String key, String value) {
        return new ParametersBuilder().with(key, value);
    }

    public static class ParametersBuilder {
        public ParametersBuilder with(String key, String value) {
            return this;
        }

        public Parameters build() {
            return new Parameters();
        }
    }
}
