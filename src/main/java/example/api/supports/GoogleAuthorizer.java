package example.api.supports;

import example.api.Client;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoogleAuthorizer {

    private static GoogleAuthorizer googleAuthorizer;

    static {
        googleAuthorizer = new GoogleAuthorizer();
    }

    public static GoogleAuthClientBuilder builder() {
        return googleAuthorizer.new GoogleAuthClientBuilder();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public class GoogleAuthClientBuilder {
        public GoogleAuthClientBuilder tokenKey(String tokenKey) {
            return this;
        }

        public GoogleAuthClientBuilder applicationName(String applicationName) {
            return this;
        }

        public GoogleAuthClientBuilder applicationVersion(String applicationVersion) {
            return this;
        }

        public Client build() {
            return null;
        }


    }
}
