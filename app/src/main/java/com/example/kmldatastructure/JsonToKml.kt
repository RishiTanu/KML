package com.example.kmldatastructure

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject

fun convertJsonToKml(jsonString: String): String {
    val json = JsonParser.parseString(jsonString).asJsonObject
    val locations = json.getAsJsonArray("locations") // Assuming your JSON has a "locations" array

    val kmlStart = """
        <?xml version="1.0" encoding="UTF-8"?>
        <kml xmlns="http://www.opengis.net/kml/2.2">
        <Document>
    """.trimIndent()

    var placemarks = ""
    for (location in locations) {
        location as JsonObject
        val name = location.get("name").asString
        val latitude = location.get("latitude").asString
        val longitude = location.get("longitude").asString

        placemarks += """
            <Placemark>
                <name>$name</name>
                <Point>
                    <coordinates>$longitude,$latitude,0</coordinates>
                </Point>
            </Placemark>
        """.trimIndent()
    }

    val kmlEnd = """
        </Document>
        </kml>
    """.trimIndent()

    return kmlStart + placemarks + kmlEnd
}

fun jsonToKml(jsonString: String): String {
    val jsonObject = JSONObject(jsonString)
    val locations = jsonObject.getJSONArray("locations")

    val kmlHeader = """
        <?xml version="1.0" encoding="UTF-8"?>
        <kml xmlns="http://www.opengis.net/kml/2.2">
        <Document>
    """.trimIndent()

    var placemarks = ""
    for (i in 0 until locations.length()) {
        val location = locations.getJSONObject(i)
        placemarks += """
            <Placemark>
                <name>${location.getString("name")}</name>
                <description>Ground: ${location.getString("ground")}, Altitude: ${location.getString("altitude")}, Vertical Speed: ${location.getString("verticalspeed")}</description>
                <Point>
                    <coordinates>${location.getString("longitude")},${location.getString("latitude")},${location.getString("altitude")}</coordinates>
                </Point>
            </Placemark>
        """.trimIndent()
    }

    val kmlFooter = """
        </Document>
        </kml>
    """.trimIndent()

    return kmlHeader + placemarks + kmlFooter
}


