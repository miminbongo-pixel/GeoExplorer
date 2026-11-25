package id.geoexplorer.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import id.geoexplorer.app.core.coordinates.IndonesiaUtmZones

class MainActivity : Activity() {
    private val navy = Color.rgb(13, 55, 105)
    private val blue = Color.rgb(20, 105, 190)
    private val pale = Color.rgb(239, 245, 252)
    private lateinit var content: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()

    private fun text(value: String, size: Float, color: Int = Color.BLACK, bold: Boolean = false): TextView =
        TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
            if (bold) setTypeface(typeface, Typeface.BOLD)
            setPadding(dp(4), dp(6), dp(4), dp(6))
        }

    private fun baseScreen(title: String) {
        val outer = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(pale)
        }
        outer.addView(TextView(this).apply {
            text = "  GeoExplorer  |  $title"
            textSize = 20f
            setTextColor(Color.WHITE)
            setTypeface(typeface, Typeface.BOLD)
            setPadding(dp(16), dp(18), dp(12), dp(18))
            setBackgroundColor(navy)
        })
        val scroll = ScrollView(this)
        content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(16), dp(12), dp(16), dp(24))
        }
        scroll.addView(content)
        outer.addView(scroll)
        setContentView(outer)
    }

    private fun addButton(label: String, action: () -> Unit) {
        val b = Button(this).apply {
            text = label
            isAllCaps = false
            setTextColor(navy)
            setOnClickListener { action() }
        }
        content.addView(b, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply { bottomMargin = dp(7) })
    }

    private fun showHome() {
        baseScreen("GIS Lapangan Indonesia")
        content.addView(text("Pemetaan • Survei • Analisis", 16f, blue, true))
        content.addView(text(
            "Fondasi aplikasi GeoExplorer. Pilih modul untuk melihat rancangan awal dan status pengembangannya.",
            14f
        ))
        addButton("Peta Utama", ::showMapPreview)
        addButton("Sistem Koordinat WGS 84 / UTM", ::showCoordinates)
        addButton("GPS & Survey", { showInfo("GPS & Survey", "Modul GNSS, waypoint, tracking, akurasi, elevasi, dan catatan lapangan akan diintegrasikan pada tahap berikutnya.") })
        addButton("Layer Manager", { showInfo("Layer Manager", "Rencana: mengatur layer raster dan vektor, urutan, visibilitas, transparansi, CRS, dan simbol.") })
        addButton("Data Center", { showInfo("Data Center", "Rencana format: SHP, KML/KMZ, GPX, GeoJSON, GeoPackage, ECW, GeoTIFF, PDF georeferensi, CSV/TXT.") })
        addButton("Digitasi & Editor", { showInfo("Digitasi & Editor", "Rencana: titik, garis, poligon, vertex, snap, undo/redo, atribut, dan warna tiap objek.") })
        addButton("Analisis Spasial", { showInfo("Analisis Spasial", "Rencana: jarak, luas, keliling, bearing, buffer, seleksi spasial, overlay, dan validasi geometri.") })
        addButton("Map Layout & Ekspor", { showInfo("Map Layout", "Rencana: layout A4/A3, judul, legenda, skala, arah utara, grid koordinat, dan ekspor PDF.") })
        content.addView(text("Versi fondasi 0.2.0 • Belum merupakan GIS lengkap", 12f, Color.DKGRAY))
    }

    private fun showMapPreview() {
        baseScreen("Peta Utama - Pratinjau")
        content.addView(text("AREA PETA", 18f, navy, true))
        content.addView(text(
            "Peta belum terhubung ke penyedia basemap. Pada tahap berikutnya, area ini akan diisi MapLibre dengan sumber peta yang penggunaan dan lisensinya sesuai.",
            15f
        ))
        content.addView(text("Panel informasi lokasi", 16f, blue, true))
        content.addView(text("Latitude: —\nLongitude: —\nEasting: —\nNorthing: —\nZona UTM: —\nAkurasi GPS: menunggu izin lokasi", 14f))
        addButton("Kembali ke Beranda", ::showHome)
    }

    private fun showCoordinates() {
        baseScreen("Sistem Koordinat")
        content.addView(text("Zona UTM Indonesia", 18f, navy, true))
        content.addView(text(
            "Starter mendukung pemilihan zona UTM standar 46–54 berdasarkan longitude, serta kode EPSG utara/selatan berdasarkan latitude.",
            14f
        ))
        val examples = listOf(
            -5.1 to 119.0,
            -4.0 to 135.0,
            1.0 to 103.0,
            -8.0 to 140.0
        )
        examples.forEach { (lat, lon) ->
            val epsg = IndonesiaUtmZones.epsgFor(lat, lon)
            val zone = IndonesiaUtmZones.zoneFor(lon)
            content.addView(text(
                "Lat %.4f°, Lon %.4f° → %s".format(lat, lon,
                    if (epsg == null) "Di luar cakupan starter" else "Zona ${zone?.number}${if (lat >= 0) "N" else "S"}, EPSG:$epsg"),
                14f
            ))
        }
        content.addView(text("Catatan", 16f, blue, true))
        content.addView(text(
            "CRS asli dari setiap data impor tetap harus dipertahankan. Data SRGI 2013, TM-3, data lintas zona, dan epoch memerlukan transformasi CRS yang benar; fungsi PROJ belum diintegrasikan pada versi ini.",
            14f
        ))
        addButton("Kembali ke Beranda", ::showHome)
    }

    private fun showInfo(title: String, message: String) {
        baseScreen(title)
        content.addView(text(title, 18f, navy, true))
        content.addView(text(message, 15f))
        addButton("Kembali ke Beranda", ::showHome)
    }
}
