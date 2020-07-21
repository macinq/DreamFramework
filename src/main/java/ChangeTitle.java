import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Меняет заголовок сайта
 */

public class ChangeTitle {


    /**
     * Описывает тело заголовка(*если нужно*)
     */


    /**
     * по умолч replace
     */
    public String op;
    /**
     * указывает что менять
     */
    public String path;
    /**
     * новый заголовок
     */
    public String value;

    /**
     * устанавливает что нужно сделать (replace)
     *
     * @param op
     */
    public void setOp(String op) {
        this.op = op;
    }

    /**
     * указывает где конкретно(title)
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * указывает на какой текст поменять
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    public String getOp() {
        return op;
    }

    public String getPath() {
        return path;
    }

    public String getValue() {
        return value;
    }
}