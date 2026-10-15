package net.rophim.app;

import android.net.Uri;

import java.util.Locale;

/**
 * Cấu hình trung tâm của ứng dụng. Chỉ cần sửa file này khi muốn đổi tên miền hoặc danh sách chặn quảng cáo.
 */
final class Config {

    private Config() {}

    /** Trang mở đầu tiên khi vào app. */
    static final String START_URL = "https://rophim.net/";

    /**
     * Các tên miền được phép mở TRONG app (bao gồm cả tên miền con).
     * Nếu website đổi sang tên miền mới, hãy thêm tên miền đó vào đây.
     */
    static final String[] ALLOWED_HOSTS = {
            "rophim.net"
    };

    /** Bật/tắt chặn quảng cáo và popup. */
    static final boolean BLOCK_ADS = true;

    /** Các tên miền quảng cáo bị chặn tải tài nguyên (bao gồm tên miền con). */
    static final String[] BLOCKED_HOSTS = {
            "i2ut3e.rophim.net",
            "d351izjhjvccs2.cloudfront.net",
            "doubleclick.net",
            "googlesyndication.com",
            "googleadservices.com",
            "adservice.google.com",
            "popads.net",
            "popcash.net",
            "propellerads.com",
            "adsterra.com",
            "exoclick.com",
            "juicyads.com",
            "trafficjunky.com",
            "clickadu.com",
            "hilltopads.com",
            "adnxs.com",
            "taboola.com",
            "outbrain.com"
    };

    /** Các đoạn đường dẫn đặc trưng của hệ thống quảng cáo. */
    static final String[] BLOCKED_PATH_PARTS = {
            "/c-cl.php",
            "/c-lg.php",
            "/www/delivery/"
    };

    /** CSS ẩn các banner quảng cáo còn sót lại trong trang. */
    static final String HIDE_ADS_CSS =
            "a[href*=\"c-cl.php\"],img[src*=\"c-lg.php\"],iframe[src*=\"i2ut3e\"]{display:none!important}";

    private static boolean hostMatches(String host, String[] list) {
        if (host == null) return false;
        String h = host.toLowerCase(Locale.ROOT);
        for (String item : list) {
            if (h.equals(item) || h.endsWith("." + item)) return true;
        }
        return false;
    }

    /** Cho phép điều hướng trang chính (main frame) tới địa chỉ này hay không. */
    static boolean isAllowedNavigation(Uri uri) {
        if (uri == null) return false;
        String scheme = uri.getScheme();
        if (scheme == null) return false;
        if ("about".equals(scheme)) return true;
        if (!"http".equals(scheme) && !"https".equals(scheme)) return false;
        return hostMatches(uri.getHost(), ALLOWED_HOSTS);
    }

    /** Yêu cầu tài nguyên này có phải quảng cáo cần chặn không. */
    static boolean isAdRequest(Uri uri) {
        if (!BLOCK_ADS || uri == null) return false;
        if (hostMatches(uri.getHost(), BLOCKED_HOSTS)) return true;
        String path = uri.getPath();
        if (path != null) {
            String p = path.toLowerCase(Locale.ROOT);
            for (String part : BLOCKED_PATH_PARTS) {
                if (p.contains(part)) return true;
            }
        }
        return false;
    }
}
