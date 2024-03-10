package renderEngine

import org.lwjgl.glfw.GLFW.glfwGetTime
import org.lwjgl.glfw.GLFW.glfwWindowShouldClose

import WINDOW

fun gameLoop(fpsCap: Int = 0, codeBlock: () -> Unit) {
    check(WINDOW != 0L) { "Create the display before using gameLoop()." }
    require(fpsCap >= 0)
    val FPS_CAP = if (fpsCap == 0) Int.MAX_VALUE else fpsCap

    var lastTime: Double = glfwGetTime()
    while (!glfwWindowShouldClose(WINDOW)) {
        codeBlock()

        while (glfwGetTime() < lastTime + 1.0/FPS_CAP) {
            // Zzz
        }
        lastTime += 1.0/FPS_CAP
    }
}