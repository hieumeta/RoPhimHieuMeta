# RoPhim – Ứng dụng Android (WebView)

## Cách 1: Build APK online, KHÔNG cần cài gì (khuyên dùng)
1. Tạo tài khoản https://github.com (miễn phí) và tạo repository mới, chọn **Private**.
2. Giải nén file zip này, tải TOÀN BỘ nội dung thư mục lên repository (kể cả thư mục ẩn `.github`).
3. Vào tab **Actions** → chọn **Build APK** → **Run workflow**.
4. Chờ khoảng 3–5 phút. Mở lần chạy vừa xong → mục **Artifacts** → tải **RoPhim-APK**.
5. Giải nén, chép file `app-release.apk` vào điện thoại, mở lên và cho phép "Cài đặt từ nguồn không xác định".

## Cách 2: Build bằng Android Studio
1. Cài Android Studio (bản mới nhất).
2. File → Open → chọn thư mục `RoPhimApp`, chờ Gradle sync xong.
3. Build → Generate Signed App Bundle / APK hoặc chạy `Build > Build APK(s)`.
   File nằm ở `app/build/outputs/apk/`.

## Tùy chỉnh
- Đổi tên miền/ danh sách chặn quảng cáo: `app/src/main/java/net/rophim/app/Config.java`
- Đổi tên app: `app/src/main/res/values/strings.xml`
- Đổi icon: thay các file trong `res/mipmap-*` và `res/drawable/ic_launcher_foreground.xml`
- Nếu website đổi tên miền: thêm tên miền mới vào `ALLOWED_HOSTS` và `AndroidManifest.xml`.

## Tính năng
- Toàn màn hình + tự xoay ngang khi xem video, giữ màn hình sáng
- Nút Back quay lại trang trước, nhấn 2 lần để thoát
- Kéo xuống để tải lại, thanh tiến trình, màn hình báo lỗi mạng + nút Thử lại
- Chặn popup, chuyển hướng quảng cáo/cá cược và banner phổ biến
- Lưu đăng nhập (cookie), mở link rophim.net từ ứng dụng khác

> Keystore `rophim-release.jks` (mật khẩu `rophim2026`) dùng để ký APK. Hãy giữ repository ở chế độ Private
> và giữ nguyên file này để các bản cập nhật sau cài đè được lên bản cũ.
