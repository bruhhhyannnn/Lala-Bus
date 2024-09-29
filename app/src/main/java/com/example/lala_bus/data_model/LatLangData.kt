package com.example.lala_bus.data_model

import com.google.android.gms.maps.model.LatLng

// MarkerDataStore.kt
object LatLangData {

    // Modern Jeep Permanent Stations
    val modernJeepStations = listOf(
        LatLangDataModel(LatLng(18.195729, 120.590441), "Laoag-Paoay Modern Jeep Station"),
        LatLangDataModel(LatLng(18.051355, 120.518746), "Paoay-Laoag Modern Jeep Station")
    )

    // Rotation Stop Points (Laoag-Batac-Paoay)
    val laoagPaoayStopPoints = listOf(
        LatLangDataModel(LatLng(18.186152, 120.588687), "Before Bridge of Laoag City"),
        LatLangDataModel(LatLng(18.179402, 120.590341), "Robinsons Ilocos, San Nicolas"),
        LatLangDataModel(LatLng(18.172783, 120.593965), "San Nicolas Municipality"),
        LatLangDataModel(LatLng(18.153305, 120.585531), "Cockfight Arena, San Nicolas"),
        LatLangDataModel(LatLng(18.140267, 120.584148), "San Lorenzo, San Nicolas"),
        LatLangDataModel(LatLng(18.136204, 120.583580), "Bingao Elementary & National High School, San Nicolas"),
        LatLangDataModel(LatLng(18.128952, 120.579599), "Brgy. Bingao, San Nicolas"),
        LatLangDataModel(LatLng(18.116064, 120.572574), "Baay Elementary School, Batac"),
        LatLangDataModel(LatLng(18.111539, 120.570348), "Batac National High School, Batac"),
        LatLangDataModel(LatLng(18.108161, 120.568836), "Market Place, Brgy. Baay, Batac"),
        LatLangDataModel(LatLng(18.088731, 120.568068), "GARSH and Bil-loca Elementary School, Batac"),
        LatLangDataModel(LatLng(18.079227, 120.562914), "Rana-Ann Gas Station, Baligat, Batac"),
        LatLangDataModel(LatLng(18.067465, 120.561356), "Arko Batac"),
        LatLangDataModel(LatLng(18.065589, 120.562940), "Market Place, Batac"),
        LatLangDataModel(LatLng(18.0567, 120.5647), "Pik A Bun (Centro), Batac"),
        LatLangDataModel(LatLng(18.055136, 120.561324), "Cebuana Pawnshop (Centro), Batac"),
        LatLangDataModel(LatLng(18.055791, 120.557112), "Eco-Oil Gas Station (Crossing), Batac"),
        LatLangDataModel(LatLng(18.056060, 120.555191), "MMSU Gate 3, Batac"),
        LatLangDataModel(LatLng(18.056383, 120.552828), "MMSU Teatro, Batac"),
        LatLangDataModel(LatLng(18.057171, 120.548076), "MMSU Twin Gate, Batac"),
        LatLangDataModel(LatLng(18.057863, 120.544007), "MMSU COE Shortcut, Batac"),
        LatLangDataModel(LatLng(18.061506, 120.523962), "Salbang, Paoay"),
        LatLangDataModel(LatLng(18.061527, 120.522527), "Paoay Church"),
        LatLangDataModel(LatLng(18.060370, 120.521639), "Paoay Municipality")
    )

    // Rotation Stop Points (Paoay-Batac-Laoag)
    val paoayLaoagStopPoints = listOf(
        LatLangDataModel(LatLng(18.060268, 120.521710), "7-Eleven (Centro), Paoay"),
        LatLangDataModel(LatLng(18.061411, 120.522567), "Paoay Church"),
        LatLangDataModel(LatLng(18.061446, 120.524006), "Salbang, Paoay"),
        LatLangDataModel(LatLng(18.057726, 120.544146), "Phil-Rice Research Institute, Batac"),
        LatLangDataModel(LatLng(18.057144, 120.547645), "MMSU Twin Gate, Batac"),
        LatLangDataModel(LatLng(18.056291, 120.552822), "MMSU Teatro, Batac"),
        LatLangDataModel(LatLng(18.055938, 120.555326), "MMSU Gate 3, Batac"),
        LatLangDataModel(LatLng(18.0556, 120.5575), "Crossing (Tealive), Batac"),
        LatLangDataModel(LatLng(18.054762, 120.562627), "Jollibee, Batac"),
        LatLangDataModel(LatLng(18.054760, 120.564350), "In-Front of City of Batac Municipality"),
        LatLangDataModel(LatLng(18.056616, 120.565189), "Ricarte Park, Batac"),
        LatLangDataModel(LatLng(18.066417, 120.562268), "Market Place, Batac"),
        LatLangDataModel(LatLng(18.078790, 120.563053), "Rana-Ann Gas Station, Baligat, Batac"),
        LatLangDataModel(LatLng(18.068090, 120.561330), "Arko Batac"),
        LatLangDataModel(LatLng(18.088505, 120.568151), "GARSH and Bil-loca Elementary School, Batac"),
        LatLangDataModel(LatLng(18.108253, 120.568959), "Market Place, Brgy. Baay, Batac"),
        LatLangDataModel(LatLng(18.111380, 120.570396), "Batac National High School, Batac"),
        LatLangDataModel(LatLng(18.115854, 120.572622), "Baay Elementary School, Batac"),
        LatLangDataModel(LatLng(18.128701, 120.579635), "Brgy. Bingao, San Nicolas"),
        LatLangDataModel(LatLng(18.136172, 120.583707), "Bingao Elementary & National High School, San Nicolas"),
        LatLangDataModel(LatLng(18.140290, 120.584265), "San Lorenzo, San Nicolas"),
        LatLangDataModel(LatLng(18.152961, 120.585532), "Cockfight Arena, San Nicolas"),
        LatLangDataModel(LatLng(18.172889, 120.594202), "San Nicolas Municipality"),
        LatLangDataModel(LatLng(18.179488, 120.590411), "Robinsons Ilocos, San Nicolas"),
        LatLangDataModel(LatLng(18.185368, 120.588945), "Gabu Terminal, San Nicolas"),
        LatLangDataModel(LatLng(18.196158, 120.592418), "Laoag City Municipality")
    )
}