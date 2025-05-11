package com.example.riot_elo.data



fun getLaneInmageLink(lane: String): String {
    return when (lane.trim().uppercase()) {
        "TOP" -> "https://ih1.redbubble.net/image.2355783081.2767/st,small,507x507-pad,600x600,f8f8f8.jpg"
        "MID" -> "https://ih1.redbubble.net/image.2354235969.4306/raf,360x360,075,t,fafafa:ca443f4786.jpg"
        "JUNGLE" -> "https://ih1.redbubble.net/image.3271051226.7343/st,small,845x845-pad,1000x1000,f8f8f8.u2.jpg"
        "ADC" -> "https://ih1.redbubble.net/image.2354182162.2636/raf,360x360,075,t,fafafa:ca443f4786.jpg"
        "SUPPORT" -> "https://ih1.redbubble.net/image.2354270772.5334/st,small,507x507-pad,600x600,f8f8f8.jpg"
        else -> "https://ih1.redbubble.net/image.2354270772.5334/st,small,507x507-pad,600x600,f8f8f8.jpg"
    }
}
