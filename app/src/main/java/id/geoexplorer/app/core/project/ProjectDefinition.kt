package id.geoexplorer.app.core.project

data class ProjectDefinition(
    val id: String,
    val name: String,
    val crsAuthority: String = "EPSG",
    val crsCode: Int = 4326,
    val countryScope: String = "Indonesia"
)
