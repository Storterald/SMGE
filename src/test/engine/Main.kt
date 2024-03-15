import renderEngine.*

fun main() {
    createDisplay("Title")

    loadScene(FPSCounterScene())

    gameLoop(60) {
        update()
    }

    deleteResources()
    closeDisplay()
}