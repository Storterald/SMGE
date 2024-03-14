import renderEngine.*

fun main() {
    createDisplay("Title")

    loadScene(FPSCounterScene())

    gameLoop {
        update()
    }

    deleteResources()
    closeDisplay()
}