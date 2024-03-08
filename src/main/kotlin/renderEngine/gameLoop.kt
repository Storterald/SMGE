package renderEngine

import org.lwjgl.glfw.GLFW.glfwGetTime
import org.lwjgl.glfw.GLFW.glfwWindowShouldClose

fun gameLoop(window: Long, fpsCap: Int = 0, codeBlock: () -> Unit) {
    require(fpsCap >= 0)
    val FPS_CAP = if (fpsCap == 0) 2147483647 else fpsCap

    var lastTime: Double = glfwGetTime()
    while (!glfwWindowShouldClose(window)) {
        codeBlock()

        while (glfwGetTime() < lastTime + 1.0/FPS_CAP) {
            // Zzz
        }
        lastTime += 1.0/FPS_CAP
    }
}