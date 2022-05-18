package com.example.projectmvvm.model

class ProviderQuot {

    companion object{

        fun getRandomQout(): Quote = qoutList[(0 until qoutList.size).random()]

        fun getRandomQout(response: (Quote) -> Unit){
            response(qoutList[(0 until qoutList.size).random()])
        }

        private val qoutList: MutableList<Quote> = mutableListOf(
            Quote(
                quot = "It’s not a bug. It’s an undocumented feature!",
                autor = "Anonymous"
            ),
            Quote(
                quot = "“Software Developer” – An organism that turns caffeine into software",
                autor = "Anonymous"
            ),
            Quote(
                quot = "If debugging is the process of removing software bugs, then programming must be the process of putting them in",
                autor = "Edsger Dijkstra"
            ),
            Quote(
                quot = "A user interface is like a joke. If you have to explain it, it’s not that good.",
                autor = "Anonymous"
            ),
            Quote(
                quot = "I don’t care if it works on your machine! We are not shipping your machine!",
                autor = "Vidiu Platon"
            ),
            Quote(
                quot = "Measuring programming progress by lines of code is like measuring aircraft building progress by weight.",
                autor = "Bill Gates"
            ),
            Quote(
                quot = "My code DOESN’T work, I have no idea why. My code WORKS, I have no idea why.",
                autor = "Anonymous"
            ),
            Quote(quot = "Things aren’t always #000000 and #FFFFFF", autor = "Anonymous"),
            Quote(quot = "Talk is cheap. Show me the code.", autor = "Linus Torvalds"),
            Quote(
                quot = "Software and cathedrals are much the same — first we build them, then we pray.",
                autor = "Anonymous"
            ),
            Quote(quot = "¿A que esperas?, suscríbete.", autor = "AristiDevs")

        )
    }
}