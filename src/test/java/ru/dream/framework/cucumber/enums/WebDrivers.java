package ru.dream.framework.cucumber.enums;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public enum WebDrivers {
    CHROME("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe") {
        @Override
        public String getName() {
            return "google-chrome";
        }

        @Override
        protected void initAliases() {
            aliases.put(this.getName(), new HashSet<>());
            aliases.get(this.getName()).add(this.getName());
            aliases.get(this.getName()).add(this.toString());
            aliases.get(this.getName()).add("'google-chrome'");
        }
    },

    FIREFOX("webdriver.gecko.driver", "C:\\webdriver\\geckodriver.exe") {
        @Override
        public String getName() {
            return "mozilla-firefox";
        }

        @Override
        protected void initAliases() {
            aliases.put(this.getName(), new HashSet<>());
            aliases.get(this.getName()).add(this.getName());
            aliases.get(this.getName()).add(this.toString());
            aliases.get(this.getName()).add("'mozilla-firefox'");
        }
    };

    private static Map<String, HashSet<String>> aliases;

    static {
        aliases = new HashMap<>();
        for (WebDrivers webDrivers : WebDrivers.values()) {
            webDrivers.initAliases();
        }
    }

    public String type;
    public String path;

    WebDrivers(String type, String path) {
        this.type = type;
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    /**
     * По имени web-driver возвращает enum значение. Работет
     * со сценариями из cucumber
     *
     * @param alias имя web-driver или его псевдоним
     * @return WebDrivers, иначе при отсутствии значения возвращает null
     */
    public static WebDrivers getValue(String alias) {
        for (WebDrivers webDriver : WebDrivers.values()) {
            if (webDriver.hasAlias(alias)) {
                return webDriver;
            }
        }
        return null;
    }

    /**
     * Метод показывает если данные псевдоним у соответствующего
     * экземпляра enum. Если на вход подано имя enum, также возвращает
     * True
     * @param alias псевдоним
     * @return True если такое псевдоним есть или это имя enum. Иначе False
     */
    public Boolean hasAlias(String alias) {
        return aliases.get(this.getName()).contains(alias);
    }

    public abstract String getName();

    protected abstract void initAliases();
}
