# GeoExplorer Android Studio Starter 0.2

Paket ini disusun agar dapat dimasukkan ke Android Studio. Ini adalah **starter aplikasi yang bisa dikembangkan**, bukan GeoExplorer 1.0 yang sudah lengkap.

## Cara paling aman membuka di Android Studio

Karena paket ini tidak menyertakan `gradle-wrapper.jar`, gunakan cara berikut agar Android Studio membuat wrapper yang cocok dengan instalasi Anda:

1. Buka Android Studio.
2. Pilih **New Project** → **Empty Views Activity**.
3. Isi:
   - Name: `GeoExplorer`
   - Package name: `id.geoexplorer.app`
   - Language: `Kotlin`
   - Minimum SDK: `API 26` (Android 8.0)
4. Klik **Finish** dan tunggu Gradle Sync sampai selesai.
5. Tutup proyek.
6. Cadangkan folder `app/src/main` yang dibuat Android Studio.
7. Salin folder `app/src/main` dari paket ini ke proyek baru, lalu izinkan penggantian file.
8. Ganti isi `app/build.gradle.kts` dengan file dari paket ini.
9. Salin `core/` ke `app/src/main/java/id/geoexplorer/app/core/` sesuai struktur folder.
10. Buka kembali proyek dan tekan **Sync Project with Gradle Files**.
11. Hubungkan ponsel Android dengan USB debugging atau jalankan emulator.
12. Tekan tombol **Run ▶**.

Jika proyek baru Anda menggunakan versi Android Gradle Plugin/Kotlin yang berbeda, pertahankan versi yang dibuat Android Studio dan hanya salin bagian `android { ... }` serta source code. Jangan menurunkan versi plugin secara sembarangan.

## Isi paket

- Kerangka aplikasi Android Kotlin.
- Antarmuka awal GeoExplorer dengan menu modul.
- Halaman informasi koordinat dan zona UTM Indonesia.
- Fungsi pemilihan zona UTM 46–54 dan EPSG belahan utara/selatan.
- Struktur awal modul GIS.
- Catatan tahapan integrasi selanjutnya.

## Status fitur

Sudah ada pada starter:
- aplikasi Android dasar dan menu utama;
- pemilihan zona UTM berdasarkan longitude 90°–144°;
- kode EPSG WGS 84 / UTM utara atau selatan berdasarkan latitude.

Belum diimplementasikan:
- peta MapLibre;
- GPS GNSS;
- impor/ekspor SHP, KML/KMZ, GPX, GeoPackage;
- pembacaan ECW dan GeoTIFF;
- mesin transformasi PROJ;
- digitasi, analisis spasial, dan layout PDF;
- autosave/backup database.

## Catatan koordinat

Daftar zona 46–54 hanya cakupan zona UTM standar yang melintasi Indonesia. Zona dipilih berdasarkan posisi, tetapi proyek harus tetap dapat memakai CRS asli dari data impor. Data lintas zona, SRGI 2013, TM-3, dan epoch memerlukan pemeriksaan/transformasi CRS yang benar; jangan mengubahnya secara otomatis tanpa konfirmasi.

## Pengujian awal

Setelah aplikasi berjalan:
1. Pastikan layar GeoExplorer terbuka.
2. Buka **Sistem Koordinat**.
3. Uji longitude 119.0 dan latitude -5.1: hasil seharusnya zona 50 belahan selatan, EPSG:32750.
4. Uji longitude 135.0 dan latitude -4.0: hasil seharusnya zona 53 belahan selatan, EPSG:32753.
5. Uji longitude di luar 90°–144°: aplikasi memberi tahu bahwa posisi berada di luar cakupan zona UTM Indonesia yang disediakan starter.

## Lingkungan yang disarankan

- Android Studio versi stabil terbaru yang kompatibel dengan Android Gradle Plugin proyek.
- JDK 17.
- Android SDK Platform 35 atau versi yang dipilih Android Studio.
- Perangkat Android API 26 atau lebih baru.

Paket ini belum dikompilasi pada komputer pengguna. Keberhasilan build harus dipastikan melalui Gradle Sync dan Run di Android Studio.
