package movie.service;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryConfirmationStore {
    private Map<String, ConfirmationCode> confirmationStore = new ConcurrentHashMap<>();

    public void saveCode(String email, String code) {
        ConfirmationCode confirmationCode = new ConfirmationCode(code, new Date(System.currentTimeMillis() + 3600000)); // 1 hour expiry
        confirmationStore.put(email, confirmationCode);
    }

    public boolean verifyCode(String email, String code) {
        ConfirmationCode confirmationCode = confirmationStore.get(email);
        if (confirmationCode != null && confirmationCode.getCode().equals(code) && confirmationCode.getExpiryDate().after(new Date())) {
            confirmationStore.remove(email);
            return true;
        }
        return false;
    }

    private static class ConfirmationCode {
        private String code;
        private Date expiryDate;

        public ConfirmationCode(String code, Date expiryDate) {
            this.code = code;
            this.expiryDate = expiryDate;
        }

        public String getCode() {
            return code;
        }

        public Date getExpiryDate() {
            return expiryDate;
        }
    }
}

