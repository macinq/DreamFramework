import com.google.gson.annotations.SerializedName;

public class header {
    @SerializedName("cookie")
    public String cookie;
    @SerializedName("CSRFT")
    public String CSRFToken;

    /**
     * Устанавливает куки для сайта
     *
     * @param cookie
     */
    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    /**
     * Устанавливает CSFT токен
     *
     * @param CSRFToken
     */
    public void setCSRFToken(String CSRFToken) {
        this.CSRFToken = CSRFToken;
    }

    /**
     * Получает куки
     *
     * @return
     */
    public String getCookie() {
        return cookie;
    }

    /**
     * получает токен
     *
     * @return
     */
    public String getCSRFToken() {
        return CSRFToken;
    }
}
