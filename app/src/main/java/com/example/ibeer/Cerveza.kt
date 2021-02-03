package com.example.ibeer

data class Cerveza (
    val id: Double,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val description: String,
    val image_url: String,
    val abv: Double,
    val ibu: Double,
    val targetFg: Double,
    val targetOg: Double,
    val ebc: Double,
    val srm: Double,
    val ph: Double,
    val attenuationLevel: Double,
    val volume: BoilVolume,
    val boilVolume: BoilVolume,
    val method: Method,
    val ingredients: Ingredients,
    val foodPairing: List<String>,
    val brewersTips: String,
    val contributedBy: String
) {
    constructor() : this(
        0.0,
        "ERROR, CERVEZA NO ENCONTRADA",
        "",
        "",
        "",
        "https://images.punkapi.com/v2/keg.png",
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        0.0,
        BoilVolume(0.0, ""),
        BoilVolume(0.0, ""),
        Method(emptyList(), Fermentation(BoilVolume(0.0, ""))),
        Ingredients(emptyList(), emptyList(), ""),
        listOf(""),
        "",
        ""
    )
}

data class BoilVolume (
    val value: Double,
    val unit: String
)

data class Ingredients (
    val malt: List<Malt>,
    val hops: List<Hop>,
    val yeast: String
)

data class Hop (
    val name: String,
    val amount: BoilVolume,
    val add: String,
    val attribute: String
)

data class Malt (
    val name: String,
    val amount: BoilVolume
)

data class Method (
    val mashTemp: List<MashTemp>,
    val fermentation: Fermentation,
    val twist: Any? = null
)

data class Fermentation (
    val temp: BoilVolume
)

data class MashTemp (
    val temp: BoilVolume,
    val duration: Double
)

