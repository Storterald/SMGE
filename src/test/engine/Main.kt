import renderEngine.*

fun main() {
    createDisplay("Title")

    loadScene(FPSCounterScene())

    gameLoop(0) {
        update()
    }

    deleteResources()
    closeDisplay()
}